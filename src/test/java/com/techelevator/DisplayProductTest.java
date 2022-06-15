package com.techelevator;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class DisplayProductTest extends TestCase {
    Product displayProductTest = new Product();

    @Before
    public void setUp() {
        displayProductTest = new Product("B3", "Wonka Bar", 1.50, "Candy", 5);
    }

    public void testGetProductBySlotID() {
        assertEquals("B3", displayProductTest.getSlotId());
        assertEquals("Wonka Bar", displayProductTest.getName());
        assertEquals(1.50, displayProductTest.getPurchasePrice());
        assertEquals("Candy", displayProductTest.getType());
        assertEquals(5, displayProductTest.getQuantity());
    }

}