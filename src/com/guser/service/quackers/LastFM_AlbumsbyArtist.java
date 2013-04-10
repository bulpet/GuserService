package com.guser.service.quackers;

import java.net.URLEncoder;
import java.util.Collection;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;

import com.guser.service.common.GuserMessage;
import com.guser.service.common.JSONParser;
import com.guser.service.utils.gutils;

public class LastFM_AlbumsbyArtist extends
		AsyncTask<String, String, GuserMessage> {

	String API_AlbumsByArtist = "http://ws.audioscrobbler.com/2.0/?method=artist.gettopalbums&api_key=a5bb63698ee1496d74a14f9ce0d308be&format=json&artist=%s";
	String API_TracksByAlbum = "http://ws.audioscrobbler.com/2.0/?method=album.getinfo&api_key=a5bb63698ee1496d74a14f9ce0d308be&format=json&artist=%s&album=%s";
	String API_LyricTrack = "http://metrolyrics.com/api/v1/search/artistsong/artist/%s/song/%s/X-API-KEY/1234567890123456789012345678901234567890";
	String artist = "Justin Bieber";

	protected GuserMessage doInBackground(String... arg0) {

		String AlbumName = "";
		//String ArtistName = "";
		//String AlbumUrl = "";
		//String ArtistUrl = "";
		String imgUrl = "";
		String lyric = "";
		String TrackName = "";
		String TrackUrl = "";
		String TrackDuration= "";
		JSONParser parser = new JSONParser();
		GuserMessage msg = null;
		//gutils utls = new gutils();

		String url = String.format(API_AlbumsByArtist, artist.replace(" ", "%20"));
		Log.i("GuserService - Get AlbumsByArtist", "url - " + url);
		
		
		
		JSONObject object = parser.getJSONFromUrl(url);
		JSONObject album = GetAlbumObject(object);

		if(album == null)
			return null;
		
		/* get all album by artist */
		try {
			AlbumName = album.getString("name");
			//AlbumUrl = album.getString("url");
			//ArtistName = album.getJSONObject("artist").getString("name");
			imgUrl = album.getJSONArray("image").getJSONObject(2).getString("#text");

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		/* get all track by album */
		url = String.format(API_TracksByAlbum, artist.replace(" ", "%20"), AlbumName.replace(" ", "%20"));
		Log.i("GuserService - Get TracksByAlbum", "url - " + url);
		
		object = parser.getJSONFromUrl(url);
		JSONObject track = GetTrackObject(object);
		
		if(track == null)
			return null;
		
		try {
			TrackName = track.getString("name");
			TrackUrl = track.getString("url");
			TrackDuration = track.getString("duration");

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		/* get lyric by track and artist */
		lyric = GetLyricByArtistTrack(artist.replace(" ", "%20"),TrackName.replace(" ", "%20"));

		
		msg = new GuserMessage();

		msg.setMsg_picture(imgUrl);
		msg.setMsg_caption(artist + " (" + AlbumName + ")");
		msg.setMsg_name(TrackName + " - " +  new gutils().secondsToHms(TrackDuration));
		msg.setMsg_description(lyric);
		msg.setMsg_link(TrackUrl);

		return msg;
	}

	public String GetLyricByArtistTrack(String Artist,String Track)
	{
		String returnedValue = "";
		String lyricUrl = String.format(API_LyricTrack, Artist, Track);
		
		Log.i("GuserService - Get Lyric", "url - " + lyricUrl);
		JSONObject object = new JSONParser().getJSONFromUrl(lyricUrl);

		if (object != null) {
			try {
				returnedValue = object.getJSONArray("items").getJSONObject(0).getString("snippet");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return returnedValue;
	}
	
	public JSONObject GetAlbumObject(JSONObject artistJson)
	{
		JSONArray jarr = null;
		JSONObject returnedValue = null;
		
		try
		{
			jarr = artistJson.getJSONObject("topalbums").getJSONArray("album");
			int count = jarr.length();
			int randomAlbum = new gutils().GetRandomInt(1, count);
			
			returnedValue = jarr.getJSONObject(randomAlbum);
		}
		catch(JSONException e)
		{
			e.printStackTrace();
			try {
				returnedValue = artistJson.getJSONObject("topalbums").getJSONObject("album");
			} catch (JSONException e1) {
				e1.printStackTrace();
				returnedValue = null;
			}
		}
	
		return returnedValue;
	}
	
	public JSONObject GetTrackObject(JSONObject albumJson) {
		JSONArray jarr = null;
		JSONObject returnedValue = null;

		try 
		{
			
			jarr = albumJson.getJSONObject("album").getJSONObject("tracks").getJSONArray("track");
			int count = jarr.length();
			int randomTrack = new gutils().GetRandomInt(1, count);

			returnedValue = jarr.getJSONObject(randomTrack);
		} 
		catch (JSONException e) {
			e.printStackTrace();
			try {
				returnedValue = albumJson.getJSONObject("album").getJSONObject("tracks").getJSONObject("track");
			} catch (JSONException e1) {
				e1.printStackTrace();
				returnedValue = null;
			}
		}

		return returnedValue;
	}
}
