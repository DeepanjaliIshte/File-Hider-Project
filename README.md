# File-Hider-Project

<h6>The File Hider project is a Java-based application that allows users to securely hide and unhide files on their system. Users must register and authenticate using an OTP sent to their email. Once authenticated, they can manage their files through a simple console interface. The application securely stores file data in a MySQL database, allowing users to hide files by removing them from the file system and restoring them later when needed.</h6>

<h3>Maven Configuration (pom.xml)</h3>
<h5>Purpose:</h5>
<h6>Configures the project's build settings, dependencies, and other properties using Maven.<br>
Manages the external libraries required for database connectivity, email services, and other functionalities.</h6>

<h5>Key Dependencies:<h5>
<h5>mysql-connector-java: </h5><h6>Used for connecting to the MySQL database.</h6>
<h5>javax.mail:</h5> <h6>Used for sending emails via the JavaMail API.</h6>
<h5>Usage: </h5><h6>Ensures that all necessary libraries are available and properly configured for the project.</h6>
  
========================================================================================================================================================================

<h3>1. User Model (User.java)</h3>
Purpose:
Represents a user in the system with basic attributes like name and email.
This class encapsulates the data related to a user and provides methods to access and modify this data.
Attributes:
String name: Stores the name of the user.
String email: Stores the email of the user.
Key Methods:
getName(): Returns the name of the user.
setName(String name): Sets the user's name.
getEmail(): Returns the email of the user.
setEmail(String email): Sets the user's email.
Usage: This model is used throughout the application to create user objects and interact with user data.

========================================================================================================================================================================

<h3>2. Data Model (Data.java)</h3>
Purpose:
Represents file metadata in the system, including its ID, filename, file path, and associated user's email.
This model is essential for managing file-related operations like hiding and unhiding files.
Attributes:
int id: Unique identifier for the file record in the database.
String filename: Name of the file.
String path: Path where the file is stored.
String email: Email of the user who owns the file.
Key Methods:
getId(): Returns the ID of the file.
setId(int id): Sets the ID of the file.
getFilename(): Returns the filename.
setFilename(String filename): Sets the filename.
getPath(): Returns the file path.
setPath(String path): Sets the file path.
getEmail(): Returns the email associated with the file.
setEmail(String email): Sets the email associated with the file.
Usage: This model is used to create objects representing files and interact with the database for file operations.

========================================================================================================================================================================

<h3>3. Database Connection (MyConnection.java)</h3>
Purpose:
Manages the connection to the MySQL database used by the application.
Ensures that database connections are properly opened and closed, preventing resource leaks.
Key Methods:
getConnection(): Establishes and returns a connection to the database.
Uses JDBC to connect to a MySQL database with the specified credentials.
closeConnection(): Closes the active database connection if it exists.
Usage: Used by Data Access Objects (DAOs) to perform CRUD operations on the database.

========================================================================================================================================================================

<h3>5. Welcome Screen (Welcome.java)</h3>
Purpose:
Provides the initial user interface (via the console) for logging in or signing up.
Guides users through the process of authentication and account creation.
Key Methods:
welcomeScreen(): Displays options for logging in, signing up, or exiting the application. Handles user input and directs the flow accordingly.
login(): Prompts the user for their email, checks if they exist, and handles OTP-based authentication.
signup(): Prompts the user to create an account by entering their name and email, sends an OTP for verification, and registers the user upon successful OTP validation.
Usage: This class serves as the entry point for user interaction, determining the flow of the application based on user choices.

========================================================================================================================================================================

<h3>6. User View (UserView.java)</h3>
Purpose:
Provides a user interface for authenticated users to manage their hidden files.
Allows users to view hidden files, hide new files, or unhide existing files.
Key Methods:
home(): Displays a menu for users to choose between viewing, hiding, and unhiding files.
Shows a list of hidden files belonging to the user.
Handles file hiding by accepting a file path, storing the file data in the database, and deleting the original file.
Manages file unhiding by restoring the file from the database to the original path and removing the database record.
Usage: This class is used after the user has successfully logged in, enabling them to manage their files.

========================================================================================================================================================================

<h3>7. OTP Generation (GenerateOTP.java)</h3>
Purpose:
Generates a random One-Time Password (OTP) for user authentication during login or signup.
Ensures that each OTP is unique and valid for a single session.
Key Method:
getOTP(): Returns a randomly generated 4-digit OTP.
Usage: Used during the login and signup process to authenticate users.
8. Email OTP Service (SendOTPService.java)
Purpose:
Sends the OTP to the user's email using JavaMail API.
Facilitates secure authentication by ensuring that only users with access to the registered email can log in or sign up.
Key Method:
sendOTP(String email, String genOTP): Sends an email containing the OTP to the specified email address.
Configures SMTP properties, authenticates the sender, and sends the email.
Usage: Integrated with the login and signup process to deliver OTPs to users.

========================================================================================================================================================================

<h3>9. User Service (UserService.java)</h3>
Purpose:
Manages business logic related to user operations, particularly saving new users and checking for existing users.
Key Method:
saveUser(User user): Attempts to save a new user to the database. Returns 1 if the user already exists, 0 if the user is successfully saved, and -1 if an error occurs.
Usage: Used during the signup process to register new users in the system.

========================================================================================================================================================================

<h3>10. User DAO (UserDAO.java)</h3>
Purpose:
Interacts directly with the database to perform CRUD operations on user data.
Abstracts database interaction, allowing other parts of the application to handle user data without managing SQL directly.
Key Methods:
isExists(String email): Checks if a user with the given email already exists in the database. Returns true if the user exists.
saveUser(User user): Inserts a new user record into the database and returns the number of rows affected.
Usage: Called by the UserService class to handle user-related database operations.

========================================================================================================================================================================

<h3>11. Data DAO (DataDAO.java)</h3>
Purpose:
<h6>Handles all database operations related to file data, including storing, retrieving, hiding, and unhiding files.<br>
Ensures that file data is securely managed and that original files are deleted after being hidden.
<h5>Key Methods:</h5>
<h5>getAllFiles(String email): </h5>Retrieves all hidden files associated with the user's email. Returns a list of Data objects.
hideFile(Data file): Saves file data to the database and deletes the original file from the file system.
unhide(int id): Retrieves the file from the database and restores it to its original location, then deletes the corresponding record from the database.
Usage: Used by the UserView class to perform file management operations for authenticated users.</h6>

========================================================================================================================================================================

<h3>12. Main Entry Point (Main.java)</h3>
Purpose:
Serves as the main entry point of the application, initiating the welcome screen and maintaining the application's loop.
Key Method:
main(): Starts the application by invoking the welcome screen and keeps the application running in a loop.
Usage: Launches the application and manages its execution flow.
  
