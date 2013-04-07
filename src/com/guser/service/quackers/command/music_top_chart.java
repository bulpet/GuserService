package com.guser.service.quackers.command;

import java.util.concurrent.ExecutionException;

import android.content.Context;
import android.os.Bundle;

import com.guser.service.common.GuserMessage;
import com.guser.service.quackers.LastFM_AlbumsbyArtist;
import com.guser.service.quackers.LastFM_TopChart;

public class music_top_chart implements IQuack {

	@Override
	public GuserMessage execute(Bundle params, Context context) {

		GuserMessage message=null;
		
		try 
		{
			//execute LastFM Top Cahrt (1000)
			//message = new LastFM_TopChart().execute("").get();
			message = new LastFM_AlbumsbyArtist().execute("").get();
			
		} catch (InterruptedException e) {
			message=null;
			e.printStackTrace();
		} catch (ExecutionException e) {
			message=null;
			e.printStackTrace();
		}
		
		return message;
	}

}
