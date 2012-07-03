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

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

public class GetFileList extends AsyncTask<Void, Void, String[]> {

	Context filecontext;
	static String split[];

	public GetFileList(Context context) {
		filecontext = context;
	}

	@Override
	protected String[] doInBackground(Void... params) {

		String result = getFileList();

		split = result.split(",");

		return split;
	}

	protected void onPostExecute(String[] result) {
		/*
		for (int i = 0; i < split.length; i++) {

			CharSequence text = split[i];
			int duration = Toast.LENGTH_SHORT;

			Toast toast = Toast.makeText(filecontext, text, duration);
			toast.show();
		} */
		
		Intent lecturenotesActivity = new Intent(filecontext,
				LectureNotesActivity.class);
		lecturenotesActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		filecontext.startActivity(lecturenotesActivity);
		
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

}