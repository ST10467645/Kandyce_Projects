/**
 * The Person class represents a general patient with common attributes.
 *
 * This class serves as a parent class for the child class 
 * (Patient) that extends it.
 *
 * It defines key attributes for a person, including:
 * - patientID: a unique identifier for the patient.
 * - patientName: the patient's full name.
 * - patientAge: the patient's age in years.
 * - patientGender: the patient's gender.
 *
 * The class provides getter and setter methods to access and modify
 * these fields safely.
 *
 */
package Section_B;

/**
 *
 * @author ST10467645
 */
public class Person {

    //Declaring patients variables and making them protected for information hiding.
    protected String patientID;
    protected String patientName;
    protected int patientAge;
    protected String patientGender;

    //Default constructor for the Person class.
    public Person() {

    }

    /**
     * Getters and setters for access to protected patients variables.
     *
     * @return patients variables.
     */
    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public int getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(int patientAge) {
        this.patientAge = patientAge;
    }

    public String getPatientGender() {
        return patientGender;
    }

    public void setPatientGender(String patientGender) {
        this.patientGender = patientGender;
    }

}
