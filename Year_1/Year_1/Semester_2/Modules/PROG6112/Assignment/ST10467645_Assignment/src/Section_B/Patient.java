/**
 * The Patient class is responsible for managing all operations related to patients
 * in the system.
 *
 * It acts as the main logic class of the patient portal by:
 * - Storing a patient's symptoms and diagnoses in ArrayLists.
 * - Allows patients to enter their personal details.
 * - Validates and adds symptoms from a predefined list.
 * - Matches symptoms to diagnoses using a simple mapping.
 * - Updates patient details (name, age, gender).
 * - Generates a patient report with all information.
 * - Exits the application safely when requested.
 *
 * In short, this class provides all the functionality needed to add, update,
 * and display patient data while working closely with the Person class for
 * shared patient attributes.
 */
package Section_B;

import java.util.*;

/**
 *
 * @author ST10467645
 */
public class Patient extends Person {

    //ArrayList to store the diagnoses assigned to this patient.
    private ArrayList<String> patientDiagnosis = new ArrayList();

    //ArrayList to store the symptoms entered by this patient.
    private ArrayList<String> patientSymptoms = new ArrayList();

    //Array to store valid symptoms a patient can enter.
    String[] symptoms = {"Fatigue", "Weight Gain", "Weight Loss", "Headache", "Dizziness", "Shortness of Breath", "Chest Pain", "Palpitations", "Swelling in Ankles", "Dry Skin", "Increased Thirst", "Frequent Urination"};

    //Corresponding diagnoses for each symptom in the same order.
    String[] diagnostics = {"Hypothyroidism", "Hypothyroidism", "Hyperthyroidism", "Hypertension (High BP)", "Hypotension (Low BP)", "Asthma", "Heart Disease", "Heart Disease", "Chronic Kidney Disease", "Hypothyroidism", "Diabetes Mellitus", "Diabetes Mellitus"};

    //Getter for unit tests.
    public ArrayList<String> getPatientSymptoms() {
        return patientSymptoms;
    }

    /**
     * Default constructor for the Patient class.
     */
    public Patient() {

    }

    /**
     * This method generates a random 10-digit patient ID.
     *
     * It uses a string containing digits from 0 to 9 and randomly selects 10
     * digits to build the patient ID. Each digit is chosen by generating a
     * random index from 0 to 9.
     *
     * The resulting patient ID is always 10 digits long and unique for each
     * patient.
     *
     * @return A randomly generated 10-digit numeric patient ID.
     */
    public String generateID() {

        //This string has all the digits used to make the message ID.
        String digits = "0123456789";

        //Variable to store the final generated message ID.
        String patientID = "";

        //The loop will loop 10 times to get 10 random digits.
        for (int i = 0; i < 10; i++) {

            //Picks a random number between 0 and 9 to use as an index.
            int index = (int) (Math.random() * 10);

            //Add the digit from that index to the message ID.
            patientID += digits.substring(index, index + 1);
        }

        //Returns the generated 10-digit message ID.
        return patientID;
    }

    /**
     * This method adds a new patient to the system.
     *
     * It first checks if all required patient details (name, age, gender) are
     * provided. If any details are missing, it prompts the user to enter all
     * information.
     *
     * When all details are valid, it generates a unique patient ID and displays
     * it.
     *
     * @return true if the patient was successfully added; false otherwise.
     */
    public boolean addPatient() {

        //Checks that all patient details are filled in.
        if (patientName != null && !patientName.isEmpty() && patientAge > 0 && patientGender != null && !patientGender.isEmpty()) {

            //Generates am unique ID for patient if all details are filled in.
            patientID = generateID();

            System.out.println("Your patientID = " + patientID);

            return true;

        } else {

            //Displays an error message if details are not complete.
            System.out.println("Please enter ALL details.");
        }

        return false;

    }

    /**
     * This method adds a symptom to the patient's list of symptoms.
     *
     * It first validates that the symptom is not null or empty. Then it checks
     * if the symptom exists in the predefined list of valid symptoms. Only
     * valid symptoms are added to the patient's symptom list.
     *
     * @param newSymptoms The symptom entered by the patient.
     * @return true if the symptom was successfully added; false if invalid or
     * empty.
     */
    public boolean addSymptoms(String newSymptoms) {

        //Returns false if input is null or empty.
        if (newSymptoms == null || newSymptoms.isEmpty()) {

            return false;

        }

        //Used to check if symptom is valid.
        boolean valid = false;

        //Loops through the list of valid symptoms.
        for (int i = 0; i < symptoms.length; i++) {

            //Compares input symptom with valid symptom and ignores case.
            if (symptoms[i].equalsIgnoreCase(newSymptoms)) {

                //Marks as true if it is the same.
                valid = true;

                break;

            }

        }

        //If symptom is not in the list, false is returned.
        if (!valid) {

            return false;

        }

        //Adds valid symptom to patient's symptom list.
        patientSymptoms.add(newSymptoms);

        //Returns true when symptoms are successfully added.
        return true;
    }

    /**
     * This method retrieves the list of symptoms for a specific patient.
     *
     * It checks if the provided patient ID matches this patient's ID. If so, it
     * returns the list of symptoms stored for the patient. Otherwise, it
     * returns an empty list and prints an error message.
     *
     * @param validateID The patient ID to validate.
     * @return An ArrayList of symptoms for the patient, or an empty list if not
     * found.
     */
    public ArrayList<String> getSymptoms(String validateID) {

        //Checks if patient ID matches an existing patient ID.
        if (this.patientID != null && this.patientID.equals(validateID)) {

            //Returns the stored symptoms if it matches an ID.
            return patientSymptoms;

        } else {

            //Displays an error message if no ID was found.
            System.out.println("Patient ID " + validateID + " could not be found!");

            //Returns an empty list.
            return new ArrayList<>();

        }

    }

    /**
     * This method matches a single symptom to its corresponding diagnosis.
     *
     * It loops through the predefined list of symptoms, and if a match is
     * found, the corresponding diagnosis is added to the patient's diagnosis
     * list.
     *
     * This method accumulates diagnoses for all symptoms the patient has
     * entered.
     *
     * @param psymptoms The symptom to match.
     * @return The updated ArrayList of diagnoses for the patient.
     */
    public ArrayList<String> matchSymptomsToDiagnosis(String psymptoms) {

        //Loops through all predefined symptoms.
        for (int j = 0; j < symptoms.length; j++) {

            //Checks if the entered symptom matches the predefined symptoms.
            if (psymptoms.equalsIgnoreCase(symptoms[j])) {

                //Adds the corresponding diagnosis to patient's diagnosis list.
                patientDiagnosis.add(diagnostics[j]);

            }

        }

        //Returns the updated list of diagnoses.
        return patientDiagnosis;
    }

    /**
     * This method generates the patient's diagnosis based on all their
     * symptoms.
     *
     * It first retrieves all symptoms for the patient. Then it clears the
     * existing diagnosis list to prevent duplicates. Next, it loops through
     * each symptom and calls matchSymptomsToDiagnosis() to add the
     * corresponding diagnosis.
     *
     * This makes sure that the diagnosis list always shows the current
     * symptoms, without repeating previous results.
     *
     * @param validateID The patient ID to validate.
     * @return An ArrayList of diagnoses corresponding to the patient's
     * symptoms, or an empty list if no symptoms exist.
     */
    public ArrayList<String> getPatientDiagnosis(String validateID) {

        //Retrieves stored symptoms for this patient with valid ID.
        patientSymptoms = getSymptoms(validateID);

        //Checks if any symptoms exist.
        if (patientSymptoms != null && !patientSymptoms.isEmpty()) {

            //Clears previous diagnoses to avoid duplicates.
            patientDiagnosis.clear();

            //Loops through all symptoms to generate diagnoses.
            for (int i = 0; i < patientSymptoms.size(); i++) {

                //Uses matchSymptomsToDiagnosis method to add diagnosis for each symptom stored for the patient.
                matchSymptomsToDiagnosis(patientSymptoms.get(i));

            }

            //Returns the diagnosis list if there were valid symptoms.
            return patientDiagnosis;

        } else {

            //If no symptoms were found or valid, an empty array list is returned.
            return new ArrayList<>();
        }

    }

    /**
     * This method updates the patient's details if the ID matches.
     *
     * It checks for new values for name, age, and gender. Only non-empty and
     * valid inputs are updated. A summary of changes is displayed to the user.
     *
     * @param validateID The patient ID to validate.
     * @param newName The new name to update (if not empty).
     * @param newAge The new age to update (if greater than 0).
     * @param newGender The new gender to update (if not empty).
     * @return true if the patient details were updated; false otherwise.
     */
    public boolean updatePtDetails(String validateID, String newName, int newAge, String newGender) {

        //Checks if patient ID matches an existing patient ID.
        if (this.patientID != null && this.patientID.equals(validateID)) {

            //Instantiating string builder.
            StringBuilder changes = new StringBuilder();

            //Updates name if provided.
            if (newName != null && !newName.isBlank()) {
                setPatientName(newName);
                changes.append("Name updated to: ").append(newName).append(".\n");
            }

            //Updates age if provided and valid.
            if (newAge > 0) {
                setPatientAge(newAge);
                changes.append("Age updated to: ").append(newAge).append(".\n");
            }

            //Updates gender if provided.
            if (newGender != null && !newGender.isBlank()) {
                setPatientGender(newGender);
                changes.append("Gender updated to: ").append(newGender).append(".\n");
            }

            //Displays changes or indicates no updates were made.
            if (changes.length() == 0) {

                System.out.println("No changes were made.");

            } else {

                System.out.println("Changes made: \n" + changes.toString());
            }

            //If update is successful, true is returned.
            return true;

        }

        //If no patient ID is found, false is returned.
        return false;
    }

    /**
     * This method prints a full patient report for a given patient ID.
     *
     * It loops through all patients in the system and finds the one matching
     * the ID. If found, it displays their details, symptoms, and diagnoses. If
     * not found, it prints a message indicating the patient was not found.
     *
     * @param patients The ArrayList of all patients.
     * @param validateID The patient ID to find.
     * @return true if a report was generated; false if the patient ID was not
     * found.
     */
    public boolean patientReport(ArrayList<Patient> patients, String validateID) {

        System.out.println("************************************************");
        System.out.println("             Patient REPORT                     ");
        System.out.println("************************************************");

        //Loops through all patients to find the matching ID.
        for (int i = 0; i < patients.size(); i++) {

            Patient currentP = patients.get(i);

            //Checks if patient ID matches an existing patient ID.
            if (currentP.patientID != null && currentP.patientID.equals(validateID)) {

                //If it matches an existing patient ID, all patient details are printed for that patient.
                System.out.println("Patient Details for patient ID " + validateID + ": ");
                System.out.println("-------------------------------------------------");
                System.out.println("Patient Name: " + currentP.getPatientName());
                System.out.println("Patient Age: " + currentP.getPatientAge());
                System.out.println("Patient Gender: " + currentP.getPatientGender());

                //If it matches an existing patient ID, all patient symptoms and diagnosis are printed for that patient.
                System.out.println("Patient symptoms: " + currentP.patientSymptoms);
                System.out.println("Patient diagnosis: " + currentP.patientDiagnosis);

                //If report is successfully generated, true is returned.
                return true;

            }
        }

        //Displays an error message and returns false if ID was not found.
        System.out.println("Patient ID " + validateID + " not found!");

        return false;

    }

    /**
     * Exits the Patient Portal application.
     *
     * Displays a message to inform the user that the application is closing.
     * Terminates the program using System.exit(0).
     */
    public void exitPatientsApplication() {

        System.out.println("Exiting application.");

        //Terminate the program / Exits system.
        System.exit(0);

    }
}
