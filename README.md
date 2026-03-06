# OOP E-Commerce Console Application

A fully functional **Object-Oriented e-commerce console application** built with Java as a term project. The application allows users to register, log in, browse a marketplace, manage a shopping cart, handle favorites, place orders with credit card payments, and more — all through a command-line interface backed by a remote MySQL database.

> **Note:** Since the project submission has been completed, the remote MySQL database is no longer accessible. Therefore, the application cannot connect to the server and will not function at runtime. The source code is preserved here for review and grading purposes only.

---

## Table of Contents

- [Features](#features)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Class Descriptions](#class-descriptions)
- [How It Works](#how-it-works)
- [Database](#database)
- [Documentation](#documentation)

---

## Features

- **User Authentication** – Register and log in with username/password
- **Marketplace Browsing** – View all available products from the database
- **Shopping Cart** – Add products to cart and manage them
- **Favorites** – Mark products as favorites for quick access
- **Order Management** – Place orders with quantity selection and credit card payment
- **Credit Card Management** – Add and remove credit cards to/from your account
- **Stock Tracking** – Automatic stock decrease upon order confirmation
- **Persistent Data** – All data is stored in a remote MySQL database

---

## Tech Stack

| Technology | Purpose |
|------------|---------|
| **Java** | Core programming language |
| **MySQL** | Remote relational database |
| **JDBC** | Java Database Connectivity for DB operations |

---

## Project Structure

```
oopProjectSubmit/
├── OopProjectHalilKayra.java   # Main entry point of the application
├── CommandLineUi.java          # Command-line user interface (menus & interactions)
├── AuthManager.java            # Authentication logic (login, register, session)
├── User.java                   # User model with cart, favorites, orders & cards
├── Product.java                # Product model with stock management
├── Order.java                  # Order model linking user, product & payment
├── CreditCard.java             # Credit card model
├── ProductsOrdered.java        # Tracks ordered products and their quantities
├── Database.java               # All database CRUD operations
├── DatabaseConnection.java     # MySQL connection manager
└── OOPTermProjectReport.pdf    # Project report document
```

---

## Class Descriptions

### `OopProjectHalilKayra` *(Main Class)*
The entry point of the application. Initializes the marketplace by fetching all products from the database and starts the command-line UI.

### `CommandLineUi`
Handles all user interaction through the console. Provides menus for login, registration, marketplace browsing, cart management, favorites, ordering, and credit card operations.

### `AuthManager`
Manages user authentication including:
- `login()` – Validates credentials and initializes user session
- `register()` – Creates a new user account in the database
- `initializeActiveUser()` – Loads user's cart, favorites, orders, and credit cards upon login

### `User`
Represents a user with personal information and associated data:
- Personal info: username, name, surname, email, date of birth, addresses
- Collections: shopping cart, favorite products, credit cards, ordered products
- Methods for adding/removing favorites, cart items, credit cards, and confirming orders

### `Product`
Represents a marketplace product with:
- Properties: id, name, color, category, stock, weight, description, price
- Stock management: `isStockEnough()` and `decreaseStock()` methods
- Unique identification by combination of product name and color

### `Order`
Represents a single order linking:
- The ordering user
- The ordered product and quantity
- The payment credit card
- `createOrder()` method to finalize the purchase

### `CreditCard`
Represents a credit card with card number, owner name, security code, and expiration date.

### `ProductsOrdered`
Tracks all products a user has ordered along with their respective quantities and payment information.

### `Database`
Contains all database CRUD operations including:
- User management (add/get users)
- Marketplace operations (fetch products, update stock)
- Cart operations (add to cart, get cart items)
- Favorites operations (add/remove favorites)
- Order operations (add orders, get order history)
- Credit card operations (add/remove cards)

### `DatabaseConnection`
Manages the MySQL database connection using JDBC with configured host, port, credentials, and database name.

---

## How It Works

1. **Startup** – The application connects to the remote MySQL database and loads all marketplace products.
2. **Authentication** – The user is prompted to either log in or register a new account.
3. **Session Initialization** – Upon successful login, the user's cart, favorites, orders, and credit cards are loaded from the database.
4. **Marketplace** – The user can browse available products, view details, and add items to their cart or favorites.
5. **Cart & Ordering** – The user can review their cart, select a credit card, and place orders. Stock is automatically updated.
6. **Data Persistence** – Every action (adding to cart, placing an order, managing favorites/cards) is immediately reflected in the database.

---

## Database

The application was originally connected to a remote **MySQL** database hosted on `alwaysdata.net`.

> **The database is no longer accessible.** The project submission period has ended and the remote database server has been shut down. As a result, the application cannot establish a connection and will not run as expected.

**Original Database Configuration:**
- **Host:** `mysql-dbdeneme.alwaysdata.net`
- **Port:** `3306`
- **Database:** `dbdeneme_2025`
- **Driver:** JDBC (`java.sql`)

---

##Documentation

The full project report is available in the repository:
- 📎 [OOPTermProjectReport.pdf](OOPTermProjectReport.pdf)
