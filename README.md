# Website Monitor – Software Engineering Design Project

## Project Overview

This project is a Java implementation of a Website Monitor application for the Software Engineering Design course.

The application allows a user to register website subscriptions. Each subscription contains a website URL, a checking frequency, a communication channel, and stored previous content. The Website Monitor checks whether the website content has changed and creates a notification when a relevant change is detected.

The project was developed step by step through the course exercises. It demonstrates object-oriented design, the Observer Pattern, the Strategy Pattern, unit testing with JUnit, and Docker containerization.

## Main Features

- Register website subscriptions for a user
- Store website URL, frequency, communication channel, and previous content
- Check website updates using mock website content
- Compare website content with different comparison strategies
- Notify observers when a change is detected
- Run the application from the command line
- Run unit tests with JUnit
- Build and run the application in a Docker container

## Important Note About Website Content

This project uses mock website content instead of real HTTP requests.

The method `mockUpCurrentContent()` simulates current website content. This keeps the focus on software design patterns and application structure rather than network programming.

## Design Patterns Used

### 1. Observer Pattern

The Observer Pattern is used for the notification mechanism.

The `WebsiteMonitor` acts as the subject. It keeps a list of observers and notifies them when a website change is detected.

The `NotificationObserver` receives a notification and sends it to the user. In this prototype, sending means printing the notification to the console.

This design separates the monitoring logic from the notification logic.

### 2. Strategy Pattern

The Strategy Pattern is used for website content comparison.

The interface `WebsiteComparisonStrategy` defines the comparison behavior. Different strategy classes implement different ways of checking whether a website changed.

Implemented strategies:

- `ContentSizeComparison`
- `HtmlContentComparison`
- `TextContentComparison`

This makes the application flexible because the comparison algorithm can be changed without changing the main monitoring logic.

## Comparison Strategies

### ContentSizeComparison

Compares only the length of the old and new website content.

Example:

- `"abc"` and `"xyz"` have the same length, so no change is detected.
- `"abc"` and `"abcd"` have different lengths, so a change is detected.

### HtmlContentComparison

Compares the complete HTML content.

Example:

- `<body>Hello</body>` and `<div>Hello</div>` are considered different because the HTML structure changed.

### TextContentComparison

Compares only the visible text content and ignores HTML tags.

Example:

- `<body>Hello</body>` and `<div>Hello</div>` are considered equal because the visible text is the same.

## Project Structure

```text
src/
├── main/
│   └── java/
│       ├── app/
│       ├── enums/
│       ├── gui/
│       ├── model/
│       ├── monitor/
│       ├── observer/
│       └── strategy/
└── test/
    └── java/
        └── strategy/
