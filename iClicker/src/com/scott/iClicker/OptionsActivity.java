package com.scott.iClicker;

import android.media.AudioManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;


import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class OptionsActivity extends Activity{
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.options);
		
	}
	
	public void silentmodeClicked (View v) {
	    // Perform action on clicks, depending on whether it's now checked
	    if (((CheckBox) v).isChecked()) {
	    	AudioManager audiomanager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
	    	audiomanager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
	        Toast.makeText(OptionsActivity.this, "Sounds Disabled", Toast.LENGTH_SHORT).show();
	    } else {
	    	AudioManager audiomanager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
	    	audiomanager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
	        Toast.makeText(OptionsActivity.this, "Sounds Enabled", Toast.LENGTH_SHORT).show();
	    }
	}

		
}
