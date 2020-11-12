package plant;

import android.content.res.Resources;
import com.example.pocketgarden.R;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

//need to create your own account with
public class GetPlantInfo {
  private static final String apiRequest = Resources.getSystem().getString(R.string.trefle_api_url);
  private static final String token = Resources.getSystem().getString(R.string.trefle_token);
  private static HttpsURLConnection connection;
  private static StringBuilder responseContent;

  public GetPlantInfo(){
    this.responseContent = new StringBuilder();
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
  public String makeUrlSci(String scientificName) {
    return apiRequest + "/species" + scientificName + "?" + token;
  }

  /**
   * @param location URL to connect to
   * @return status of connection attempt after one is successful
   * @throws IOException if the location URL is incorrect
   */
  public int makeConnection(String location) throws IOException {
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
  public String getSciName(String commonName) {
    String location = makeUrlCommon(commonName);

    try {
      int status = makeConnection(location);
      responseContent = parseUrl(status);
      parseUrl(status);

    } catch (IOException e) {
      e.printStackTrace();
    }
    connection.disconnect();
    return responseContent.toString();
  }

  /**
   * @param commonName text input of a plant's common name, e.g. strawberry
   * @return a link to a trefle API query searching for the common name
   */
  public String makeUrlCommon(String commonName) {
    return apiRequest + "/plants/search?" + token + "&" + process(commonName);
  }

  /**
   * @param status Code received after attempted connection to trefle API
   * @return responseContent all the data from the trefle API url
   * @throws IOException if the URL is invalid
   */
  private static StringBuilder parseUrl(int status) throws IOException {
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

  /**
   *  * @param input - plain text URL
   * @return valid HTML URL
   */
  public static String process(String input) {
    String[] words = input.split(" ");
    StringBuilder output = new StringBuilder();

    for (String word : words) {
      output.append(word).append("%20");
    }

    return output.substring(0, output.length() - 2);
  }


}
