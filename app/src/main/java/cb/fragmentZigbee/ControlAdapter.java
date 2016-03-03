package cb.fragmentZigbee;

import java.util.List;

import com.csr.mesh.PowerModelApi.PowerState;

import android.R.string;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Toast;

public class ControlAdapter extends BaseAdapter {

	private List<ControlDevice> controlDevices; // 数据
	private int resource; // item的布局
	private Context context;

	public ControlListener mControlListener;

	/**
	 * 
	 * @param context
	 *            mainActivity
	 * @param controlDevices
	 *            显示的数据
	 * @param resource
	 *            一个Item的布局
	 */
	public ControlAdapter(Context context, List<ControlDevice> controlDevices,
			int resource) {
		this.context = context;
		mControlListener = (ControlListener) context;
		this.controlDevices = controlDevices;
		this.resource = resource;
	}

	/*
	 * 获得数据总数
	 */
	@Override
	public int getCount() {
		return controlDevices.size();
	}

	/*
	 * 根据索引为position的数据
	 */
	@Override
	public ControlDevice getItem(int position) {
		ControlDevice item = null;
		if (controlDevices != null) {
			item = controlDevices.get(position);
		}
		return item;
	}


	/*
	 * 根据索引值获得Item的Id
	 */
	@Override
	public long getItemId(int position) {
		return position;
	}

	private static class ViewHolder {
		TextView nameTextView;
		TextView idTextView;
		TextView brightnessTextView;// 为了减少开销，则只在第一页时调用findViewById
		Switch powerSwitch;
		SeekBar brightBar;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final int index=position;
		final ViewHolder viewHolder;

		if (convertView == null) {
			viewHolder = new ViewHolder();
			LayoutInflater inflator = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflator.inflate(resource, null);
			viewHolder.nameTextView = (TextView) convertView
					.findViewById(R.id.name); // 为了减少开销，则只在第一页时调用findViewById
			viewHolder.powerSwitch = (Switch) convertView
					.findViewById(R.id.power);
			viewHolder.idTextView = (TextView) convertView
					.findViewById(R.id.id1);
			viewHolder.brightBar = (SeekBar) convertView
					.findViewById(R.id.brightness);
			viewHolder.brightnessTextView = (TextView) convertView
					.findViewById(R.id.brightText);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		ControlDevice device = getItem(position);
		if (device != null) {
			viewHolder.nameTextView.setText(device.getDeviceName());
			viewHolder.idTextView.setText(device.getDeviceId());
			viewHolder.powerSwitch
					.setOnCheckedChangeListener(new OnCheckedChangeListener() {

						@Override
						public void onCheckedChanged(CompoundButton buttonView,
								boolean isChecked) {
							String viewId = viewHolder.idTextView.getText()
									+ "";
							Log.i("switch", viewId);

							mControlListener.switchOnOff(viewId, isChecked);

						}
					});
			viewHolder.brightBar
					.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

						@Override
						public void onStopTrackingTouch(SeekBar arg0) {
							// TODO Auto-generated method stub

						}

						@Override
						public void onStartTrackingTouch(SeekBar arg0) {
							// TODO Auto-generated method stub

						}

						@Override
						public void onProgressChanged(SeekBar arg0, int arg1,
								boolean arg2) {
							// TODO Auto-generated method stub
							String viewId = viewHolder.idTextView.getText()
									+ "";

							mControlListener.brightness(viewId, (byte) arg1);

							// Toast.makeText(context,controlDevices.get(heiheiId).getDeviceName()+arg1,Toast.LENGTH_SHORT).show();
						}
					});
		}
		
		setData(index,viewHolder);
		return convertView;
	}
	 private void setData(int position, ViewHolder view) {
		 
	        ControlDevice data = LightFragment.devices.get(position);
	        if (data.getDevicePower()) {
	            view.powerSwitch.setChecked(true);
	         
	        } else {
	        	view.powerSwitch.setChecked(false);
	        }
	        view.brightBar.setProgress(data.getDeviceBrightness());
//	        view.brightBar.set
	 }

	public interface ControlListener {
		public void switchOnOff(String id, boolean state);

		public void brightness(String id, byte bright);
	}

}
