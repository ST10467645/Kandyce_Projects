/**
 * The Question1 class contains the main method and acts as the entry point
 * for the Patient Portal application.
 *
 * It provides a text-based menu system that allows patients to:
 * - Enter and store their personal details.
 * - Record symptoms from a predefined list.
 * - Receive a diagnosis based on entered symptoms.
 * - Update patient details such as name, age, or gender.
 * - Generate and view a complete patient report.
 * - Exit the application safely.
 *
 * This class makes use of the Patient class for managing patient logic
 * and the Person class for shared attributes like name, age, and gender.
 *
 * The main loop runs continuously until the user chooses to exit,
 * ensuring that multiple operations can be performed in one session.
 */
package Section_B;

import java.util.*;

/**
 *
 * @author ST10467645
 */
public class Question1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //ArrayList to store all patient objects created during the session.
        ArrayList<Patient> patients = new ArrayList<>();

        //Instantiating scanner object.
        Scanner sc = new Scanner(System.in);

        //Loop used to allow user to stay in the menu until the user decides to exit.
        while (true) {

            //Menu displayed to the user.
            String menu = """
                      ------------------------------------
                         Welcome to the Patient Portal 
                      ------------------------------------
                      Please select an option - Enter ONLY a number.
                      
                      1. Add your details. 
                      2. Enter your symptoms.
                      3. Get diagnosis.
                      4. Update patient details.
                      5. Get a patient report.
                      6. Exit.
                      
                      """;

            //Prints the menu to the user.
            System.out.println(menu);
            //Stores the users choice.
            String choice = sc.nextLine();

            //Handle menu options using switch statements.
            switch (choice) {

                case "1":

                    //Creates a new patient.
                    Patient pt = new Patient();

                    //Prompts user for name and sets it.
                    System.out.println("Enter the following details: ");
                    System.out.println("\nPatients full name: ");
                    pt.setPatientName(sc.nextLine());

                    //Prompts user for age and validates its a number using loop and if statment.
                    System.out.println("Patients age: ");

                    while (true) {

                        if (sc.hasNextInt()) {

                            //If age is valid, it gets set.
                            pt.setPatientAge(sc.nextInt());
                            sc.nextLine();

                            break;

                        } else {

                            //If not valid, an error message is displayed.
                            System.out.println("Enter only a NUMBER!");

                            sc.nextLine();

                        }

                    }

                    //Prompts user for gender and sets it.
                    System.out.println("Patients gender: ");
                    pt.setPatientGender(sc.nextLine());

                    //Uses addPatient method from patient class to generate message ID if all details are entered.
                    pt.addPatient();
                    
                    //Each patient object then gets stored in the Array List called patients.
                    patients.add(pt);

                    break;

                case "2":

                    //Adds symptoms for an existing patient by validating ID.
                    System.out.println("Enter your patient ID to add symptoms.");
                    String validatePatientID = sc.nextLine();

                    boolean found = false;

                    //Searches for patient by ID by going through list and matching entered ID to stored patient ID.
                    for (int i = 0; i < patients.size(); i++) {

                        Patient current = patients.get(i);

                        if (current.getPatientID() != null && current.getPatientID().equals(validatePatientID)) {

                            found = true;

                            //If patient exists, all available symptoms are displayed.
                            System.out.println("\nFrom the following list, enter the symptoms you are experiencing. \nEnter the word next when you have entered all your symptoms.\n");

                            System.out.println(Arrays.toString(current.symptoms));

                            //Loops until patient has entered all symptoms and enters next.
                            while (true) {

                                System.out.println("\nEnter a symptom (or next when you have entered everything.)");

                                String ptInput = sc.nextLine();

                                if (ptInput.equalsIgnoreCase("next")) {

                                    break;
                                }

                                //If symptoms are valid, they get stored.
                                if (current.addSymptoms(ptInput)) {

                                    System.out.println("\nSymptom " + ptInput + " added successfully.");

                                } else {

                                    //Error message is displayed if symptom is not valid.
                                    System.out.println("Invalid symptom! Please try again!");

                                }
                            }

                            //Displays a success message to show all valid symptoms were added.
                            System.out.println("\nAll symptoms added successfully!");

                            break;
                        }
                    }

                    //If patient was not found, an error message is displayed.
                    if (!found) {

                        System.out.println("Patient ID " + validatePatientID + " does not exist!");

                    }

                    break;

                case "3":

                    //Generates and displays diagnosis for a patient by validating the patient exists.
                    System.out.println("Enter your patient ID to get your diagnosis.");
                    String validateID = sc.nextLine();

                    boolean foundDiag = false;

                     //Searches for patient by ID by going through list and matching entered ID to stored patient ID.
                    for (int i = 0; i < patients.size(); i++) {

                        Patient current = patients.get(i);

                        //If patient exists, a dignosis is made.
                        if (current.getPatientID() != null && current.getPatientID().equals(validateID)) {

                            foundDiag = true;

                            //A diagnosis is made using the getPatientDiagnosis method from the Patient class.
                            ArrayList<String> diagnosis = current.getPatientDiagnosis(validateID);

                            //Displays diagnosis if symptoms were entered an valid, otherwise shows an error message.
                            if (!diagnosis.isEmpty()) {

                                System.out.println("\nYour diagnosis could be any of the following: " + diagnosis + "\nPlease enter 5 when you return to the menu and show the report to your doctor.");

                            } else {

                                System.out.println("\nNo diagnosis available yet. Add symptoms first.");
                            }

                            break;
                        }
                    }

                    //If patient ID does not exist, an error message is displayed.
                    if (!foundDiag) {

                        System.out.println("Patient ID " + validateID + " does not exist!");
                    }
                    break;

                case "4":

                    //Updated patient details by validating ID.
                    System.out.println("Enter your patient ID to update patient information.");
                    String validatePID = sc.nextLine();

                    boolean fnd = false;

                    //Searches for patient by ID by going through list and matching entered ID to stored patient ID.
                    for (int i = 0; i < patients.size(); i++) {

                        Patient current = patients.get(i);

                        //If patient exists then updates on patient details can be made.
                        if (current.getPatientID() != null && current.getPatientID().equals(validatePID)) {

                            fnd = true;

                            //Promps user for new name and stores it to pass through method.
                            System.out.println("Enter the name you want updated. (Press enter if you do not want to change.)");
                            String newName = sc.nextLine();

                            //Promps user for new age  and makes sure its a number and stores it to pass through method to update.
                            System.out.println("Enter the age you want updated. (Press 0 if you do not want to change.)");

                            int newAge = 0;

                            while (true) {

                                //Checks if new age entered is a number.
                                if (sc.hasNextInt()) {

                                    //If new age is a number, it gets stored to be used to update.
                                    newAge = sc.nextInt();

                                    sc.nextLine();

                                    break;

                                } else {

                                    //An error message is displayed if new age is not a number.
                                    System.out.println("Enter only a NUMBER!");

                                    sc.nextLine();
                                }
                            }

                            //Promps user for new gender and stores it to pass through method to update.
                            System.out.println("Enter the gender you want updated. (Press enter if you do not want to change.)");
                            String newGender = sc.nextLine();

                            //Updates details using updatePtDetails method from Patient class.
                            current.updatePtDetails(validatePID, newName, newAge, newGender);

                            break;
                        }
                    }

                    //If patient ID does not exist, an error message is displayed.
                    if (!fnd) {
                        System.out.println("Patient ID " + validatePID + " does not exist!");
                    }

                    break;

                case "5":

                    //Generates and prints patient report by validating ID.
                    System.out.println("Enter your patient ID to see your full patient report.");
                    String ptID = sc.nextLine();

                    //Instantiating patient object to call method.
                    Patient patientRcd = new Patient();

                    //Checks if a report was generated by using patientReport from Patient class. Returns true if found, otherwise false is returned.
                    boolean reportFound = patientRcd.patientReport(patients, ptID);

                    //If the report was not found an error message is displayed
                    if (!reportFound) {

                        System.out.println("No report could be found.");

                    }

                    break;

                case "6":

                    //Instantiation patient object to call method.
                    Patient patientExit = new Patient();

                    //Exits the application safely by using exitPatientsApplication method from Patients class.
                    patientExit.exitPatientsApplication();

                    break;

            }

        }

    }
}
