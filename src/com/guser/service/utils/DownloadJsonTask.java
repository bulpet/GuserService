package com.guser.service.utils;

import org.json.JSONObject;
import com.guser.service.common.JSONParser;
import android.os.AsyncTask;

public class DownloadJsonTask  extends AsyncTask<String, Void, JSONObject> {

	@Override
	protected JSONObject doInBackground(String... urls) {
		
		String url = urls[0];
		JSONParser parser = new JSONParser();
		JSONObject json = parser.getJSONFromUrl(url);
		
		return json;
	}

}
