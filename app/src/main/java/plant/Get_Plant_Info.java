package plant;


import android.content.res.Resources;

import com.example.pocketgarden.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;


//need to create your own account with
public class Get_Plant_Info {
    private static String apiRequest = Resources.getSystem().getString(R.string.trefle_api_url);
    private static String token = Resources.getSystem().getString(R.string.trefle_token);
    private static HttpsURLConnection connection;


    //only scientific name
    public JSONArray getPlantInfoSName(String scientificName) throws JSONException {
        String location = apiRequest + "/species" + scientificName + "?" + token;
        String line;
        StringBuffer responseContent = new StringBuffer();
        BufferedReader reader;

        try {
            URL url = new URL (location);
            connection = (HttpsURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();

            if(status > 299){
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while((line = reader.readLine()) != null){
                    responseContent.append(line);
                }
                reader.close();
            }

            else{
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while((line = reader.readLine()) != null){
                    responseContent.append(line);
                }
                reader.close();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            connection.disconnect();
            return new JSONArray(responseContent.toString());
        }
    }

    public String getSciName(String commonName){
        String location = apiRequest + "/plants/search?" + token + "&" + process(commonName);
        String line;
        StringBuffer responseContent = new StringBuffer();
        BufferedReader reader;

        try {
            URL url = new URL (location);
            connection = (HttpsURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();

            if(status > 299){
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while((line = reader.readLine()) != null){
                    responseContent.append(line);
                }
                reader.close();
            }

            else{
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while((line = reader.readLine()) != null){
                    responseContent.append(line);
                }
                reader.close();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            connection.disconnect();
        }
        JSONArray allData;
        try {
            allData = new JSONArray(responseContent.toString());
            return allData.getString(12);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


    public JSONArray parseData(JSONArray input){
        JSONArray output = new JSONArray();
        try {
            output.put(input.getJSONArray(27));
            output.put(input.getJSONArray(28));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return output;
    }

    public String process(String input){
        String[] words = input.split(" ");
        String output = "";

        for (String word: words){
            output += words + "%20";
        }

        return output.substring(0, output.length()-2);
    }


}
