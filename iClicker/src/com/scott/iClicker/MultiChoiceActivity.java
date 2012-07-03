package com.scott.iClicker;

import android.app.Activity;
import android.os.Bundle;

import java.net.URL;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

//import edu.umass.cs.gcrs.server.HTTPClient;

import android.telephony.TelephonyManager;
import android.util.Log;

public class MultiChoiceActivity extends Activity {

	private Button submitButton;
	private Button A;
	private Button B;
	private Button C;
	private Button D;
	private Button E;
	private String answer=null;
	private Map<String, String> id_data = new HashMap<String, String>();

	final Context context = this;
	protected Object savedInstanceState;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.multichoice);

		ButtonListenerA();
		ButtonListenerB();
		ButtonListenerC();
		ButtonListenerD();
		ButtonListenerE();
		submitButton_handler();

	}

	public void submitButton_handler() {
		submitButton = (Button) findViewById(R.id.multchoice_submitButton);
		submitButton.setOnClickListener(new OnClickListener(){
			//@Override
			public void onClick(View v) {
				System.out.println("Submit button was clicked"); 

				//AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

				// set title
				//alertDialogBuilder.setTitle("Submit");

				// set dialog message
				
				if (answer!=null) {
					AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
					alertDialogBuilder.setTitle("Submit");
					alertDialogBuilder
					.setMessage("You have chosen answer " + answer + ". Are you sure you want to submit this answer?")
					.setCancelable(false)
					.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							// if this button is clicked,
							// current activity completed
							// Perform action on clicks

							System.out.println("submission confirmed");
							startActivity(getIntent());
							System.out.println("get intent started");
							id_data.put("ans", answer);
							System.out.println("answer put into table");
							id_data.put("id", getID());
							System.out.println("id put into table");
							/*
							 try {
											id_data.put("guid", HTTPClient.registerNewUser(getID()));
										} catch (NoSuchAlgorithmException e) {
											e.printStackTrace();
										} catch (IOException e) {
											e.printStackTrace();
										}
							 */

							doFileUpload();
							System.out.println("file uploaded");
							MultiChoiceActivity.this.finish();
						}      
						// if this button is clicked,
						// current activity completed

					}) //end setpositivebutton


					.setNegativeButton("No",new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,int id) {
							// if this button is clicked close
							// the dialog box to cancel
							dialog.cancel();
						}
					}); //end setNegativeButton, end chain of calls

					// create alert dialog
					AlertDialog alertDialog = alertDialogBuilder.create();

					// show it
					alertDialog.show();

				}//end if

				else {
					AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
					alertDialogBuilder.setTitle("Alert");
					alertDialogBuilder
					.setMessage("You have not chosen an answer. Please select an answer.")
					.setCancelable(false)
					.setNeutralButton("OK",new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,int id) {
							// if this button is clicked close
							// the dialog box to cancel
							dialog.cancel();
						}
					});

					// create alert dialog
					AlertDialog alertDialog = alertDialogBuilder.create();

					// show it
					alertDialog.show();
				}//end else 



				/*
				// create alert dialog
				AlertDialog alertDialog = alertDialogBuilder.create();

				// show it
				alertDialog.show();

			}

		}); //END OF SUBMIT CLICK HANDLER FUNCTION

				 */
			}

		});
	}

	public void ButtonListenerA() {

		A = (Button) findViewById(R.id.multchoice_ButtonA);

		A.setOnClickListener(new OnClickListener() {

			//@Override
			public void onClick(View v) {

				answer = "A"; // set answer to "A"
				System.out.println("Button A clicked");
				Context context = getApplicationContext();
				CharSequence text = "You have selected answer A.";
				int duration = Toast.LENGTH_SHORT;
				Toast.makeText(context, text, duration).show();

			}

		});
	}

	public void ButtonListenerB() {

		B = (Button) findViewById(R.id.multchoice_ButtonB);

		B.setOnClickListener(new OnClickListener() {

			//@Override
			public void onClick(View v) {

				answer = "B"; // set answer to "B"
				System.out.println("Button B clicked");
				Context context = getApplicationContext();
				CharSequence text = "You have selected answer B.";
				int duration = Toast.LENGTH_SHORT;
				Toast.makeText(context, text, duration).show();

			}

		});
	}

	public void ButtonListenerC() {

		C = (Button) findViewById(R.id.multchoice_ButtonC);

		C.setOnClickListener(new OnClickListener() {

			//@Override
			public void onClick(View v) {

				answer = "C"; // set answer to "C"
				System.out.println("Button C clicked");
				Context context = getApplicationContext();
				CharSequence text = "You have selected answer C.";
				int duration = Toast.LENGTH_SHORT;
				Toast.makeText(context, text, duration).show();

			}

		});
	}

	public void ButtonListenerD() {

		D = (Button) findViewById(R.id.multchoice_ButtonD);

		D.setOnClickListener(new OnClickListener() {

			//@Override
			public void onClick(View v) {

				answer = "D"; // set answer to "D"
				System.out.println("Button D clicked");
				Context context = getApplicationContext();
				CharSequence text = "You have selected answer D.";
				int duration = Toast.LENGTH_SHORT;
				Toast.makeText(context, text, duration).show();

			}

		});
	}

	public void ButtonListenerE() {

		E = (Button) findViewById(R.id.multchoice_ButtonE);

		E.setOnClickListener(new OnClickListener() {

			//@Override
			public void onClick(View v) {

				answer = "E"; // set answer to "E"
				System.out.println("Button E clicked");
				Context context = getApplicationContext();
				CharSequence text = "You have selected answer E.";
				int duration = Toast.LENGTH_SHORT;
				Toast.makeText(context, text, duration).show();

			}

		});
	}

	private void doFileUpload() {
		System.out.println("Submitting");
		HttpURLConnection conn = null;
		DataOutputStream dos = null;
		DataInputStream inStream = null;


		String lineEnd = "\r\n";
		String twoHyphens = "--";
		String boundary = "*****";

		String urlString = "http://dolan.bounceme.net/multiplechoice.php";


		try {
			// ------------------ CLIENT REQUEST
			Log.d("MediaPlayer", "Inside second Method");

			// open a URL connection to the Servlet
			URL url = new URL(urlString);

			// Open a HTTP connection to the URL
			conn = (HttpURLConnection) url.openConnection();

			// Allow Inputs
			conn.setDoInput(true);

			// Allow Outputs
			conn.setDoOutput(true);

			// Don't use a cached copy.
			conn.setUseCaches(false);

			// Use a post method.
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Connection", "Keep-Alive");
			//System.setProperty("http.keepAlive", "false");
			conn.setRequestProperty("Content-Type","multipart/form-data;boundary=" + boundary);

			dos = new DataOutputStream(conn.getOutputStream());


			// Post the parameters (ID/GUID)

			Set<String> keys = id_data.keySet();
			Iterator<String> keyIter = keys.iterator();
			//for(int i=0; keyIter.hasNext(); i++) {
			while(keyIter.hasNext()) {
				Object key = keyIter.next();
				dos.writeBytes(twoHyphens + boundary + lineEnd);
				dos.writeBytes("Content-Disposition: form-data; name=" + key + lineEnd);
				dos.writeBytes(lineEnd);
				dos.writeBytes(id_data.get(key));
				dos.writeBytes(lineEnd);
			}
		}

		catch (MalformedURLException ex)
		{
			Log.d("MediaPlayer", "error: " + ex.getMessage(), ex);
		}

		catch (IOException ioe)
		{
			Log.d("MediaPlayer", "error: " + ioe.getMessage(), ioe);
		}

		// ------------------ read the SERVER RESPONSE
		try {
			inStream = new DataInputStream(conn.getInputStream());
			String str;

			while ((str = inStream.readLine()) != null) {
				Log.d("MediaPlayer", "Server Response: " + str);
			}

			inStream.close();
		}

		catch (IOException ioex) {
			Log.d("MediaPlayer", "error: " + ioex.getMessage(), ioex);
		}
		Log.d("MediaPlayer", "Done uploading everything");
	}

	private String getID() {
		TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
		return telephonyManager.getDeviceId();
	}

} //end activity
