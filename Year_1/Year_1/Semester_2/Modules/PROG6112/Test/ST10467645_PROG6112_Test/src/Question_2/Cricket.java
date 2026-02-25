/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Question_2;

/**
 *
 * @author lab_services_student
 */
public abstract class Cricket implements ICricket {

    //Declaring protected variables for information hiding.
    protected String batsmenName;
    protected String stadiumName;
    protected int totalRuns;

    //Constructor that accepts all the variables.
    public Cricket(String batsmenName, String stadiumName, int totalRuns) {
        this.batsmenName = batsmenName;
        this.stadiumName = stadiumName;
        this.totalRuns = totalRuns;
    }

    //Implemnted methods from the ICricket interface class that are get methods for the variables.
    @Override
    public String getBatsman() {

        return batsmenName;

    }

    @Override
    public String getStadium() {

        return stadiumName;

    }

    @Override
    public int getRunsScored() {

        return totalRuns;

    }

}
