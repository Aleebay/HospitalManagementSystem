
// Importing the java.io package to access classes for input/output operations.
import java.io.*;

// Importing the java.util package to use utility classes and data structures like lists, sets, and maps.
import java.util.*;

// Declaring a class named Person that implements the Serializable interface.
class Person implements Serializable {
    // A unique identifier for the class to support versioning during serialization.
    private static final long serialVersionUID = 1L;

    // Instance variables to store information about a person.
    protected String firstName; // First name of the person.
    protected String lastName; // Last name of the person.
    protected String role; // Role or designation of the person.
    protected String username; // Username used for identification.
    protected String password; // Password for authentication.
    protected String email; // Email address of the person.
    protected String address; // Physical address of the person.
    protected String phoneNumber; // Contact phone number of the person.

    // Additional attributes for login functionality.
    protected int loginAttempts = 0; // Number of login attempts.
    protected boolean banned = false; // Flag indicating whether the person is banned.

    // Constructor for the Person class that takes various attributes to initialize
    // a Person object.
    public Person(String firstName, String lastName, String role, String username, String password,
            String email, String address, String phoneNumber) {
        // Initializing the instance variables of the Person object with the provided
        // values.
        this.firstName = firstName; // Set the first name.
        this.lastName = lastName; // Set the last name.
        this.role = role; // Set the role or designation.
        this.username = username; // Set the username.
        this.password = password; // Set the password.
        this.email = email; // Set the email address.
        this.address = address; // Set the physical address.
        this.phoneNumber = phoneNumber; // Set the contact phone number.
    }

    // Method to perform login for the Person.
    public boolean login(String enteredPassword, Scanner scanner) {
        // Check if the account is banned. If banned, inform the user and return false.
        if (banned) {
            System.out.println("Account is banned. Please try again after 60 minutes.");
            return false;
        }

        // Attempt to log in with a maximum of 3 trials.
        for (int i = 0; i < 3; i++) {
            System.out.print("Enter password: ");
            String attempt = scanner.nextLine();

            // If the entered password matches the stored password, log in and reset login
            // attempts.
            if (password.equals(attempt)) {
                System.out.println("Logged in as " + role + ": " + firstName + " " + lastName);
                loginAttempts = 0;
                return true;
            } else {
                // If the entered password is incorrect, inform the user and increment login
                // attempts.
                System.out.println("Invalid password. Please try again.");
                loginAttempts++;
            }
        }

        // If there are too many unsuccessful login attempts, ban the account for 60
        // minutes.
        System.out.println("Too many unsuccessful login attempts. Account is banned for 60 minutes.");
        banned = true;
        return false;
    }

}

// Declare a class named Doctor that extends (inherits from) the Person class.
class Doctor extends Person {

    // Constructor for the Doctor class, taking specific attributes to initialize a
    // Doctor object.
    public Doctor(String firstName, String lastName, String username, String password,
            String email, String address, String phoneNumber) {

        // Call the constructor of the superclass (Person) using the 'super' keyword.
        // Pass the necessary attributes to initialize the Doctor object as a
        // specialized Person.
        super(firstName, lastName, "Doctor", username, password, email, address, phoneNumber);
    }

    // Method to view patient records based on user input.
    public void viewPatientRecords(Map<String, Person> userDatabase, Map<String, Person> patientDatabase,
            Scanner scanner) {

        // Prompt the user if they want to view patient records.
        System.out.print("Do you want to view Patients Records? (yes/no): ");
        String viewOption = scanner.nextLine().toLowerCase();

        // Check the user's response.
        if ("yes".equals(viewOption)) {

            // Prompt the user to enter the patient's ID.
            System.out.print("Enter Patients ID: ");
            String patientID = scanner.nextLine();

            // Check if the patient ID exists in the patient database and if the
            // corresponding object is a Patient.
            if (patientDatabase.containsKey(patientID) && patientDatabase.get(patientID) instanceof Patient) {

                // Retrieve the Patient object from the patient database.
                Patient patient = (Patient) patientDatabase.get(patientID);

                // Provide feedback to the user about the validity of the patient ID.
                System.out.println("Checking Patients ID.........");
                System.out.println("Patient ID is Valid");

                // Display the patient records.
                // Display the patient records with additional information relevant to the
                // nurse.
                System.out.println("Viewing patient records");
                System.out.println("- Patient ID: " + patientID);
                System.out.println("- Health Card No: " + patient.healthCardNumber);
                System.out.println("- First Name: " + patient.firstName);
                System.out.println("- Last Name: " + patient.lastName);
                System.out.println("- Address: " + patient.address);
                System.out.println("- Phone Number: " + patient.phoneNumber);
                System.out.println("- D.O.B: " + patient.dateOfBirth);
                System.out.println("- Credit Card No: " + patient.creditCardNumber);
                System.out.println("- Credit Card Security Code: " + patient.creditCardSecurityCode);
                System.out.println("- Credit Card Expiry Date: " + patient.creditCardExpiryDate);
                System.out.println("- Check-In Date: " + patient.checkInDate);
                System.out.println("- Check-Out Date: " + patient.checkOutDate);
                System.out.println("- Initial Diagnoses: " + patient.initialDiagnoses);
                System.out.println("- Medical History: " + patient.medicalHistory);
                System.out.println("- Assigned Nurse: " + patient.getAssignedNurse());

                // Display nurse-specific information or actions:
                System.out.println("Nurse Actions:");
                System.out.println("- Assign a Task: [placeholder for nurse task assignment]");
                System.out.println("- Update Patient Status: [placeholder for updating patient status]");

                System.out.println("- Status in the hospital: " + "Admitted");

                // details relevant to the nurse's view:
                System.out.println("- Room Number: " + patient.getRoomNumber());

                System.out.println("- Admission Date: " + patient.getAdmissionDate());

                // Display nurse-specific information or actions:
                System.out.println("Nurse Actions:");
                System.out.println("- Assign a Task: [placeholder for nurse task assignment]");
                System.out.println("- Update Patient Status: [placeholder for updating patient status]");

            } else {
                // Inform the user that the patient ID is invalid or the patient is not found.
                System.out.println("Invalid Patient ID or Patient not found.");
            }

        } else {
            // Inform the user that no patient records will be viewed.
            System.out.println("No patient records will be viewed.");
        }
    }

}

/**
 * Nurse class with attributes for patient care and management.
 * - dateOfBirth, healthCardNumber, patientAddress, creditCardNumber,
 * creditCardSecurityCode,
 * creditCardExpiryDate: Nurse's personal and financial details.
 * - patientPhoneNumber: Contact number for the nurse's assigned patient.
 * - medicalInformation, initialDiagnoses, medicalHistory: Health-related
 * information.
 * - checkInDate, checkOutDate: Dates when the patient checked in and out.
 */
class Nurse extends Person {
    private String dateOfBirth, healthCardNumber, patientAddress, creditCardNumber,
            creditCardSecurityCode, creditCardExpiryDate, patientPhoneNumber,
            medicalInformation, initialDiagnoses, medicalHistory, checkInDate, checkOutDate;

    /**
     * Constructor for the Nurse class.
     * Initializes a Nurse object with the provided personal and login details.
     * Calls the superclass (Person) constructor with the role set to "Nurse".
     */
    public Nurse(String firstName, String lastName, String username, String password,
            String email, String address, String phoneNumber) {
        super(firstName, lastName, "Nurse", username, password, email, address, phoneNumber);
    }

    // Method in the Nurse class to enter a new patient record into the system.
    // Prompts the user for information related to the patient and stores it in the
    // patient database.
    public void enterPatientRecord(Map<String, Person> patientDatabase, Scanner scanner) {
        System.out.println("== Adding New Patient Record == ");
        System.out.print("Enter Patient ID: ");
        String patientID = scanner.nextLine();
        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter Fathers Name: ");
        String fathersName = scanner.nextLine();
        System.out.print("Enter Date of Birth (DD/MM/YYYY): ");
        String dateOfBirth = scanner.nextLine();
        System.out.print("Enter Health Card Number: ");
        String healthCardNumber = scanner.nextLine();
        System.out.print("Enter Patient Address: ");
        String patientAddress = scanner.nextLine();
        System.out.print("Enter Credit Card Number: ");
        String creditCardNumber = scanner.nextLine();
        System.out.print("Enter Credit Card Security Code: ");
        String creditCardSecurityCode = scanner.nextLine();
        System.out.print("Enter Credit Card Expiry Date (MM/YY): ");
        String creditCardExpiryDate = scanner.nextLine();
        System.out.print("Enter Patient Phone Number: ");
        String patientPhoneNumber = scanner.nextLine();
        System.out.print("Enter Medical Information: ");
        String medicalInformation = scanner.nextLine();
        System.out.print("Enter Check-In Date (DD/MM/YYYY): ");
        String checkInDate = scanner.nextLine();
        System.out.print("Enter Check-Out Date (DD/MM/YYYY): ");
        String checkOutDate = scanner.nextLine();
        System.out.print("Enter Initial Diagnoses: ");
        String initialDiagnoses = scanner.nextLine();
        System.out.print("Enter Medical History: ");
        String medicalHistory = scanner.nextLine();

        // Create a new Patient object and add it to the patient database
        Patient patient = new Patient(firstName, lastName, username, password, email, address, phoneNumber,
                patientID, fathersName, dateOfBirth, healthCardNumber, patientAddress,
                creditCardNumber, creditCardSecurityCode, creditCardExpiryDate,
                patientPhoneNumber, medicalInformation, checkInDate, checkOutDate,
                initialDiagnoses, medicalHistory);
        patientDatabase.put(patientID, patient);
        System.out.println("Patient Record added successfully!");
    }
}

// Constructor for the Staff class, initializing a Staff object with the
// provided attributes.
class Staff extends Person {
    public Staff(String firstName, String lastName, String username, String password,
            String email, String address, String phoneNumber) {
        super(firstName, lastName, "Staff", username, password, email, address, phoneNumber);
    }

    // Displays the accounts by iterating through the user database and printing
    // user information.

    public void viewAccounts(Map<String, Person> userDatabase) {
        System.out.println("View Accounts option selected");

        for (Map.Entry<String, Person> entry : userDatabase.entrySet()) {
            Person person = entry.getValue();
            System.out.println(person.role + ": " + person.firstName + " " + person.lastName);
        }
    }
}

/**
 * Represents a Patient, extending the Person class and adding specific
 * attributes for patient details.
 * Includes fields such as fathersName, dateOfBirth, healthCardNumber,
 * patientAddress, creditCardNumber,
 * creditCardSecurityCode, creditCardExpiryDate, patientPhoneNumber,
 * medicalInformation, checkInDate,
 * checkOutDate, initialDiagnoses, and medicalHistory.
 */
class Patient extends Person {
    private static final long serialVersionUID = 1L;
    protected String fathersName;
    protected String dateOfBirth;
    protected String healthCardNumber;
    protected String patientAddress;
    protected String creditCardNumber;
    protected String creditCardSecurityCode;
    protected String creditCardExpiryDate;
    protected String patientPhoneNumber;
    protected String medicalInformation;
    protected String checkInDate;
    protected String checkOutDate;
    protected String initialDiagnoses;
    protected String medicalHistory;

    /**
     * Constructor for the Patient class, initializing a Patient object with
     * specific attributes.
     * Takes parameters such as firstName, lastName, username, password, email,
     * address, phoneNumber,
     * along with additional patient-specific details like patientID, fathersName,
     * dateOfBirth,
     * healthCardNumber, patientAddress, creditCardNumber, creditCardSecurityCode,
     * creditCardExpiryDate,
     * patientPhoneNumber, medicalInformation, checkInDate, checkOutDate,
     * initialDiagnoses, and medicalHistory.
     */

    public Patient(String firstName, String lastName, String username, String password,
            String email, String address, String phoneNumber, String patientID,
            String fathersName, String dateOfBirth, String healthCardNumber,
            String patientAddress, String creditCardNumber, String creditCardSecurityCode,
            String creditCardExpiryDate, String patientPhoneNumber, String medicalInformation,
            String checkInDate, String checkOutDate, String initialDiagnoses, String medicalHistory) {
        super(firstName, lastName, "Patient", username, password, email, address, phoneNumber);
        this.fathersName = fathersName;
        this.dateOfBirth = dateOfBirth;
        this.healthCardNumber = healthCardNumber;
        this.patientAddress = patientAddress;
        this.creditCardNumber = creditCardNumber;
        this.creditCardSecurityCode = creditCardSecurityCode;
        this.creditCardExpiryDate = creditCardExpiryDate;
        this.patientPhoneNumber = patientPhoneNumber;
        this.medicalInformation = medicalInformation;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.initialDiagnoses = initialDiagnoses;
        this.medicalHistory = medicalHistory;
    }

    /**
     * Returns the assigned nurse for the patient.
     * 
     * @return The name or identifier of the assigned nurse.
     */
    public String getAssignedNurse() {
        return null;
    }

    /**
     * Returns the admission date of the patient.
     * 
     * @return The date when the patient was admitted to the hospital.
     */
    public String getAdmissionDate() {
        return null;
    }

    /**
     * Returns the room number of the patient.
     * 
     * @return The room number where the patient is located in the hospital.
     */
    public String getRoomNumber() {
        return null;
    }
}

public class HospitalManagementSystem {
    // File names for storing user and patient databases
    private static final String USER_DATABASE_FILE = "userDatabase.dat";
    private static final String PATIENT_DATABASE_FILE = "patientDatabase.dat";

    // Main method to start the Hospital Management System
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Person> userDatabase = loadUserDatabase();
        Map<String, Person> patientDatabase = loadPatientDatabase();

        // User interaction loop for registration, login, and system exit
        while (true) {
            System.out.print("Are you a new user? (yes/no/exit): ");
            String newUserOption = scanner.nextLine();

            if ("yes".equalsIgnoreCase(newUserOption)) {
                registerUser(scanner, userDatabase);
            } else if ("no".equalsIgnoreCase(newUserOption)) {
                loginUser(scanner, userDatabase, patientDatabase);
            } else if ("exit".equalsIgnoreCase(newUserOption)) {
                System.out.println("Exiting the system. Goodbye!");
                saveUserDatabase(userDatabase);
                savePatientDatabase(patientDatabase);
                scanner.close();
                System.exit(0);
            } else {
                System.out.println("Invalid option. Please enter 'yes', 'no', or 'exit'.");
            }
        }
    }

    // Register a new user based on the selected role
    private static void registerUser(Scanner scanner, Map<String, Person> userDatabase) {
        System.out.println("=== User Registration ===");
        System.out.print("Select role (Doctor/Nurse/Staff/Patient): ");
        String role = scanner.nextLine();
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter address: ");
        String address = scanner.nextLine();
        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine();

        Person newUser = null;

        // Create a new user based on the selected role
        switch (role.toLowerCase()) {
            case "doctor":
                newUser = new Doctor(firstName, lastName, username, password, email, address, phoneNumber);
                break;
            case "nurse":
                newUser = new Nurse(firstName, lastName, username, password, email, address, phoneNumber);
                break;
            case "staff":
                newUser = new Staff(firstName, lastName, username, password, email, address, phoneNumber);
                break;
            case "patient":
                newUser = new Patient(firstName, lastName, username, password, email, address, phoneNumber,
                        "PatientID123", "FathersName123", "DOB123", "HealthCard123", "Address123",
                        "CreditCard123", "SecurityCode123", "ExpiryDate123", "PhoneNumber123",
                        "MedicalInfo123", "CheckInDate123", "CheckOutDate123", "Diagnoses123", "History123");
                break;
            default:
                System.out.println("Invalid role. Registration failed.");
                break;
        }

        // Add the new user to the user database if created
        if (newUser != null) {
            userDatabase.put(username, newUser);
            System.out.println(role + " registration is successful!");
        }
    }

    // Log in an existing user
    private static void loginUser(Scanner scanner, Map<String, Person> userDatabase,
            Map<String, Person> patientDatabase) {
        System.out.print("Enter username: ");
        String enteredUsername = scanner.nextLine();
        System.out.print("Enter password: ");
        String enteredPassword = scanner.nextLine();

        // Check if the entered username exists in the user database
        if (userDatabase.containsKey(enteredUsername)) {
            Person user = userDatabase.get(enteredUsername);

            // Validate the entered password for the user
            if (user.login(enteredPassword, scanner)) {
                // Perform role-specific actions based on the user's role
                performRoleSpecificActions(user, userDatabase, patientDatabase, scanner);
            }
        } else {
            System.out.println("Username not found. Please try again.");
        }
    }

    // Perform role-specific actions for the logged-in user
    private static void performRoleSpecificActions(Person user, Map<String, Person> userDatabase,
            Map<String, Person> patientDatabase, Scanner scanner) {
        if (user instanceof Doctor) {
            // If the user is a Doctor, view patient records and access the doctor's menu
            Doctor doctor = (Doctor) user;
            doctor.viewPatientRecords(userDatabase, patientDatabase, scanner);
            doctorMenu(doctor, userDatabase, patientDatabase, scanner);
        } else if (user instanceof Nurse) {
            // If the user is a Nurse, enter patient records and access the nurse's menu
            Nurse nurse = (Nurse) user;
            nurse.enterPatientRecord(patientDatabase, scanner);
            nurseMenu(nurse, patientDatabase, scanner);
        } else if (user instanceof Staff) {
            // If the user is a Staff member, view accounts and access the staff menu
            Staff staff = (Staff) user;
            staff.viewAccounts(userDatabase);
            staffMenu(staff, userDatabase, patientDatabase, scanner);
        } else if (user instanceof Patient) {
            // If the user is a Patient, access the patient menu
            Patient patient = (Patient) user;
            patientMenu(patient, patientDatabase, scanner);
        }
    }

    // Patient menu (empty for now)
    private static void patientMenu(Patient patient, Map<String, Person> patientDatabase, Scanner scanner) {
    }

    // Doctor menu with options to view patients' info, enter recommendations, and
    // logout
    private static void doctorMenu(Doctor doctor, Map<String, Person> userDatabase,
            Map<String, Person> patientDatabase, Scanner scanner) {
        System.out.println("== Doctor Menu ==");
        System.out.println("1. View Patients Info");
        System.out.println("2. Enter Recommendations");
        System.out.println("3. Logout");

        System.out.print("Enter Your Choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        switch (choice) {
            case 1:
                doctor.viewPatientRecords(userDatabase, patientDatabase, scanner);
                break;
            case 2:
                enterRecommendations(doctor, patientDatabase, scanner);
                break;
            case 3:
                System.out.println("Logging out. Goodbye, Doctor " + doctor.firstName + " " + doctor.lastName + "!");
                break;
            default:
                System.out.println("Invalid choice. Please enter a number between 1 and 3.");
        }
    }

    // Method for a doctor to enter recommendations for a patient
    private static void enterRecommendations(Doctor doctor, Map<String, Person> patientDatabase, Scanner scanner) {
        // Prompt for the patient's ID
        System.out.print("Enter Patient's ID: ");
        String patientID = scanner.nextLine();

        // Check if the patient ID exists in the database and is an instance of Patient
        if (patientDatabase.containsKey(patientID) && patientDatabase.get(patientID) instanceof Patient) {
            // Retrieve the patient object
            Patient patient = (Patient) patientDatabase.get(patientID);
            System.out.println("Checking Patient's ID.........");
            System.out.println("Patient's ID is Valid");

            // Prompt the doctor to enter recommendations
            System.out.print("Enter Recommendations: ");
            String recommendations = scanner.nextLine();

            // Append recommendations to the patient's medical history
            patient.medicalHistory += "\nRecommendations by Dr. " + doctor.lastName + ": " + recommendations;

            // Display success message
            System.out.println("Recommendation Successful.");
        } else {
            // Display an error message for an invalid patient ID or not found patient
            System.out.println("Invalid Patient ID or Patient not found.");
        }
    }

    private static void nurseMenu(Nurse nurse, Map<String, Person> patientDatabase, Scanner scanner) {
        // Display Nurse Menu options
        System.out.println("== Nurse Menu ==");
        System.out.println("1. Enter Patients Info");
        System.out.println("2. Check-in Patient");
        System.out.println("3. Check Out Patient");
        System.out.println("4. Logout");

        // Prompt for user's choice
        System.out.print("Enter Your Choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        // Perform actions based on the user's choice
        switch (choice) {
            case 1:
                nurse.enterPatientRecord(patientDatabase, scanner);
                break;
            case 2:
                checkInPatient(nurse, patientDatabase, scanner);
                break;
            case 3:
                checkOutPatient(nurse, patientDatabase, scanner);
                break;
            case 4:
                System.out.println("Logging out. Goodbye, Nurse " + nurse.firstName + " " + nurse.lastName + "!");
                break;
            default:
                System.out.println("Invalid choice. Please enter a number between 1 and 4.");
        }
    }

    private static void checkInPatient(Nurse nurse, Map<String, Person> patientDatabase, Scanner scanner) {
        // Prompt for patient ID
        System.out.print("Enter Patient's ID: ");
        String patientID = scanner.nextLine();

        // Check if the patient ID is valid and corresponds to a patient
        if (patientDatabase.containsKey(patientID) && patientDatabase.get(patientID) instanceof Patient) {
            Patient patient = (Patient) patientDatabase.get(patientID);
            System.out.println("Checking Patient's ID.........");
            System.out.println("Patient's ID is Valid");

            // Implement check-in logic here
            System.out.println("Checking In " + patient.firstName + " " + patient.lastName + "...");
            System.out.println("Patient Checked In by Nurse " + nurse.firstName + " " + nurse.lastName +
                    " at " + getCurrentDateTime());

        } else {
            System.out.println("Invalid Patient ID or Patient not found.");
        }
    }

    // Method to get the current date and time (implementation needed)
    private static String getCurrentDateTime() {
        return null;
    }

    private static void checkOutPatient(Nurse nurse, Map<String, Person> patientDatabase, Scanner scanner) {
        // Prompt for patient ID
        System.out.print("Enter Patient's ID: ");
        String patientID = scanner.nextLine();

        // Check if the patient ID is valid and corresponds to a patient
        if (patientDatabase.containsKey(patientID) && patientDatabase.get(patientID) instanceof Patient) {
            Patient patient = (Patient) patientDatabase.get(patientID);
            System.out.println("Checking Patient's ID.........");
            System.out.println("Patient's ID is Valid");

            // Implement check-out logic here
            System.out.println("Checking Out " + patient.firstName + " " + patient.lastName + "...");
            System.out.println("Patient Checked Out by Nurse " + nurse.firstName + " " + nurse.lastName +
                    " at " + getCurrentDateTime());

        } else {
            System.out.println("Invalid Patient ID or Patient not found.");
        }
    }

    private static void staffMenu(Staff staff, Map<String, Person> userDatabase,
            Map<String, Person> patientDatabase, Scanner scanner) {
        // Display Staff Menu options
        System.out.println("== Staff Menu ==");
        System.out.println("1. Manage Database");
        System.out.println("2. View Accounts");
        System.out.println("3. Make Request");
        System.out.println("4. Logout");

        // Prompt for user's choice
        System.out.print("Enter Your Choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        // Perform actions based on the user's choice
        switch (choice) {
            case 1:
                manageDatabase(staff, userDatabase, patientDatabase, scanner);
                break;
            case 2:
                staff.viewAccounts(userDatabase);
                break;
            case 3:
                makeRequest(staff, scanner);
                break;
            case 4:
                System.out.println("Logging out. Goodbye, Staff " + staff.firstName + " " + staff.lastName + "!");
                break;
            default:
                System.out.println("Invalid choice. Please enter a number between 1 and 4.");
        }
    }

    private static void manageDatabase(Staff staff, Map<String, Person> userDatabase,
            Map<String, Person> patientDatabase, Scanner scanner) {
        // Display message indicating the Manage Database option is selected
        System.out.println("Manage Database option selected");
    }

    private static void makeRequest(Staff staff, Scanner scanner) {
        // Prompt staff to enter their request
        System.out.print("Enter Your Request: ");
        String request = scanner.nextLine();

        // Implement request submission logic here
        System.out.println("Request submitted successfully!");
    }

    // Loads the user database from a file. If the file doesn't exist, initializes
    // an empty database.
    private static Map<String, Person> loadUserDatabase() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(USER_DATABASE_FILE))) {
            return (Map<String, Person>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No existing user database found. Starting with an empty database.");
            return new HashMap<>();
        }
    }

    // Saves the user database to a file.
    private static void saveUserDatabase(Map<String, Person> userDatabase) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(USER_DATABASE_FILE))) {
            outputStream.writeObject(userDatabase);
            System.out.println("User database saved successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Loads the patient database from a file. If the file doesn't exist,
    // initializes an empty database.
    private static Map<String, Person> loadPatientDatabase() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(PATIENT_DATABASE_FILE))) {
            return (Map<String, Person>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No existing patient database found. Starting with an empty database.");
            return new HashMap<>();
        }
    }

    // Saves the patient database to a file.
    private static void savePatientDatabase(Map<String, Person> patientDatabase) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(PATIENT_DATABASE_FILE))) {
            outputStream.writeObject(patientDatabase);
            System.out.println("Patient database saved successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
