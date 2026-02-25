/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Question2;

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
     * Test of getSalesOverLimit method, of class ProductSales.
     */
    @Test
    public void testGetSalesOverLimit_ReturnsNumberOfSalesOver() {
        System.out.println("getSalesOverLimit - Returns Number Of Sales over limit");

        ProductSales instance = new ProductSales();

        //Expected result should be 2 as only two sales are over 500.
        int expResult = 2;

        //Reslut should be the same as expected result.
        int result = instance.getSalesOverLimit();

        //expResult and result sould match and if so should return true.
        assertEquals(expResult, result);

    }

    /**
     * Test of getSalesUnderLimit method, of class ProductSales.
     */
    @Test
    public void testGetSalesUnderLimit_ReturnsNumberOfSalesUnder() {
        System.out.println("getSalesUnderLimit - Returns Number Of Sales under limit");

        ProductSales instance = new ProductSales();

        //Expected result should be 4 as  four sales are under 500.
        int expResult = 4;

        //Reslut should be the same as expected result.
        int result = instance.getSalesUnderLimit();

        //expResult and result sould match and if so should return true.
        assertEquals(expResult, result);

    }

}
