package com.guser.service.quackers;

import java.util.Random;
import java.util.concurrent.ExecutionException;

import org.json.JSONException;
import org.json.JSONObject;
import com.guser.service.common.GuserMessage;
import com.guser.service.utils.DownloadJsonTask;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;

public class LastFM_TopChart{

	public GuserMessage GetChartMessage() {
		
		GuserMessage msg =null;
		int min = 1;
		int max = 1000;
		int i1;
		Random r = new Random();
		i1 = r.nextInt(max - min + 1) + min;
		
		String url = "http://ws.audioscrobbler.com/2.0/?method=chart.gettoptracks&api_key=a5bb63698ee1496d74a14f9ce0d308be&format=json&limit=1&page=" + i1;
		
		//JSONParser parser = new JSONParser();
		JSONObject object = null;
		
		try {
			
			//JSONObject object = parser.getJSONFromUrl(url);
			object = new DownloadJsonTask().execute(url).get();
			
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
			
			Log.i("LAST-FM - LYRIC escapeHtml", "artist - " + Html.escapeHtml(artist));
			Log.i("LAST-FM - LYRIC htmlEncode", "artist - " + TextUtils.htmlEncode(artist));
			Log.i("LAST-FM - LYRIC", "--------------------------------------");
			Log.i("LAST-FM - LYRIC escapeHtml", "artist - " + Html.escapeHtml(songName));
			Log.i("LAST-FM - LYRIC htmlEncode", "artist - " + TextUtils.htmlEncode(songName));
			
			String lyricUrl = "http://metrolyrics.com/api/v1/search/artistsong/artist/" + Html.escapeHtml(artist) + "/song/" + TextUtils.htmlEncode(songName) + "/X-API-KEY/1234567890123456789012345678901234567890";
			//object = parser.getJSONFromUrl(lyricUrl);
			object = new DownloadJsonTask().execute(lyricUrl).get();
			
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
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return msg;
	}

}
