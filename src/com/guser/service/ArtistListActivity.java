package com.guser.service;

import com.guser.service.activity.fragments.FragmentArtist;

import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.Menu;

public class ArtistListActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_artist_list);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.artist_list, menu);
		return true;
	}

	public void AddNewArtistFragment()
	{
		FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        
        fm.beginTransaction();
        Fragment fragOne = new FragmentArtist();
//        Bundle arguments = new Bundle();
//        arguments.putBoolean("shouldYouCreateAChildFragment", true);
//        fragOne.setArguments(arguments);
        ft.add(R.id.artistListLayout, fragOne);
        ft.commit();
        
	}
	
}
