package plant;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


//need to create your own account with
public class Get_Plant_Info {

  private static final String apiRequest = "https://trefle.io/api/v1/";
  private static final String token = "token=ALt0fE6yO8mIuJKrReIYktfe6eC5GDbuHTUt4zGhMS4";
  private static HttpsURLConnection connection;
  private static final int maxTries = 5; //number of times to retry connection if it fails

  public static JSONArray getSciName(String commonName) {
    String location = apiRequest + "/plants/search?" + token + "&" + process(commonName);
    StringBuilder responseContent = new StringBuilder();

    try {
      int status = openConnection(location);
      parseURL(status, responseContent);

    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      connection.disconnect();
    }
    JSONArray allData;
    try {
      allData = new JSONArray(responseContent.toString());
      return allData;
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return null;
  }

  private static StringBuilder parseURL(int status, StringBuilder responseContent) throws IOException {
    String line;

    BufferedReader reader;
    if (status > 299) {
      reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
      while ((line = reader.readLine()) != null) {
        responseContent.append(line);
      }
      reader.close();
    } else {
      reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      while ((line = reader.readLine()) != null) {
        responseContent.append(line.replaceAll("\"", "\\\""));
      }
      reader.close();
    }

    return responseContent;
  }

  private static int openConnection(String location) throws IOException {
    URL url = new URL(location);
    connection = (HttpsURLConnection) url.openConnection();
    connection.setRequestMethod("GET");
    connection.setConnectTimeout(5000);
    connection.setReadTimeout(5000);
    return connection.getResponseCode();
  }

  /**
 * takes output of getSciNameInfo and pares it down to just what is needed to make plant_object
 * @param input - JSONArray output form getSciNameInfo
 * @return output- farming data necessary for plant object
 */
  public JSONArray parseData(JSONArray input) {
    JSONArray output = new JSONArray();
    try {
      output.put(input.getJSONArray(27));
      output.put(input.getJSONArray(28));
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return output;
  }

  /**
  *  * @param input - plain text URL
  * @return valid HTML URL
  */
  public static String process(String input) {
    String[] words = input.split(" ");
    StringBuilder output = new StringBuilder();

    for (String word : words) {
      output.append(Arrays.toString(words)).append("%20");
    }

    return output.substring(0, output.length() - 2);
  }


}
