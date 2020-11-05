package plant;

import org.json.JSONArray;
import org.json.JSONException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Get_Plant_InfoTest {

    @Test
    public void getPlantInfoSNameTest1() throws JSONException {
        String sciName = "Fragaria chiloensis";
        JSONArray output = Get_Plant_Info.getSciNameInfo(sciName);
        assertEquals(output.getJSONArray(0).get(0), 137607);
    }

    @Test
    public void getPlantInfoSNameTest2() throws JSONException {
        String sciName = "Petunia axillaris";
        JSONArray output = Get_Plant_Info.getSciNameInfo(sciName);
        assertEquals(output.getJSONArray(0).get(0), 164803);
    }

    @Test
    public void getPlantInfoSNameTest3() throws JSONException {
        String sciName = "Vaccinium corymbosum";
        JSONArray output = Get_Plant_Info.getSciNameInfo(sciName);
        assertEquals(output.getJSONArray(0).get(0), 190761);
    }

    @Test
    public void getPlantInfoSNameTest4() throws JSONException {
        String sciName = "Mangifera indica";
        JSONArray output = Get_Plant_Info.getSciNameInfo(sciName);
        assertEquals(output.getJSONArray(0).get(0), 154534);
    }

}