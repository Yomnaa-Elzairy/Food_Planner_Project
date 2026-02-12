# 🍽️ Meal Planner Android Application

## 📖 Description

Meal Planner is an Android application designed to help users explore different meals, save their favorite recipes, and plan meals for specific days. The application was developed as part of an Android course project, focusing on applying real-world Android development practices and clean architectural patterns.

The app is built using **Java** and follows the **MVP (Model–View–Presenter)** architecture within a **Single Activity** structure that hosts multiple Fragments.

---

## ⚙️ Technical Stack

* **Programming Language:** Java
* **Architecture:** MVP
* **Asynchronous Handling:** RxJava3
* **API Communication:** Retrofit
* **Local Storage:** Room Database

---

## 🚀 Application Features

* Display meals retrieved from a remote API
* Search meals by name or ingredients
* View detailed meal information
* Add and remove meals from Favorites
* Plan meals for selected days
* Store favorite and planned meals locally using Room
* Handle loading, empty, and error states gracefully
* Support offline access for saved data

---

## 🗂️ Architecture Overview

The project is structured into clear layers to separate responsibilities:

* **Data Layer:**

  * Handles API calls and database operations
  * Contains data models, data sources, and repositories

* **Domain Layer:**

  * Contains business models

* **Presentation Layer:**

  * Contains Activities, Fragments, Presenters, and Views

---

## ▶️ Running the Project

1. Clone the repository:

```
git clone https://github.com/Yomnaa-Elzairy/Food_Planner_Project
```

2. Open the project in **Android Studio**

3. Sync the Gradle files

4. Run the application on an emulator or a physical Android device

---



