# Java Email Sender (Without Maven)

## Overview
This project allows you to send emails using **Jakarta Mail (JavaMail API)** in a standalone Java program without using Maven or Gradle.

## Prerequisites
- Java Development Kit (JDK) installed (Java 8 or later)
- VS Code with Java Extension Pack
- Internet connection (for sending emails via SMTP)

## Project Structure
```
JavaEmailSender/
├── src/
│   ├── Mail.java
├── lib/
│   ├── jakarta.mail-2.0.1.jar
├── .vscode/
├── README.md
```

## Setup Instructions
### 1. Download Jakarta Mail Library
- Download `jakarta.mail-2.0.1.jar` from [Maven Repository](https://mvnrepository.com/artifact/com.sun.mail/jakarta.mail)
- Place the JAR file inside the `lib/` folder

### 2. Configure Classpath in VS Code
#### Option 1: Using VS Code Settings
1. Open **Command Palette** (`Ctrl + Shift + P`).
2. Search for **"Java: Configure Runtime"** and select it.
3. Click **"Edit"** and add the path to `lib/jakarta.mail-2.0.1.jar`.

#### Option 2: Compile and Run via Terminal
```sh
javac -cp ".;lib/jakarta.mail-2.0.1.jar" src/Mail.java
java -cp ".;lib/jakarta.mail-2.0.1.jar" src.Mail
```
_(Use `:` instead of `;` in macOS/Linux)_

## How to Use
1. Open `Mail.java` and configure your SMTP details:
   ```java
   final String myAccEmail = "your-email@example.com";
   final String myAccPass = "your-email-password";
   ```
2. Run the program to send an email!

## Troubleshooting
- **Error: `javax.mail does not exist`** → Ensure the JAR file is correctly added to the classpath.
- **Authentication Issues** → Enable "Less Secure Apps" or generate an App Password in Gmail/Outlook.

## License
This project is open-source and free to use.

