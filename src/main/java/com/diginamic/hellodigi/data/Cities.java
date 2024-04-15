package com.diginamic.hellodigi.data;

import okio.BufferedSource;
import okio.FileSystem;
import okio.Okio;
import okio.Path;
import okio.Source;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.*;

/**
 * A utility class for loading city data from a json file
 */
public class Cities {
  /**
   * The Json file location
   */
  private static final String FILE_URI = "assets/cities.json";

  /**
   * Keys for retrieving values in the json file
   */
  public static final String KEY_CITY_NAME = "label";
  public static final String KEY_DEPARTMENT_NAME = "department_name";
  public static final String KEY_DEPARTMENT_CODE = "department_number";

  /**
   * Creates a singleton of OpenFoodFact.
   *
   * @return An OpenFoodFact instance.
   */
  public static Set<CityData> create() throws IOException {
    Set<CityData> cities = new HashSet<>();

    JSONArray objects = readJsonFile();

    for (int index = 0; index < objects.length(); index++) {
      JSONObject object = objects.getJSONObject(index);

      CityData city = buildCity(object);
      cities.add(city);
    }

    return cities;
  }

  /**
   * Reads a JSON file and returns its content as a JSONArray.
   *
   * @return A JSONArray containing the data from the JSON file.
   * @throws IOException If there's an error reading the file.
   */
  private static JSONArray readJsonFile() throws IOException {
    Path path = Path.get(FILE_URI);
    Source source = FileSystem.SYSTEM.source(path);
    BufferedSource bufferedSource = Okio.buffer(source);
    String data = bufferedSource.readUtf8();
    bufferedSource.close();

    return new JSONArray(data);
  }

  /**
   * Builds a city object from a JSONObject.
   *
   * @param object The JSONObject containing data for the city.
   * @return A {@link CityData} object.
   */
  private static CityData buildCity(JSONObject object) {
    // Get city name
    String cityName = object.optString(KEY_CITY_NAME);

    // Get department name
    String departmentName = object.optString(KEY_DEPARTMENT_NAME);


    // Get department code
    String departmentCode = object.optString(KEY_DEPARTMENT_CODE);

    return new CityData(cityName, departmentName, departmentCode);
  }

  public record CityData(String cityName, String departmentName, String departmentCode) { }
}
