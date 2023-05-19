# E-Campus
E-Campus is a Java console application for Grading System. It lets teachers and students create and enroll in courses, assign and view marks, and store and secure their data.

## Features

- User registration and login: Teachers and students can register and log in to the application using their username and password.
- Course creation and management: Teachers can create courses and specify course details such as total grade, credit and percentile. They can also enroll students and assign marks to them in each session.
- Score card generation and display: Students can view their enrolled courses and their marks in each session in a score card format.
- Data storage and security: The application uses file I/O operations to store and update user data and course information even after shutting down the application. It also applies object-oriented programming concepts such as encapsulation, inheritance and polymorphism to provide security and facility for the users.

## Installation

To run the application, you need to have Java installed on your system. You can download Java from [here](https://www.java.com/en/download/).

To install the application, you can clone this repository or download the zip file. Then, navigate to the src folder and compile the Main.java file using the following command:

```
javac Main.java
```

To run the application, use the following command:

```
java Main
```

## Usage

When you run the application, you will see a welcome message and a menu with two options: Register and Login. You can choose either option by entering the corresponding number.

If you choose to register, you will be asked to enter your username, password, name, email and role (teacher or student). If your registration is successful, you will see a confirmation message and be redirected to the login menu.

If you choose to login, you will be asked to enter your username and password. If your login is successful, you will see a greeting message and a menu with different options depending on your role.

If you are a teacher, you will see the following options:

- Create course: This option allows you to create a new course by entering the course name, code, credit and total grade. You will see a confirmation message if the course is created successfully.
- View courses: This option allows you to view all the courses that you have created along with their details.
- Enroll student: This option allows you to enroll a student in a course by entering the student's username and the course code. You will see a confirmation message if the enrollment is successful.
- Assign marks: This option allows you to assign marks to a student in a session by entering the student's username, the course code and the session number. You will see a confirmation message if the marks are assigned successfully.
- Delete student: This option allows you to delete a student from a course by entering the student's username and the course code. You will see a confirmation message if the deletion is successful.
- Logout: This option allows you to logout from the application and return to the main menu.

If you are a student, you will see the following options:

- View courses: This option allows you to view all the courses that you have enrolled in along with their details.
- View score card: This option allows you to view your score card for each course that shows your marks in each session along with your percentile.
- Logout: This option allows you to logout from the application and return to the main menu.

You can exit the application at any time by entering 0 in any menu.
