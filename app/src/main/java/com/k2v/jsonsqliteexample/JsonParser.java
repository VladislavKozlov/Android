package com.k2v.jsonsqliteexample;


import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;

/**
 * Created by Vladislav Kozlov <k2v.akosa@gmail.com>
 */
public class JsonParser {

    public static ArrayList<String> Parse (String json) {

        try {
			JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("json_array");
            ArrayList<String> contactList = new ArrayList();
            String[] contactFromJson = new String[jsonArray.length()];
           
            for (int i = 0; i<jsonArray.length(); i++) {
			
				contactFromJson[i] = jsonArray.getString(i);
                contactList.add(contactFromJson[i]);
            }
			
            return contactList;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
