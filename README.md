# Airline-Reservation-System
Will allow the user to login to their account, book flights and access the flight information.

(Desktop Based) The system includes features such as:
- Flight tracking based on arrival and departure times.
- Browsing available flights via their respective departure dates and destinations.
- Viewing and managing (canceling) reservations.
This project is being implemented using Java for the backend logic, JavaFX for the graphical user interface, and PostgreSQL for managing the database. I am utilizing JDBC to connect Java to PostgreSQL, enabling user authentication, flight bookings, and reservation management.

# Can now just double click run.bat in the file explorer to run my program!

javac -cp ".;libraries\postgresql-42.7.8.jar" scripts/*.java  -  Compiles all files to allow access to PostgreSQL
java -cp ".;libraries\postgresql-42.7.8.jar;scripts" Main   - executes the java file called "Main" which is under the scripts folder

javac --module-path C:\openjfx-21.0.9_windows-x64_bin-sdk\javafx-sdk-21.0.9\lib --add-modules javafx.controls scripts\LoginApp.java - these allow u to run the JavaFX UI for whatever script u want (Replace LoginApp)
java --module-path "C:\openjfx-21.0.9_windows-x64_bin-sdk\javafx-sdk-21.0.9\lib" --add-modules javafx.controls -cp scripts LoginPage
(May need to compile all java files at once)!




