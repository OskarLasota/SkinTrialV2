# SkinTrial

Rewriting the original app found here : https://play.google.com/store/apps/details?id=com.frezzcoding.skincare&hl=en_GB

## Tech Stack & Open-Library Sources : 
+ Minimum SDK level 21
+ 100% Kotlin based + Coroutines
+ Dagger2-android
+ JetPack
   + LiveData - notify domain layer data to views.
   + Lifecycle - dispose observing data when lifecycle state changes.
   + ViewModel - UI related data holder, lifecycle aware.
   + Room Persistence - construct database.
   + Jetpack Navigation
+ Firebase Notifications
+ Data Binding
+ Architecture
   + MVVM Architecture (View - DataBinding - ViewModel - Model)
   + Repository pattern
   + One Activity pattern

+ Material Design & Animations
   + LottieFlies
  
+ Retrofit2 & Gson - Also implementing the REST API


## Todo : 

+ JUnit
+ Integration Tests
+ Re-implement notification scheduler feature, need to use broadcast to listen to timezone and user time change
