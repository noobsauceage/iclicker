package com.scott.iClicker;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class IClickerActivity extends ListActivity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item,
				menuItems));

		ListView lv = getListView();
		lv.setTextFilterEnabled(true);

		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// When clicked, switch to appropriate activity
				switch (position) {
				case 0:
					Intent multiChoiceActivity = new Intent(IClickerActivity.this,
							MultiChoiceActivity.class);
					startActivity(multiChoiceActivity);
					break;
					
				case 1:
					Intent openEndedActivity = new Intent(getApplicationContext(),
							OpenEndedActivity.class);
					startActivity(openEndedActivity);
					break;
					
				
				case 2:
					//Intent lecturenotesActivity = new Intent(getApplicationContext(),
					//		LectureNotesActivity.class);
					//startActivity(lecturenotesActivity);
					
					final GetFileList getFileList = new GetFileList(getApplicationContext());
					getFileList.execute();
					
					break;
					
				case 3:
					Intent optionsActivity = new Intent(getApplicationContext(),
							OptionsActivity.class);
					startActivity(optionsActivity);
					break;
				}
			}
		});
	}

	static final String[] menuItems = new String[] { "Multiple Choice", "Open Ended", "Notes", "Options" };
}
