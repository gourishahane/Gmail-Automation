## Google Calendar Automation Project

## Description:
Google Calendar is a robust web-based calendar application that simplifies scheduling, organizing, and managing events, tasks, and appointments.This project focuses on automating key functionalities of Google Calendar using Selenium, Java, and TestNG.
The project includes verifying navigation, adding, updating, and deleting tasks on Google Calendar to ensure its functionality and accuracy. Additionally, it explores techniques for handling signed Chrome instances and addressing challenges like CAPTCHA during automation.

## Key Features
1. Automated Test Cases:
- Verify Google Calendar homepage URL.
- Navigate the calendar and add tasks.
- Update task details.
- Delete existing tasks and confirm their deletion.
2. Dynamic XPath Usage:
Enhanced locator accuracy and reliability by using dynamic XPaths.
3. JavaScript Executor:
Used JavaScript Executor for interacting with complex elements on the web page.

## Prerequisites
1. System Requirements
- Java 11 or above
- Maven or Gradle for dependency management
- ChromeDriver compatible with the installed Chrome version
2. Dependencies
- Ensure the following dependencies are included in the pom.xml or build.gradle file:
1. Selenium WebDriver
2. TestNG

## Installation and Setup
1. Clone the Repository
git clone https://github.com/username/google-calendar-automation.git  
cd google-calendar-automation  
2. Install Dependencies
Run the following command to install dependencies:
mvn install  
3. Set Up ChromeDriver
- Download and configure ChromeDriver as per your system's Chrome browser version.
- Update the ChromeDriver path in your project setup if required.
4. Running the Tests
1. Configure TestNG XML
2. Execute Tests
Run the tests using Maven:
gradlew test

## Automated Test Cases
1. TestCase01: Verify Calendar Home Page
2. TestCase02: Verify Calendar Navigation and Add Task
3. TestCase03: Verify Task Updation
4. TestCase04: Verify Task Deletion

Project Challenges and Solutions
1. CAPTCHA Handling
- Challenge: CAPTCHA prompts interrupt automation execution.
- Solution: Incorporated Thread.sleep to allow manual CAPTCHA resolution during execution.
2. Dynamic Element Interaction
- Used JavaScript Executor to manipulate elements that were not directly accessible through standard Selenium commands.
  
## Enhancements
1. Logging and Debugging
Added log statements for better traceability and debugging of test execution.

2. Modular Code Structure
Organized test cases in a modular structure for improved readability and maintainability.

3. Reliable Locators
Replaced static locators with dynamic XPaths to handle UI changes effectively.

## Skills Utilized
- Automation Tools: Selenium WebDriver
- Programming: Java
- Test Design: TestNG framework
- Advanced Techniques: JavaScript Executor, dynamic XPath
- Debugging: Logs, breakpoints, error handling

## Contact Information
- Name: Gouri Shahane
- Email: gourishahane@gmail.com
- GitHub: github.com/gourishahane
- LinkedIn: linkedin.com/in/gourishahane
