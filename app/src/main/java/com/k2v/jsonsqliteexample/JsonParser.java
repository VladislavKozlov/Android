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
    public static ArrayList<String> Parse (String json) {

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
            ArrayList<String> contactList = new ArrayList();
            contactList.add("companyname1");
            contactList.add("latestprice1");
            contactList.add("companyname2");
            contactList.add("latestprice2");
            contactList.add("companyname3");
            contactList.add("latestprice3");
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
