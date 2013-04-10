package com.guser.service.common;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

public class ArtistInfo  extends AsyncTask<String, Void, Bundle> {

	String API_ArtistInfo = "http://ws.audioscrobbler.com/2.0/?method=artist.getinfo&artist=%s&api_key=a5bb63698ee1496d74a14f9ce0d308be&format=json";
	
	@Override
	protected Bundle doInBackground(String... params) {

		String typedArtist = params[0];
		String url = String.format(API_ArtistInfo, typedArtist.replace(" ", "%20"));
		String name = "";
		String imgUrl = "";
		JSONParser parser = new JSONParser();
		Bundle data = new Bundle();
		
		Log.i("GuserService - Get AlbumsByArtist", "url - " + url);
		
		JSONObject object = parser.getJSONFromUrl(url);
		try {
			JSONObject artistJson = object.getJSONObject("artist");
			name = artistJson.getString("name");
			imgUrl = artistJson.getJSONArray("image").getJSONObject(2).getString("#text");
		
			data.putString("artistName", name);
			data.putString("artistImageUrl", imgUrl);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return data;
		
	}

}
