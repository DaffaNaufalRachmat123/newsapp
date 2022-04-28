
## Architecture

- MVVM
- Modularization

## Unit Testing

I've been write some Unit Test in this application with Mockito , Koin and ViewModel

- Fetch News API Top Headlines

## Library

- OkHttp
- Retrofit
- Kotlin Coroutines
- RxJava
- ViewBindingKtx DylanCai
- Gson
- etc

## Modularization

To keep the code in this application and easily to read for other people and maintainable.
I separate the features with different modules

- app - contains all of the config and all of modules
- core - contains the core of this app like Networking and User Settings
- common - contains custom widget and custom value or custom type
- features - contains the feature home news and splash screen.
- Navigation - contains string that navigate between other activities
- UiResources - contains resources of this application like drawable , colors , string ,etc.
