package com.sketchware.care.get.contacts;

import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.*;
import android.content.Intent;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.net.Uri;
import android.os.*;
import android.os.Bundle;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.io.*;
import java.io.InputStream;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import org.json.*;

public class MainActivity extends Activity {
	
	private LinearLayout linear1;
	private TextView textview1;
	
	private Intent intent = new Intent();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.main);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		textview1 = findViewById(R.id.textview1);
	}
	
	private void initializeLogic() {
		_explanation("• Tutorial by Sketchware Admins •");
		_explanation("   - Feel free to learn, use & copy.");
		_explanation("   - Request another tutorials on #sketchware-request.");
		_explanation("   - You can request on Sketchware Facebook Group too, just tag @SketchwareAdmins");
		/*

DO NOT FORGET TO ADD THE PERMISSION TO THE MANIFEST TO RUN THIS APP, YOU CAN USE APK EDITOR AND ADD THIS TO THE MANIFEST:

<uses-permission android:name="android.permission.READ_CONTACTS"/>

*/
		// Please to do not put write/read block or any blocks/components which will ask for more manifest permissions in MainActivity to prevent double/clone onRequestPermissionsResult() error
		// Checking the read_contact permission
		
		if (checkSelfPermission(android.Manifest.permission.READ_CONTACTS) == android.content.pm.PackageManager.PERMISSION_DENIED) {
			
			requestPermissions(new String[] {android.Manifest.permission.READ_CONTACTS}, 9090);
			
		} else {
			intent.setClass(getApplicationContext(), MyContactsActivity.class);
			startActivity(intent);
			finish();
			
		}
	}
	
	public void _init() {
	}
	
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		
		if (requestCode == 9090 && checkSelfPermission(android.Manifest.permission.READ_CONTACTS) == android.content.pm.PackageManager.PERMISSION_GRANTED) {
			
			initializeLogic();
			
		} else {
			
			showMessage("Please grant the permissions request");
			
			finish();
			
		}
	}
	
	
	public void _explanation(final String _justReadMe) {
		
	}
	
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels() {
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels() {
		return getResources().getDisplayMetrics().heightPixels;
	}
}