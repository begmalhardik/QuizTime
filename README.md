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
<img src="https://github.com/user-attachments/assets/0e915ef8-1b40-4d82-8c88-499cde9be30d" width="300" height="auto">
<img src="https://github.com/user-attachments/assets/0f983cf6-4929-43cd-89a1-76e29f17407f" width="300" height="auto">

* Quiz Screen
<img src="https://github.com/user-attachments/assets/83d01098-a0e6-4cbf-afdc-2fe9b728cf50" width="300" height="auto">
<img src="https://github.com/user-attachments/assets/bea1a2de-1763-45f9-b734-70cb3747d717" width="300" height="auto">
<img src="https://github.com/user-attachments/assets/14c34e14-597b-4784-bc19-eb123d95bde7" width="300" height="auto">

* Result Screen
<img src="https://github.com/user-attachments/assets/8212dc32-eafc-4be7-8f65-92311e25e418" width="300" height="auto">
<img src="https://github.com/user-attachments/assets/97b22b68-64a7-4f15-8be5-63b7e4ea8b6e" width="300" height="auto">

---

## 👨‍💻 Author

**Hardik Kumar Begmal**
Android Developer | Aspiring Software Engineer

---

## ⭐ If you like this project

Give it a ⭐ on GitHub and share your feedback!
