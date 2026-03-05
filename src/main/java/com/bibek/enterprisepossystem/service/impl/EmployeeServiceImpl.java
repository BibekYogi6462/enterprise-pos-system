package com.bibek.enterprisepossystem.service.impl;

import com.bibek.enterprisepossystem.domain.UserRole;
import com.bibek.enterprisepossystem.mapper.UserMapper;
import com.bibek.enterprisepossystem.model.Branch;
import com.bibek.enterprisepossystem.model.Store;
import com.bibek.enterprisepossystem.model.User;
import com.bibek.enterprisepossystem.payload.dto.UserDto;
import com.bibek.enterprisepossystem.repository.BranchRepository;
import com.bibek.enterprisepossystem.repository.StoreRepository;
import com.bibek.enterprisepossystem.repository.UserRepository;
import com.bibek.enterprisepossystem.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final BranchRepository branchRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto createStoreEmployee(UserDto employee, Long storeId) throws Exception {

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new Exception("Store not found with id: " + storeId));

        Branch branch = null;

        if (employee.getRole() == UserRole.ROLE_BRANCH_MANAGER) {

            if (employee.getBranchId() == null) {
                throw new Exception("Branch ID is required for branch manager role");
            }

            branch = branchRepository.findById(employee.getBranchId())
                    .orElseThrow(() -> new Exception("Branch not found with id: " + employee.getBranchId()));
        }

        User user = UserMapper.toEntity(employee);

        user.setStore(store);
        user.setBranch(branch);
        user.setPassword(passwordEncoder.encode(employee.getPassword()));

        User savedEmployee = userRepository.save(user);
        if(employee.getRole() == UserRole.ROLE_BRANCH_MANAGER && branch!=null){
            branch.setManager(savedEmployee);
            branchRepository.save(branch);
        }
        return UserMapper.toDTO(savedEmployee);
    }



    @Override
    public UserDto createBranchEmployee(UserDto employee, Long branchId) throws Exception {

       Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new Exception("Branch not found with id: " + employee.getBranchId()));

       if(employee.getRole() == UserRole.ROLE_BRANCH_CASHIER || employee.getRole() == UserRole.ROLE_BRANCH_MANAGER){
           User user = UserMapper.toEntity(employee);
           user.setBranch(branch);
           user.setStore(branch.getStore());
           user.setPassword(passwordEncoder.encode(employee.getPassword()));
           User savedEmployee = userRepository.save(user);
           return UserMapper.toDTO(savedEmployee);


       }
        throw  new Exception("Branch not found with id: " + employee.getBranchId());
    }

    @Override
    public User updateEmployee(Long employeeId, UserDto employeeDetails) throws Exception {
        User existingEmployee = userRepository.findById(employeeId)
                .orElseThrow(() -> new Exception("Employee not found with id: " + employeeId));

        Branch branch = branchRepository.findById(employeeDetails.getBranchId())
                        .orElseThrow(() -> new Exception("Branch not found with id: " + employeeDetails.getBranchId()));

        existingEmployee.setEmail(employeeDetails.getEmail());
        existingEmployee.setFullName(employeeDetails.getFullName());
        existingEmployee.setPassword(employeeDetails.getPassword());
        existingEmployee.setRole(employeeDetails.getRole());
        existingEmployee.setBranch(branch);
        return userRepository.save(existingEmployee);
    }

    @Override
    public void deleteEmployee(Long employeeId) throws Exception {
       User employee = userRepository.findById(employeeId)
                .orElseThrow(() -> new Exception("Employee not found with id: " + employeeId));
       userRepository.delete(employee);
    }

    @Override
    public List<UserDto> findStoreEmployees(Long storeId, UserRole role) throws Exception {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new Exception("Store not found with id: " + storeId));
    return userRepository.findByStore(store).stream()
            .filter(user -> role==null || user.getRole() == role)
            .map(UserMapper::toDTO)
            .collect(Collectors.toList());
    }

    @Override
    public List<UserDto> findBranchEmployees(Long branchId, UserRole role) throws Exception {
        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new Exception("Branch not found with id: " + branchId));

        return userRepository.findByBranchId(branchId)
                .stream()
                .filter(user -> role == null || user.getRole() == role)
                .map(UserMapper::toDTO)  // This converts User to UserDto
                .collect(Collectors.toList());
    }
}
