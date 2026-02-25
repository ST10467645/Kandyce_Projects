/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Question_1;

import java.util.Scanner;

/**
 *
 * @author lab_services_student
 */
public class Q1_Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //Instantiating Scanner object.
        Scanner sc = new Scanner(System.in);

        //Declaring array to store the names of the stadiums.
        String[] stadium = {"Kingsmead", "St Georges", "Wanderers"};

        //Declaring array to store the cricket players.
        String[] cricketPlayer = {"Jacques Kallis", "Hashim Amla", "AB De Villiers"};

        //Declaring 2D array to store the runs at each stadium for different batsmen.
        int[][] totalRuns = new int[stadium.length][cricketPlayer.length];

        //Declaring array to store thetotal runs made at each stadium.
        int[] runSum = new int[stadium.length];

        //For loop to prompt the usr for the number of runs made at each stadium by different players and storring them.
        for (int i = 0; i < stadium.length; i++) {

            System.out.println("For the the " + stadium[i] + " stadium, enter the total number of runs scored for the following batsmen: ");

            for (int j = 0; j < cricketPlayer.length; j++) {

                //Storing the users input.
                System.out.println(cricketPlayer[j] + ": ");
                totalRuns[i][j] = sc.nextInt();

                //Calculating and storing the sums for each stadium.
                runSum[i] += totalRuns[i][j];

            }

        }

        //Displaying a report of the runs scored.
        System.out.println("---------------------------------------------------");
        System.out.println("                Runs Scored Report                 ");
        System.out.println("---------------------------------------------------");
        
        System.out.printf("");

        for (int i = 0; i < runSum.length; i++) {

            System.out.println(cricketPlayer[i] + " runs scored at " + stadium[0] + ": " + totalRuns[0][i] );
            System.out.println(cricketPlayer[i] + " runs scored at " + stadium[1] + ": " + totalRuns[1][i]);
            System.out.println(cricketPlayer[i] + " runs scored at " + stadium[2] + ": " + totalRuns[2][i]);
            
            System.out.println();

        }

        //Displaying total runs at each stadium.
        System.out.println("---------------------------------------------------");
        System.out.println("            Total Runs at Stadiums                 ");
        System.out.println("---------------------------------------------------");

        for (int i = 0; i < runSum.length; i++) {

            System.out.println(stadium[i] + ": " + runSum[i]);

        }

        //Displayig the stadium with the most runs.
        
        System.out.println("--------------------------------------------------------------");
        System.out.println("Stadium with the most runs: " + highestStadium(runSum, stadium));
        System.out.println("--------------------------------------------------------------");

    }

    //Method to identify the stadium that has the highest total runs for all players.
    public static String highestStadium(int[] runSum, String[] stadium) {

        int max = runSum[0];

        int index = 0;

        for (int i = 0; i < runSum.length; i++) {

            if (runSum[i] > max) {

                max = runSum[i];

                index = i;

            }

        }

        return stadium[index];

    }

}
