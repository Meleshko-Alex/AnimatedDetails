# Android Project

This Android project follows the **MVVM architecture** and leverages modern tools and libraries to provide a smooth and efficient user experience.

## Key Features

- **MVVM Architecture**: The project follows the Model-View-ViewModel architecture to separate the UI from the business logic, ensuring better scalability and maintainability.
- **Hilt for Dependency Injection**: Hilt is used for managing dependencies.
- **Retrofit2 for Networking**: Retrofit2 is used for network operations.
- **Coil for Image Loading**: Coil is used to load and display images efficiently.
- **Jetpack Compose for UI**: The app uses Jetpack Compose, a modern and declarative UI toolkit, to build the user interface.
- **Animations**: Animations are implemented using Jetpack Compose for a seamless and responsive experience.
- **No Navigation Library**: For simplicity, the navigation library was not added.
- **Dynamic Category Management**: The app displays four categories of images by default: "dogs", "cats", "birds", and "flowers". You can easily modify the number or type of categories by updating the `categories` array in the `HomeViewModel` class. For example:
  
  categories = listOf("dogs", "cats", "birds", "flowers", "nature", "sky")
- **Smooth Build Variant**: For the best performance, it's recommended to build the project using the "smooth" build variant, which disables the debug mode.

## Notes

- The app focuses on functionality, so design and color schemes are kept minimal for simplicity.
- Feel free to modify the categories to suit your needs.

## Enjoy the app!
