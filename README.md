# Hospital_Management-System

A robust backend system designed to manage hospital workflows including **patient/doctor management**, **appointment booking**, **billing through Razorpay**, and **automatic email invoicing**. Built using **Spring Boot**, **PostgreSQL**, and **Spring Security**.

---

## 🔧 Features

### 👨‍💼 Admin
- Add/view doctors
- Add/view patients
- View all appointments
- Create bills for patients
- Initiate and track Razorpay payments

### 👨‍⚕️ Doctor
- View assigned appointments
- View patient information

### 🧑‍🦱 Patient
- View doctors
- Book appointments
- View and pay bills securely
- Receive email confirmation with bill

---

## 🛠️ Tech Stack

| Layer       | Technology                     |
|-------------|--------------------------------|
| Backend     | Spring Boot                    |
| Database    | PostgreSQL                     |
| ORM         | Spring Data JPA / Hibernate    |
| Security    | Spring Security (Basic Auth)   |
| Payment     | Razorpay Java SDK              |
| Email       | Spring Mail (JavaMailSender)   |
| Tools       | Postman, Git, Maven            |
| APIs        | RESTful APIs                   |

---

## 🧱 System Design

### ⚙️ Architecture Diagram

[ Postman / Frontend Client ]
|
+--> 
[ Spring Boot Controllers ]
|
+--> 
[ Service Layer ]
|
+--> 
[ Repositories (JPA) ] ---> [ PostgreSQL Database ]
|
+--> [ Razorpay Integration ]
|
+--> [ JavaMailSender (SMTP) ]

1. **Clone the Repository**
   ```bash
   git clone https://github.com/your-username/hospital-management-system.git
   cd hospital-management-system
Configure Database & Credentials

Update src/main/resources/application.properties:

properties
Copy
Edit
# Database
spring.datasource.url=jdbc:postgresql://localhost:5432/hospital_db
spring.datasource.username=your_db_user
spring.datasource.password=your_db_password

# Email
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=youremail@gmail.com
spring.mail.password=yourpassword
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true

# Razorpay
razorpay.key_id=your_razorpay_key_id
razorpay.key_secret=your_razorpay_key_secret
Build and Run the Application

bash
Copy
Edit
mvn clean install
mvn spring-boot:run
🔐 Authentication (Basic Auth)
Role	Username	Password
Admin	admin	admin123
Doctor	drsmith	dr123
Patient	john	john123

📚 API Reference (Sample)
👥 User APIs
Endpoint	Method	Role	Description
/api/admin/add-doctor	POST	Admin	Add a new doctor
/api/admin/add-patient	POST	Admin	Add a new patient
/api/patient/doctors	GET	Patient	View available doctors

📅 Appointment APIs
Endpoint	Method	Role	Description
/api/patient/book-appointment	POST	Patient	Book appointment
/api/doctor/appointments	GET	Doctor	View appointments

💸 Billing & Payment APIs
Endpoint	Method	Role	Description
/api/admin/create-bill	POST	Admin	Create bill for patient
/api/patient/pay	POST	Patient	Pay bill via Razorpay
/api/payment/verify	POST	System	Verify Razorpay transaction
/api/patient/bill-email	GET	System	Email bill PDF to patient

## 📧 Email Bill Integration

Upon generating a bill, the system sends an email to the patient using **Spring Mail**.

### 💬 Email Contents:
- Patient Name
- Appointment and Bill Details
- Amount to be paid
- A secure "Pay Now" button linking to the **Razorpay checkout URL**

💡 Possible Enhancements
Add frontend with Angular/React

Add JWT-based authentication

Upload patient reports and prescriptions

Add admin dashboard UI

