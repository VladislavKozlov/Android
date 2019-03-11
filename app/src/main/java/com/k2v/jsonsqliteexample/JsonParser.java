package com.k2v.jsonsqliteexample;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vladislav Kozlov <k2v.akosa@gmail.com>
 */
public class JsonParser {

    //private static final String TAG_BTC_BCN = "BTC_BCN";
    public static List<String> Parse (String json) {

        //String index = jsonArray.getString(0);
        System.out.println("json = ");
        System.out.println(json);

        try {
            //JSONObject jsonObject = new JSONObject(json);
            //JSONArray jsonArray = jsonObject.getJSONArray("BTC_BCN");
            //JSONArray jsonArray = new JSONArray(json);
            //String index = jsonArray.getString(0);
            //System.out.println("index_0 = ");
            //System.out.println(index);
            ArrayList contactList = new ArrayList<String>();
            contactList.add("last1 21");
            contactList.add("last2 22");
            contactList.add("last3 23");
            contactList.add("last4 24");
            contactList.add("last5 25");
            /*
            for (int i = 0; i<jsonArray.length(); i++){

               JSONObject jb = jsonArray.getJSONObject(i);

               contactList.add(jb.getString("last"));
            }
            */
            //System.out.println("jsonArray.leght() = ");
            //System.out.println(jsonArray.length());
            //System.out.println();

            return contactList;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
