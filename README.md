Software Needed
Java Development Kit (JDK)
•	Purpose: The main software required to write, compile, and run Java programs.
•	Version: Java 8 or higher (preferably Java 11 for long-term support).
•	Installation:
o	You can download it from Oracle JDK or use OpenJDK.
Steps to Execute the Code
1.	Install Java:
o	Ensure Java JDK is installed by checking with the command: java -version.
o	If not installed, download and install it.
2.	Recommended Version:
o	Java 8: Minimum for solid Swing support and modern features.
o	Java 11 or higher: Better performance, long-term support (LTS), and updated features.
3.	Save the Code:
o	Save the code in a file named FinanceTrackerApp.java.
4.	Compile the Code:
o	Open the terminal, navigate to the folder where the file is saved, and run:

javac FinanceTrackerApp.java
Run the Application:
o	Execute the program by running:
java FinanceTrackerApp
5.	Login Details:
o	When the login window opens, use the following credentials:
	Username: admin
	Password: password123



Libraries Used
javax.swing.*
•	Purpose: Helps to create graphical interfaces for the app.
•	Important Parts:
o	JFrame: The main window of the app.
o	JTextArea: A box to show the list of transactions.
o	JButton: Buttons for user actions (e.g., Add Income, Add Expense).
o	JScrollPane: Adds a scrollbar to components like the transaction area.
o	JOptionPane: Shows pop-ups for messages or inputs.
o	JPasswordField: A field to securely enter passwords.
java.awt.*
•	Purpose: Provides tools to arrange and style GUI components.
•	Important Parts:
o	Color: Used to set the colors of buttons and panels.
o	GridBagLayout and GridBagConstraints: Help arrange components neatly in the login screen.
o	Insets: Adds space around components for better spacing.
java.awt.event.*
•	Purpose: Handles user actions like button clicks.
•	Important Parts:
o	ActionEvent: Represents a button click or similar action.
o	ActionListener: Allows defining what happens when a button is clicked.
java.util.*
•	Purpose: Provides tools to manage collections of data.
•	Important Parts:
o	ArrayList: Used to keep track of all the transactions (in TransactionManager).
java.lang. (Automatically Included)
•	Purpose: Includes basic classes that every Java program can use without importing.
•	Important Parts:
o	String: For text like transaction details and profile information.
o	Double: To work with numbers like income, expenses, and budget.


