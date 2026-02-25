/**
 * The SeriesModel class is a blueprint for creating and storing series objects.
 *
 * This class acts as a data model by:
 * - Declaring private variables for series details such as ID, name, age restriction,
 *   and number of episodes.
 * - Providing getters and setters to access and update these values safely.
 * - Allowing other classes (like Series) to create objects and work with
 *   individual series records.
 *
 * The main purpose of this class is to hold series information in a structured way
 * so that it can be managed and displayed throughout the application.
 */
package Section_A;

/**
 *
 * @author ST10467645
 */
public class SeriesModel {

    //Declaring series variables and making them protected for information hiding.
    private int seriesID;
    private String seriesName;
    private int seriesAge;
    private int seriesNumberOfEpisodes;

    //Default constructor for the SeriesModel class.
    public SeriesModel() {
    }

    /**
     * Getters and setters for access to protected series variables.
     * 
     * @return series variables.
     */
    public int getSeriesID() {
        return seriesID;
    }

    public void setSeriesID(int seriesID) {
        this.seriesID = seriesID;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public int getSeriesAge() {
        return seriesAge;
    }

    public void setSeriesAge(int seriesAge) {
        this.seriesAge = seriesAge;
    }

    public int getSeriesNumberOfEpisodes() {
        return seriesNumberOfEpisodes;
    }

    public void setSeriesNumberOfEpisodes(int seriesNumberOfEpisodes) {
        this.seriesNumberOfEpisodes = seriesNumberOfEpisodes;
    }

}
