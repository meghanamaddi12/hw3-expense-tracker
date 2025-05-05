# Expense Tracker – CS520 Spring 2025 HW3

This project is an extension of the previous Homework 2 "Expense Tracker" application. It is a Java Swing-based GUI app that allows users to track daily expenses. The application uses the **Model-View-Controller (MVC)** architecture and showcases software engineering principles including **modularity**, **extensibility**, **debuggability**, and **testability**.


##  Features

###  Add Transaction
- Users can specify an amount and category.
- On clicking **Add Transaction**, a new entry is added to the list and the total cost is updated.

###  Filter Transactions
- Users can filter transactions by:
    - **Amount**
    - **Category**
- Matching entries are highlighted.
- **Clear Filter** resets the view.


##  Homework 3 Enhancements

###  Undo Functionality
- Supports removing the **last added transaction** or a **selected row** from the table.
- Transaction list and total cost update immediately.
- UI gracefully handles empty list and invalid selection.
- Designed with good UI principles and clean MVC separation.

#### How to Use:
- Add a few transactions using the form.
- Click the "Undo" button to remove the most recent entry.
- The transaction table and total amount update live.

###  Export to CSV
- Users can export the current transaction list to a `.csv` file.
- Features:
    - Input validation for filename
    - Header row: `Serial, Amount, Category, Date`
    - Each transaction appears on a new line
- Designed using the **Open-Closed Principle** with no magic strings.

##  Design Principles & Patterns

- **Model-View-Controller (MVC)**: Clean separation of model, view, and controller logic.
- **Strategy Pattern**: Used to allow flexible filtering by amount or category.
- **Open-Closed Principle**: CSV export was added without modifying core logic classes.
- **Encapsulation & Immutability**: Transactions are immutable; model is protected.


##  How to Compile & Run

### ▶ Using Terminal:
```bash
cd src
javac ExpenseTrackerApp.java
java ExpenseTrackerApp
```

### ▶ Using IntelliJ

- Open the project in **IntelliJ IDEA**.
- Locate `ExpenseTrackerApp.java` in the `src/` folder.
- Right-click the file → **Run 'ExpenseTrackerApp.main()'**
- You should see the **Expense Tracker GUI** window.


###  Testing

- Unit tests are located in the `test/` folder.
- The test suite includes:
    - Tests for **adding and removing transactions**
    - A new test for **removing a non-existent transaction**
- Tests can be run using **JUnit** in IntelliJ or through **command-line configuration**.


###  Javadoc

- Javadoc is generated using **Tools → Generate JavaDoc** in IntelliJ.
- It includes documentation for all **public classes and methods**.
- The output is stored in the `jdoc/` folder.


###  Java Version

This project runs successfully with:
openjdk 23.0.2 2025-01-21

Ensure you have **Java 17 or higher** installed to avoid compatibility issues.


###  Git Log

- Git commit history is stored in `gitlog.txt`.
- Commits are:
    - **Atomic**
    - **Descriptive**
    - Include **author, timestamp, and message**

