# fixtureRetriever

fixtureRetriever is a system for logging and analyzing user activity, consisting of a Java-based backend web service and a companion Android app. The backend uses MongoDB for data storage and provides analytics on request data, such as top teams, date ranges, and device models. The Android app interacts with the backend for data collection and visualization.

## Table of Contents

- [Features](#features)
- [Architecture](#architecture)
- [Backend Setup](#backend-setup)
- [Android App Setup](#android-app-setup)
- [Contributing](#contributing)
- [License](#license)

## Features

- Log user activity and requests from the Android app
- Store and manage data in MongoDB
- Analytics endpoints: top teams, date ranges, device models, and more
- Android app for data collection and visualization

## Architecture

```
Android App <----> Java Web Service (Spring Boot) <----> MongoDB
```

- **Backend:** Java (TomEE 10.1.34, Jakarta EE), Gradle, MongoDB
- **Frontend:** Android (Java)

## Backend Setup

### Prerequisites

- Java 17+
- Apache TomEE 10.1.34
- Gradle or Maven
- MongoDB (local or remote)

### Installation

1. Clone the repository:
   ```sh
   git clone https://github.com/RishabhDev42/fixtureRetriever.git
   cd fixtureRetriever/backend
   ```

2. Configure MongoDB connection in `application.properties`:
   ```
   spring.data.mongodb.uri=mongodb://localhost:27017/fixtureRetriever
   ```

3. Build and run the backend:
    - Using Gradle:
      ```sh
      ./gradlew bootRun
      ```

4. The backend will be available at `http://localhost:8080`.

## Android App Setup

### Prerequisites

- Android Studio
- Android device or emulator

### Installation

1. Open the `android-app` folder in Android Studio.
2. Update the backend API URL in the app's configuration if needed.
3. Build and run the app on your device or emulator.

## Contributing

1. Fork the repository
2. Create a new branch (`git checkout -b feature/your-feature`)
3. Commit your changes
4. Push to your branch and open a pull request

## License

This project is licensed under the MIT License.
