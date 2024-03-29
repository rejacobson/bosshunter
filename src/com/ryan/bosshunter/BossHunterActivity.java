package com.ryan.bosshunter;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class BossHunterActivity extends Activity {
	
	private static final String TAG = BossHunterActivity.class.getSimpleName();
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(new MainGamePanel(this));
        Log.d(TAG, "View created.");
    }
    
    @Override
    protected void onStop() {
    	Log.d(TAG, "Stopping.");
    	super.onStop();
    }
    
    @Override
    protected void onDestroy() {
    	Log.d(TAG, "Destroying.");
    	super.onDestroy();
    }
}