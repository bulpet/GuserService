package com.guser.service.quackers.command;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.guser.service.DB.ArtistDBHandler;
import com.guser.service.common.GlobalVariables;
import com.guser.service.common.GuserMessage;
import com.guser.service.quackers.LastFM_AlbumsbyArtist;

public class music_top_chart implements IQuack {

	@Override
	public GuserMessage execute(Bundle params, Context context) {

		ArtistDBHandler artistDb = new ArtistDBHandler(context);
		Bundle data = artistDb.GetRandomArtist();
		String artistName = data.getString(GlobalVariables.DB_ARTIST_FIELD_artistName);
		
		GuserMessage message = new LastFM_AlbumsbyArtist().GetTrackFromAlbumsByArtist(artistName);	
		Log.i("GuserService - music_top_chart.execute()", " @@@@@@@@@");
		message.Log();
		return message;
	}

}
