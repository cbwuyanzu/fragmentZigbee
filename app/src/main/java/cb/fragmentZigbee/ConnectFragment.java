package cb.fragmentZigbee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import android.os.Bundle;
import android.os.Parcelable;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Switch;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * 
 */
public class ConnectFragment extends Fragment {

	private Button mScanButton;
	private Switch mBleSwitch;


	public static ConnectFragment newInstance(int index) {
		// Required empty public constructor
		ConnectFragment fragment = new ConnectFragment();
		Bundle args = new Bundle();
		args.putInt("index", index);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View mRootView = inflater.inflate(R.layout.fragment_connect, container,
				false);
		return mRootView;

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		mScanButton = (Button) getActivity().findViewById(R.id.scanbutton);
		ScanButtonListener clicklistener = new ScanButtonListener();
		mScanButton.setOnClickListener(clicklistener);
		mBleSwitch = (Switch) getActivity().findViewById(R.id.bluetoothonoff);


	}

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);

	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

	}

	class ScanButtonListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {


		}

	}



	

}
