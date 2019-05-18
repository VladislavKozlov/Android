package com.k2v.jsonsqliteexample;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;

/**
 * Created by Vladislav Kozlov <k2v.akosa@gmail.com>
 */
public class JsonParser {

    public static ArrayList<String> Parse (String json, String itemName) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            ArrayList<String> tickerList = new ArrayList();
            JSONArray jsonArray = jsonObject.names();
            String[] namesFromJson = new String[jsonArray.length()];
            String[] lastFromJson = new String[jsonArray.length()];
            for (int i = 0; i< jsonArray.length(); i++){
                namesFromJson[i] = jsonArray.getString(i);
                tickerList.add(namesFromJson[i]);
                JSONObject jsonObjectName = jsonObject.getJSONObject(namesFromJson[i]);
                lastFromJson[i] = jsonObjectName.getString(itemName);
                tickerList.add(lastFromJson[i]);
            }
            return tickerList;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
