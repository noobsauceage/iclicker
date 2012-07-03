package com.scott.iClicker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class LectureNotesActivity extends ListActivity {
	
	
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		/*
		 * setListAdapter(new ArrayAdapter<String>(this, R.layout.noteslist,
		 * notesItems));
		 */
		
		String[] filelist = GetFileList.split;

		//final GetFileList getFileList = new GetFileList(getApplicationContext());
		//getFileList.execute();

		ListView lv = getListView();
		lv.setTextFilterEnabled(true);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				R.layout.noteslist, filelist);

		// listView1 = (ListView) findViewById(R.id.listView1);

		View header = (View) getLayoutInflater().inflate(R.layout.list_item,
				null);
		lv.addHeaderView(header);
		lv.setAdapter(adapter);

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

	
	/*
	public class GetFileList extends AsyncTask<Void, Void, Void> {

		Context filecontext;
		//String split[];

		public GetFileList(Context context) {
			filecontext = context;
		}

		@Override
		protected Void doInBackground(Void... params) {

			String result = getFileList();

			split = result.split(",");

			return null;
		}

		protected void onPostExecute(Void result) {
			for (int i = 0; i < split.length; i++) {

				CharSequence text = split[i];
				int duration = Toast.LENGTH_SHORT;

				Toast toast = Toast.makeText(filecontext, text, duration);
				toast.show();
			}
			return;
		}

		public String getFileList() {
			StringBuilder builder = new StringBuilder();
			HttpClient client = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(
					"http://dolan.bounceme.net/dirlist.php");
			try {
				HttpResponse response = client.execute(httpGet);
				StatusLine statusLine = response.getStatusLine();
				int statusCode = statusLine.getStatusCode();
				if (statusCode == 200) {
					HttpEntity entity = response.getEntity();
					InputStream content = entity.getContent();
					BufferedReader reader = new BufferedReader(
							new InputStreamReader(content));
					String line;
					while ((line = reader.readLine()) != null) {
						builder.append(line);
					}
				} else {
					// Log.e(ParseJSON.class.toString(),
					// "Failed to download file");
				}
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return builder.toString();
		}

	}*/

	static final String[] notesItems = new String[] { "Lecture1-Notes.ppt",
			"Lecture2-Notes.ppt", "Lecture3-Notes.ppt", "Lecture4-Notes.ppt",
			"Lecture5-Notes.ppt", "Lecture6-Notes.ppt", "Lecture7-Notes.ppt",
			"Lecture8-Notes.ppt" };
}
