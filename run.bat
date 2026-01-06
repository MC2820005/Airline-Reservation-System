@echo off
rem ------------------------------
rem Compile all Java files
rem ------------------------------
javac --module-path "C:\openjfx-21.0.9_windows-x64_bin-sdk\javafx-sdk-21.0.9\lib" --add-modules javafx.controls scripts\*.java

rem ------------------------------
rem Run the app with JavaFX + PostgreSQL driver
rem ------------------------------
java --module-path "C:\openjfx-21.0.9_windows-x64_bin-sdk\javafx-sdk-21.0.9\lib" --add-modules javafx.controls -cp "scripts;libraries\postgresql-42.7.8.jar" SearchFlightsPage

pause
