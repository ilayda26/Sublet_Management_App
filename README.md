# Student Sublet Management System

## 1. Project Overview

The **Student Sublet Management System** is a Java-based desktop application designed to simplify the process of finding, advertising and managing temporary student accommodation.

The application provides a central platform where students can search for available sublets and apply for accommodation, while listers can create and manage their property listings. Administrators are provided with functionality for managing users, listings and submitted reports.

The project was developed as part of a university software engineering assignment and demonstrates the practical application of:

- Object-Oriented Programming (OOP)
- Java desktop application development
- Graphical User Interface (GUI) development
- Database integration
- User authentication
- CRUD operations
- Input validation
- Software modelling
- Software testing
- Static code analysis
- Test coverage analysis
- Git and GitHub version control
- Collaborative software development

The system was developed using **Java Swing** for the graphical user interface and **SQLite** for persistent data storage.

---

## 2. Project Objectives

The main objective of the project is to develop a functional desktop-based subletting platform that supports the main activities involved in advertising and finding temporary accommodation.

The system was designed to:

1. Allow users to register accounts.
2. Allow registered users to log into the system.
3. Support different user roles.
4. Allow students to search available listings.
5. Allow students to view detailed listing information.
6. Allow listers to create accommodation listings.
7. Allow listers to edit their existing listings.
8. Allow listers to delete their listings.
9. Allow students to apply for available sublets.
10. Allow listers to manage submitted applications.
11. Allow users to report problematic listings.
12. Allow users to communicate through messaging functionality.
13. Allow administrators to manage users, listings and reports.
14. Store application information persistently using a database.
15. Validate user input before information is processed.
16. Provide appropriate feedback when invalid operations occur.
17. Apply software testing techniques to verify the main functionality of the system.

---

## 3. User Roles

The application contains three main user roles:

### 3.1 Student

Students can use the system to search for temporary accommodation.

Student functionality includes:

- Registering an account
- Logging into the system
- Searching available listings
- Filtering listings
- Viewing listing details
- Applying for available sublets
- Viewing submitted applications
- Reporting inappropriate listings
- Messaging users
- Managing profile information
- Logging out

### 3.2 Lister

Listers are users who advertise accommodation.

Lister functionality includes:

- Registering an account
- Logging into the system
- Creating listings
- Viewing their own listings
- Editing listings
- Changing listing information and images
- Deleting listings
- Viewing submitted applications
- Accepting applications
- Rejecting applications
- Managing profile information
- Logging out

### 3.3 Administrator

Administrators are responsible for managing the platform.

Administrator functionality includes:

- Logging into the administrator dashboard
- Viewing registered users
- Managing users
- Viewing listings
- Managing listings
- Viewing submitted reports
- Managing reports
- Resolving reports
- Removing inappropriate content

---

## 4. Main Features

The main features included in the system are:

### 4.1 User Registration

New users can create an account by entering the required personal information.

The system validates the information before the account is created.

Validation includes:

- Required fields cannot be empty
- Email addresses must use a valid format
- Duplicate email addresses are rejected
- Passwords must meet the minimum length requirement

---

### 4.2 User Login

Registered users can log into the application using their email address and password.

The system verifies the credentials before allowing access.

Successful login redirects the user to the appropriate dashboard according to their role.

Invalid credentials are rejected and an appropriate error message is displayed.

---

### 4.3 Search Listings

Students can search for available accommodation.

Listings can be filtered using information such as:

- Location
- Maximum price
- Availability dates

The system displays listings that satisfy the selected search criteria.

---

### 4.4 View Listing Details

Students can select a listing to view more information.

Listing details may include:

- Listing title
- Location
- Borough
- Neighbourhood
- Price
- Description
- Start date
- End date
- Room type
- Availability
- Listing image

This allows students to review accommodation before deciding whether to apply.

---

### 4.5 Create Listing

Listers can create new accommodation listings.

A listing contains information such as:

- Title
- Location
- Borough
- Neighbourhood
- Price
- Description
- Availability dates
- Room type
- Listing image

The system validates the information before the listing is stored.

---

### 4.6 Edit Listing

Listers can edit listings belonging to their account.

Editable information can include:

- Title
- Location
- Price
- Description
- Availability dates
- Room information
- Listing image

Updated information is saved and displayed when the listing is viewed again.

---

### 4.7 Delete Listing

Listers can delete listings they own.

Before deletion, the system requests confirmation to reduce accidental deletion.

After confirmation, the listing is removed from the system.

---

### 4.8 Report Listing

Students can report listings they consider inappropriate or problematic.

A report records information including:

- The reporting user
- The reported listing
- The reason for the report
- Report status

Submitted reports can then be reviewed through the administrator functionality.

---

### 4.9 Message User

The system provides messaging functionality that allows communication between users.

Messages cannot be submitted when the required message content is empty.

---

### 4.10 Apply for Sublet

Students can submit an application for an available listing.

The application connects:

- The student
- The selected listing
- The application status

New applications are initially stored with a **Pending** status.

The system also prevents duplicate applications for the same listing where applicable.

---

### 4.11 Manage Applications

Listers can view applications submitted for their listings.

Applications can be reviewed and their status can be changed.

Application statuses include:

- Pending
- Accepted
- Rejected

This allows the lister to manage students who are interested in their accommodation.

---

## 5. Technologies Used

| Technology | Purpose |
|---|---|
| Java | Main programming language |
| Java Swing | Desktop graphical user interface |
| SQLite | Persistent data storage |
| SQLite JDBC | Communication between Java and SQLite |
| Apache Maven | Build and dependency management |
| JUnit 5 | Automated testing |
| JaCoCo | Automated test coverage measurement |
| SonarQube for IDE | Static code analysis |
| Git | Version control |
| GitHub | Remote repository and team collaboration |
| Visual Studio Code | Development environment |
| draw.io | UML and architecture diagrams |

---

## 6. Development Environment

The application was developed as a Java desktop application.

The main development environment includes:

```text
Programming Language: Java
Java Version:          Java 17 compatible
GUI:                   Java Swing
Build Tool:            Maven
Database:              SQLite
Database Connection:   JDBC
IDE:                    Visual Studio Code
Version Control:        Git / GitHub
```

The Maven project configuration is contained in:

```text
pom.xml
```

This file manages project information and required dependencies.

---

## 7. System Architecture

The application follows an object-oriented layered architecture.

The main architectural areas are:

### 7.1 User Interface Layer

The User Interface Layer is implemented using Java Swing.

It provides the screens and forms through which Students, Listers and Administrators interact with the system.

Examples include:

- Login interface
- Registration interface
- Student Dashboard
- Lister Dashboard
- Administrator Dashboard
- Listing forms
- Search interface
- Application management interface
- Report management interface

### 7.2 Application Logic

The application logic processes the main operations requested through the user interface.

This includes:

- Authentication
- User management
- Listing management
- Search and filtering
- Application management
- Report management
- Messaging
- Validation

### 7.3 Data Access

The data-access functionality connects the application logic to the database.

It is responsible for operations such as:

- Creating records
- Retrieving records
- Updating records
- Deleting records

### 7.4 Database Layer

SQLite is used to provide persistent storage.

The database stores important system information including:

- Users
- Listings
- Applications
- Reports

---

## 8. Main System Classes

The application uses several important object-oriented classes.

### 8.1 User

`User` represents the common information shared by users of the application.

It acts as the parent class for the different user roles.

### 8.2 Student

`Student` extends `User` and represents users searching and applying for temporary accommodation.

### 8.3 Lister

`Lister` extends `User` and represents users advertising accommodation.

### 8.4 Admin

`Admin` extends `User` and provides administrator functionality.

### 8.5 Listing

`Listing` represents accommodation advertised through the application.

Listing information includes:

- Listing ID
- Lister ID
- Title
- Location
- Borough
- Neighbourhood
- Price
- Description
- Start date
- End date
- Room type
- Status

### 8.6 Application

`Application` represents an application submitted by a student for a listing.

### 8.7 Report

`Report` represents a report submitted against a listing.

### 8.8 Database

`Database` manages the connection between the application and SQLite and supports persistent storage operations.

### 8.9 Validator

`Validator` provides reusable validation methods that can be used by different parts of the application.

This reduces duplicated validation code and improves consistency.

---

## 9. Database

The application uses **SQLite** for persistent data storage.

Using SQLite allows important information to remain available after the application is closed and restarted.

The database stores information related to:

```text
Users
Listings
Applications
Reports
```

The application communicates with SQLite through JDBC.

Database persistence allows:

- Registered users to remain available
- Listings to remain available between sessions
- Applications to be stored and retrieved
- Reports to be stored for administrator review
- Existing records to be updated
- Records to be removed when required

---

## 10. CRUD Operations

The project demonstrates the four main CRUD database operations.

### Create

Used when new information is added.

Examples:

```text
Register User
Create Listing
Submit Application
Create Report
```

### Read

Used when stored information is retrieved.

Examples:

```text
View Users
Search Listings
View Listing Details
View Applications
View Reports
```

### Update

Used when existing information is changed.

Examples:

```text
Edit Listing
Update Application Status
Update User Information
Resolve Report
```

### Delete

Used when information is removed.

Examples:

```text
Delete Listing
Remove inappropriate content
```

---

## 11. Input Validation

Input validation is used throughout the application to prevent invalid information from being processed or stored.

Validation includes:

- Required fields cannot be empty
- Email addresses must have an appropriate format
- Duplicate email addresses are rejected
- Passwords must meet minimum requirements
- Listing prices must be greater than zero
- Dates must contain valid values
- End dates must occur after start dates
- Empty messages are rejected
- Duplicate applications are prevented
- Duplicate reports are prevented
- Invalid operations display appropriate feedback

The reusable `Validator` class supports validation across different parts of the system.

---

## 12. Error Handling

The application provides feedback when an operation cannot be completed successfully.

Examples include:

- Incorrect login credentials
- Missing required information
- Invalid email addresses
- Duplicate accounts
- Invalid prices
- Invalid dates
- Duplicate applications
- Invalid listing operations
- Database-related problems

Instead of silently accepting invalid data, the application displays an appropriate message to the user.

---

## 13. Installation and Setup

### Prerequisites

Before running the project, ensure that the following are available:

- Java 17 or later
- Git
- Maven or a Java development environment with Maven support

Check Java using:

```bash
java -version
```

Check Git using:

```bash
git --version
```

If Maven is installed separately, it can be checked using:

```bash
mvn -version
```

---

## 14. Clone the Repository

Clone the GitHub repository using:

```bash
git clone https://github.com/ilayda26/Sublet_Management_App.git
```

Move into the project directory:

```bash
cd Sublet_Management_App
```

Open the project in Visual Studio Code or another Java development environment.

---

## 15. Running the Application

To run the application from a Java development environment:

1. Open the project.
2. Allow the required dependencies to load.
3. Locate `Main.java`.
4. Run the `Main` class.
5. The application login interface should open.

The main application entry point contains:

```java
public static void main(String[] args)
```

---

## 16. Basic User Workflow

A typical Student workflow is:

```text
Start Application
        ↓
Register / Login
        ↓
Student Dashboard
        ↓
Search Listings
        ↓
View Listing Details
        ↓
Apply for Sublet
        ↓
Application Submitted
```

A typical Lister workflow is:

```text
Start Application
        ↓
Login
        ↓
Lister Dashboard
        ↓
Create Listing
        ↓
Manage Listings
        ↓
View Applications
        ↓
Accept / Reject Application
```

A reporting workflow is:

```text
Search Listings
        ↓
Select Listing
        ↓
Report Listing
        ↓
Enter Reason
        ↓
Submit Report
        ↓
Administrator Reviews Report
```

---

## 17. Software Testing

Testing was performed to determine whether the application satisfies its functional requirements and behaves correctly under valid and invalid conditions.

The project used:

- Unit Testing
- Integration Testing
- System Testing
- Functional Testing
- Positive Testing
- Negative Testing
- Static Testing
- Dynamic Testing

Black-box testing techniques included:

- Equivalence Partitioning
- Boundary Value Analysis
- Decision Table Testing
- State Transition Testing
- Use Case Testing

Testing covered the major functionality of the system, including registration, login, listings, applications, reports and application management.

---

## 18. Automated Testing

JUnit 5 was used for selected automated tests.

The automated tests focused mainly on functionality that could be tested independently of the Java Swing user interface.

The automated test suite can be executed through Maven using:

```bash
mvn test
```

### Automated Test Results

The final automated test execution produced:

```text
Tests Run:    6
Tests Passed: 6
Tests Failed: 0
```

This means all six implemented automated tests completed successfully.

The remaining Java Swing workflows were primarily evaluated using manual system testing.

---

## 19. Test Coverage

JaCoCo was used to measure the amount of application code exercised by the automated tests.

The final recorded coverage results were:

| Coverage Type | Result |
|---|---:|
| Line Coverage | 0.2% |
| Branch Coverage | 2.5% |
| Method Coverage | 1.7% |

The automated coverage is relatively low because the automated tests focused on selected application and validation logic.

A significant amount of the project consists of Java Swing user-interface and workflow functionality, which was evaluated mainly through manual system testing.

Future development could increase automated coverage by adding additional unit and integration tests for database operations, listings, applications, reports and user-management functionality.

---

## 20. Static Code Analysis

Static code analysis was performed using **SonarQube for IDE**.

Static analysis was used to identify code-quality issues without executing the application.

Examples of identified issues included:

- Unused imports
- Unused fields
- Repeated string literals

The identified issues were reviewed during development and selected issues were corrected.

Static analysis was used alongside dynamic testing to improve overall code quality.

---

## 21. Defects Identified During Testing

Testing identified genuine defects in the application.

### BUG-01 – Sublet Application Notification

A student could submit an application successfully, but the listing owner was not sufficiently notified.

The functionality was improved so that the lister could review the submitted application and accept or reject it.

### BUG-02 – Changing Listing Image While Editing

The Edit Listing functionality originally allowed textual information to be changed but did not provide an option to change the listing image.

The functionality was updated to allow the listing image to be changed.

### BUG-03 – Reported Listing Not Visible to Administrator

A student could submit a report, but the submitted report was initially not visible through the administrator interface.

The report-management functionality was corrected so that reports could be viewed and managed by the administrator.

Detailed defect information, screenshots and testing evidence are provided in the Software Testing Report.

---

## 22. Software Modelling and Design

Software modelling was used to represent both the structure and behaviour of the application.

The project documentation includes:

- Use Case Diagrams
- Activity Diagrams
- Sequence Diagrams
- Class Diagram
- System Architecture Diagram
- Entity Relationship Diagram (ERD)

### Use Case Diagrams

Use case diagrams show how the different user roles interact with the system.

The main actors are:

```text
Student
Lister
Administrator
```

### Activity Diagrams

Activity diagrams represent the workflow of important system operations.

Examples include:

```text
Search Listing
Create Listing
```

### Sequence Diagrams

Sequence diagrams represent communication between objects and components during a particular operation.

Sequence diagrams were created for functionality including:

- Register Account
- Login Account
- Search and Filter Listings
- View Listing
- Create Listing
- Edit Listing
- Delete Listing
- Apply for a Sublet
- Manage Applications
- Accept or Reject Application
- Administrator Management

### Class Diagram

The class diagram represents the static structure of the application, including:

- Classes
- Attributes
- Methods
- Inheritance
- Associations
- Multiplicity

### Entity Relationship Diagram

The ERD represents the main persistent entities and relationships used by the database.

---

## 23. Object-Oriented Programming

The application demonstrates several important Object-Oriented Programming principles.

### Encapsulation

Related information and behaviour are grouped inside classes.

Examples include:

```text
User
Student
Lister
Admin
Listing
Application
Report
```

### Inheritance

The user-role classes use inheritance to share common user functionality.

For example:

```text
             User
          /    |    \
     Student Lister Admin
```

This reduces duplicated code and represents the relationship between the different user types.

### Abstraction

Different parts of the application are responsible for specific tasks.

For example, the graphical interface does not need to contain all of the implementation details required to communicate with the database.

### Association

Objects in the system are related to one another.

Examples include:

```text
Lister → Listing
Student → Application
Listing → Application
Student → Report
Listing → Report
```

These relationships represent interactions that occur within the subletting process.

---

## 24. Version Control

Git was used throughout development to manage changes to the source code.

Git was used to:

- Track modifications
- Maintain development history
- Work on features independently
- Share code between team members
- Merge completed functionality
- Resolve integration issues
- Maintain the final version of the application

Useful commands include:

```bash
git status
```

```bash
git branch
```

```bash
git fetch origin
```

```bash
git pull
```

```bash
git add .
```

```bash
git commit -m "Describe changes"
```

```bash
git push
```

---

## 25. GitHub Collaboration

GitHub was used as the remote repository for the project.

The team used separate development branches so that different functionality could be developed independently before being integrated.

A simplified workflow was:

```text
GitHub Repository
       ↓
Local Repository
       ↓
Development Branch
       ↓
Code Changes
       ↓
Commit
       ↓
Push
       ↓
Merge
       ↓
Main Branch
```

The completed integrated application is maintained in the `main` branch.

Repository:

```text
https://github.com/ilayda26/Sublet_Management_App
```

---

## 26. Functional Requirements

The main functional requirements of the system include:

| ID | Requirement |
|---|---|
| FR-01 | The system shall allow users to register |
| FR-02 | The system shall allow registered users to log in |
| FR-03 | The system shall allow students to search listings |
| FR-04 | The system shall allow students to view listing details |
| FR-05 | The system shall allow listers to create listings |
| FR-06 | The system shall allow listers to edit their listings |
| FR-07 | The system shall allow listers to delete their listings |
| FR-08 | The system shall allow students to report listings |
| FR-09 | The system shall support messaging functionality |
| FR-10 | The system shall allow students to apply for sublets |
| FR-11 | The system shall allow listers to manage applications |
| FR-12 | The system shall allow administrators to manage users |
| FR-13 | The system shall allow administrators to manage listings |
| FR-14 | The system shall allow administrators to manage reports |
| FR-15 | The system shall validate user input |
| FR-16 | The system shall store important application data persistently |

---

## 27. Non-Functional Requirements

### Usability

The graphical interface should be understandable and allow users to navigate the main functionality without specialist technical knowledge.

### Reliability

The application should perform expected operations consistently and handle invalid input appropriately.

### Maintainability

The use of separate classes, reusable validation and object-oriented design makes the system easier to maintain and extend.

### Data Integrity

Validation and database operations help prevent invalid or incomplete information from being stored.

### Performance

Normal operations such as searching listings, submitting applications and managing listings should complete without unnecessary delay.

### Portability

Because the application is developed using Java, it can potentially run on different operating systems with an appropriate Java Runtime Environment.

---

## 28. Security Considerations

Although the system is an academic desktop application, several security principles are relevant.

These include:

- Validating user input
- Authenticating users before providing access
- Restricting listing management to the appropriate lister
- Preventing users from editing another lister's listings
- Associating applications with the correct student and listing
- Protecting database operations
- Preventing invalid records
- Restricting administrator functionality to administrator users

A production version would require additional security measures.

---

## 29. Current Limitations

The application was developed as a university project and focuses on demonstrating the core functionality of a student subletting platform.

Current limitations include:

- Desktop-based operation rather than online deployment
- Limited automated test coverage
- Limited real-time communication
- Basic notification functionality
- Basic image management compared with commercial platforms
- No integrated online payment system
- No automated identity verification
- No map integration
- Limited scalability compared with a commercial accommodation platform

---

## 30. Future Improvements

Future versions of the application could include:

### Increased Automated Testing

Additional unit and integration tests could improve automated code coverage.

### Improved Messaging

Messaging functionality could be expanded to provide a more complete conversation system between students and listers.

### Notifications

Notifications could be generated when:

- A new application is received
- An application is accepted
- An application is rejected
- A listing changes
- A report is resolved

### Advanced Search

Additional search filters could be added for:

- Price range
- Room type
- Property type
- Availability
- Additional location information

### Improved Image Management

Future versions could support:

- Multiple images
- Image galleries
- Image previews
- Improved image storage

### Map Integration

Listings could be displayed on a map to provide additional location information.

### Improved Authentication

A future production version could introduce:

- Password hashing
- Password reset
- Email verification
- Multi-factor authentication

### Online Deployment

The application could eventually be converted into a web or mobile application to allow remote access.

---

## 31. Troubleshooting

### Application Does Not Start

Check that Java is installed:

```bash
java -version
```

Verify that the project has been opened correctly and that `Main.java` can be executed.

### Maven Dependencies Are Missing

If Maven is available, dependencies can be refreshed using:

```bash
mvn clean compile
```

### Automated Tests Do Not Run

Run:

```bash
mvn test
```

Check that the JUnit test files and required dependencies are present.

### Database Does Not Load

Check:

- SQLite JDBC dependency
- Database connection configuration
- Database file location
- File permissions

### Images Do Not Display

Check that:

- The image exists
- The image path is correct
- The application has permission to access the file
- The stored path points to the correct image

### GitHub Changes Are Missing

Check the repository status:

```bash
git status
```

Download remote information:

```bash
git fetch origin
```

Update the current branch when appropriate:

```bash
git pull
```

---

## 32. Project Documentation

The complete project documentation includes:

- README
- Software Modelling and Design Report
- Software Testing Report
- Requirements and User Stories
- Acceptance Criteria
- Use Case Diagrams
- Activity Diagrams
- Sequence Diagrams
- Class Diagram
- System Architecture Diagram
- Entity Relationship Diagram
- Test Plan
- Test Cases
- Automated Test Results
- Static Analysis Results
- Defect Log
- Test Coverage Results
- Application Screenshots

The reports provide more detailed information than this README about the system design, implementation and testing process.

---

## 33. Contributors

This project was completed collaboratively by:

### Star Kudzai Chamunorwa

**Student Functionality and Validation**

Main contributions included:

- Student functionality
- User and Student classes
- Application and Report models
- Validation functionality
- Student Dashboard
- Listing search and filtering
- Listing details
- Student application functionality
- Reporting functionality

### Ilayda Ray

**Lister, Administrator and Database Functionality**

Main contributions included:

- Database development and integration
- Lister functionality
- Lister Dashboard
- Listing creation
- Listing editing
- Listing deletion
- Application management
- Administrator functionality
- User management
- Listing management
- Report management

### Shared Work

Both team members contributed to:

- Requirements analysis
- Software modelling
- System architecture
- Integration
- GitHub collaboration
- Testing and debugging
- Documentation
- Final project preparation

---

## 34. Academic Context

This application was developed as an academic software engineering project at **GISMA University of Applied Sciences**.

The project demonstrates the main stages of the software development lifecycle:

```text
Requirements Analysis
        ↓
Software Modelling
        ↓
System Design
        ↓
Implementation
        ↓
Database Integration
        ↓
Testing
        ↓
Debugging
        ↓
Version Control
        ↓
Documentation
```

The completed system demonstrates how software engineering concepts can be applied to the development and testing of a practical Java desktop application.

---

## 35. Conclusion

The **Student Sublet Management System** demonstrates the development of a Java desktop application using object-oriented programming, Java Swing, database integration, validation, software modelling, software testing and version control.

The application provides the core functionality required for a student subletting platform, including registration, authentication, accommodation listings, search functionality, applications, listing management, reporting, messaging and administrator functionality.

The project also demonstrates the use of automated and manual testing, static code analysis, defect identification and test coverage measurement.

Although additional development would be required before the system could be deployed as a commercial accommodation platform, the completed application provides a functional foundation that could be extended with additional features in the future.

---

## Authors

**Star Kudzai Chamunorwa**  
**Ilayda Ray**

GISMA University of Applied Sciences  
2026