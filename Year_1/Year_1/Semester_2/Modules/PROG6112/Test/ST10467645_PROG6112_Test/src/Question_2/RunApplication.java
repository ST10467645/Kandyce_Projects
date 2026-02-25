/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Question_2;

import java.util.Scanner;

/**
 *
 * @author lab_services_student
 */
public class RunApplication {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //Instantiating Scanner object.
        Scanner sc = new Scanner(System.in);
        
        //Prompting user for variables that will be used to pass through the parameters of the constructor.
        System.out.println("Enter the cricketer's name: ");
        String cName = sc.nextLine();
        
        System.out.println("Enter the stadium: ");
        String stadium = sc.nextLine();
        
        System.out.println("Enter the total runs scored by " + cName + " at " + stadium);
        int totalRuns = sc.nextInt();
        sc.nextLine();
        
        //Instantiating the CricketRunsScored class and passing through the user input to the parameters.
        CricketRunsScored crs = new CricketRunsScored(cName, stadium, totalRuns);
        
        //Calling the PrintReport method from the CricketRunsScored to display the report.
        crs.PrintReport();
    }
    
}
