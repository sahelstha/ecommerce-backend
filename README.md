# Spring Boot Ecommerce Backend

A comprehensive, production-ready REST API backend for ecommerce applications built with **Spring Boot 4.0**, **Spring Security**, and **JWT Authentication**. This project provides complete functionality for managing products, categories, shopping carts, orders, payments, and user accounts.

---

## 📋 Table of Contents

- [Features](#features)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Installation & Setup](#installation--setup)
- [API Endpoints](#api-endpoints)
- [Configuration](#configuration)
- [Database Schema](#database-schema)
- [Authentication](#authentication)
- [Contributing](#contributing)
- [License](#license)

---

## ✨ Features

### Core Ecommerce Features
- **Product Management** - Add, update, delete, and browse products with images and descriptions
- **Category Management** - Organize products into categories with CRUD operations
- **Shopping Cart** - Add/remove items, update quantities, view cart totals
- **Order Management** - Create orders, track order status, view order history
- **Payment Integration** - Payment processing with support for multiple payment methods
- **User Addresses** - Manage multiple shipping addresses per user

### Security & Authentication
- **JWT Authentication** - Secure token-based authentication with configurable expiration
- **Role-Based Access Control** - Support for CUSTOMER, SELLER, and ADMIN roles
- **Spring Security** - Enterprise-grade security with password encoding and authorization
- **Stateless API** - JWT-based stateless authentication for scalability

### Additional Features
- **Pagination & Sorting** - Efficient data retrieval with customizable page size and sorting
- **File Upload** - Product image upload and management capabilities
- **Global Exception Handling** - Centralized error handling with meaningful error responses
- **Validation** - Request payload validation with detailed error messages
- **Logging** - Debug-level logging for development and troubleshooting

---

## 🛠 Tech Stack

| Technology | Version | Purpose |
|-----------|---------|---------|
| **Java** | 17+ | Programming language |
| **Spring Boot** | 4.0.3 | Web framework |
| **Spring Security** | Latest | Authentication & Authorization |
| **Spring Data JPA** | Latest | Database ORM |
| **MySQL** | 5.7+ | Primary Database |
| **JWT (JJWT)** | 0.13.0 | Token-based authentication |
| **Lombok** | Latest | Reduce boilerplate code |
| **ModelMapper** | 3.2.4 | DTO mapping |
| **Maven** | 3.6+ | Build tool |

---

## 📁 Project Structure

```
src/main/java/com/ecommerce/project/
│
├── model/                          # JPA Entity classes
│   ├── User.java
│   ├── Product.java
│   ├── Category.java
│   ├── Cart.java
│   ├── CartItem.java
│   ├── Order.java
│   ├── OrderItem.java
│   ├── Payment.java
│   ├── Address.java
│   ├── Role.java
│   └── AppRole.java
│
├── controller/                     # REST API Controllers
│   ├── AuthController.java         # Authentication endpoints
│   ├── ProductController.java      # Product CRUD endpoints
│   ├── CategoryController.java     # Category CRUD endpoints
│   ├── CartController.java         # Shopping cart endpoints
│   ├── OrderController.java        # Order management endpoints
│   └── AddressController.java      # Address management endpoints
│
├── service/                        # Business Logic Layer
│   ├── ProductService.java
│   ├── CategoryService.java
│   ├── CartService.java
│   ├── OrderService.java
│   ├── AddressService.java
│   └── FileService.java
│
├── repositories/                   # Data Access Layer (Spring Data JPA)
│   ├── UserRepository.java
│   ├── ProductRepository.java
│   ├── CategoryRepository.java
│   ├── CartRepository.java
│   ├── OrderRepository.java
│   └── PaymentRepository.java
│
├── security/                       # Security Configuration
│   ├── WebSecurityConfig.java      # Spring Security configuration
│   ├── jwt/
│   │   ├── JwtUtils.java           # JWT token generation & validation
│   │   ├── AuthTokenFilter.java    # JWT authentication filter
│   │   └── AuthEntryPointJwt.java  # JWT error handler
│   ├── services/
│   │   ├── UserDetailsImpl.java
│   │   └── UserDetailsServiceImpl.java
│   ├── request/
│   │   ├── LoginRequest.java
│   │   └── SignupRequest.java
│   └── response/
│       ├── UserInfoResponse.java
│       └── MessageResponse.java
│
├── payload/                        # DTOs (Data Transfer Objects)
│   ├── ProductDTO.java
│   ├── CategoryDTO.java
│   ├── CartDTO.java
│   ├── OrderDTO.java
│   ├── PaymentDTO.java
│   ├── AddressDTO.java
│   └── *Response.java
│
├── exceptions/                     # Custom Exceptions
│   ├── APIException.java
│   ├── ResourceNotFoundException.java
│   └── MyGlobalExceptionHandler.java
│
├── config/                         # Configuration Classes
│   ├── AppConfig.java              # Bean configurations
│   └── AppConstants.java           # Application constants
│
├── util/                           # Utility Classes
│   └── AuthUtil.java               # Authentication utilities
│
└── SbEcomeBackendApplication.java # Main Spring Boot application class

resources/
└── application.properties          # Configuration file
```

---

## 🚀 Installation & Setup

### Prerequisites
- **Java 17+** - [Download](https://www.oracle.com/java/technologies/downloads/)
- **Maven 3.6+** - [Download](https://maven.apache.org/)
- **MySQL 5.7+** - [Download](https://www.mysql.com/downloads/)

### Step 1: Clone the Repository
```bash
git clone https://github.com/yourusername/sb-ecommerce-backend.git
cd sb-ecommerce-backend
```

### Step 2: Configure Database

Create a MySQL database:
```sql
CREATE DATABASE ecommerce;
```

Update `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce
spring.datasource.username=root
spring.datasource.password=your_password

spring.app.jwtSecret=your_secret_key_here
spring.app.jwtExpirationMs=300000
```

### Step 3: Build the Project
```bash
mvn clean install
```

### Step 4: Run the Application
```bash
mvn spring-boot:run
```

The server will start locally.

---

## 📡 API Endpoints

### Authentication Endpoints (`/api/auth`)

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/signin` | User login |
| POST | `/signup` | User registration |
| POST | `/signout` | User logout |

**Example Sign Up Request:**
```json
{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john@example.com",
  "password": "securePassword123",
  "roles": ["ROLE_CUSTOMER"]
}
```

**Example Sign In Request:**
```json
{
  "email": "john@example.com",
  "password": "securePassword123"
}
```

### Product Endpoints (`/api`)

| Method | Endpoint | Description | Auth |
|--------|----------|-------------|------|
| GET | `/public/products` | Get all products (paginated) | No |
| GET | `/public/categories/{categoryId}/products` | Get products by category | No |
| POST | `/admin/categories/{categoryId}/product` | Create new product | Admin |
| PUT | `/admin/products/{productId}` | Update product | Admin |
| DELETE | `/admin/products/{productId}` | Delete product | Admin |
| GET | `/public/products/{productId}` | Get product details | No |

**Get Products with Pagination:**
```
GET /api/public/products?pageNumber=0&pageSize=20&sortBy=productId&sortOrder=asc
```

### Category Endpoints (`/api`)

| Method | Endpoint | Description | Auth |
|--------|----------|-------------|------|
| GET | `/public/categories` | Get all categories | No |
| POST | `/admin/categories` | Create new category | Admin |
| PUT | `/admin/categories/{categoryId}` | Update category | Admin |
| DELETE | `/admin/categories/{categoryId}` | Delete category | Admin |

### Cart Endpoints (`/api`)

| Method | Endpoint | Description | Auth |
|--------|----------|-------------|------|
| POST | `/carts/products/{productId}/quantity/{quantity}` | Add item to cart | User |
| GET | `/carts/users/cart` | Get user's cart | User |
| PUT | `/cart/products/{productId}/quantity/{operation}` | Update cart item quantity | User |
| DELETE | `/cart/{cartItemId}` | Remove item from cart | User |
| DELETE | `/carts/{cartId}` | Clear entire cart | User |

### Order Endpoints (`/api`)

| Method | Endpoint | Description | Auth |
|--------|----------|-------------|------|
| POST | `/orders` | Create new order | User |
| GET | `/orders` | Get all orders | Admin |
| GET | `/orders/users` | Get user's orders | User |
| GET | `/orders/{orderId}` | Get order details | User/Admin |
| PUT | `/admin/orders/{orderId}/status` | Update order status | Admin |

### Address Endpoints (`/api`)

| Method | Endpoint | Description | Auth |
|--------|----------|-------------|------|
| POST | `/addresses` | Create new address | User |
| GET | `/addresses` | Get user's addresses | User |
| GET | `/addresses/{addressId}` | Get address details | User |
| PUT | `/addresses/{addressId}` | Update address | User |
| DELETE | `/addresses/{addressId}` | Delete address | User |

---

## ⚙️ Configuration

### JWT Configuration
Located in `application.properties`:
```properties
spring.app.jwtSecret=your_secret_key_minimum_32_chars
spring.app.jwtExpirationMs=300000  # 5 minutes
spring.ecom.app.jwtCookieName=springBootEcom
```

### Database Configuration
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce
spring.datasource.username=root
spring.datasource.password=password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```

### Logging Configuration
```properties
logging.level.org.springframework=DEBUG
logging.level.org.hibernate.SQL=DEBUG
logging.level.org.springframework.security=DEBUG
logging.level.com.ecommerce.project=DEBUG
```

### Image Storage
```properties
project.image=images/
```

---

## 🔐 Authentication

This application uses **JWT (JSON Web Tokens)** for stateless authentication.

### How It Works
1. User signs up with email and password
2. User signs in with credentials
3. Server returns JWT token in response
4. Client includes token in `Authorization: Bearer <token>` header for protected endpoints
5. Server validates token on each request

### Token Structure
```
Header.Payload.Signature
```

### Protected Routes
Routes requiring authentication must include the JWT token:
```bash
curl -H "Authorization: Bearer <your_jwt_token>" http://localhost:8080/api/carts/users/cart
```

---

## 📊 Database Schema

### Key Entities & Relationships

**User** - Stores user information and authentication details
- Relationships: One-to-Many with Cart, Order, Address

**Product** - Represents items available for purchase
- Relationships: Many-to-One with Category, Many-to-Many with Cart (via CartItem)

**Category** - Groups products by type
- Relationships: One-to-Many with Product

**Cart** - User's shopping cart
- Relationships: One-to-One with User, One-to-Many with CartItem

**CartItem** - Individual items in cart
- Relationships: Many-to-One with Cart, Many-to-One with Product

**Order** - Purchase orders
- Relationships: Many-to-One with User, One-to-Many with OrderItem, One-to-One with Payment

**OrderItem** - Items within an order
- Relationships: Many-to-One with Order, Many-to-One with Product

**Payment** - Payment information for orders
- Relationships: One-to-One with Order

**Address** - Shipping/billing addresses
- Relationships: Many-to-One with User, Many-to-One with Order

---

## 🧪 Testing the API

### Using cURL

**Sign Up:**
```bash
curl -X POST http://localhost:8080/api/auth/signup \
  -H "Content-Type: application/json" \
  -d '{
    "firstName":"John",
    "lastName":"Doe",
    "email":"john@example.com",
    "password":"pass123"
  }'
```

**Sign In:**
```bash
curl -X POST http://localhost:8080/api/auth/signin \
  -H "Content-Type: application/json" \
  -d '{
    "email":"john@example.com",
    "password":"pass123"
  }'
```

**Get Products:**
```bash
curl -X GET "http://localhost:8080/api/public/products?pageNumber=0&pageSize=10"
```

**Add to Cart (with token):**
```bash
curl -X POST http://localhost:8080/api/carts/products/1/quantity/2 \
  -H "Authorization: Bearer <your_jwt_token>" \
  -H "Content-Type: application/json"
```

### Using Postman

1. Import the API endpoints as a Postman collection
2. Set up an environment with `base_url=http://localhost:8080`
3. Use the Sign In endpoint to get a JWT token
4. Add the token to Authorization header for protected endpoints

---

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

---

## 📝 License

This project is licensed under the MIT License - see the LICENSE file for details.

---

## 📧 Contact & Support

- **Email:** sahel.xtha@gmail.com
- **Issues:** [GitHub Issues](https://github.com/sahelstha/ecommerce-backend)

---

## 🗺️ Roadmap

- [ ] Integration with payment gateways (Stripe, PayPal)
- [ ] Email notifications for orders
- [ ] Product reviews and ratings
- [ ] Wishlist feature
- [ ] Search and advanced filtering
- [ ] Admin dashboard analytics
- [ ] Automated testing suite
- [ ] Docker containerization

---

