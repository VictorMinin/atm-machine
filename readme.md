    #Description
This project is an ATM simulation that is designed to process debit and
credit cards from a virtual atm machine. It utilizes a MySQL database to
process and save user requests.

    #How To Run
Database Setup:

Download and install MySQL Workbench.
During installation, set up a local database.
In the MySQL client, add a connection using the provided credentials.
Open ATMDB.mwb, go to Database -> Forward Engineer, and follow the prompts.
Create db.properties File:

In the Resources folder, create a file named db.properties.

Copy the contents below into the file and enter your MySQL username and password.

driver=com.mysql.cj.jdbc.Driver
url=jdbc:mysql://localhost:3306/atm_db
username= {enter username}
password= {enter password}

Run from terminal:
Ensure the terminal's working directory is ATM_Project and run the following commands:

mvn clean install

This will create an executable file named atm-1.0.jar in the target directory
To run enter command below:

java -jar target/atm-1.0.jar

    #Installations
OpenJDK Version: 21.0.1
Apache Maven Version: 3.6.3
MySQL Workbench
MySQL Database

    #Dependencies
Log4j API:
Group ID: org.apache.logging.log4j
Artifact ID: log4j-api
Version: 2.21.1
Log4j Core:

Group ID: org.apache.logging.log4j
Artifact ID: log4j-core
Version: 2.21.1
MyBatis:

Group ID: org.mybatis
Artifact ID: mybatis
Version: 3.5.15
MySQL Connector/J:

Group ID: mysql
Artifact ID: mysql-connector-java
Version: 8.0.33
Jackson Databind:

Group ID: com.fasterxml.jackson.core
Artifact ID: jackson-databind
Version: 2.16.1

    #Contributors
Saranya Rajendran
Jesse Lunger
Artem Halushka
Victor Minin
