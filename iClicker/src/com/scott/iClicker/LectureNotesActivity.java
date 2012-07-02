package com.scott.iClicker;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class LectureNotesActivity extends ListActivity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setListAdapter(new ArrayAdapter<String>(this, R.layout.noteslist,
				notesItems));

		ListView lv = getListView();
		lv.setTextFilterEnabled(true);

		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// When clicked, switch to appropriate activity
				switch (position) {
				case 0:
					String url = "https://www.dropbox.com/s/psgv31hq7ktotvq/Lecture1.ppt";
					Intent i = new Intent(Intent.ACTION_VIEW);
					i.setData(Uri.parse(url));
					startActivity(i);
					break;
					
				case 1:
					
					break;
					
				
				case 2:
					
					break;
					
				case 3:
					
					break;
				}
			}
		});
	}

	static final String[] notesItems = new String[] { "Lecture1-Notes.ppt", "Lecture2-Notes.ppt", "Lecture3-Notes.ppt", "Lecture4-Notes.ppt", "Lecture5-Notes.ppt", "Lecture6-Notes.ppt", "Lecture7-Notes.ppt", "Lecture8-Notes.ppt" };
}
