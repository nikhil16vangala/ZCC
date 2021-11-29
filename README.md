# ZCC
Zendesk Ticket Viewer

By: Sai Nikhil Vangala

Contact: nikhil16.vangala@gmail.com


README file assists with the installation and usage of the product.


Ticket Viewer is a CLI product that lets users:
* Connect to the Zendesk API
* Request all the tickets for their specific account
* Display them in a list
* Display individual ticket details
* Page through tickets when more than 25 are returned

The product includes:
* Code files: Main.java, Authentication.java, Json.java, Ticket.java
* JUnit Test files: MainTest.java, AuthenticationTest.java, JsonTest.java, TicketTest.java
* Java Library Files: hamcrest-core-1.3.jar, javax.xml.blind.jar, json-simple-1.1.1.jar, junit-4.12.java

Product Installation(Windows Only):
* Download zipped folder from Github to local computer and extract the file and rename it to your convenience.
* Install Java 8: https://www.java.com/en/download/manual.jsp
* Install JDK and Add the JDK Path by finding the location of the JDK bin: https://www.oracle.com/java/technologies/downloads/
* Navigate to Environment Variables:
    1) Control Panel
    2) System and Security
    3) System
    4) Advanced System Settings
    5) Environment Variables
* Edit/Add Environment Variables:
    1) Click on New of "System Variables"
    2) Create an environment variable with the name "user" and value of the respective username.
    3) Create an environment variable with the name "password" and value of the respective password.
    4) Create an environment variable with the name "subdomain" and value of the respective subdomain.
* To use my credentials:
    1) user = zcctestemail@gmail.com
    2) password = zcctest123
    3) subdomain = https://zcctest123.zendesk.com

Running the Product:
* Open up command line terminal and locate into the root product folder.
* Compile the product using: javac -cp .\lib\* .\src\Test\*.java .\src\Code\*.java
* Run the product and execute: java -cp .\lib\*;.\src Code.Menu
* Perform JUnit Verification: java -cp .\lib\*;.\src Test.MainTest
