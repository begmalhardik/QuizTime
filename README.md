# 📱 Quiz App (Android - Java)

A modern and interactive Quiz Application built using **Java, Fragments, and ViewBinding**, following clean coding practices and a scalable architecture approach.

---

## 🚀 Features

* 🎯 5 Multiple Choice Questions (MCQs)
* ✅ Instant answer validation (Correct/Incorrect)
* 🎨 Visual feedback:

  * Correct answer → 🟢 Green
  * Wrong answer → 🔴 Red + correct highlighted
* 🔒 Answer locking after submission
* 📊 Dynamic Progress Bar
* 🔄 Smooth navigation between questions
* 🏁 Result screen with:

  * Score
  * Congratulation message
  * Restart option

---

## 🧱 Architecture

This project follows a **clean and scalable structure**:

```text
Single Activity
    ├── Fragments (UI Layer)
    │     ├── StartQuizFragment
    │     ├── QuizFragment
    │     └── ResultFragment
    │
    ├── ViewModel (Optional Upgrade)
    └── Repository (Optional Upgrade)
```

* **Single Activity + Multiple Fragments**
* Logic separated for maintainability
* Easily extendable to MVVM

---

## 🛠️ Tech Stack

* **Language:** Java
* **UI:** XML + ConstraintLayout
* **Architecture:** Single Activity + Fragments
* **View Binding:** Enabled
* **State Handling:** Local Fragment State

---

## 🎮 Quiz Flow

```text
Start Screen
     ↓
Quiz Screen (5 Questions)
     ↓
Submit Answer
     ↓
Feedback (Color-based)
     ↓
Next Question
     ↓
Result Screen
```

---

## 🧠 Key Learnings

* Fragment lifecycle management
* ViewBinding usage
* UI state handling
* Clean code practices
* User interaction flow design

---

## 🔮 Future Improvements

* MVVM with LiveData / StateFlow
* Question API integration
* Timer-based quiz ⏱️
* Animations & sound effects
* Score sharing feature

---

## 📸 Screens

* Start Screen
* Quiz Screen
* Result Screen

---

## 👨‍💻 Author

**Hardik Kumar Begmal**
Android Developer | Aspiring Software Engineer

---

## ⭐ If you like this project

Give it a ⭐ on GitHub and share your feedback!
