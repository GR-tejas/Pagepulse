# PagePulse - Webpage Analyzer

## Overview

PagePulse is a Spring Boot web application that analyzes a webpage based on a user-provided URL. The application retrieves useful information from the webpage and displays the analysis results through a simple web interface.

The application provides the following information:

- HTTP Status Code
- Response Time
- Page Title
- Meta Description
- Number of H1 Tags
- Number of Images Missing Alt Text
- Approximate Word Count

The application also handles common error scenarios such as invalid URLs, non-HTML webpages, connection failures, and request timeouts.

---

## Technologies Used

- Java
- Spring Boot
- Maven
- Jsoup
- HTML
- CSS
- JavaScript
- jQuery

---

## Project Setup

### Prerequisites

- Java JDK (Version 21 or later)
- Maven
- Git
- Visual Studio Code (or any Java IDE)

### Running the Application

1. Clone the repository.

```bash
git clone <repository-url>
```

2. Open the project in Visual Studio Code or your preferred IDE.

3. Allow Maven to download all required dependencies.

4. Run the Spring Boot application.

5. Open your browser and navigate to:

```
http://localhost:8080
```

---

# API Contract

### Endpoint

```
POST /analyze
```

### Request Body

```json
{
    "url": "https://www.example.com"
}
```

### Successful Response

```json
{
    "httpStatus": 200,
    "responseTime": 125,
    "title": "Example Domain",
    "metaDescription": "Example description",
    "h1Count": 1,
    "missingAltImages": 0,
    "wordCount": 532,
    "error": null
}
```

### Error Response

```json
{
    "error": "Invalid URL!"
}
```

Possible error messages include:

- Invalid URL!
- The website took too long to respond!
- The URL does not point to a HTML webpage!
- Unable to connect to the website or analyze the webpage!

---

# Design Decisions

## 1. Modular Analysis Methods

Instead of implementing all webpage analysis logic inside a single method, each analysis task has been separated into its own helper method (such as retrieving the page title, meta description, H1 count, image alt-text count, and word count).

This approach follows the Single Responsibility Principle, making the code easier to read, maintain, debug, and extend. Future modifications to a specific analysis can be made without affecting the rest of the application.

---

## 2. Separation of Controller and Service Layers

The application follows a layered architecture by separating the Controller and Service components.

The Controller is responsible only for handling incoming HTTP requests and returning responses, while all webpage analysis logic is implemented within the Service layer.

This separation improves code organization, readability, maintainability, and makes the business logic easier to test independently.

---

## 3. Single HTTP Request Design

The application performs only one HTTP request for each webpage analysis.

The HTTP response is reused to obtain both the status code and the parsed HTML document, avoiding multiple requests to the same webpage.

This approach reduces unnecessary network overhead and improves the overall efficiency of the application.

---

## Testing

The application includes automated JUnit test cases covering:

- Successful webpage analysis
- Invalid URL handling
- Non-HTML webpage handling

---

## Future Improvements

Given additional development time, the following enhancements could be implemented:

- Additional SEO metrics
- Support for asynchronous analysis
- Improved frontend UI and responsiveness
- Caching frequently analyzed webpages
- More comprehensive unit and integration tests