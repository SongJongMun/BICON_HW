package com.example.bicon_hw;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ListView;

import com.radiusnetworks.ibeacon.*;

public class MonitoringActivity extends Activity{
	private ListView list = null;
	private BeaconAdapter adapter = null;
	private ArrayList<beacon> arrayL = new ArrayList<beacon>();
	private LayoutInflater inflater;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_monitor);
		
		list = (ListView) findViewById(R.id.list);
		//비콘리스트를 표시하는 리스트뷰객체 가져옴
		
		adapter = new BeaconAdapter();
		//내부 private 객체 가져옴
		
		list.setAdapter(adapter);
		//리스트 뷰 내부적으로 List Adapter를 통해서만 데이터에 접근
		
		inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		//레이아웃 XML파일을 View객체로 만듬
		
		/** 
		 * Test Code Printing Beacon Data
		 */
		Toast.makeText(getApplicationContext(), "비콘 서비스에 연결되었습니다.", Toast.LENGTH_LONG).show();
		
		beacon aB = new beacon();
		beacon bB = new beacon();
		beacon cB = new beacon();
		
		arrayL.clear();
		arrayL.add(aB);
		arrayL.add(bB);
		arrayL.add(cB);
		//arrayL.addAll((ArrayList<IBeacon>) new IBeacons());
		adapter.notifyDataSetChanged();
		
		Toast.makeText(getApplicationContext(), "Test 내용 출력.", Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.monitoring, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}


	private class BeaconAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			if (arrayL != null && arrayL.size() > 0)
				return arrayL.size();
			else
				return 0;
		}

		@Override
		public IBeacon getItem(int arg0) {
			return arrayL.get(arg0);
		}

		@Override
		public long getItemId(int arg0) {
			return arg0;
		}

		/**
		 * Get a View that displays the data at the specified position in the data set. 
		 * You can either create a View manually or inflate it from an XML layout file. When the View is inflated, 
		 * the parent View (GridView, ListView...) will apply default layout parameters unless you use 
		 * */
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			try {
				ViewHolder holder;

				if (convertView != null) {
					holder = (ViewHolder) convertView.getTag();
				} else {
					holder = new ViewHolder(convertView = inflater.inflate(R.layout.tupple_monitoring, null));
				}
				
				if (arrayL.get(position).getProximityUuid() != null)
					holder.beacon_uuid.setText("UUID: " + arrayL.get(position).getProximityUuid());
					holder.beacon_major.setText("Major: " + arrayL.get(position).getMajor());
					holder.beacon_minor.setText(", Minor: " + arrayL.get(position).getMinor());
					holder.beacon_proximity.setText("Proximity: " + arrayL.get(position).getProximity());
					holder.beacon_rssi.setText(", Rssi: " + arrayL.get(position).getRssi());
					holder.beacon_txpower.setText(", TxPower: " + arrayL.get(position).getTxPower());
					holder.beacon_range.setText("" + arrayL.get(position).getAccuracy());
			} catch (Exception e) {
				e.printStackTrace();
			}

			return convertView;
		}
	
		private class ViewHolder {
			private TextView beacon_uuid;
			private TextView beacon_major;
			private TextView beacon_minor;
			private TextView beacon_proximity;
			private TextView beacon_rssi;
			private TextView beacon_txpower;
			private TextView beacon_range;
	
			public ViewHolder(View view) {
				beacon_uuid = (TextView) view.findViewById(R.id.BEACON_uuid);
				beacon_major = (TextView) view.findViewById(R.id.BEACON_major);
				beacon_minor = (TextView) view.findViewById(R.id.BEACON_minor);
				beacon_proximity = (TextView) view.findViewById(R.id.BEACON_proximity);
				beacon_rssi = (TextView) view.findViewById(R.id.BEACON_rssi);
				beacon_txpower = (TextView) view.findViewById(R.id.BEACON_txpower);
				beacon_range = (TextView) view.findViewById(R.id.BEACON_range);
	
				view.setTag(this);
			}
		}
	}
}
