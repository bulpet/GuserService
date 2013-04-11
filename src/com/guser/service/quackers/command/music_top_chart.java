package com.guser.service.quackers.command;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.guser.service.common.GuserMessage;
import com.guser.service.quackers.LastFM_AlbumsbyArtist;

public class music_top_chart implements IQuack {

	@Override
	public GuserMessage execute(Bundle params, Context context) {

		GuserMessage message = new LastFM_AlbumsbyArtist().GetTrackFromAlbumsByArtist("Pink Floyd");	
		Log.i("GuserService - music_top_chart.execute()", " @@@@@@@@@");
		message.Log();
		return message;
	}

}
