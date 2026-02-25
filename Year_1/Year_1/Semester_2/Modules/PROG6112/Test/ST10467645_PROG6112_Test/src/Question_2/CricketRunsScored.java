/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Question_2;

/**
 *
 * @author lab_services_student
 */
public class CricketRunsScored extends Cricket {

    //Constructor that accepts all the variables.
    public CricketRunsScored(String batsmenName, String stadiumName, int totalRuns) {
        super(batsmenName, stadiumName, totalRuns);
    }

    //Method that prints the batsmans name, stadium name and total runs the batsman scored at the stadium during their career.
    public void PrintReport() {

        System.out.println("--------------------------");
        System.out.println("BATSMAN RUNS SCORED REPORT");
        System.out.println("--------------------------");

        System.out.println("Cricket Player: " + getBatsman());
        System.out.println("Stadium: " + getStadium());
        System.out.println("Total Runs Scored: " + getRunsScored());

    }

}
