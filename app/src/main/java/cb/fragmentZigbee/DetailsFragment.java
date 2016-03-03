package cb.fragmentZigbee;

import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass. Use the
 * {@link DetailsFragment#newInstance} factory method to create an instance
 * of this fragment.
 * 
 */
public class DetailsFragment extends Fragment {

	public static DetailsFragment newInstance(int index) {
		DetailsFragment fragment = new DetailsFragment();
		Bundle args = new Bundle();
		args.putInt("index", index);
		fragment.setArguments(args);
		return fragment;
	}

	public DetailsFragment() {
		// Required empty public constructor
	}

//	@Override
//	public void onAttach(Activity activity) {
//		super.onAttach(activity);
//		try {
//			UI_UPDATE_LISTENER = (OnTutSelectedListener) activity;
//		} catch (Exception e) {
//		}
//	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		if (container == null) {              
            return null;  
        }  
			View viewer=inflater.inflate(R.layout.fragment_test, container,false);
        return viewer;  
	}
}
