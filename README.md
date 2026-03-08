# 🏪 Enterprise POS System - Backend API

<p align="center">
  <img src="https://img.shields.io/badge/Java%20Spring%20Boot-6DB33F?style=for-the-badge&logo=spring&logoColor=white"/>
  <img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white"/>
  <img src="https://img.shields.io/badge/JWT-000000?style=for-the-badge&logo=json-web-tokens&logoColor=white"/>
  <img src="https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white"/>
</p>

---
✅ Full Backend Implementation Complete - 14+ API modules fully developed and tested
Multi-Store Retail Management Backend – A scalable backend system built with Java 17 and Spring Boot that manages stores, branches, products, inventory, employees, customers, and orders. It includes JWT-based authentication, role-based access control, payment gateway integration (Razorpay/Stripe), refund handling, and real-time analytics dashboards. The system supports multi-store management with centralized reporting and sales analytics, built using Spring Security, JPA/Hibernate, and MySQL, with 14+ fully implemented REST API modules tested using Postman.

## 📸 API Testing - Quick Preview

Here are some sample API test screenshots. You can test all endpoints using Postman collection.

### 🔐 Authentication APIs
| | |
|:---:|:---:|
| <img src="./screenshots/auth-signup.jpg" alt="User Signup" width="400"/> | <img src="./screenshots/auth-login.jpg" alt="User Login" width="400"/> |
| *User Signup API* | *User Login API* |
| <img src="./screenshots/user-profile-get.jpg" alt="User Profile" width="400"/> | |
| *Get User Profile API* | |

---

### 🏢 Store Management APIs
| | |
|:---:|:---:|
| <img src="./screenshots/store-creation.jpg" alt="Store Creation" width="400"/> | <img src="./screenshots/store-admin-signup.jpg" alt="Store Admin Signup" width="400"/> |
| *Store Creation API* | *Store Admin Signup API* |
| <img src="./screenshots/store-update.jpg" alt="Store Update" width="400"/> | |
| *Store Update API* | |

---

### 📦 Category Management APIs
| | |
|:---:|:---:|
| <img src="./screenshots/category-create.jpg" alt="Category Create" width="400"/> | <img src="./screenshots/category-get.jpg" alt="Get Categories" width="400"/> |
| *Create Category API* | *Get Categories API* |

---

### 🏬 Branch Management APIs
| | |
|:---:|:---:|
| <img src="./screenshots/branch-create.jpg" alt="Branch Create" width="400"/> | <img src="./screenshots/branch-get.jpg" alt="Get Branches" width="400"/> |
| *Create Branch API* | *Get Branches API* |

> ✨ **Note:** These are just sample screenshots. All 14+ API modules are fully functional and tested including Product, Inventory, Order, Refund, Employee, Customer, Payment, and Dashboard APIs.

---

## 🚀 API Modules

<div style="display: grid; grid-template-columns: repeat(2, 1fr); gap: 10px;">

### 🔐 Authentication
- User Signup
- User Login
- Profile Management
- User CRUD Operations

### 🏢 Store
- Store Creation (Admin)
- Store Admin Signup
- Store Details Update
- Store Retrieval

### 📦 Category
- Category Creation
- Get Categories
- Category Update/Delete
- Category by Store

### 🏬 Branch
- Branch Creation
- Get Branches
- Branch Update/Delete
- Branches by Store

### 📊 Product
- Product Creation
- Product Retrieval
- Product Update/Delete
- Stock Management

### 📦 Inventory
- Add Inventory
- Update Inventory
- Track Stock Levels
- Low Stock Alerts

### 👥 Employee
- Employee Registration
- Roles Assignment
- Employee Details
- Shift Management

### 👤 Customer
- Customer Registration
- Customer Details
- Loyalty Points
- Purchase History

### 🛒 Order
- Create Order
- Process Payment
- Order History
- Invoice Generation

### 💰 Refund
- Process Refunds
- Refund History
- Payment Reversal

### 📈 Reports
- Daily Sales Report
- Shift Summary
- Employee Performance
- Branch Analytics

### 💳 Payment
- Razorpay Integration
- Stripe Integration
- Multiple Methods
- Gateway Switching

### 🏪 Multi-Store
- Cross-store Management
- Centralized Control
- Store Metrics

### 📱 Dashboard
- Cashier Terminal
- Branch Manager
- Store Admin Panel
- Real-time Analytics

</div>

---

## 🛠️ Technology Stack

| Category | Technologies |
|----------|-------------|
| **Language** | Java 17 |
| **Framework** | Spring Boot 3.x |
| **Security** | Spring Security, JWT |
| **Database** | MySQL, JPA/Hibernate |
| **Build Tool** | Maven |
| **Utilities** | Lombok |
| **Testing** | Postman |

---

## 🚦 Getting Started

### Prerequisites
✅ JDK 17 or later
✅ MySQL Server 8.0+
✅ Maven 3.8+

text

### Quick Setup

```bash
# Clone the repository
git clone https://github.com/yourusername/enterprise-pos-backend.git

# Navigate to project
cd enterprise-pos-backend

# Configure database in application.yml
# Build the application
mvn clean install

# Run the application
mvn spring-boot:run
📌 API Base URL: http://localhost:8080/api

🔒 Security Features
Feature	Description
Authentication	JWT-based with token expiration
Authorization	Role-based (ADMIN, STORE_ADMIN, BRANCH_MANAGER, CASHIER)
Encryption	BCrypt password hashing
Validation	Request validation & CORS config

🧪 Testing with Postman
📥 Download the Postman collection from repository

📤 Import into Postman

⚙️ Set up environment variables

✅ Start testing APIs in sequence

🤝 Contributing
Fork the repository

Create feature branch (git checkout -b feature/AmazingFeature)

Commit changes (git commit -m 'Add AmazingFeature')

Push to branch (git push origin feature/AmazingFeature)

Open a Pull Request

⭐ Support
If you find this project helpful, please give it a ⭐ on GitHub!



