package plant;

import org.json.JSONArray;
import org.json.JSONException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Get_Plant_InfoTest {

    @Test
    public void getPlantInfoSNameTest1() throws JSONException {
        String sciName = "Fragaria chiloensis";
        JSONArray output = Get_Plant_Info.getPlantInfoSName(sciName);
        assertEquals(output.getJSONObject(1).get("id"), 131974);
    }

    @Test
    public void getPlantInfoSNameTest2() throws JSONException {
        String sciName = "Petunia axillaris";
        JSONArray output = Get_Plant_Info.getPlantInfoSName(sciName);
        assertEquals(output.getJSONObject(1).get("id"), 159170);
    }

    @Test
    public void getPlantInfoSNameTest3() throws JSONException {
        String sciName = "Vaccinium corymbosum";
        JSONArray output = Get_Plant_Info.getPlantInfoSName(sciName);
        assertEquals(output.getJSONObject(1).get("id"), 185128);
    }

    @Test
    public void getPlantInfoSNameTest4() throws JSONException {
        String sciName = "Mangifera indica";
        JSONArray output = Get_Plant_Info.getPlantInfoSName(sciName);
        assertEquals(output.getJSONObject(1).get("id"), 148901);
    }

    @Test
    public void getSciNameTest() {

    }

}