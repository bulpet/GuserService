package com.guser.service.activity;

import java.util.concurrent.ExecutionException;

import com.guser.service.R;
import com.guser.service.DB.ArtistDBHandler;
import com.guser.service.common.GlobalVariables;
import com.guser.service.utils.DownloadImageTask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

public class ArtistList extends Activity {

	EditText txtEdit;
	ImageButton btnSearch;
	ImageView imgArtist;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_artist_list);

		SetListViewItems();
		
		btnSearch = (ImageButton) findViewById(R.id.imageBtnSearch);
		btnSearch.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(final View v) {
				ProgressDialog dialog = ProgressDialog.show(v.getContext(), "Loading", "Please wait...", true);

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

				if (data.containsKey(GlobalVariables.DB_ARTIST_FIELD_artistName)) {
					String tmp = data.getString(GlobalVariables.DB_ARTIST_FIELD_artistName).toString().toUpperCase();
					String url = data.getString(GlobalVariables.DB_ARTIST_FIELD_artistImageUrl).toString();

					if (artistName.toUpperCase().equals(tmp)) {
						new DownloadImageTask(imgArtist).execute(url);

						ArtistDBHandler dbHandler = new ArtistDBHandler(v.getContext());
						dbHandler.addArtist(tmp, url);

						SetListViewItems();
						
						// ArtistListActivity art =
						// (ArtistListActivity)getActivity();
						// art.AddNewArtistFragment();
					}

				}
			
				dialog.dismiss();
			}
			
		});

		// return view;
	}
	
	public void SetListViewItems()
	{
		ArtistDBHandler dbHandler = new ArtistDBHandler(getApplicationContext());
		SimpleCursorAdapter dataAdapter;
		Cursor cursor = dbHandler.fetchAllArtist();
		
		 String[] columns = new String[] {
				 GlobalVariables.DB_ARTIST_FIELD_artistName,
				 GlobalVariables.DB_ARTIST_FIELD_artistImageUrl
				  };
		 int[] to = new int[] { 
				    R.id.continent,
				    R.id.region,
				  };
		
		 // create the adapter using the cursor pointing to the desired data 
		  //as well as the layout information
		  dataAdapter = new SimpleCursorAdapter(
		    this, 
		    R.layout.artist_item, 
		    cursor, 
		    columns, 
		    to,
		    0);
		
		  ListView listView = (ListView) findViewById(R.id.artist_listview);
		  // Assign adapter to ListView
		  listView.setAdapter(dataAdapter);

	}
}
