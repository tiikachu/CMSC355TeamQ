package com.example.pocketgarden;

import org.junit.Test;

import plant.Plant_Object;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testGetName(){
        System.out.println("getName");
        Plant_Object plant = new Plant_Object();
        String expResult = "plant";
        plant.setName(expResult);
        String result = plant.getName();
        assertEquals(expResult, result);
    }
}