# Employee Login Management System

A desktop-based Employee Login Management System developed using **Java Swing**, **JDBC**, and **Oracle Database 11g XE**. The application provides secure employee authentication, profile viewing, login activity tracking, and automatic login history storage with an intuitive graphical user  interface.
<img width="1600" height="844" alt="image" src="https://github.com/user-attachments/assets/f307d18b-ed41-4b52-9eb8-87e8d2819f5d" />
<img width="1600" height="844" alt="image" src="https://github.com/user-attachments/assets/9c8d11eb-85d5-4fb1-a4ea-a34c72962bf1" />
<img width="1600" height="848" alt="image" src="https://github.com/user-attachments/assets/01a6f9bf-f562-449a-a1d3-c347624370eb" />
<img width="1280" height="458" alt="image" src="https://github.com/user-attachments/assets/1e776682-4193-42b7-8a96-27d4b28e5ea8" />


---

## Features

### Employee Authentication

* Secure Employee Login
* Employee ID and Password Validation
* Database-Based Authentication
* Invalid Login Detection

### Employee Profile Dashboard

After successful login, the system displays:

* Employee ID
* First Name
* Last Name
* Department
* Designation
* Login Date & Time
* Employee Profile Picture

### Real-Time Date & Time

* Live System Date Display
* Live System Time Display
* Automatic Updates Using Java Timer

### Login Activity Tracking

* Automatic Login History Recording
* Unique Login ID Generation
* Login Timestamp Storage
* Employee Login Audit Trail

### Profile Picture Support

* Employee Photo Storage Path
* Automatic Image Loading
* Scaled Profile Picture Display

### Database Integration

* Oracle Database 11g XE Connectivity
* Automatic Table Creation
* Persistent Data Storage
* JDBC-Based Communication

---

## Technology Stack

| Technology             | Purpose                   |
| ---------------------- | ------------------------- |
| Java                   | Core Programming Language |
| Java Swing             | Graphical User Interface  |
| JDBC                   | Database Connectivity     |
| Oracle Database 11g XE | Database Management       |
| SQL                    | Data Manipulation         |
| ImageIO                | Profile Image Processing  |

## Database Schema
---
### EMPLOYEE Table

```sql
CREATE TABLE EMPLOYEE
(
    EMPLOYEEID VARCHAR2(15) PRIMARY KEY,
    FNAME      VARCHAR2(15),
    LNAME      VARCHAR2(15),
    DEPT       VARCHAR2(15),
    DESIG      VARCHAR2(15),
    PWD        VARCHAR2(15),
    PROPICT    VARCHAR2(150)
);
```

### LOGIN Table

```sql
CREATE TABLE LOGIN
(
    LOGINID    CHAR(15) PRIMARY KEY,
    EMPLOYEEID VARCHAR2(15),
    LOGINTIME  DATE
);
```

---

## Oracle Database Configuration

### JDBC Driver

```java
Class.forName("oracle.jdbc.driver.OracleDriver");
```

### Database Connection

```java
DriverManager.getConnection(
    "jdbc:oracle:thin:@localhost:1521:XE",
    "HR",
    "hr"
);
```

### Default Database Credentials

| Username | Password |
| -------- | -------- |
| HR       | hr       |

---

## Functional Modules

### Employee Login

* Employee ID Authentication
* Password Verification
* Database Validation

### Employee Dashboard

* Profile Information Display
* Profile Picture Display
* Login Timestamp Display

### Login Monitoring

* Automatic Login Recording
* Login History Maintenance
* Audit Tracking

### System Clock

* Real-Time Date
* Real-Time Time
* Continuous Updates

---

## User Interface Features

### Login Window

* Employee ID Input
* Password Input
* Date Display
* Time Display
* Submit Button
* Reset Button

### Login Status Window

* Employee Profile Photo
* Employee Information
* Department Information
* Designation Information
* Login Date & Time

---

## Key Concepts Implemented

* Java Swing GUI Development
* JDBC Programming
* Oracle Database Integration
* Event Handling
* Timer-Based Applications
* Image Processing
* Authentication Systems
* Database CRUD Operations
* Object-Oriented Programming (OOP)

---

## How to Run

### Step 1: Start Oracle Database

Ensure Oracle Database 11g XE is running.

### Step 2: Configure Oracle User

```sql
ALTER USER HR IDENTIFIED BY hr;
GRANT CONNECT, RESOURCE TO HR;
```

### Step 3: Add JDBC Driver

Add Oracle JDBC driver to your project:

```text
ojdbc6.jar
```

or

```text
ojdbc8.jar
```

### Step 4: Compile and Run

```bash
javac *.java
java MainClass
```

---

## Security Features

* Employee Authentication
* Password Verification
* Database-Based Login Validation
* Login History Tracking
* Unique Login Session Recording

---

## Application Workflow

1. Launch Application
2. Enter Employee ID
3. Enter Password
4. Click Submit
5. Validate Credentials
6. Display Employee Information
7. Record Login Activity
8. Store Login History in Database

---

## Educational Objectives

This project demonstrates practical implementation of:

* Employee Management Systems
* Authentication Mechanisms
* Java Swing GUI Design
* Oracle Database Programming
* JDBC Connectivity
* Login Audit Systems
* Image Handling in Java
* Event-Driven Programming

---
## License

This project is developed for educational, academic, and learning purposes.
