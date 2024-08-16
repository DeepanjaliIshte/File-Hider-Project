# File-Hider-Project

<h6>The File Hider project is a Java-based application that allows users to securely hide and unhide files on their system. Users must register and authenticate using an OTP sent to their email. Once authenticated, they can manage their files through a simple console interface. The application securely stores file data in a MySQL database, allowing users to hide files by removing them from the file system and restoring them later when needed.</h6>

<h3>Maven Configuration (pom.xml)</h3>
<h4>Purpose:</h4>
<h6>Configures the project's build settings, dependencies, and other properties using Maven.<br>
Manages the external libraries required for database connectivity, email services, and other functionalities.</h6>

<h4>Key Dependencies:<h4>
<h5>mysql-connector-java: </h5><h6>Used for connecting to the MySQL database.</h6>
<h5>javax.mail:</h5> <h6>Used for sending emails via the JavaMail API.</h6>
<h5>Usage: </h5><h6>Ensures that all necessary libraries are available and properly configured for the project.</h6>
  
------------------------------------------------------------------------------------------------------------------------------------------------------------------------

<h2>1. User Model (User.java)</h2>
<h4>Purpose:</h4>
<h6>Represents a user in the system with basic attributes like name and email.<br>
This class encapsulates the data related to a user and provides methods to access and modify this data.</h6>
<br>
<h4>Attributes:</h4>
<h5>String name:</h5><h6> Stores the name of the user.</h6>
<h5>String email:</h5><h6> Stores the email of the user.</h6>
<br>
<h4>Key Methods:</h4>
<h5>getName():</h5> <h6>Returns the name of the user.</h6>
<h5>setName(String name):</h5><h6> Sets the user's name.</h6>
<h5>getEmail():</h5> <h6>Returns the email of the user.</h6>
<h5>setEmail(String email):</h5> <h6>Sets the user's email.</h6>
<h5>Usage:</h5> <h6>This model is used throughout the application to create user objects and interact with user data.</h6>

------------------------------------------------------------------------------------------------------------------------------------------------------------------------

<h3>2. Data Model (Data.java)</h3>
<h4>Purpose:</h4>
<h6>Represents file metadata in the system, including its ID, filename, file path, and associated user's email.<br>
This model is essential for managing file-related operations like hiding and unhiding files.</h6>
<br>
<h4>Attributes:</h4>
<h5>int id:</h5><h6> Unique identifier for the file record in the database.</h6>
<h5>String filename: </h5><h6>Name of the file.</h6>
<h5>String path: </h5><h6>Path where the file is stored.</h6>
<h5>String email:</h5> <h6>Email of the user who owns the file.</h6>
<br>
<h4>Key Methods:</h4>
<h5>getId(): </h5><h6>Returns the ID of the file.</h6>
<h5>setId(int id):</h5><h6> Sets the ID of the file.</h6>
<h5>getFilename():</h5> <h6>Returns the filename.</h6>
<h5>setFilename(String filename):</h5><h6> Sets the filename.</h6>
<h5>getPath():</h5><h6> Returns the file path.</h6>
<h5>setPath(String path):</h5><h6>Sets the file path.</h6>
<h5>getEmail():</h5> <h6>Returns the email associated with the file.</h6>
<h5>setEmail(String email):</h5> <h6>Sets the email associated with the file.</h6>
<h5>Usage:</h5> <h6>This model is used to create objects representing files and interact with the database for file operations.</h6>

------------------------------------------------------------------------------------------------------------------------------------------------------------------------

<h3>3. Database Connection (MyConnection.java)</h3>
<h4>Purpose:</h4>
<h6>Manages the connection to the MySQL database used by the application.<br>
Ensures that database connections are properly opened and closed, preventing resource leaks.</h6>
<br>
<h4>Key Methods:</h4>
<h5>getConnection():</h5><h6> Establishes and returns a connection to the database.<br>
Uses JDBC to connect to a MySQL database with the specified credentials.</h6>
<h5>closeConnection():</h5> <h6>Closes the active database connection if it exists.</h6>
<h5>Usage:</h5> <h6>Used by Data Access Objects (DAOs) to perform CRUD operations on the database.</h6>

------------------------------------------------------------------------------------------------------------------------------------------------------------------------

<h3>5. Welcome Screen (Welcome.java)</h3>
<h4>Purpose:</h4>
<h6>Provides the initial user interface (via the console) for logging in or signing up.<br>
Guides users through the process of authentication and account creation.</h6>
<br>
<h4>Key Methods:</h4>
<h5>welcomeScreen():</h5> <h6>Displays options for logging in, signing up, or exiting the application. Handles user input and directs the flow accordingly.</h6>
<h5>login():</h5> <h6>Prompts the user for their email, checks if they exist, and handles OTP-based authentication.</h6>
<h5>signup(): </h5><h6>Prompts the user to create an account by entering their name and email, sends an OTP for verification, and registers the user upon successful OTP validation.</h6>
<h5>Usage:</h5> <h6>This class serves as the entry point for user interaction, determining the flow of the application based on user choices.</h6>

------------------------------------------------------------------------------------------------------------------------------------------------------------------------

<h3>6. User View (UserView.java)</h3>
<h4>Purpose:</h4>
<h6>Provides a user interface for authenticated users to manage their hidden files.<br>
Allows users to view hidden files, hide new files, or unhide existing files.</h6>
<br>
<h4>Key Methods:<h4>
<h5>home():</h5> <h6>Displays a menu for users to choose between viewing, hiding, and unhiding files.<br>
Shows a list of hidden files belonging to the user.<br>
Handles file hiding by accepting a file path, storing the file data in the database, and deleting the original file.<br>
Manages file unhiding by restoring the file from the database to the original path and removing the database record.</h6>
<h5>Usage:</h5> <h6>This class is used after the user has successfully logged in, enabling them to manage their files.</h6>

------------------------------------------------------------------------------------------------------------------------------------------------------------------------

<h3>7. OTP Generation (GenerateOTP.java)</h3>
<h4>Purpose:</h4>
<h6>Generates a random One-Time Password (OTP) for user authentication during login or signup.
Ensures that each OTP is unique and valid for a single session.</h6>
<br>
<h4>Key Method:</h4>
<h5>getOTP():</h5> <h6>Returns a randomly generated 4-digit OTP.</h6>
<h5>Usage:</h5> Used during the login and signup process to authenticate users.


<h3>8. Email OTP Service (SendOTPService.java)</h3>
<h4>Purpose:</h4>
<h6>Sends the OTP to the user's email using JavaMail API.<br>
Facilitates secure authentication by ensuring that only users with access to the registered email can log in or sign up.</h6>
<br>
<h4>Key Method:</h4>
<h5>sendOTP(String email, String genOTP):</h5> <h6>Sends an email containing the OTP to the specified email address.
Configures SMTP properties, authenticates the sender, and sends the email.</h6>
<h5>Usage:</h5><h6> Integrated with the login and signup process to deliver OTPs to users.</h6>

------------------------------------------------------------------------------------------------------------------------------------------------------------------------

<h3>9. User Service (UserService.java)</h3>
<h4>Purpose:</h4>
<h6>Manages business logic related to user operations, particularly saving new users and checking for existing users.</h6>
<br>
<h4>Key Method:</h4>
<h5>saveUser(User user):</h5><h6> Attempts to save a new user to the database. Returns 1 if the user already exists, 0 if the user is successfully saved, and -1 if an error occurs.</h6>
<h5>Usage:</h5><h6>Used during the signup process to register new users in the system.</h6>

------------------------------------------------------------------------------------------------------------------------------------------------------------------------

<h3>10. User DAO (UserDAO.java)</h3>
<h4>Purpose:</h4>
<h6>Interacts directly with the database to perform CRUD operations on user data.<br>
Abstracts database interaction, allowing other parts of the application to handle user data without managing SQL directly.</h6>
<br>
 <h4>Key Methods:</h4>
<h5>isExists(String email): </h5><h6>Checks if a user with the given email already exists in the database. Returns true if the user exists.</h6>
<h5>saveUser(User user): </h5><h6>Inserts a new user record into the database and returns the number of rows affected.</h6>
<h5>Usage:</h5> <h6>Called by the UserService class to handle user-related database operations.</h6>

------------------------------------------------------------------------------------------------------------------------------------------------------------------------

<h3>11. Data DAO (DataDAO.java)</h3>
<h4>Purpose:</h4>
<h6>Handles all database operations related to file data, including storing, retrieving, hiding, and unhiding files.<br>
Ensures that file data is securely managed and that original files are deleted after being hidden.</h6>
<br>
<h4>Key Methods:</h4>
<h5>getAllFiles(String email): </h5><h6>Retrieves all hidden files associated with the user's email. Returns a list of Data objects.</h6>
<h5>hideFile(Data file):</h5> <h6>Saves file data to the database and deletes the original file from the file system.</h6>
<h5>unhide(int id):</h5> <h6>Retrieves the file from the database and restores it to its original location, then deletes the corresponding record from the database.</h6>
<h5>Usage:</h5> <h6>Used by the UserView class to perform file management operations for authenticated users.</h6>

------------------------------------------------------------------------------------------------------------------------------------------------------------------------

<h3>12. Main Entry Point (Main.java)</h3>
<h4>Purpose:</h4>
<h6>Serves as the main entry point of the application, initiating the welcome screen and maintaining the application's loop.</h6>
  <br>
<h4>Key Method:</h4>
<h5>main():</h5><h6> Starts the application by invoking the welcome screen and keeps the application running in a loop.</h6>
<h5>Usage:</h5> <h6>Launches the application and manages its execution flow.</h6>
  
