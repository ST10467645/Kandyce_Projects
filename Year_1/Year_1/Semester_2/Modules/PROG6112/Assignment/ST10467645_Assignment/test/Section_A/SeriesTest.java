/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Section_A;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

/**
 *
 * @author ST10467645
 */
public class SeriesTest {

    public SeriesTest() {
    }

    /**
     * Test for the searchSeries method in the Series class.
     *
     * This test checks if the method can find a series when it exists in the
     * list. I added a fake series (Breaking Bad) and then searched for it using
     * the ID. The test should return the same series.
     */
    @Test
    public void testsearchSeries() {

        System.out.println("SearchSeries - Found");

        //Instantiating the Series object that holds the list.
        Series instance = new Series();

        //A manual SeriesModel with test data.
        SeriesModel sm = new SeriesModel();
        sm.setSeriesID(1);
        sm.setSeriesName("Breaking Bad");
        sm.setSeriesAge(16);
        sm.setSeriesNumberOfEpisodes(62);

        //Adds it directly to the list using the getter in the Series class.
        instance.getSeriesList().add(sm);

        //Searches for the series by its ID.
        SeriesModel result = instance.searchSeries(1);

        //Checks the ID and Name match the one that was added.
        assertNotNull(result);
        assertEquals(1, result.getSeriesID());
        assertEquals("Breaking Bad", result.getSeriesName());

    }

    /**
     * Test for the searchSeries method in the Series class.
     *
     * This test checks what happens when the series is not in the list. I
     * search for an ID (99) that was never added, so the result should be null.
     */
    @Test
    public void testsearchSeries_SeriesNotFound() {

        System.out.println("SearchSeries - Not Found");

        //Instantiating the Series object that holds the list.
        Series instance = new Series();

        //Searches for an ID that doesn't exist.
        SeriesModel result = instance.searchSeries(99);

        //Result should be null and it is checked.
        assertNull(result);
    }

    /**
     * Test for the ageRestriction method in the Series class.
     *
     * The age chosen is 15, which should be allowed according to the
     * restriction rules. The method is expected to return true, and the
     * assertion confirms that the actual result matches the expected outcome.
     */
    @Test
    public void testAgeRestriction__AgeValid() {
        System.out.println("AgeRestriction - Valid Age");

        //Declaring the age to be tested.
        int age = 15;

        //Instantiating the Series object that contains the method.
        Series instance = new Series();

        //Expected result is true since age 15 is valid.
        boolean expResult = true;

        //Calling the method with the test age.
        boolean result = instance.ageRestriction(age);

        //Checks if the expected result matches the actual result.
        assertEquals(expResult, result);

    }

    /**
     * Test for the ageRestriction method in the Series class.
     *
     * The age chosen is 23, which should be invalid according to the
     * restriction rules. The method is expected to return false, and the
     * assertion confirms that the actual result matches the expected outcome.
     */
    @Test
    public void testAgeRestriction__AgeInvalid() {
        System.out.println("AgeRestriction - Invalid Age");

        //Declaring the age to be tested.
        int age = 23;

        //Instantiating the Series object that contains the method.
        Series instance = new Series();

        //Expected result is false since age 23 is invalid.
        boolean expResult = false;

        //Calling the method with the test age.
        boolean result = instance.ageRestriction(age);

        //Checks if the expected result matches the actual result.
        assertEquals(expResult, result);

    }

    /**
     * Test for the updateSeries method in the Series class.
     *
     * This test checks that when a series with a specific ID exists in the
     * series list, calling updateSeries with new name, age restriction, and
     * number of episodes correctly updates the series details. The returned
     * SeriesModel is expected to reflect the updated values, and assertions
     * confirm that the changes were applied successfully.
     */
    @Test
    public void testUpdateSeries() {
        System.out.println("UpdateSeries");

        //Instantiating the Series object that contains the method.
        Series instance = new Series();

        //A manual SeriesModel with test data.
        SeriesModel sm = new SeriesModel();
        sm.setSeriesID(1);
        sm.setSeriesName("Old Name");
        sm.setSeriesAge(10);
        sm.setSeriesNumberOfEpisodes(5);

        //Adds it directly to the list using the getter in the Series class.
        instance.getSeriesList().add(sm);

        //New values to update.
        int idSrch = 1;
        String newName = "New Name";
        int newAge = 12;
        int newEpisodes = 8;

        //Calls updateSeries method to update.
        SeriesModel result = instance.updateSeries(idSrch, newName, newAge, newEpisodes);

        //Checks that the object was updated successfully.
        assertNotNull(result);
        assertEquals(idSrch, result.getSeriesID());
        assertEquals(newName, result.getSeriesName());
        assertEquals(newAge, result.getSeriesAge());
        assertEquals(newEpisodes, result.getSeriesNumberOfEpisodes());
    }

    /**
     * Test for the deleteSeries method in the Series class.
     *
     * This test checks that when a series with a specific ID exists in the
     * series list, calling deleteSeries with that ID correctly removes the
     * series from the list. The method is expected to return true, and the
     * assertion confirms that the series was successfully deleted.
     */
    @Test
    public void testDeleteSeries() {
        System.out.println("DeleteSeries - Series Found and Deleted");

        //Instantiating the Series object that contains the method.
        Series instance = new Series();

        //A manual SeriesModel with test data.
        SeriesModel sm = new SeriesModel();
        sm.setSeriesID(1);
        sm.setSeriesName("Breaking Bad");
        sm.setSeriesAge(16);
        sm.setSeriesNumberOfEpisodes(62);

        //Adds it directly to the list using the getter in the Series class.
        instance.getSeriesList().add(sm);

        //Declaring the ID to be delete the series.
        int deleteID = 1;

        //Expected result is true since the ID exists and belongs to a series.
        boolean expResult = true;

        //Calling the method with the test ID to verify if the ID exists to delete the series.
        boolean result = instance.deleteSeries(deleteID);

        //Checks if the expected result matches the actual result.
        assertEquals(expResult, result);

    }

    /**
     * Test for the deleteSeries method in the Series class.
     *
     * This test verifies that when a series with a specific ID is not found
     * in the series list, calling deleteSeries with that ID does not remove any
     * series. The method is expected to return false, and the assertion
     * confirms that the actual result matches the expected outcome.
     */
    @Test
    public void testDeleteSeries_SeriesNotFound() {
        System.out.println("DeleteSeries- Series Not Found.");

        //Declaring the ID to be delete the series.
        int deleteID = 101;

        //Instantiating the Series object that contains the method.
        Series instance = new Series();

        //Expected result is false since the ID does not exist.
        boolean expResult = false;

        //Calling the method with the test ID to verify if the ID exists to delete the series.
        boolean result = instance.deleteSeries(deleteID);

        //Checks if the expected result matches the actual result.
        assertEquals(expResult, result);

    }

}
