# 🎓 Student Management System

A **Spring Boot-based Student Management Application** that allows users to manage student records, handle fee payments, and generate **PDF reports using JasperReports**.

---

##  Features

###  Student Management
- Add, View, Update, and Delete Students
- Search Students by ID or Name

###  Fee Management
- Add and Update Student Fees
- Track Pending and Paid Fees
- View Fee History Per Student

### 📄 PDF Reporting (JasperReports)
- Generate **Student Fee Summary Report**
- Export **Individual or Bulk Fee Reports**
- Download Directly via REST API

---

## 🛠 Tech Stack

| Layer       | Technology |
|-------------|------------|
| Backend     | Spring Boot |
| Database    | H2 / MySQL |
| ORM         | Spring Data JPA |
| Build Tool  | Maven |
| Reporting   | JasperReports |

---

## 📦 Dependencies (Add to `pom.xml`)

```xml
<dependency>
    <groupId>net.sf.jasperreports</groupId>
    <artifactId>jasperreports</artifactId>
    <version>7.0.3</version>
</dependency>

<dependency>
    <groupId>net.sf.jasperreports</groupId>
    <artifactId>jasperreports-pdf</artifactId>
    <version>7.0.3</version>
</dependency>
