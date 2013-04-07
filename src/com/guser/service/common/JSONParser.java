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

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

public class JSONParser {
	 
    URLConnection connection = null;
    
    // constructor
    public JSONParser() {

    }
 
    public JSONObject getJSONFromUrl(String url) {
 
        // Making HTTP request
   	
		try 
		{
			//connection = new URL(url).openConnection();
			Log.i("GuserService - getJSONFromUrl", "Uri.parse - " + Uri.parse(url).toString());
			connection =  new URL(Uri.parse(url).toString()).openConnection();
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		}
 
		BufferedReader reader = null;
		StringBuffer builder = new StringBuffer();
		String line;
		JSONObject object = null;
		
		try 
		{
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()), 1024 * 24);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
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
			return null;
		}
		
		String tmp = builder.toString();
		try {
			object = new JSONObject(tmp);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} 

        // return JSON String
        return object;
 
    }
}
