package plant;

import android.content.Context;
import com.example.pocketgarden.R;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

//need to create your own account with
public class GetPlantInfo {
  private final String apiRequest;
  private final String token;
  private HttpsURLConnection connection;
  private StringBuilder responseContent;

  public GetPlantInfo(Context context){
    apiRequest = context.getResources().getString(R.string.trefle_api_url);
    token = context.getResources().getString(R.string.trefle_token);
    responseContent = new StringBuilder();

  }

  /**
   * @param scientificName - scientific name either inputted directly or recieved from getSciName
   * @return JSON in string format of the entire page from the trefle API
   */
  public String getSciNameInfo(String scientificName) {
    String location = makeUrlSci(scientificName);
    StringBuilder responseContent = new StringBuilder();
    try {
      int status = makeConnection((location));
      responseContent = parseUrl(status);

    } catch (IOException e) {
      e.printStackTrace();
    }
    connection.disconnect();
    return responseContent.toString();
  }

  /**
   * @param scientificName scientific name to search in Trefle API
   * @return location- a URL that queries the trefle API for the scientific name
   */
  private String makeUrlSci(String scientificName) {
    return apiRequest + "species/" + scientificName.replace(' ', '-') + "?" + token;
  }

  /**
   * @param location URL to connect to
   * @return status of connection attempt after one is successful
   * @throws IOException if the location URL is incorrect
   */
  private int makeConnection(String location) throws IOException {
    int status = 201;
    int count = 0;
    int numAttempts = 5;
    while (status > 200 && count < numAttempts) {
      URL url = new URL(location);
      connection = (HttpsURLConnection) url.openConnection();
      connection.setRequestMethod("GET");
      connection.setConnectTimeout(5000);
      connection.setReadTimeout(5000);
      status = connection.getResponseCode();
      count++;
    }
    return status;
  }


  /**
   * @param commonName common name for a plant, e.g. strawberry
   * @return scientific name, e.g. Fragaria chiloensis
   */
  public String getCommonNameInfo(String commonName) {
    String location = makeUrlCommon(commonName);

    try {
      int status = makeConnection(location);
      responseContent = parseUrl(status);

    } catch (IOException e) {
      e.printStackTrace();
    }
    connection.disconnect();
    return parseCommon(responseContent.toString());
  }

  /**
   * @param commonName text input of a plant's common name, e.g. strawberry
   * @return a link to a trefle API query searching for the common name
   */
  private String makeUrlCommon(String commonName) {
    return apiRequest + "plants/search?q=" + commonName.replace(' ', '-') + "&" + token;
  }

  /**
   * @param status Code received after attempted connection to trefle API
   * @return responseContent all the data from the trefle API url
   * @throws IOException if the URL is invalid
   */
  private StringBuilder parseUrl(int status) throws IOException {
    String line;
    responseContent.setLength(0); //clear responseContent just in case
    BufferedReader reader;
    if (status > 299) {
      reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
      while ((line = reader.readLine()) != null) {
        responseContent.append(line);
      }
    } else {
      reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      while ((line = reader.readLine()) != null) {
        responseContent.append(line.replaceAll("\"", "\\\""));
      }
    }
    reader.close();
    return responseContent;
  }

  private String parseCommon(String input){
    int sciNameIndex = input.indexOf("scientific_name") + 18;
    if(sciNameIndex == 17){
      throw new IndexOutOfBoundsException();
    }
    int endIndex = input.indexOf("\"", sciNameIndex);
    return getSciNameInfo(input.substring(sciNameIndex, endIndex));
  }

  /**
   *
   * @param JSON output from getCommonNameInfo in a string format
   * @return a String array of length 3, array[0] is the common name, array[1] a range of min-max precipitation in mm (e.g. "140-500 mm"), array[2] is a url to an image of the plant
   */
  public String[] parseJSON(String JSON){
    String[] output = new String[3];
    int[] commonNameIndex = {JSON.indexOf("common_name") + 14, JSON.indexOf("slug")-3};
    output[0] = JSON.substring(commonNameIndex[0], commonNameIndex[1]);

    int[] wateringIndices = new int[4];
    wateringIndices[0] = JSON.indexOf("minimum_precipitation") + 29;
    wateringIndices[1] = JSON.indexOf("}",wateringIndices[0]);
    wateringIndices[2] = wateringIndices[1] + 32;
    wateringIndices[3] = JSON.indexOf("}", wateringIndices[2]);
    output[1] = JSON.substring(wateringIndices[0],wateringIndices[1]) + "-" + JSON.substring(wateringIndices[2],wateringIndices[3]);

    int[] urlIndex = {JSON.indexOf("image_url") + 12, JSON.indexOf("genus", JSON.indexOf("genus") + 1) - 3};
    output[2] = JSON.substring(urlIndex[0], urlIndex[1]);

    return output;
  }

}
