package com.example.plantbasics_pocketgarden;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.junit.Test;
import org.junit.runner.RunWith;

import plant.GetPlantInfo;

import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class GetPlantInfoTest {
    Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
    GetPlantInfo query = new GetPlantInfo(appContext);
    @Test
    public void getPlantInfoSNameTest1() {
        String sciName = "Fragaria chiloensis";
        String output = query.getSciNameInfo(sciName);
        assertEquals(output.substring(14, 20), "137607");
    }

    @Test
    public void getPlantInfoSNameTest2() {
        String sciName = "Petunia axillaris";
        String output = query.getSciNameInfo(sciName);
        assertEquals(output.substring(14, 20), "164803");
    }

    @Test
    public void getPlantInfoSNameTest3() {
        String sciName = "Vaccinium corymbosum";
        String output = query.getSciNameInfo(sciName);
        assertEquals(output.substring(14, 20), "190761");
    }

    @Test
    public void getPlantInfoSNameTest4() {
        String sciName = "Mangifera indica";
        String output = query.getSciNameInfo(sciName);
        assertEquals(output.substring(14, 20), "154534");
    }

    @Test
    public void getCommonNameInfoTest1(){
        String cName = "Beach Strawberry";
        String output = query.getCommonNameInfo(cName);
        assertEquals(output.substring(14, 20), "137607");
    }@Test
    public void getCommonNameInfoTest2() {
        String cName = "Large white Petunia";
        String output = query.getCommonNameInfo(cName);
        assertEquals(output.substring(14, 20), "164803");
    }

    @Test
    public void getCommonNameInfoTest3() {
        String cName = "Highbush Blueberry";
        String output = query.getCommonNameInfo(cName);
        assertEquals(output.substring(14, 20), "190761");
    }

    @Test
    public void getCommonNameInfoTest4() {
        String cName = "Mango";
        String output = query.getCommonNameInfo(cName);
        assertEquals(output.substring(14, 20), "154534");
    }
}