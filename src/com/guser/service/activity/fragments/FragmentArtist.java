package com.guser.service.activity.fragments;

import java.util.concurrent.ExecutionException;

import com.guser.service.ArtistListActivity;
import com.guser.service.R;
import com.guser.service.DB.ArtistDBHandler;
import com.guser.service.activity.ArtistList;
import com.guser.service.common.GlobalVariables;
import com.guser.service.utils.DownloadImageTask;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

public class FragmentArtist  extends Fragment{
	
	EditText txtEdit;
	ImageButton btnSearch;
	ImageView imgArtist;
	
	@Override
	  public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.fragment_artist ,container, false);
		btnSearch = (ImageButton) view.findViewById(R.id.imageBtnSearch);
		txtEdit = (EditText)view.findViewById(R.id.artistTextEditor);
		imgArtist = (ImageView)view.findViewById(R.id.imageArtist);
		
		btnSearch.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {

				String artistName = txtEdit.getText().toString();
				
				Bundle data = null;
				try {
					data = new com.guser.service.common.ArtistInfo().execute(artistName).get();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if(data.containsKey(GlobalVariables.DB_ARTIST_FIELD_artistName))
				{
					String tmp = data.getString(GlobalVariables.DB_ARTIST_FIELD_artistName).toString().toUpperCase();
					String url = data.getString(GlobalVariables.DB_ARTIST_FIELD_artistImageUrl).toString();
					
					if(artistName.toUpperCase().equals(tmp))
					{
						new DownloadImageTask(imgArtist).execute(url);
						
						ArtistDBHandler dbHandler = new ArtistDBHandler(getActivity().getApplicationContext());
						dbHandler.addArtist(tmp, url);
						
						ArtistListActivity art = (ArtistListActivity)getActivity();
						art.AddNewArtistFragment();
					}

				}
			}
		});
		
	    return view;
	  }
	
}
