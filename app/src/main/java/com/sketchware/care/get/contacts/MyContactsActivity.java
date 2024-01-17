package com.sketchware.care.get.contacts;

import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.os.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;
import org.json.*;

public class MyContactsActivity extends Activity {
	
	private double a = 0;
	private String names = "";
	
	private ArrayList<HashMap<String, Object>> listColor = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> contacts = new ArrayList<>();
	
	private LinearLayout linear1;
	private ListView listview1;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.my_contacts);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		listview1 = findViewById(R.id.listview1);
		
		listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				SketchwareUtil.showMessage(getApplicationContext(), "You clicked ".concat(contacts.get((int)_position).get("name").toString().concat(" (".concat(contacts.get((int)_position).get("phone").toString().concat(")")))));
			}
		});
	}
	
	private void initializeLogic() {
		names = ":";
		_initColors();
		listview1.setAdapter(new Listview1Adapter(contacts));
		_getAllContacts();
		((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
		getActionBar().setSubtitle(String.valueOf(contacts.size()).concat(" Contacts found"));
	}
	
	public void _getAllContacts() {
		android.database.Cursor c = getContentResolver().query(android.provider.ContactsContract.CommonDataKinds.Phone.CONTENT_URI, new String[] {android.provider.ContactsContract.CommonDataKinds.Phone._ID, android.provider.ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, android.provider.ContactsContract.CommonDataKinds.Phone.NUMBER}, null, null, android.provider.ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC");
		while(c.moveToNext()) {
			_addContact(c.getString(1), c.getString(2));
		}
	}
	
	
	public void _addContact(final String _name, final String _phone) {
		if (!names.contains(":".concat(_name.concat(":")))) {
			a = SketchwareUtil.getRandom((int)(0), (int)(listColor.size() - 1));
			{
				HashMap<String, Object> _item = new HashMap<>();
				_item.put("name", _name.trim());
				contacts.add(_item);
			}
			
			contacts.get((int)contacts.size() - 1).put("phone", _phone);
			contacts.get((int)contacts.size() - 1).put("color_a", listColor.get((int)a).get("a").toString());
			contacts.get((int)contacts.size() - 1).put("color_b", listColor.get((int)a).get("b").toString());
			names = names.concat(_name.concat(":"));
		}
	}
	
	
	public void _addColor(final String _a, final String _b) {
		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("a", _a);
			listColor.add(_item);
		}
		
		listColor.get((int)listColor.size() - 1).put("b", _b);
	}
	
	
	public void _initColors() {
		_addColor("#FFEBEE", "#F44336");
		_addColor("#FCE4EC", "#E91E63");
		_addColor("#F3E5F5", "#9C27B0");
		_addColor("#EDE7F6", "#673AB7");
		_addColor("#E8EAF6", "#3F51B5");
		_addColor("#E3F2FD", "#2196F3");
		_addColor("#E1F5FE", "#03A9F4");
		_addColor("#E0F7FA", "#00BCD4");
		_addColor("#E0F2F1", "#009688");
		_addColor("#E8F5E9", "#4CAF50");
		_addColor("#F1F8E9", "#8BC34A");
		_addColor("#F9FBE7", "#CDDC39");
		_addColor("#FFFDE7", "#FFEB3B");
		_addColor("#FFF8E1", "#FFC107");
		_addColor("#FFF3E0", "#FF9800");
		_addColor("#FBE9E7", "#FF5722");
		_addColor("#EFEBE9", "#795548");
	}
	
	
	public void _setRoundCorner(final View _view, final String _color, final double _radius) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
		
		gd.setColor(Color.parseColor(_color));
		
		gd.setCornerRadius((int)_radius);
		_view.setBackground(gd);
	}
	
	
	public void _explanation(final String _justReadMe) {
		
	}
	
	public class Listview1Adapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Listview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public int getCount() {
			return _data.size();
		}
		
		@Override
		public HashMap<String, Object> getItem(int _index) {
			return _data.get(_index);
		}
		
		@Override
		public long getItemId(int _index) {
			return _index;
		}
		
		@Override
		public View getView(final int _position, View _v, ViewGroup _container) {
			LayoutInflater _inflater = getLayoutInflater();
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.contact_item, null);
			}
			
			final LinearLayout linear1 = _view.findViewById(R.id.linear1);
			final TextView textview1 = _view.findViewById(R.id.textview1);
			final LinearLayout linear2 = _view.findViewById(R.id.linear2);
			final TextView textview2 = _view.findViewById(R.id.textview2);
			final TextView textview3 = _view.findViewById(R.id.textview3);
			
			textview1.setText(contacts.get((int)_position).get("name").toString().substring((int)(0), (int)(1)));
			textview2.setText(contacts.get((int)_position).get("name").toString());
			textview3.setText(contacts.get((int)_position).get("phone").toString());
			_setRoundCorner(textview1, contacts.get((int)_position).get("color_a").toString(), SketchwareUtil.getDip(getApplicationContext(), (int)(21)));
			textview1.setTextColor(Color.parseColor(contacts.get((int)_position).get("color_b").toString()));
			
			return _view;
		}
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