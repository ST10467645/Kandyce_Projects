/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Section_B;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ST10467645
 */
public class PatientTest {

    public PatientTest() {
    }

    /**
     * Test of generateID method, of class Patient.
     *
     * This test verifies that the generateID method of the Patient class
     * correctly generates a unique 10-digit patient ID. The test checks both
     * the length and the numeric format of the ID.
     */
    @Test
    public void testGenerateID() {
        System.out.println("generateID method test. ");

        Patient instance = new Patient();

        String result = instance.generateID();

        //Checks if the generated ID is exactly 10 characters long
        assertEquals(10, result.length());

        //Checks if all characters in the ID are digits (0-9).
        assertTrue(result.matches("\\d{10}"));

    }

    /**
     * Test of addPatient method, of class Patient.
     *
     * This test verifies that a patient with all required details is
     * successfully added. It checks the following: - The addPatient method
     * returns true when valid patient details are provided. - A patientID is
     * generated and is exactly 10 digits long. - The patient object is properly
     * initialized with the provided details.
     */
    @Test
    public void testAddPatient_AddsPatient() {

        System.out.println("\naddPatient method - Successfully adds patient.");

        //Creating a patient and adding valid details.
        Patient instance = new Patient();
        instance.setPatientName("John Doe");
        instance.setPatientAge(30);
        instance.setPatientGender("Male");

        //Calls the addPatient Method and should return true as all details were filled in and valid.
        boolean result = instance.addPatient();
        assertTrue(result);

        //The patientID should now be a 10-digit string.
        assertNotNull(instance.getPatientID());
        assertEquals(10, instance.getPatientID().length());

    }

    /**
     * Test of addPatient method, of class Patient.
     *
     * This test verifies that a patient with missing details is not added. It
     * checks the following: - The addPatient method returns false when no
     * patient details are provided. - No patientID is generated (should be
     * null). - The patient object remains uninitialised due to incomplete data.
     */
    @Test
    public void testAddPatient_FailsToAddPatient() {
        System.out.println("\naddPatient method - fails to add patient.");

        //Creates but with no details.
        Patient instance = new Patient();

        //Calls the addPatient Method and should return false as no patient was created and no details were entered.
        boolean result = instance.addPatient();
        assertFalse(result);

        //Should be null as patient was not created.
        assertNull(instance.getPatientID());

    }

    /**
     * Test of addSymptoms method, of class Patient.
     *
     * This test verifies that a valid symptom from the predefined list can be
     * successfully added to a patient. It checks the following: - The
     * addSymptoms method returns true when a valid symptom is provided. - The
     * symptom is correctly stored in the patient's symptom list.
     *
     */
    @Test
    public void testAddSymptoms_AddsSymptoms() {
        System.out.println("\naddSymptoms - successfully adds symptoms.");

        Patient instance = new Patient();

        //Declaring valid symptom for patient that is in symptoms list.
        String newSymptoms = "Fatigue";

        //Result should return true.
        boolean result = instance.addSymptoms(newSymptoms);

        assertTrue(result);
        assertTrue(instance.getPatientSymptoms().contains(newSymptoms));
    }

    /**
     * Test of addSymptoms method, of class Patient.
     *
     * This test verifies that an invalid symptom (not in the predefined list)
     * is rejected and not added to the patient. It checks the following: - The
     * addSymptoms method returns false when the symptom is invalid. - The
     * patient's symptom list does not contain the invalid symptom.
     *
     */
    @Test
    public void testAddSymptoms_FailsToAddSymptoms() {
        System.out.println("\naddSymptoms - fails to add symptoms.");
        Patient instance = new Patient();

        //Declaring invalid symptom for patient that is not in symptoms list.
        String newSymptoms = "Cough";

        //Result should return false.
        boolean result = instance.addSymptoms(newSymptoms);

        assertFalse(result);
        assertFalse(instance.getPatientSymptoms().contains(newSymptoms));

    }

    /**
     * Test of matchSymptomsToDiagnosis method, of class Patient.
     *
     * This test verifies that a valid symptom is correctly matched to its
     * corresponding diagnosis. It checks the following: - When a valid symptom
     * is provided, the method returns the correct list of possible diagnoses. -
     * The returned list contains the expected diagnosis.
     */
    @Test
    public void testMatchSymptomsToDiagnosis() {
        System.out.println("\nmatchSymptomsToDiagnosis");

        //Declaring valid symptom for a patient.
        String psymptoms = "Fatigue";

        Patient instance = new Patient();

        //Calling method with valid symptom.
        ArrayList<String> expResult = instance.matchSymptomsToDiagnosis(psymptoms);

        //Should return true as that is the diagnosis for the valid symptom.
        assertTrue(expResult.contains("Hypothyroidism"));

    }

    /**
     * Test of updatePtDetails method, of class Patient.
     *
     * This test verifies that a patient's details can be successfully updated
     * when a valid PatientID exists in the system. It checks the following: -
     * The updatePtDetails method returns true when the correct PatientID is
     * provided. - The patient's name, age, and gender are updated to the new
     * values.
     *
     */
    @Test
    public void testUpdatePtDetails_SuccessfulUpdate() {
        System.out.println("\nupdatePtDetails - Successfully updates patient details.");

        //Creating a patient and adding valid detail.
        Patient instance = new Patient();

        instance.setPatientID("6589742369");
        instance.setPatientName("Old Name");
        instance.setPatientAge(30);
        instance.setPatientGender("Male");

        //Declaring existing ID to pick up the patient.
        String validateID = "6589742369";

        //Declaring details to update.
        String newName = "Jane";
        int newAge = 22;
        String newGender = "Female";

        //Should return true as patient with ID exists and correct details were entered.
        assertTrue(instance.updatePtDetails(validateID, newName, newAge, newGender));

    }

    /**
     * Test of updatePtDetails method, of class Patient.
     *
     * This test verifies that updating a patient's details fails when the
     * PatientID does not exist in the system. It checks the following: - The
     * updatePtDetails method returns false when an invalid PatientID is
     * provided. - The patient's details remain unchanged since no record
     * matches the given ID.
     *
     */
    @Test
    public void testUpdatePtDetails_UnsuccessfullUpdate() {
        System.out.println("\nupdatePtDetails - patient details fails to update.");

        Patient instance = new Patient();

        //Declaring PatientID that does not exist.
        String validateID = "0316897568";
        String newName = "Jane";
        int newAge = 22;
        String newGender = "Female";

        //Should return false as patient ID does not exist.
        assertFalse(instance.updatePtDetails(validateID, newName, newAge, newGender));

    }

    /**
     * Test of patientReport method, of class Patient.
     *
     * This test verifies that a patient report is successfully generated when a
     * valid PatientID exists in the provided list. It checks the following: -
     * The patientReport method returns true when a matching PatientID is found.
     * - A report is generated for the patient with the corresponding details.
     *
     */
    @Test
    public void testPatientReport_ReportGenerated() {
        System.out.println("\npatientReport - report successfully generated.");

        //Creates a new list.
        ArrayList<Patient> patients = new ArrayList();

        //Creates a Patient with details and adds it to the list.
        Patient patient = new Patient();
        patient.setPatientID("6987431687");
        patient.setPatientName("Jane");
        patient.setPatientAge(25);
        patient.setPatientGender("Female");
        patients.add(patient);

        //Creates a new instance of Patient to call the method on.
        Patient instance = new Patient();

        //Calls the method with a valid patient ID.
        boolean result = instance.patientReport(patients, "6987431687");

        //Should return true and generate a report as the patient ID exists.
        assertTrue(result);

    }

    /**
     * Test of patientReport method, of class Patient.
     *
     * This test verifies that a patient report is not generated when an invalid
     * PatientID is provided or the patient does not exist in the list. It
     * checks the following: - The patientReport method returns false when no
     * matching PatientID is found. - No report is generated for a non-existent
     * patient.
     *
     */
    @Test
    public void testPatientReport_ReportNotGenerated() {
        System.out.println("\npatientReport - report fails to generate.");

        //Creates a new list.
        ArrayList<Patient> patients = new ArrayList();

        //Declaring an ID that does not exist.
        String validateID = "1467986317";
        Patient instance = new Patient();

        //Calls the method with an invalid ID.
        boolean result = instance.patientReport(patients, validateID);

        //Should return false as ID does not exist and no report will be generated.
        assertFalse(result);

    }

}
