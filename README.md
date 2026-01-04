# Airline-Reservation-System
Will allow the user to login to their account, book flights and access the flight information.

(Desktop Based) The system includes features such as:
- Flight tracking based on arrival and departure times.
- Browsing available flights via their respective departure dates and destinations.
- Viewing and managing (canceling) reservations.
This project is being implemented using Java for the backend logic, JavaFX for the graphical user interface, and PostgreSQL for managing the database. I am utilizing JDBC to connect Java to PostgreSQL, enabling user authentication, flight bookings, and reservation management.

javac -cp ".;libraries\postgresql-42.7.8.jar" scripts/*.java  -  Compiles all files to allow access to PostgreSQL
java -cp ".;libraries\postgresql-42.7.8.jar;scripts" Main   - executes the java file called "Main" which is under the scripts folder


