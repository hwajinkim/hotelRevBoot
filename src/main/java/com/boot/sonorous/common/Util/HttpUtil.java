package com.boot.sonorous.common.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpUtil {

    public static JSONObject callApi(JSONObject params, String type){

        HttpURLConnection conn = null;
        JSONObject responseJson = null;

        try{
            URL url = new URL("http://54.180.190.107/api/review?id=1");
            conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod(type);
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);

            //결과값 받기
            StringBuilder sb = new StringBuilder();
            if(conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
                String line = "";
                while ((line = br.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                br.close();
                responseJson = new JSONObject(sb.toString());
                System.out.println("responseJson :: " + responseJson);
                return responseJson;
            } else {
                System.out.println(conn.getResponseMessage());
            }

        } catch (Exception e){
            System.err.println(e.toString());
        }
        return null;
    }
}
