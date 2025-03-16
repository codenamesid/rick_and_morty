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

- Jetpack Compose: For building the UI.
- Dagger Hilt: For dependency injection.
- Retrofit: For network requests.
- MockK: For mocking in tests.
- Kotlin Coroutines: For asynchronous programming.


## Project Structure

- `app/src/main/kotlin/com/example/rickandmorty/model/`: Contains the data classes and repository.
- `app/src/main/kotlin/com/example/rickandmorty/viewmodel/`: Contains the ViewModel classes.
- `app/src/main/kotlin/com/example/rickandmorty/view/composables/`: Contains the composable functions for the UI.
- `app/src/test/kotlin/com/example/rickandmorty/`: Contains the unit tests.

  
 
