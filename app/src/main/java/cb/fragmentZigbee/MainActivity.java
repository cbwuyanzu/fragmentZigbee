package cb.fragmentZigbee;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import cb.fragmentZigbee.ControlAdapter.ControlListener;

import com.csr.mesh.LightModelApi;
import com.csr.mesh.PowerModelApi;
import com.csr.mesh.PowerModelApi.PowerState;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class MainActivity extends Activity implements ControlListener {
	public Handler uiHandler;
	private MyThread myThread = new MyThread();
	public static final String username = "admin";
	public static final String password = "admin";
	public String xml;
	public HttpClient httpclient;
	public static int firstRun = 0;

	Runnable connect = new Runnable() {

		public void run() {

			HttpPost httppost = new HttpPost(
					"http://192.168.1.37/gateway/default/system/user/login?user=admin");

			try {

				HttpResponse response = httpclient.execute(httppost);
				Log.i("net", response.getProtocolVersion() + "");
				Header head[] = response.getAllHeaders();
				int r = head.length;
				for (int i = 0; i < r; i++) {
					Log.i("head", head[i].toString());
				}

				// System.out.println(response.getStatusLine().getStatusCode());
				// System.out.println(response.getStatusLine().getReasonPhrase());
				Log.i("net", response.getStatusLine().toString());
				HttpEntity entity2 = response.getEntity();
				if (entity2 != null) {
					String resultentity = EntityUtils.toString(entity2);

					Log.i("net", resultentity);

				}

			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {

				e.printStackTrace();
			}

			Message msg = new Message();
			Bundle data = new Bundle();
			data.putString("value", "请求结果");
			msg.setData(data);
			uiHandler.sendMessage(msg);
			postToNoneUIThread(get, 500);
		}
	};
	Runnable get = new Runnable() {
		public void run() {

			HttpGet httpget = new HttpGet("http://192.168.1.37/Device/List");

			try {

				HttpResponse response = httpclient.execute(httpget);
				Log.i("net", response.getProtocolVersion() + "");

				Log.i("net", response.getStatusLine().toString());
				Header head[] = response.getAllHeaders();
				int r = head.length;
				for (int i = 0; i < r; i++) {
					Log.i("head", head[i].toString());
				}
				HttpEntity entity22 = response.getEntity();
				if (entity22 != null) {
					String resultentity2 = EntityUtils.toString(entity22);
					Document document = DocumentHelper.parseText(resultentity2);
					Element root = document.getRootElement();
					List nodes = root.elements("Device");
					for (Iterator it = nodes.iterator(); it.hasNext();) {
						Element element = (Element) it.next();
						String id = "";
						String name = "";
						// String level = "";
						for (Iterator it2 = element.elementIterator(); it2
								.hasNext();) {
							Element element2 = (Element) it2.next();
							String elementName = element2.getName();
							Log.i("XML", elementName);
							switch ((Animal.getAnimal(elementName))) {
							case ID: {
								id = element2.getText();
//								Log.i("XML", id);
								break;
							}
							case Name: {
								name = element2.getText();
//								Log.i("XML", name);
								break;
							}
							// case Level: {
							// level = element2.getText();
							// Log.i("XML", level);
							// break;
							// }
							default:
								break;
							}
						}
						if (firstRun < nodes.size()) {
							ControlDevice controlDevice = new ControlDevice(
									name, id);
							LightFragment.devices.add(controlDevice);
							firstRun++;
						}
					}
					Log.i("size", LightFragment.devices.size() + "");

				}

				// }
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Message msg = new Message();
			Bundle data = new Bundle();
			data.putString("value", "请求结果");
			msg.setData(data);
			uiHandler.sendMessage(msg);
			postToNoneUIThread(getstate, 500);
		}
	};
	Runnable getstate = new Runnable() {
		public void run() {

			HttpGet httpget = new HttpGet("http://192.168.1.37/Device/Status");

			try {

				HttpResponse response = httpclient.execute(httpget);
				Log.i("net", response.getProtocolVersion() + "");

				Log.i("net", response.getStatusLine().toString());
				Header head[] = response.getAllHeaders();
				int r = head.length;
				for (int i = 0; i < r; i++) {
					Log.i("head", head[i].toString());
				}
				HttpEntity entity22 = response.getEntity();
				if (entity22 != null) {
					String resultentity2 = EntityUtils.toString(entity22);
					Document document = DocumentHelper.parseText(resultentity2);
					Element root = document.getRootElement();
					List nodes = root.elements("Device");
					for (Iterator it = nodes.iterator(); it.hasNext();) {
						Element element = (Element) it.next();
						String id = "";
						String power = "";
						String level = "";
						for (Iterator it2 = element.elementIterator(); it2
								.hasNext();) {
							Element element2 = (Element) it2.next();
							String elementName = element2.getName();
							Log.i("XML", elementName);
							switch ((Animal.getAnimal(elementName))) {
							case ID: {
								id = element2.getText();
								Log.i("XML", id);
								break;
							}
							case OnOff: {
								power = element2.getText();
								Log.i("XML", power);
								break;
							}
							case Level: {
								level = element2.getText();
								Log.i("XML", level);
								break;
							}
							default:
								break;
							}
						}

						Iterator<ControlDevice> iter = LightFragment.devices
								.iterator();
						while (iter.hasNext()) {
							ControlDevice controlDevice = iter.next();
							if (id.equals(controlDevice.getDeviceId())) {
								int deviceBrightness = Integer.valueOf(level,16);
								controlDevice
										.setDeviceBrightness(deviceBrightness);
								if (power.equals("01")) {
									controlDevice.setDevicePower(true);
								} else if (power.equals("00")) {
									controlDevice.setDevicePower(false);
								}
							}
						}

					}

				}

				// }
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Message msg = new Message();
			Bundle data = new Bundle();
			data.putString("value", "请求结果");
			msg.setData(data);
			uiHandler.sendMessage(msg);
			postToNoneUIThread(getstate, 5000);
		}
	};
	Runnable post = new Runnable() {

		public void run() {

			// 连网操作

			HttpPost httppost = new HttpPost(
					"http://192.168.1.37/Device/Control");
			try {
				StringEntity entity = new StringEntity(xml);
				httppost.setEntity(entity);
				HttpResponse response3 = httpclient.execute(httppost);
				Log.i("net", response3.getProtocolVersion() + "");
				Header head[] = response3.getAllHeaders();
				int r = head.length;
				for (int i = 0; i < r; i++) {
					Log.i("head", head[i].toString());
				}
				Log.i("net", response3.getStatusLine().toString());
				HttpEntity entity2 = response3.getEntity();
				if (entity2 != null) {
					String resultentity = EntityUtils.toString(entity2);
					Log.i("net", resultentity);

				}
				// }
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Message msg = new Message();
			Bundle data = new Bundle();
			data.putString("value", "请求结果");
			msg.setData(data);
			uiHandler.sendMessage(msg);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		httpclient = new DefaultHttpClient();
		postToNoneUIThread(connect);
		uiHandler = new Handler() {

			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				Bundle data = msg.getData();
				String val = data.getString("value");
				Log.i("handler", val);

				// TODO
				// UI界面的更新等相关操作
			}
		};

	}

	public void switchOnOff(String id, boolean state) {

		if (state == true) {
			StringBuilder sb = new StringBuilder();
			sb.append("<Control><ID>");
			sb.append(id);
			sb.append("</ID><OnOff><Cmd>");
			sb.append("01");
			sb.append("</Cmd></OnOff></Control>");
			xml = sb.toString();
			Log.i("POST", xml);
			postToNoneUIThread(post);
		} else {
			StringBuilder sb = new StringBuilder();
			sb.append("<Control><ID>");
			sb.append(id);
			sb.append("</ID><OnOff><Cmd>");
			sb.append("00");
			sb.append("</Cmd></OnOff></Control>");
			xml = sb.toString();
			Log.i("POST", xml);
			postToNoneUIThread(post);
		}

	}

	@Override
	public void brightness(String id, byte bright) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append("<Control><ID>");
		sb.append(id);
		sb.append("</ID><Level><Cmd>");
		sb.append("04");
		sb.append("</Cmd><Status>");
		if (bright>16){
		sb.append(Integer.toHexString(bright));}
		else{
			sb.append(0);
			sb.append(Integer.toHexString(bright));
		}
		sb.append("</Status></Level></Control>");
		xml = sb.toString();
		Log.i("POST", xml);
		postToNoneUIThread(post);

	}

	public void postToNoneUIThread(Runnable r) {
		// 执行到这里的时候，子线程可能尚未启动，等待子线程启动，等待的时间会很短，
		while (myThread.handler == null) {
		}
		myThread.handler.post(r);

	}

	public void postToNoneUIThread(Runnable r, long i) {
		while (myThread.handler == null) {
		}
		myThread.handler.postDelayed(r, i);
	}

	public enum Animal {
		ID, OnOff, Level, Name, MacAddress, Type, SubType, State;

		public static Animal getAnimal(String animal) {
			return valueOf(animal);
		}
	}

}
