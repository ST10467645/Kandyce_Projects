/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Question2;

/**
 *
 * @author lab_services_student
 */
public class ProductSales implements IProductSales {

    //Declaring variables.
    int[][] productSales = {{300, 150, 700}, {250, 200, 600}};
    
    //Declaring array to store the names of the years.
    String[] years = {"Sales For year 1", "Sales For year 2"};

    

    //Method to get the productSales Array.
    @Override
    public int[][] getProductSales() {

        return productSales;

    }

    //method to get the total sales.
    @Override
    public int getTotalSales() {

        int[][] pSales = ProductSales.this.getProductSales();

        int total = 0;

        for (int i = 0; i < pSales.length; i++) {

            for (int j = 0; j < 3; j++) {

                total += pSales[i][j];

            }

        }

        return total;

    }

    //Method to get the sales that are over the limit.
    @Override
    public int getSalesOverLimit() {

        int[][] sales = ProductSales.this.getProductSales();

        int limit = 500;

        int overlmtCount = 0;

        for (int i = 0; i < sales.length; i++) {

            for (int j = 0; j < 3; j++) {

                if (sales[i][j] > limit) {

                    overlmtCount++;

                }

            }

        }

        return overlmtCount;

    }

    //Method to get the sales that are under the limit.
    @Override
    public int getSalesUnderLimit() {

        int[][] sales = ProductSales.this.getProductSales();

        int limit = 500;

        int overlmtCount = 0;

        for (int i = 0; i < sales.length; i++) {

            for (int j = 0; j < 3; j++) {

                if (sales[i][j] < limit) {

                    overlmtCount++;

                }

            }

        }

        return overlmtCount;

    }

    //Method that gets the number of proccessed years.
    @Override
    public int getProductsProccessed() {

        int[][] sales = ProductSales.this.getProductSales();

        int ProccessedYears = years.length;

        return ProccessedYears;

    }

    //Method to calculate the average sales.
    @Override
    public double getAverageSales() {

        int total = ProductSales.this.getTotalSales();

        return total / 6.0;

    }

}
