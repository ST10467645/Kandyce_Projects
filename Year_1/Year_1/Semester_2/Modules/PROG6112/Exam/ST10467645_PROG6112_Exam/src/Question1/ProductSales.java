/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Question1;

/**
 *
 * @author lab_services_student
 */
public class ProductSales implements IProductSales {

    //Method to calculate the total sales for a year.
    @Override
    public int totalSales(int[][] productSales) {

        int total = 0;

        for (int i = 0; i < productSales.length; i++) {

            for (int j = 0; j < 3; j++) {

                total += productSales[i][j];

            }

        }

        return total;

    }

    //Method to calculate the average sales by calling totalSales method to get the total sales and diving it by 3.
    @Override
    public double averageSales(int[][] productSales) {

        double total = ProductSales.this.totalSales(productSales);

        return total / 6.0;

    }

    //Method to calculate the maximum sales by calling averageSales method to get the average sales.
    @Override
    public int maxSale(int[][] productSales) {

        int max = productSales[0][0];

        for (int i = 0; i < productSales.length; i++) {

            for (int j = 0; j < 3; j++) {

                if (productSales[i][j] > max) {

                    max = productSales[i][j];

                }

            }

        }

        return max;

    }

    //Method to calculate the minimum sales by calling averageSales method to get the average sales.
    @Override
    public int minSaless(int[][] productSales) {

        int min = productSales[0][0];

        for (int i = 0; i < productSales.length; i++) {

            for (int j = 0; j < 3; j++) {

                if (productSales[i][j] < min) {

                    min = productSales[i][j];

                }

            }

        }

        return min;

    }

}
