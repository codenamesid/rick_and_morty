# Rick and Morty Character Search

This project is an Android application that allows users to search for characters from the Rick and Morty series. It follows the MVVM (Model-View-ViewModel) architecture pattern and uses Jetpack Compose for the UI.

## Features

- Search for characters by name
- Display character details
- Handle network errors gracefully

## Architecture

The project follows the MVVM architecture pattern:

- **Model**: Contains the data classes and repository for fetching data from the API.
- **ViewModel**: Holds the UI-related data and business logic. It uses `StateFlow` to manage and expose the state to the View.
- **View**: Composable functions that observe the `StateFlow` properties from the ViewModel and update the UI accordingly.

## Libraries Used

- [Jetpack Compose](https://developer.android.com/jetpack/compose) - For building the UI.
- [Dagger Hilt](https://dagger.dev/hilt/) - For dependency injection.
- [Retrofit](https://square.github.io/retrofit/) - For network requests.
- [MockK](https://mockk.io/) - For mocking in tests.
- [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) - For asynchronous programming.

## Getting Started

### Prerequisites

- Android Studio
- Kotlin 1.5+
- Gradle 7.0+

### Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/yourusername/rick-and-morty-character-search.git
    ```
2. Open the project in Android Studio.
3. Sync the project with Gradle files.

### Running the App

1. Connect an Android device or start an emulator.
2. Click on the "Run" button in Android Studio.

## Testing

The project includes unit tests for the ViewModel and Repository layers. To run the tests:

1. Open the `CharacterViewModelTest` and `CharacterRepositoryTest` files.
2. Right-click and select "Run 'Tests in ...'".

## Project Structure

- `app/src/main/kotlin/com/example/rickandmorty/model/`: Contains the data classes and repository.
- `app/src/main/kotlin/com/example/rickandmorty/viewmodel/`: Contains the ViewModel classes.
- `app/src/main/kotlin/com/example/rickandmorty/view/composables/`: Contains the composable functions for the UI.
- `app/src/test/kotlin/com/example/rickandmorty/`: Contains the unit tests.

## Contributing

Contributions are welcome! Please open an issue or submit a pull request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
