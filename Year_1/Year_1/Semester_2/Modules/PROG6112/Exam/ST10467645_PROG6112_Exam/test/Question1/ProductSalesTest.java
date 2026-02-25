/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Question1;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author lab_services_student
 */
public class ProductSalesTest {

    public ProductSalesTest() {
    }

    /**
     * Test of totalSales method, of class ProductSales.
     */
    @org.junit.Test
    public void testTotalSales_ReturnsTotalSales() {

        System.out.println("totalSales - Returns Total Sales");

        //Declaring and initialising array
        int[][] productSales = {{100, 200, 700},
        {500, 300, 200}};

        ProductSales instance = new ProductSales();

        //Total for this array should be 2000 after adding all values together.
        int expResult = 2000;

        //Reslut should be the same as expected result.
        int result = instance.totalSales(productSales);

        //expResult and result sould match and if so should return true.
        assertEquals(expResult, result);

    }

    /**
     * Test of averageSales method, of class ProductSales.
     */
    @org.junit.Test
    public void testAverageSales_ReturnsAverageProductSales() {

        System.out.println("averageSales - Returns Average Product Sales");

        //Declaring and initialising array
        int[][] productSales = {{100, 200, 700},
        {500, 300, 200}};

        ProductSales instance = new ProductSales();

        //average for this array should be 333.33 after adding all values together and dividing by 6.
        double expResult = 333.33;

        //Reslut should be the same as expected result.
        double result = instance.averageSales(productSales);

        //expResult and result sould match and if so should return true.
        assertEquals(expResult, result, 0.01);

    }

}
