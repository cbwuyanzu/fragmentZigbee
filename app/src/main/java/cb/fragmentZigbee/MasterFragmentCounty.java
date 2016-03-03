package cb.fragmentZigbee;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.net.NetworkInfo.DetailedState;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds;
import android.app.ListFragment;
import android.content.Intent;
import android.content.res.Configuration;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * A fragment representing a list of Items.
 * <p />
 * <p />
 * Activities containing this fragment MUST implement the {@link Callbacks}
 * interface.
 */
public class MasterFragmentCounty extends ListFragment {
	int mCurCheckPosition = 0;
	int mShownCheckPosition = -1;
	static ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();


	// public void setUpdateListener(OnTutSelectedListener l) {
	// UI_UPDATE_LISTENER = 1;
	// }

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setListAdapter(new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_activated_1,
				Shakespeare.TITLES)); // 使用静态数组填充列表
		if (savedInstanceState != null) {
			mCurCheckPosition = savedInstanceState.getInt("curChoice", 0);
			mShownCheckPosition = savedInstanceState.getInt("shownChoice", -1);
		}
		getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		showDetails(mCurCheckPosition);
		
		}


	

	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt("curChoice", mCurCheckPosition);
		outState.putInt("shownChoice", mShownCheckPosition);
	}

	// @Override
	// public void onAttach(Activity activity) {
	// super.onAttach(activity);
	// try {
	// UI_UPDATE_LISTENER = (OnTutSelectedListener) activity;
	// } catch (ClassCastException e) {
	//
	// }
	// }

	@Override
	public void onDetach() {
		super.onDetach();

	}



	void showDetails(int index) {
		mCurCheckPosition = index;
		getListView().setItemChecked(index, true);
		if (mShownCheckPosition != mCurCheckPosition) {
			FragmentTransaction ft = getFragmentManager().beginTransaction();
			// DetailsFragment df = DetailsFragment.newInstance(index);
			// LightFragment lf=LightFragment.newInstance(index);
			// if (index != 5) {
			//
			// ft.replace(R.id.details, df);
			// ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
			// ft.commit();
			// }
			// mShownCheckPosition = index;
			// if (index == 5) {
			// ft.replace(R.id.details, lf);
			// ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
			// ft.commit();
			switch (index) {
			case 0:
				ConnectFragment cf = ConnectFragment.newInstance(index);
				ft.replace(R.id.details, cf);
				break;
			case 1:
				LightFragment lf = LightFragment.newInstance(index);
				ft.replace(R.id.details, lf);
				break;

			default:
				DetailsFragment df = DetailsFragment.newInstance(index);
				ft.replace(R.id.details, df);
				break;

			}
			ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
			ft.commit();
		}
	}

//	public interface OnChangeLocationListener {
//		void onChangeLocation(int index);
//	}

//	public static void setOnChangeLocationListener(OnChangeLocationListener l) {
//		mChangeLocList = l;
//	}
//	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		showDetails(position);
	}

}
