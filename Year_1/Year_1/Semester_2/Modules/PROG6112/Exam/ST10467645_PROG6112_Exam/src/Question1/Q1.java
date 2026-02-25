/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Question1;

/**
 *
 * @author lab_services_student
 */
public class Q1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //Declaring array to store the years names.
        String[] years = {"Year 1", "Year 2"};

        //Declaring 2D array to store the product sales for 3 different quaters over 2 years.
        int[][] pSales = {{300, 150, 700},
        {250, 200, 600}};

        //Instantiating constructor object for ProductSales class.
        ProductSales ps = new ProductSales();

        //Calculating total sales.
        int totalSales = ps.totalSales(pSales);

        //Calculating average sales.
        double averageSales = ps.averageSales(pSales);

        //Calculating maximum sales.
        int maxSale = ps.maxSale(pSales);

        //Calculating minimum sales.
        int minSale = ps.minSaless(pSales);

        //Displaying the product sales report.
        System.out.println("Product Sales Report - 2025 ");
        System.out.println("----------------------------");

        //Displaying total sales.
        System.out.println("Total sales: " + totalSales);

        //Displaying Average sales. Sample output shows 367 so format rounds andswer to nearest one.
        System.out.println("Average Sales: " + String.format("%.0f", averageSales));

        //Displaying Maximum sales.
        System.out.println("Maximum Sale: " + maxSale);

        //Displaying Minimum sales.
        System.out.println("Minimum Sale: " + minSale);
        System.out.println("----------------------------");

    }

}
