package com.techelevator;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class PurchaseMenuTest extends TestCase {

    private PurchaseMenu purchaseMenuTest;

    @Before
    public void setUp() {
        purchaseMenuTest = new PurchaseMenu();
    }

    @Test
    public void testCheckMoney() {
        assertEquals(true, purchaseMenuTest.checkMoney(1));
        assertEquals(false, purchaseMenuTest.checkMoney(20));
        assertEquals(true, purchaseMenuTest.checkMoney(10));
    }

}