# Smart Routine Automator

## 📌 Project Overview
Smart Routine Automator is a Java-based desktop application designed to automate repetitive tasks on Windows systems. Users can schedule routines such as:
- Opening applications
- Launching websites
- Displaying reminders
- Executing system commands (shutdown, restart, Wi-Fi control)

The application uses **JavaFX** for UI and **Quartz Scheduler** for background task execution. It is designed with **Object-Oriented Programming (OOP) principles** to ensure modularity, scalability, and maintainability.

---

## 🎯 Problem Statement
Windows users frequently perform repetitive tasks manually, such as opening the same applications or setting reminders. This wastes time and reduces productivity. There is a need for a customizable, lightweight automation tool that simplifies task scheduling.

---

## 👥 Target Users
- **Students** → class reminders, opening study apps
- **Professionals** → schedule daily workflows, reports
- **General PC users** → automate shutdown, music, browser

---

## 🛠️ Tech Stack
- **Java 17+**
- **JavaFX** (UI)
- **Quartz Scheduler** (Task scheduling)
- **SQLite/MySQL** (Data persistence)
- **GitHub** (Version control)

---

## 🔑 OOP Concepts Applied
- **Encapsulation** → Data hiding in classes with getters/setters  
- **Inheritance** → Specialized action classes extend base `Action`  
- **Polymorphism** → `performAction()` behaves differently per subclass  
- **Abstraction** → Abstract `Action` class defines generic behavior  
- **Composition** → `Routine` class is composed of `Trigger` + `Action`  

---

## 📊 UML Class Diagram
```text
