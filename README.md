# RecruitCandidateAutomation
This project is a Spring Boot application designed for managing candidate data, sending email notifications to candidates based on interview results, retrying failed emails, and marking candidates as inactive after multiple failed attempts. Additionally, candidates who are successfully notified are deleted from the system for record-keeping purposes.


## Table of Contents

- [Project Overview](#project-overview)
- [Technologies Used](#technologies-used)
- [Setup and Installation](#setup-and-installation)


## Project Overview
This project automates the process of managing candidates for a recruitment system. Key functionalities include:

Sending email notifications to candidates based on interview results (passed or failed).  
Retrying email notifications if they fail a certain number of times.  
Marking candidates as inactive after multiple failed attempts to send emails.  
Logging failure reasons in the SenderFailLog table.  
Deleting candidates from the system if they have been successfully notified via email.  
The system uses Spring Boot with scheduled tasks to perform periodic operations for email sending and candidate management. It also uses an H2 in-memory database during   development and testing.  


## Technologies Used
-**Spring Boot:** The main framework for building the application.  
-**Spring Scheduler:** Used to execute periodic tasks such as sending emails, retrying failed notifications, and managing candidate statuses.  
-**Spring Email:** For sending email notifications to candidates.  
-**H2 Database:** An in-memory database for storing candidate and interview data.  
-**Java Persistence API (JPA):** For interacting with the database using object-relational mapping (ORM).  
-**Spring Data JPA:** Simplifies the integration of JPA with Spring Boot.  
-**Lombok:** A Java library used to reduce boilerplate code (like getters, setters, constructors).  
-**Validation API:** For validating input fields such as email.  


## Spring Scheduler Logic
### The application includes the following Spring Scheduler tasks:

#### Email Sending Scheduler:
This task periodically checks for candidates who need to be notified (based on interview results) and sends them HTML email templates. The email content depends on whether the candidate passed or failed the interview. Once an email is successfully sent to a candidate, their data is deleted from the candidate table.

#### Retry Scheduler:
If the email sending fails (e.g., due to invalid email or server issue), the system retries sending the email for a given number of attempts. If the email is still not sent after the specified number of retries, the candidate is marked as inactive, and the failure is logged in the SenderFailLog table.


## Setup and Installation


#### Clone the repository:

```bash
git clone https://github.com/lukanemsi/RecruitCandidateAutomation.git
cd java/RecruitCandidateAutomation
```

#### Build the project: 

```bash
mvn clean install
```

#### Start the application using:

```bash
mvn spring-boot:run
```
The application will be accessible at http://localhost:8081.

#### Access the H2 Console:

```bash
http://localhost:8081/h2-console
```
