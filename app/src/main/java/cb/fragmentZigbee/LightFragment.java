package cb.fragmentZigbee;

import java.util.ArrayList;
import java.util.zip.Inflater;

import cb.fragmentZigbee.ControlAdapter;
import cb.fragmentZigbee.ControlDevice;
import cb.fragmentZigbee.ControlAdapter.ControlListener;

import com.csr.mesh.PowerModelApi;
import com.csr.mesh.PowerModelApi.PowerState;

import android.os.Bundle;
import android.app.Fragment;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.database.sqlite.SQLiteDatabase;

/**
 * A simple {@link Fragment} subclass.
 * 
 */
public class LightFragment extends Fragment {
	private ListView listView;
	static ArrayList<ControlDevice> devices = new ArrayList<ControlDevice>();

	public static LightFragment newInstance(int index) {
		LightFragment fragment = new LightFragment();
		Bundle args = new Bundle();
		args.putInt("index", index);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View mRootView = inflater.inflate(R.layout.fragment_light, container,
				false);

		return mRootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		listView = (ListView) getActivity().findViewById(R.id.listlight);



	

		// BooksList.setOnItemClickListener(this);

		// ControlDevice all = new ControlDevice("all", 0);
		// ControlDevice niuhaha = new ControlDevice("light1", 0x8001);
		// ControlDevice cb = new ControlDevice("light2", 0x8002);
		// ControlDevice shuaicb = new ControlDevice("light3", 0x8003);
		// devices.add(all);

		// devices.add(niuhaha);
		// devices.add(cb);
		// devices.add(shuaicb);

		ControlAdapter adapter = new ControlAdapter(getActivity(), devices,
				R.layout.control_list_row);
		listView.setAdapter(adapter);

	}

}
