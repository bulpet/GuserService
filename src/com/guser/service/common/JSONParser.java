package com.guser.service.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.text.TextUtils;

public class JSONParser {
	 
    static InputStream is = null;
    static JSONObject jObj = null;
    static String json = "";
 
    // constructor
    public JSONParser() {
 
    }
 
    public JSONObject getJSONFromUrl(String url) {
 
        // Making HTTP request
    	URLConnection connection = null;
		try 
		{
			connection = new URL(url).openConnection();
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
 
		BufferedReader reader = null;
		StringBuffer builder = new StringBuffer();
		String line;
		JSONObject object = null;
		
		try 
		{
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()), 1024 * 16);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
		try 
		{
			while ((line = reader.readLine()) != null)
			{
			  builder.append(line).append("\n");
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String tmp = builder.toString();
		try {
			object = new JSONObject(tmp);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
 
		connection = null;
        // return JSON String
        return object;
 
    }
}
