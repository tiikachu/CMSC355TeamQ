package plant;

import org.json.JSONArray;
import org.json.JSONException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Get_Plant_InfoTest {
/*
    @Test
    public void getPlantInfoSNameTest1() throws JSONException {
        String sciName = "Fragaria chiloensis";
        String output = Get_Plant_Info.getSciNameInfo(sciName);
        System.out.println(output.toString());
        assertEquals(output.substring(13,19), 131974);
    }

    @Test
    public void getPlantInfoSNameTest2() throws JSONException {
        String sciName = "Petunia axillaris";
        String output = Get_Plant_Info.getSciNameInfo(sciName);
        assertEquals(output.substring(13,19), 159170);
    }

    @Test
    public void getPlantInfoSNameTest3() throws JSONException {
        String sciName = "Vaccinium corymbosum";
        String output = Get_Plant_Info.getSciNameInfo(sciName);
        assertEquals(output.substring(13,19), 185128);
    }

    @Test
    public void getPlantInfoSNameTest4() throws JSONException {
        String sciName = "Mangifera indica";
        String output = Get_Plant_Info.getSciNameInfo(sciName);
        assertEquals(output.substring(13,19), 148901);
    }

    /*@Test
    public void getSciNameTest() {

    }*/

}