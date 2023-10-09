# SWEP : Study Partner
This Android application is designed to help you being more productive. It provides some proven methods to improve your study sessions.
It is written in Kotlin, using Jetpack Compose and follows best practices such as MVVM architecture, clean architecture principles and SOLID. 

Login             |  Registration
:-------------------------:|:-------------------------:
![Login Screen](https://i.imgur.com/XMhDM9t.png) | ![Registration Screen](https://i.imgur.com/AFtTx9T.png)

Home             |  Pomodoro
:-------------------------:|:-------------------------:
![Home Screen](https://i.imgur.com/ZJftb0W.png) | ![Pomodoro Screen](https://i.imgur.com/TskVWIB.png)

Feynman             |  To-do List
:-------------------------:|:-------------------------:
![Feynman Screen](https://i.imgur.com/Kp921Sj.png) | ![To-Do List](https://i.imgur.com/T5fgxSh.png)


## Features

<ul>
  <li>Clean Architecture: The project is structured following the Clean Architecture pattern, which facilitates the separation of responsibilities and improves code maintainability.</li>
  <li>SOLID Principles: The SOLID principles (Single Responsibility, Open-Closed, Liskov Substitution, Interface Segregation, Dependency Inversion) are applied in the application's design to promote clean, modular, and extensible code.</li>
  <li>Dependency Injection - Dagger-Hilt: Dagger-Hilt is used for dependency injection, simplifying dependency management and enabling better scalability and unit testing of the code.</li>
  <li>Jetpack Compose: The user interface is developed using Jetpack Compose, the modern UI toolkit for Android that makes it easier to create flexible and dynamic user interfaces.</li>
  <li>Firebase Authentication: Firebase Authentication is integrated to provide a secure and reliable authentication system for the application's users.</li>
  <li>Room: Room, the Android persistence library, is used to store habit data in a local database, allowing fast and efficient access to this data.</li>
  <li>Retrofit: Retrofit is used to make calls to a remote API and retrieve tasks-related data, enabling efficient and up-to-date synchronization of information.</li>
  <li>Notifications: The application displays notifications during the Pomodoro study sessions as a reminder.</li>
  <li>WorkManager: WorkManager is used to manage background tasks, ensuring that habits created in airplane mode are automatically saved to the cloud once the device has an internet connection.</li>
  <li>Offline-First: The application is designed following the "Offline-First" approach, meaning that the core functionality is available even when the device is offline. Data is automatically synchronized once a connection is available using WorkManager and the remote API.</li>
</ul>

## Requirements
<ul>
  <li>Android device or emulator running Android 7.0 (API level 24) or above.</li>
</ul>

## Installation

You can download the app from the Google PlayStore here: <a href="https://play.google.com/store/apps/details?id=com.alberto.studycompanion"> SWEP : Study Partner </a>
