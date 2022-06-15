package com.techelevator;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ProductTest extends TestCase {
   private Product productTest;

   @Before
   public void setUp() {
       productTest = new Product("D4", "Triplemint", 0.75, "Gum", 5);
   }

   @Test

    public void testGetSlotId() {
       assertEquals("D4", productTest.getSlotId());
    }

    public void testTestGetName() {
       assertEquals("Triplemint", productTest.getName());
    }

    public void testGetPurchasePrice() {
       assertEquals(0.75, productTest.getPurchasePrice());
    }

    public void testGetType() {
       assertEquals("Gum", productTest.getType());
    }

    public void testGetQuantity() {
       assertEquals(5, productTest.getQuantity());
    }
}