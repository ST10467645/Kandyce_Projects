/**
 * The Question_1 class contains the main entry point of the program.
 *
 * This class handles all user interaction with the system by:
 * - Displaying the main menu and sub-menu options.
 * - Allowing the user to capture, search, update, delete, and display series details.
 * - Making calls to methods from the Series class to perform the actual logic.
 * - Continuously looping the menu until the user chooses to exit.
 *
 * The main purpose of this class is to provide a user-friendly interface
 * where the user can manage series data through a menu-driven application.
 */
package Section_A;

import java.util.*;

/**
 *
 * @author ST10467645
 */
public class Question_1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //Instantiating the scanner object.
        Scanner sc = new Scanner(System.in);

        //Instantiating Series class.
        Series srs = new Series();

        //Continuous loop to keep displaying the menu until the user chooses to exit.
        while (true) {

            //A multi-line string to represent the main menu for the user where the menu prompts the user three options to choose from.
            String menu = """
                      ************************************************    
                                LATEST SERIES - 2025
                      ************************************************
                      
                      Enter (1) to launch menu or any other key to exit.
                      
                     """;

            //Store user choice for first menu.
            System.out.println(menu);
            String choice = sc.nextLine();

            //If else statemnet to allow user to continue to the second menu or exit.
            if (choice.contains("1")) {

                String menu2 = """
                               Please select one of the following menu items:
                               
                               (1) Capture a new series.
                               (2) Search for a series.
                               (3) Update series details.
                               (4) Delete a series.
                               (5) Print series report - 2025.
                               (6) Exit application.
                               """;

                //Stores the user's chosen option number.
                int enteredNum = 0;

                //Used to check whether the user entered a valid option.
                Boolean isValidNum = false;

                //Keeps asking the user for input until a valid number is entered.
                while (!isValidNum) {

                    //Displays the menu and prompts user for option.
                    System.out.println(menu2);

                    //Reads user input to validate number.
                    String userInput = sc.nextLine();

                    //Checks if the input is one character and is the integer number between 1 and 6
                    if (userInput.length() == 1 && "123456".contains(userInput)) {

                        //Converts the valid input into an integer and stores it.
                        enteredNum = Integer.parseInt(userInput);

                        //Once input is valid, loop stops.
                        isValidNum = true;

                    } else {

                        //An error message is shown, if the user enters anything other than 1, 2, 3, 4, 5 or 6.
                        System.out.println("Invalid input! Enter ONLY a number between 1 and 6.");
                    }

                }

                switch (enteredNum) {
                    case 1:

                        //CaptureSeries method is called form the Series class.
                        srs.captureSeries();

                        break;

                    case 2:

                        int idSearch;

                        boolean validInput = false;

                        while (!validInput) {
                            System.out.println("To search for a series, please enter the series ID: \n-------------------------------------------------");

                            //Checks if input is an integer.
                            if (sc.hasNextInt()) {

                                idSearch = sc.nextInt();
                                sc.nextLine();

                                validInput = true;

                                //Calls the SearchSeries method to check if a series with the entered ID exists.
                                SeriesModel result = srs.searchSeries(idSearch);

                                //Checks if the object's series ID matches the ID entered by the user.
                                if (result != null) {

                                    //DisplaySeriesDetails method is called to display all the details for a series.
                                    srs.displaySeriesDetails(result);

                                } else {

                                    //An error message is displayed if no matching ID was found.
                                    System.out.println("Series with series ID " + idSearch + " was not found!");

                                }

                            } else {

                                //Shows an error message if the input is not an integer.
                                System.out.println("Invalid input! Please enter a number for the series ID.");
                                sc.nextLine();
                            }
                        }

                        break;

                    case 3:

                        System.out.println("Enter the ID for the series you want to update: ");
                        int idSrch = sc.nextInt();
                        sc.nextLine();

                        //Calls the SearchSeries method to check if a series with the entered ID exists.
                        SeriesModel idCheck = srs.searchSeries(idSrch);

                        //If a series with the entered ID exists, the user is prompt for new details.
                        if (idCheck != null) {

                            System.out.println("Enter the series name you want updated (Press enter if you do not wish to change it.): ");
                            String newName = sc.nextLine();

                            //Declaring variable for new age so that it can be used outside the loop.
                            int newAge;

                            //Keeps asking for age until valid.
                            while (true) {

                                System.out.println("Enter the age restriction you want updated (2-18 or enter 0 to skip): ");

                                //Checks if an integer was entered.
                                if (sc.hasNextInt()) {

                                    newAge = sc.nextInt();
                                    sc.nextLine();

                                    //Checks if age was entered and not skipped or if the age is within the age limit.
                                    if (newAge == 0 || srs.ageRestriction(newAge)) {

                                        break;

                                    } else {

                                        //Displays an error message if the age is not within the age limit.
                                        System.out.println("Invalid age! Must be between 2 and 18.");
                                    }

                                } else {

                                    //Displays an error message if the age is not in numerical form.
                                    System.out.println("Please enter a number!");
                                    sc.next();
                                }
                            }

                            System.out.println("Enter the number of episodes you want updated (Enter 0 if you do not wish to change it.) : ");
                            int newEpisodes = sc.nextInt();
                            sc.nextLine();

                            System.out.println("Series updated successfully.");

                            //Calls the updateSeries method to actually apply and update the new values.
                            srs.updateSeries(idSrch, newName, newAge, newEpisodes);

                        } else {

                            //If no series was found with the entered ID, an error message is shown.
                            System.out.println("Series with series ID " + idSrch + " was not found.");
                        }

                        break;

                    case 4:

                        System.out.println("Enter the series ID for the series you wish to delete: ");
                        int deleteID = sc.nextInt();
                        sc.nextLine();

                        //Searches for the series in the list by its ID.
                        SeriesModel rst = srs.searchSeries(deleteID);

                        //Checks if the series exists in the list.
                        if (rst != null) {

                            System.out.println("Are you sure you want to delete series " + deleteID + " from the system? \nEnter yes (y) to delete.");
                            String answ = sc.nextLine();

                            //Checks if the user confirmed to delete.
                            if (answ.equalsIgnoreCase("y") || answ.equalsIgnoreCase("yes")) {

                                boolean deleted = srs.deleteSeries(deleteID);

                                //If delete was successful, a confirmation is displayed.
                                if (deleted) {

                                    System.out.println("Series with series ID " + deleteID + " was deleted.");

                                }

                                //If the user does not return, returns to main menu.
                            } else {

                                System.out.println("Returning to menu...");
                            }

                            //If the entered series ID does not exist in the list an error message is displayed.  
                        } else {

                            System.out.println("Series with series ID " + deleteID + " was not found!");
                        }

                        break;

                    case 5:

                        //Calls the seriesReport method to display a report of all the series.
                        srs.seriesReport();

                        break;

                    case 6:

                        //Calls the exitSeriesApplication to exit the application.
                        srs.exitSeriesApplication();

                        break;

                }

            } else {

                System.out.println("Exiting Menu.");

                //Terminate the program / Exits system.
                System.exit(0);
            }
        }
    }
}
