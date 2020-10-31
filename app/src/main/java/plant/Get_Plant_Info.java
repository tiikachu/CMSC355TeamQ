package plant;


import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import com.example.pocketgarden.R;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONArray;
import org.json.JSONException;


//need to create your own account with
public class Get_Plant_Info extends Resources {

  private static String apiRequest;
  private static String token;
  private static HttpsURLConnection connection;

  /**
   * Create a new Resources object on top of an existing set of assets in an
   * AssetManager.
   *
   * @param assets  Previously created AssetManager.
   * @param metrics Current display metrics to consider when
   *                selecting/computing resource values.
   * @param config  Desired device configuration to consider when
   * @deprecated Resources should not be constructed by apps.
   * See {@link Context#createConfigurationContext(Configuration)}.
   */
  public Get_Plant_Info(AssetManager assets, DisplayMetrics metrics, Configuration config) {
    super(assets, metrics, config);
    apiRequest = getString(R.string.trefle_api_url);
    token = getString(R.string.trefle_token);

  }


  //only scientific name
  public static JSONArray getSciNameInfo(String scientificName) throws JSONException {
    String location = apiRequest + "/species" + scientificName + "?" + token;
    String line;
    StringBuilder responseContent = new StringBuilder();
    BufferedReader reader;

    try {
      URL url = new URL(location);
      connection = (HttpsURLConnection) url.openConnection();

      connection.setRequestMethod("GET");
      connection.setConnectTimeout(5000);
      connection.setReadTimeout(5000);

      int status = connection.getResponseCode();

      if (status > 299) {
        reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
        while ((line = reader.readLine()) != null) {
          responseContent.append(line);
        }
        reader.close();
      } else {
        reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        while ((line = reader.readLine()) != null) {
          responseContent.append(line);
        }
        reader.close();
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      connection.disconnect();
      return new JSONArray(responseContent.toString());
    }
  }

  public static String getSciName(String commonName) {
    String location = apiRequest + "/plants/search?" + token + "&" + process(commonName);
    String line;
    StringBuffer responseContent = new StringBuffer();
    BufferedReader reader;

    try {
      URL url = new URL(location);
      connection = (HttpsURLConnection) url.openConnection();

      connection.setRequestMethod("GET");
      connection.setConnectTimeout(5000);
      connection.setReadTimeout(5000);

      int status = connection.getResponseCode();

      if (status > 299) {
        reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
        while ((line = reader.readLine()) != null) {
          responseContent.append(line);
        }
        reader.close();
      } else {
        reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        while ((line = reader.readLine()) != null) {
          responseContent.append(line);
        }
        reader.close();
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
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
