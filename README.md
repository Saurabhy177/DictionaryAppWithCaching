Clean Architecture Dictionary App Code
======================================

This project has been developed with the help of youtube tutorial:<br>
https://www.youtube.com/watch?v=Mr8YKDh3li4&list=PLOf92TQmqSoj0XvDx38sUGS-fO5sEcqrA&index=1&t=3532s

Introduction
------------

DictionaryApp is designed using MVVM clean architecture using Jetpack Compose in a single module. <br>
Room DB is used for caching dictionary searched words locally. <br>
Retrofit library is used for interacting with dictionary api. <br>

Dictionary API:<br>
https://api.dictionaryapi.dev/

Special feature: <br>
a) Clean MVVM architecture <br>
b) Room DB for caching words info locally <br>
c) Retrofit for fetching word info using dictionary api 
d) Dagger Hilt for dependency injection <br>
e) Design using compose <br>

This app contains three layers:<br>
a) <b>Data Layer:</b> This layer contains all the data related logic (databases, api calls and 
  implementations, etc).<br>
b) <b>Domain Layer:</b> This layer contains only the business logic.<br>
c) <b>Presentation Layer:</b> The layer contains all the UI logic and it is further divided into
  components. It contains the screens, states and view models. <br>

This app lets you:<br>
a) Search any word in dictionary.<br>
b) Cache word info locally that is shown in case of no internet.<br>
c) Show list of meanings of any word.<br>


Pre-requisites
--------------

You need to know:

* Building a basic user interface (UI) for an Android app, 
  using an activity, fragments, and views.
* Navigating between fragments and using Safe Args (a Gradle plugin) 
  to pass data between fragments.
* View models, flows and StateFlow and SharedFlow.
