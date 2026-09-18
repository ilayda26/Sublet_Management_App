# Sublet Management System

## 1. Project Overview

The **Sublet Management System** is a Java-based desktop application designed to simplify the process of advertising, finding, and managing temporary accommodation.

The application provides a centralized platform where users can create accounts, publish sublet listings, browse available accommodation, apply for listings, manage their own properties, and report inappropriate or problematic listings.

The project was developed as part of a university software development assignment and demonstrates the practical application of software engineering concepts including:

* Object-Oriented Programming
* Graphical User Interface development
* Database integration
* User authentication
* CRUD operations
* Input validation
* Exception handling
* Software testing
* Git and GitHub version control
* Collaborative software development

The system is primarily aimed at **university students and other users looking for temporary accommodation**, while also allowing property owners or tenants to advertise rooms and apartments available for subletting.

---

# 2. Project Objectives

The main objective of the project is to develop a functional desktop-based subletting platform that allows users to manage the complete subletting process through one application.

The system was designed to achieve the following objectives:

1. Allow users to register and log into the system.
2. Allow authenticated users to create accommodation listings.
3. Store listing and user information persistently.
4. Allow users to browse available sublet listings.
5. Allow users to view detailed information about individual listings.
6. Allow users to apply for accommodation.
7. Allow listing owners to manage their listings.
8. Allow users to manage applications.
9. Allow inappropriate listings or behaviour to be reported.
10. Validate user input and handle invalid operations appropriately.
11. Provide a graphical desktop interface that is easy to understand and navigate.
12. Demonstrate the use of software testing techniques to verify that major system functions operate correctly.

---

# 3. Main Features

## 3.1 User Registration

New users can create an account in the system.

The registration process collects the information required to create a user profile and validates the information before saving the account.

Typical validation includes checking that required fields are not empty and ensuring that user information is entered in an acceptable format.

---

## 3.2 User Login

Registered users can log into the application using their credentials.

The login functionality verifies the information entered by the user before allowing access to the main application.

Invalid login attempts are rejected and an appropriate message is displayed.

---

## 3.3 Create Listing

Authenticated users can create accommodation listings.

A listing can contain information such as:

* Property title
* Description
* Location
* Price
* Availability dates
* Property information
* Listing image or image path
* Owner information

Before a listing is created, the system validates the information entered by the user.

Successfully created listings are stored by the system and become available for other users to view.

---

## 3.4 Browse Listings

Users can browse available accommodation through the application's listing interface.

Each listing provides important information that helps the user decide whether they are interested in the property.

The browsing functionality provides a convenient way of viewing the currently available sublets without requiring users to manually contact every property owner.

---

## 3.5 View Listing Details

Users can select a listing to view more detailed information.

The detailed listing view can display information such as:

* Listing title
* Property description
* Price
* Location
* Availability
* Property owner
* Listing image
* Other relevant accommodation information

This allows users to review a property before submitting an application.

---

## 3.6 Apply for a Listing

Users who are interested in accommodation can submit an application for a listing.

An application connects:

* The applicant
* The selected listing
* The application information
* The application status

Applications are stored by the system so that they can later be reviewed and managed.

---

## 3.7 Manage Listings

Users can access their own listings through the **Manage Listings** functionality.

This section allows the listing owner to manage properties they have previously created.

Depending on the available operation, users can perform actions such as:

* View their listings
* Edit listing information
* Update property information
* Remove listings
* Review information connected to a listing

Only the appropriate user should be able to manage listings belonging to their account.

---

## 3.8 Manage Applications

The system stores applications submitted for accommodation listings.

Application functionality allows the system to associate an applicant with a particular property.

The application model also makes it possible to track the status of an application.

Typical statuses may include:

* Pending
* Accepted
* Rejected

This provides a structured way of managing the communication between applicants and listing owners.

---

## 3.9 Report Functionality

Users can report problematic listings or activity through the application.

A report stores information related to the issue being reported and allows potentially inappropriate content to be identified.

The reporting functionality contributes to the reliability and safety of the platform by allowing users to identify listings that may require further attention.

---

# 4. Technologies Used

The project uses the following technologies:

| Technology       | Purpose                                     |
| ---------------- | ------------------------------------------- |
| Java 17          | Main programming language                   |
| Maven            | Project build and dependency management     |
| SQLite           | Persistent data storage                     |
| JDBC             | Communication between Java and the database |
| Java Desktop GUI | User interface                              |
| JUnit            | Automated testing                           |
| Git              | Version control                             |
| GitHub           | Remote repository and team collaboration    |
| IntelliJ IDEA    | Development environment                     |

---

# 5. Development Environment

The application was developed using **Java 17**.

The project uses **Maven**, which manages external dependencies and simplifies compiling and testing the application.

The Maven configuration is contained in:

```text
pom.xml
```

The project can therefore be opened in any Java IDE that provides Maven support.

Recommended development environment:

```text
Java:       Java 17
Build Tool: Maven
IDE:        IntelliJ IDEA
Database:   SQLite
```

---

# 6. System Architecture

The application follows an object-oriented architecture in which different classes are responsible for different areas of the system.

The main architectural areas include:

### User Interface Layer

Responsible for displaying information to the user and receiving user input.

Examples include interfaces for:

* Login
* Registration
* Main menu
* Listings
* Listing details
* Applications
* Listing management
* Reports

### Business Logic

Contains the rules used to process user operations.

Examples include:

* Validating user information
* Creating listings
* Processing applications
* Updating listings
* Managing reports

### Data Layer

Responsible for storing and retrieving information.

The system uses classes and database functionality to manage data related to:

* Users
* Listings
* Applications
* Reports

### Database Layer

SQLite provides persistent storage for application data.

Java communicates with the database using JDBC.

---

# 7. Main System Entities

The application is built around several important domain classes.

## 7.1 User

Represents a person registered in the system.

A user can perform actions such as:

* Log in
* Create listings
* Browse listings
* Apply for accommodation
* Manage their listings
* Submit reports

Each user is assigned a unique identifier.

---

## 7.2 Listing

Represents accommodation advertised through the application.

A listing stores information about the available property and is associated with the user who created it.

A listing can include:

* Listing ID
* Owner
* Title
* Description
* Location
* Price
* Availability
* Image
* Other property details

---

## 7.3 Application

Represents an application submitted by a user for a particular listing.

An application connects a user with the listing they want to rent.

Each application has its own unique identifier.

Application information may include:

* Applicant
* Listing
* Application date
* Application message
* Application status

---

## 7.4 Report

Represents a report created by a user.

Reports are used when a user identifies inappropriate, misleading, suspicious, or otherwise problematic content.

A report can contain:

* Report ID
* User information
* Related listing
* Reason for the report
* Additional description
* Report status

---

# 8. Data Management

The application manages four major collections of information:

```java
List<User> users;
List<Listing> listings;
List<Application> applications;
List<Report> reports;
```

Unique identifiers are generated for the major entities.

Examples include:

```text
User ID
Listing ID
Application ID
Report ID
```

This ensures that individual records can be uniquely identified by the application.

---

# 9. Database

The application uses **SQLite** for persistent storage.

Using a database provides several advantages compared with storing all information only in memory.

These include:

* Data remains available after the application closes.
* Users can log back into previously created accounts.
* Listings can remain available between sessions.
* Applications can be stored and retrieved.
* Reports can be stored for later review.
* Records can be queried and updated more efficiently.

The application communicates with SQLite using JDBC.

---

# 10. CRUD Operations

The project demonstrates the four fundamental database operations:

## Create

Used when creating new information.

Examples:

```text
Register a new user
Create a listing
Submit an application
Create a report
```

## Read

Used when retrieving information.

Examples:

```text
View users
Browse listings
View listing details
View applications
View reports
```

## Update

Used when modifying existing information.

Examples:

```text
Edit a listing
Update application status
Update account or listing information
```

## Delete

Used when information needs to be removed.

Example:

```text
Delete a listing
```

These operations form the basis of the application's data management functionality.

---

# 11. Project Structure

A typical structure of the project is:

```text
sublet-management-system/
│
├── pom.xml
├── README.md
│
├── src/
│   │
│   ├── main/
│   │   ├── java/
│   │   │
│   │   │   ├── User.java
│   │   │   ├── Listing.java
│   │   │   ├── Application.java
│   │   │   ├── Report.java
│   │   │   ├── DataStore.java
│   │   │   └── other application classes
│   │   │
│   │   └── resources/
│   │       └── application resources
│   │
│   └── test/
│       └── java/
│           └── test classes
│
└── database/
    └── SQLite database files
```

The exact structure can vary depending on the final organization of the project.

---

# 12. Installation

## Prerequisites

Before running the application, ensure that the following software is installed:

### Java Development Kit

Java 17 or a compatible JDK is required.

Check the Java installation using:

```bash
java -version
```

The output should show Java 17.

Example:

```text
java version "17"
```

---

## Maven

Maven is required to build the project and download the required dependencies.

Check Maven using:

```bash
mvn -version
```

---

## Git

Git is required if the project is being cloned from GitHub.

Check the installation using:

```bash
git --version
```

---

# 13. Clone the Repository

Clone the project from GitHub using:

```bash
git clone <repository-url>
```

Move into the project directory:

```bash
cd sublet-management-system
```

Replace `<repository-url>` with the URL of the team's GitHub repository.

---

# 14. Opening the Project in IntelliJ IDEA

The application can be opened in IntelliJ IDEA using the following steps:

1. Open IntelliJ IDEA.
2. Select **Open**.
3. Navigate to the project directory.
4. Select the folder containing `pom.xml`.
5. Open the project.
6. Allow IntelliJ IDEA to detect the Maven project.
7. Allow Maven to download the required dependencies.
8. Verify that the project SDK is set to Java 17.

The SDK can normally be checked through:

```text
File
→ Project Structure
→ Project
→ SDK
```

Select Java 17 if another Java version is currently configured.

---

# 15. Building the Project

The project can be compiled using Maven.

Run:

```bash
mvn clean compile
```

This command:

1. Removes previous build files.
2. Compiles the source code.
3. Reports compilation errors if they exist.

To package the application, run:

```bash
mvn clean package
```

The generated build files will normally be placed inside:

```text
target/
```

---

# 16. Running the Application

The application can be started from the main application class in IntelliJ IDEA.

Open the class containing the application's:

```java
public static void main(String[] args)
```

and select:

```text
Run
```

The application should then open the desktop graphical user interface.

---

# 17. Basic User Workflow

A typical user interaction with the system is:

```text
Start Application
       ↓
Register / Login
       ↓
Main Application
       ↓
Browse Listings
       ↓
View Listing Details
       ↓
Apply for Listing
```

A listing owner may follow the workflow:

```text
Login
  ↓
Create Listing
  ↓
Listing Saved
  ↓
Manage Listings
  ↓
Edit / Delete / Review Listing
```

A reporting workflow may be:

```text
Browse Listing
      ↓
Select Listing
      ↓
Report Listing
      ↓
Enter Report Reason
      ↓
Submit Report
      ↓
Report Stored
```

---

# 18. Input Validation

Input validation is an important part of the system.

The application checks user input to reduce invalid information being stored.

Examples of validation include:

* Required fields must not be empty.
* Numeric fields must contain valid numbers.
* Prices must contain acceptable values.
* Dates must use valid date values.
* Invalid login information must be rejected.
* Invalid operations must display appropriate feedback.
* Required information must be provided before a record is created.

Validation improves both system reliability and user experience.

---

# 19. Error Handling

The application includes error handling for operations that may fail.

Examples include:

* Invalid user input
* Database errors
* Incorrect login credentials
* Missing information
* Invalid IDs
* Invalid dates
* Invalid prices
* Operations involving unavailable listings
* Unexpected application errors

Instead of allowing the application to terminate unexpectedly, errors are handled and appropriate feedback can be displayed to the user.

---

# 20. Testing

Testing was performed to determine whether the system satisfies its functional requirements and behaves correctly under both valid and invalid conditions.

The testing process includes a combination of:

* Unit testing
* Functional testing
* Positive testing
* Negative testing
* Boundary testing
* Validation testing
* Database testing
* User interface testing
* Integration testing

---

# 21. Unit Testing

JUnit is used for automated testing where appropriate.

Unit tests focus on individual components and functions of the application.

Typical test areas include:

```text
User creation
Listing creation
Application creation
Report creation
Validation
ID generation
Database operations
Business logic
```

The test source files are stored under:

```text
src/test/java/
```

---

# 22. Running Automated Tests

The complete Maven test suite can be executed using:

```bash
mvn test
```

Maven compiles the tests and executes the available JUnit test cases.

A successful test run should indicate that the tests completed without failures or errors.

---

# 23. Positive Testing

Positive tests verify that the application works correctly when valid data is entered.

Examples include:

* Registering with valid user details
* Logging in with correct credentials
* Creating a listing with valid information
* Applying for an available property
* Editing an owned listing
* Submitting a valid report

Expected result:

```text
The requested operation completes successfully.
```

---

# 24. Negative Testing

Negative tests verify that the system correctly handles invalid information or invalid user actions.

Examples include:

* Attempting to log in with incorrect credentials
* Leaving required fields empty
* Entering invalid price values
* Entering invalid dates
* Attempting operations using invalid IDs
* Attempting to submit incomplete information

Expected result:

```text
The system rejects the operation and provides appropriate feedback.
```

Negative testing is particularly important because it demonstrates that the system can handle errors rather than only working under ideal conditions.

---

# 25. Boundary Testing

Boundary testing examines values around the acceptable limits of an input.

Examples may include:

* Empty strings
* Minimum price values
* Large price values
* Short text input
* Long text input
* Date boundaries

Testing boundary conditions helps identify problems that may not appear when testing ordinary values.

---

# 26. Database Testing

Database testing is performed to verify that information is correctly transferred between the application and SQLite database.

Database tests include checking whether:

* Users can be inserted.
* Listings can be inserted.
* Listings can be retrieved.
* Records can be updated.
* Records can be deleted.
* Applications can be stored.
* Reports can be stored.
* Data persists after restarting the application.

---

# 27. Integration Testing

Integration testing verifies that different parts of the system work together correctly.

Examples include:

```text
Registration → Database

Login → User Data

Create Listing → Database

Browse Listings → Database

Application → User + Listing + Database

Manage Listing → User + Listing + Database

Report → User + Listing + Database
```

This testing is important because individual classes may work correctly on their own while problems may still occur when several components interact.

---

# 28. Test Evidence

Screenshots are used as evidence during testing.

Screenshots may demonstrate:

* Successful operations
* Failed operations
* Validation messages
* Exceptions
* Database results
* JUnit test execution
* Maven test results
* Application behaviour

Both successful and unsuccessful test cases are useful because error screenshots demonstrate how the system responds to invalid operations.

Detailed test cases, expected results, actual results, and screenshots are documented in the associated project testing report.

---

# 29. Example Test Scenarios

| Test ID | Feature        | Test                        | Expected Result                   |
| ------- | -------------- | --------------------------- | --------------------------------- |
| T01     | Registration   | Enter valid details         | User account created              |
| T02     | Registration   | Leave required field empty  | Registration rejected             |
| T03     | Login          | Enter correct credentials   | Login successful                  |
| T04     | Login          | Enter incorrect credentials | Error displayed                   |
| T05     | Listing        | Enter valid listing data    | Listing created                   |
| T06     | Listing        | Enter invalid price         | Listing rejected                  |
| T07     | Listing        | View available listings     | Listings displayed                |
| T08     | Application    | Apply for valid listing     | Application stored                |
| T09     | Manage Listing | Edit owned listing          | Changes saved                     |
| T10     | Manage Listing | Delete owned listing        | Listing removed                   |
| T11     | Report         | Submit valid report         | Report stored                     |
| T12     | Database       | Restart application         | Persistent data remains available |

The exact test cases used in the final assessment are documented in the project's testing report.

---

# 30. Version Control

Git was used throughout the project to manage changes to the source code.

Git makes it possible to:

* Track modifications
* Recover previous versions
* Develop features independently
* Share code between team members
* Merge completed features
* Maintain a central project repository

---

# 31. GitHub Collaboration

GitHub was used as the remote repository for the project.

The collaborative workflow involved:

```text
Remote Main Branch
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
Updated Main Branch
```

Team members could work independently before integrating completed changes into the shared version of the application.

---

# 32. Useful Git Commands

Check the current branch:

```bash
git branch
```

Check repository status:

```bash
git status
```

Download information from GitHub:

```bash
git fetch origin
```

Update the current branch:

```bash
git pull
```

Stage changes:

```bash
git add .
```

Create a commit:

```bash
git commit -m "Describe changes"
```

Push changes:

```bash
git push
```

Switch branches:

```bash
git switch branch-name
```

---

# 33. Functional Requirements

The main functional requirements of the application include:

| ID   | Requirement                                                 |
| ---- | ----------------------------------------------------------- |
| FR01 | The system shall allow users to register                    |
| FR02 | The system shall allow registered users to log in           |
| FR03 | The system shall allow users to create listings             |
| FR04 | The system shall allow users to browse listings             |
| FR05 | The system shall allow users to view listing details        |
| FR06 | The system shall allow users to apply for listings          |
| FR07 | The system shall store applications                         |
| FR08 | The system shall allow users to manage their listings       |
| FR09 | The system shall allow listing information to be updated    |
| FR10 | The system shall allow listings to be removed               |
| FR11 | The system shall allow users to report listings or problems |
| FR12 | The system shall store important application data           |
| FR13 | The system shall validate user input                        |
| FR14 | The system shall provide appropriate error feedback         |

---

# 34. Non-Functional Requirements

## Usability

The interface should be understandable and simple enough for users to navigate without specialist technical knowledge.

## Reliability

The application should perform expected operations consistently and handle invalid input without terminating unexpectedly.

## Maintainability

The use of separate classes and object-oriented design makes the system easier to modify and extend.

## Data Integrity

Validation and database operations should prevent invalid or incomplete records wherever possible.

## Performance

Normal operations such as viewing listings, submitting applications, and managing properties should complete without unnecessary delay.

## Portability

Because the application is written in Java, it can potentially operate on different operating systems that support the appropriate Java Runtime Environment.

---

# 35. Object-Oriented Programming

The project demonstrates several important Object-Oriented Programming principles.

## Encapsulation

Data and operations relating to a particular object are grouped within classes.

For example:

```text
User
Listing
Application
Report
```

Each object is responsible for storing and managing information related to its purpose.

## Abstraction

The internal implementation of certain operations is hidden from other components.

For example, the GUI does not need to know every database implementation detail in order to display listings.

## Association

Objects in the system are related to one another.

For example:

```text
User → Listing
User → Application
Listing → Application
User → Report
Listing → Report
```

These relationships represent interactions that also exist in the real-world subletting process.

---

# 36. Design Documentation

The project was designed before and during implementation using software engineering diagrams.

Design documentation includes:

* Class diagrams
* Sequence diagrams
* Activity diagrams
* System architecture diagrams

These diagrams describe the structure and behaviour of the application.

---

# 37. Class Diagram

The class diagram represents the static structure of the system.

Important classes include:

```text
User
Listing
Application
Report
```

The diagram illustrates:

* Class attributes
* Class methods
* Relationships
* Multiplicity
* Associations between system entities

The implemented source code was developed to remain consistent with the major concepts represented in the design.

---

# 38. Sequence Diagrams

Sequence diagrams illustrate how different components communicate while completing a particular operation.

Examples include:

```text
User Login

Create Listing

Apply for Listing

Report Listing
```

A typical sequence is:

```text
User
 ↓
Graphical Interface
 ↓
Application Logic
 ↓
Database
 ↓
Application Logic
 ↓
Graphical Interface
 ↓
User
```

These diagrams were used to understand the order in which system components communicate.

---

# 39. Activity Diagrams

Activity diagrams represent the workflow associated with different system functions.

For example, a listing application process may follow:

```text
Start
 ↓
User Logs In
 ↓
Browse Listings
 ↓
Select Listing
 ↓
View Details
 ↓
Submit Application
 ↓
Validate Application
 ↓
Save Application
 ↓
Display Confirmation
 ↓
End
```

Activity diagrams are useful for understanding decision points and alternative paths through the system.

---

# 40. Security Considerations

Although the project is an academic desktop application, several important security principles are relevant.

These include:

* Validating user input
* Restricting listing management to appropriate users
* Protecting database operations
* Preventing invalid records
* Correctly associating actions with authenticated users
* Avoiding accidental modification of another user's data

A production version of the system would require additional security controls.

---

# 41. Current Limitations

As a university project, the application focuses on demonstrating the core functionality of a subletting management system rather than providing all features expected from a commercial platform.

Potential limitations include:

* Local desktop operation rather than full online deployment
* Limited real-time communication
* Limited moderation functionality
* Limited advanced search and filtering
* Basic image management
* Limited user profile functionality
* No integrated online payment system
* No automated identity verification
* No map integration
* Limited scalability compared with a commercial web-based platform

These limitations provide opportunities for future development.

---

# 42. Future Improvements

Several features could be introduced in future versions.

## Advanced Search

Users could filter listings according to:

* Location
* Price range
* Availability dates
* Property type
* Number of rooms

## Messaging

A messaging system could allow applicants and listing owners to communicate directly.

## Notifications

Notifications could be generated when:

* An application is received
* An application is accepted
* An application is rejected
* A listing changes

## Map Integration

A map service could display the exact or approximate location of available properties.

## Improved Image Management

Future versions could support:

* Multiple images
* Image galleries
* Image compression
* Image previews

## Online Deployment

The application could be converted into a web or mobile application so users could access it remotely.

## Improved Authentication

Future versions could introduce:

* Password hashing
* Password reset functionality
* Email verification
* Multi-factor authentication

## Administration Interface

An administrator account could provide functionality for:

* Reviewing reports
* Removing inappropriate listings
* Managing users
* Monitoring activity

## Improved Application Management

Listing owners could have additional tools for comparing applicants and managing application statuses.

---

# 43. Troubleshooting

## Application Does Not Compile

Run:

```bash
mvn clean compile
```

Review the error output and verify that Java 17 is configured.

---

## Maven Dependencies Are Missing

In IntelliJ IDEA, reload the Maven project or run:

```bash
mvn clean install
```

---

## Tests Do Not Run

Run:

```bash
mvn test
```

Check that the test files are located under:

```text
src/test/java
```

and that the appropriate JUnit dependency is included in `pom.xml`.

---

## Database Does Not Load

Check:

* The database file location
* JDBC configuration
* Database connection code
* File permissions
* SQLite JDBC dependency

---

## Images Do Not Display

Check that:

* The image file exists.
* The image path is correct.
* The application has access to the file.
* The path stored in the listing matches the actual image location.

---

## Changes from GitHub Are Missing

Run:

```bash
git fetch origin
```

and inspect the remote branch.

If appropriate, update the local branch using:

```bash
git pull
```

Always make sure important local changes have been committed before performing operations that overwrite local files.

---

# 44. Project Documentation

The project documentation may include:

```text
README.md
Project report
Testing report
Test designs
Test results
Screenshots
Class diagram
Sequence diagrams
Activity diagrams
System architecture diagram
Source code
Database
GitHub repository
```

Together, these materials document the complete software development process from design through implementation and testing.

---

# 45. Academic Context

This application was developed as an academic software engineering project.

The purpose of the project is not only to produce a functioning application but also to demonstrate understanding of the software development lifecycle.

The project therefore incorporates:

```text
Requirements Analysis
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

The final system demonstrates how theoretical software engineering concepts can be applied to the development of a practical desktop application.

---

# 46. Contributors

This project was completed collaboratively by the project team.

### Team Members

* Star Kudzai
* Ilayda
* Other team member(s), if applicable

Each team member contributed to areas of the project including software design, implementation, database development, testing, debugging, documentation, and version control.

---

# 47. Repository

The source code for this project is maintained using Git and GitHub.

```text
Repository:
<ADD GITHUB REPOSITORY LINK HERE>
```

---

# 48. Screenshots

Screenshots of the completed application can be added to this section.

Suggested screenshots include:

### Login Screen

```text
[Add Login Screen Screenshot]
```

### Registration Screen

```text
[Add Registration Screen Screenshot]
```

### Main/Home Screen

```text
[Add Main Screen Screenshot]
```

### Browse Listings

```text
[Add Browse Listings Screenshot]
```

### Listing Details

```text
[Add Listing Details Screenshot]
```

### Create Listing

```text
[Add Create Listing Screenshot]
```

### Manage Listings

```text
[Add Manage Listings Screenshot]
```

### Application Functionality

```text
[Add Application Screenshot]
```

### Report Functionality

```text
[Add Report Screenshot]
```

---

# 49. Conclusion

The **Sublet Management System** demonstrates the development of a complete Java desktop application using object-oriented programming, graphical user interfaces, database integration, software testing, and version control.

The application provides the main functionality required for a basic subletting platform, including user management, accommodation listings, applications, listing management, and reporting.

Developing the system also provided practical experience with the complete software development lifecycle, including analysing requirements, designing system components, implementing functionality, integrating a database, identifying and correcting errors, testing individual and integrated components, collaborating through GitHub, and documenting the completed system.

Although additional functionality would be required before deploying the application as a commercial subletting service, the current implementation provides a solid foundation that can be extended with additional features in the future.
