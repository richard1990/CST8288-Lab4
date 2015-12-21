# CST8288 Lab 4 - JDBC
This was my fourth and final lab assignment in my "Object-Oriented Programming with Design Patterns" course. This lab introduced me to JDBC and is a simple program that demonstrates how JDBC works. Using MySQL Connector/J and MySQL Server, the program allows a book to be added, edited, or deleted from the database. Each book in the database contains an ISBN (randomly generated using Java's UUID class), title, edition, and copyright. The classes are separated into business and data access layers and the program also makes use of the data transfer object design pattern (Title).

I used String fields to hold the username and password for a user within the database (Scott Tiger) just to make things easier in the DataSource class that creates the connection. The SimpleTest class has all the code to test the program, which simply loads the database with a few rows of data, inserts a new row, modifies that new row, and then deletes that new row and displays all the rows.

Note that MySQL Server must be running for this program to work and Oracle's MySQL Connector/J (version 5.1.34 was used with this program) must be used as well.
