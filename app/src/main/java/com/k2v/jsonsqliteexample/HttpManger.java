package com.k2v.jsonsqliteexample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Vladislav Kozlov <k2v.akosa@gmail.com>
 */
public class HttpManger {

    public static String getData(String stringUrl) {

        BufferedReader reader = null;
        try {

            URL url = new URL(stringUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            StringBuilder sb = new StringBuilder();
            InputStreamReader is = new InputStreamReader(connection.getInputStream());
            reader = new BufferedReader(is);
            String line;

            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }

            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
