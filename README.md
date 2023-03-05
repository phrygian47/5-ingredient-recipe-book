# Overview


This app is a simple 5 ingredient recipe book. You may add recipes to the book, view current recipes, edit current recipes, or delete those recipes. All of these recipes are
stored in a cloud database using Firebase RealtimeDatabase. 


This software was written with the intent of further increasing my proficiency in mobile app devlopment, and starting a venture into learning cloud databases and increasing
my knowledge and proficiency. As an aspiring software engineer I believe this was a good exercise in learning a skill that will be useful in the field. 

The following is a link to a video demonstration of the app.

[Software Demo Video](https://youtu.be/COj2ms09kKQ)

# Cloud Database

For this project I utilized Firebase's Realtime Database. This was the easiest to set up and it integrated well with Kotlin. To utilize the Firebase Realtime Databse all that was
needed was to add a buildscript and a few repositories into the module level gradle file, and a few plug ins into the app level gradle file. I did run into some issues 
syning this gradle file at first, the solution was to change the RepositoresMode in the gradle.settings file to PREFER_SETTINGS.

This database uses a simple JSON tree that stores recipe ID's. These ID's store the object information for each recipe including the name, id, and all ingredients involved. 
Firebase stores information as JSON objects in a tree structure. As objects are stored into the database it becomes a node in that tree with a unique key.

# Development Environment

For this project I used Android Studio and programmed using Kotlin for android. I used a few plug ins to aid in interfacing with the database, these plug ins were "implementation platform('com.google.firebase:firebase-bom:31.2.2')"
"implementation 'com.google.firebase:firebase-analytics-ktx'", and "implementation 'com.google.firebase:firebase-database-ktx:20.1.0'". These plug ins give us access
to a few functions within Android Studio that allows us to easily interface with the realtime database.

# Useful Websites

The Google Firebase website has a lot of usefule information regarding Firebase and how to use it on different platforms.
- [Firebase](https://firebase.google.com/docs/firestore)

This is a nice intro to what cloud databases are and how they work.
- [MongoDB](https://www.mongodb.com/cloud-database)

# Future Work

I am happy with this app but there could be some additions or fixes that make it all the better

- Fix edit dialog. 
This is a small issue but it bothers me. Since the edit dialog gets its data from the previous activity, if you edit a recipe, then go to edit the
same recipe without closing the current activity, it will still contain the previous information rather than the new edited information, even though it is updated
in the information table and the cloud.

- Add shopping list.
One functionality I would like to add is being able to create a shopping list with the selected recipes.

- Authentication.
Add authentication so people cannot mess with or edit your precious recipes.
