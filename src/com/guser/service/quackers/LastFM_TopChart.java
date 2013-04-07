package com.guser.service.quackers;

import java.util.Random;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import com.guser.service.common.GuserMessage;
import com.guser.service.common.JSONParser;

import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.JsonReader;
import android.util.Log;

public class LastFM_TopChart extends AsyncTask<String, String, GuserMessage> {

	@Override
	protected GuserMessage doInBackground(String... arg0) {
		
		GuserMessage msg =null;
		int min = 1;
		int max = 1000;
		int i1;
		Random r = new Random();
		i1 = r.nextInt(max - min + 1) + min;
		
		String url = "http://ws.audioscrobbler.com/2.0/?method=chart.gettoptracks&api_key=a5bb63698ee1496d74a14f9ce0d308be&format=json&limit=1&page=" + i1;
		
		JSONParser parser = new JSONParser();
			
		try {
			JSONObject object = parser.getJSONFromUrl(url);
			
			JSONObject jarr = object.getJSONObject("tracks");
			JSONObject jarr2 = jarr.getJSONObject("track");
			
			String songName = "";
			String songUrl = "";
			String artist = "";
			String imgUrl = "";
			String lyric = "";
			
			try	
			{
				songName = jarr2.getString("name");
				songUrl = jarr2.getString("url");
				artist = jarr2.getJSONObject("artist").getString("name");
				imgUrl = jarr2.getJSONArray("image").getJSONObject(2).getString("#text");
				lyric="";			
				
				Log.i("LAST-FM", "name - " + songName);
				Log.i("LAST-FM", "url - " + songUrl);
				Log.i("LAST-FM", "artist - " + artist);
				Log.i("LAST-FM", "url - " + imgUrl);
			}
			catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//String lyricUrl = "http://api.lyricsnmusic.com/songs?artist=" + TextUtils.htmlEncode(artist) + "&track=" + TextUtils.htmlEncode(name);
			
			String lyricUrl = "http://metrolyrics.com/api/v1/search/artistsong/artist/" + TextUtils.htmlEncode(artist) + "/song/" + TextUtils.htmlEncode(songName) + "/X-API-KEY/1234567890123456789012345678901234567890";
			object = parser.getJSONFromUrl(lyricUrl);
			
			if(object != null)
			{
				lyric=object.getJSONArray("items").getJSONObject(0).getString("snippet");
			}

			msg = new GuserMessage();
			msg.setMsg_picture(imgUrl);
			msg.setMsg_caption(artist);
			msg.setMsg_name(songName);
			msg.setMsg_description(lyric);
			msg.setMsg_link(songUrl);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return msg;
	}

}
