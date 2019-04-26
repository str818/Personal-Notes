package com.str818.util;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculateTest {

    @BeforeClass
    public static void beforeTest(){
        System.out.println("Start");
    }

    @AfterClass
    public static void afterTest(){
        System.out.println("Over");
    }

    @Test
    public void testDivide(){
        assertEquals(1, new Calculate().abs(1));
        assertEquals(1, new Calculate().abs(-1));
        assertEquals(0, new Calculate().abs(0));
    }
} 
