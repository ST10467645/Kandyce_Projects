/**
 * The Series class is responsible for managing all operations related to TV series.
 *
 * It acts as the main logic class of the application by:
 * - Storing a list of SeriesModel objects in an ArrayList.
 * - Allowing users to capture new series details and save them into the list.
 * - Searching for series by their unique ID.
 * - Displaying series details in a clear format.
 * - Updating and deleting existing series records.
 * - Generating a full report of all series saved in the system.
 * - Exiting the application safely when requested.
 *
 * In short, this class provides all the functionality needed to add, update,
 * remove, and display series data, while working closely with the SeriesModel
 * class to store the actual information.
 */
package Section_A;

import java.util.*;

/**
 *
 * @author ST10467645
 */
public class Series {

    //Instantiate the array list to store all the series details.
    private ArrayList<SeriesModel> seriesList = new ArrayList();

    //Instantiating scanner object.
    Scanner sc = new Scanner(System.in);

    //Getter for unit tests.
    public ArrayList<SeriesModel> getSeriesList() {
        return seriesList;
    }

    /**
     * Validates the given age for a series.
     *
     * This method checks if the age is within the valid range (2-18).
     *
     * @param age The age restriction to validate.
     * @return true if the age is valid, false otherwise.
     */
    public boolean ageRestriction(int age) {
        return age >= 2 && age <= 18;
    }

    /**
     * This method is used to capture a new series from the user. It asks the
     * user for series details (ID, name, age restriction, number of episodes),
     * validates the age restriction, and then saves the details in an
     * ArrayList.
     */
    public void captureSeries() {

        //Instantiating the SeriesModel object.
        SeriesModel sm = new SeriesModel();

        System.out.println("Enter the series ID (Enter just a numerical ID):");
        int id = sc.nextInt();
        sc.nextLine();

        //Checks if the ID already exists in the seriesList
        if (searchSeries(id) != null) {

            System.out.println("A series with ID " + id + " already exists! Please enter a differnt ID.");

            return;
        }

        //Setter used to save all the details into the SeriesModel object.
        sm.setSeriesID(id);

        System.out.println("Enter the series name: ");
        String name = sc.nextLine();
        //Setter used to save all the details into the SeriesModel object.
        sm.setSeriesName(name);

        //Declaring age variable.
        int age;

        //While loop used to keep prompting the user for a valid age restriction.
        while (true) {

            System.out.println("Enter the series age restriction:");

            //Check if user input is an integer.
            if (sc.hasNextInt()) {
                age = sc.nextInt();
                sc.nextLine();

                //Uses the ageRestriction method to validate the age limit.  
                if (ageRestriction(age)) {

                    //if age is valid, it is saved in the SeriesModel object.
                    sm.setSeriesAge(age);

                    break;

                } else {

                    //Age is not within valid range
                    System.out.println("Invalid age! Must be between 2 and 18.");
                }

            } else {

                //User entered something that is not an integer.
                System.out.println("You have entered an incorrect series age!!! \nPlease re-enter the series age >>");
                sc.next();
            }
        }

        System.out.println("Enter the number of episodes for " + sm.getSeriesName());
        int numEp = sc.nextInt();
        sc.nextLine();
        //Setter used to save all the details into the SeriesModel object.
        sm.setSeriesNumberOfEpisodes(numEp);

        //Saves all the details from the completed SeriesModel object into the ArrayList.
        seriesList.add(sm);

        //Displays a success message to inform the user that the series details have been successfully saved.
        System.out.println("Series processed successfully!");

    }

    /**
     * This method is a version of the original "captureSeries" method that
     * takes all series details as parameters instead of prompting the user.It
     * creates a new SeriesModel object, sets its properties (ID, name, age
     * restriction, number of episodes), adds it to the seriesList, and prints a
     * confirmation message.
     *
     *
     * @return The SeriesModel object that was created and added to seriesList.
     */
    public SeriesModel searchSeries(int idSearch) {

        //Loop used to search through the ArrayList to check each series one by one.
        for (int i = 0; i < seriesList.size(); i++) {

            //Gets the SeriesModel object at the current index in the list.
            SeriesModel sm = seriesList.get(i);

            //Checks if the series ID of this object matches the ID being searching for.
            if (sm.getSeriesID() == idSearch) {

                //if matching ID was found, the object of the matching ID is returned.
                return sm;
            }

        }

        //Return the null if not found.
        return null;
    }

    /**
     * Displays the details of a given SeriesModel object.
     *
     * This method prints the series ID, name, age restriction, and number of
     * episodes to the console.
     *
     * @param sm The SeriesModel object whose details will be displayed.
     */
    public void displaySeriesDetails(SeriesModel sm) {

        System.out.println("Series ID: " + sm.getSeriesID());
        System.out.println("Series Name: " + sm.getSeriesName());
        System.out.println("Series Age Restriction: " + sm.getSeriesAge());
        System.out.println("Series Number of Episodes: " + sm.getSeriesNumberOfEpisodes());
    }

    /**
     * This method updates the details of an existing series in the seriesList.
     *
     * It searches for a series object by its ID. If the series is found, it
     * updates the name, age restriction (only if between 2 and 18), and number
     * of episodes. The method then returns the updated SeriesModel object. If
     * no series with the given ID exists, it returns null.
     *
     * @param idSrch The ID of the series to search for and update.
     * @param newName The new name to assign to the series.
     * @param newAge The new age restriction to assign to the series (must be
     * 3-17).
     * @param newEpisodes The new total number of episodes for the series.
     * @return The updated SeriesModel object if the ID was found, or null if no
     * series with the given ID exists.
     */
    public SeriesModel updateSeries(int idSrch, String newName, int newAge, int newEpisodes) {

        SeriesModel updatedSeries = null;

        StringBuilder changes = new StringBuilder();

        //For loop used to loop through the seriesList to check each SeriesModel object.
        for (int i = 0; i < seriesList.size(); i++) {

            //Gets the SeriesModel object at the current index in the list.
            SeriesModel sm = seriesList.get(i);

            //Checks if the object's ID matches the one being earching for.
            if (sm.getSeriesID() == idSrch) {

                updatedSeries = sm;

                if (newName != null && !newName.isBlank()) {

                    //Update the series name.
                    sm.setSeriesName(newName);

                    changes.append("Name updated to: ").append(newName).append(".\n");

                }

                if (newAge != 0 && ageRestriction(newAge)) {

                    //Updates age restriction if it is valid.
                    sm.setSeriesAge(newAge);

                    changes.append("Restriction age updated to: ").append(newAge).append(".\n");
                }

                if (newEpisodes != 0) {

                    //Updates the number of episodes
                    sm.setSeriesNumberOfEpisodes(newEpisodes);

                    changes.append("Number of episodes updated to: ").append(newEpisodes).append(".");

                }

                System.out.println("Updates made: \n" + changes.toString());

                //Return the updated object.
                return sm;

            }

        }

        //Return the null if an ID is not found.
        return null;

    }

    /**
     * Deletes a series from the seriesList by its ID.
     *
     * This method loops through all the SeriesModel objects stored in the
     * seriesList and compares each object's series ID with the ID entered by
     * the user. If a match is found, the object is removed from the list and
     * the method returns true. If no series with the given ID exists, the
     * method returns false.
     *
     * @param deleteID The series ID entered by the user to be deleted.
     * @return true if the series was successfully deleted, false if no match
     * was found.
     */
    public boolean deleteSeries(int deleteID) {

        //For loop used to loop through the seriesList to check each SeriesModel object.
        for (int i = 0; i < seriesList.size(); i++) {

            //Gets the SeriesModel object at the current index in the list.
            SeriesModel sm = seriesList.get(i);

            //Checks if the current object's seriesID matches the entered ID.
            if (sm.getSeriesID() == deleteID) {

                //Removes object at current index where entered id matches series id.
                seriesList.remove(i);

                //Returns true to show that the series was successfully deleted.
                return true;

            }

        }

        //Return false if an ID is not found.
        return false;

    }

    /**
     * Generates and prints a detailed report of all the series in the
     * seriesList.
     *
     * If the list is empty, a message is displayed saying no series are
     * available.Otherwise, it prints a report header, then it loops through the
     * ArrayList and displays details for each series by calling the
     * displaySeriesDetails() method.
     */
    public void seriesReport() {

        //Checks if the list is empty before printing any series details.
        if (seriesList.isEmpty()) {

            System.out.println("No series available to display.");

        }

        System.out.println("************************************************");
        System.out.println("             SERIES REPORT - 2025               ");
        System.out.println("************************************************");

        //Loop used to search through the ArrayList to check each series one by one.
        for (int i = 0; i < seriesList.size(); i++) {

            //Gets the SeriesModel object at the current index in the list.
            SeriesModel sm = seriesList.get(i);

            System.out.println("------------------------------------------------- \nSeries " + (i + 1) + "\n-------------------------------------------------");

            //Calls the method to display the details of the current series.
            displaySeriesDetails(sm);

        }

    }

    /**
     * Exits the series management application.
     *
     * Displays a message to inform the user that the application is closing.
     * Terminates the program using System.exit(0).
     */
    public void exitSeriesApplication() {

        System.out.println("Exiting application.");

        //Terminate the program / Exits system.
        System.exit(0);

    }

}
