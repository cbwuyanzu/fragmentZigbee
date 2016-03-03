package cb.fragmentZigbee;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

public class MyThread extends Thread {
	{
		start(); // �������ɺ�ֱ������
	}
	Handler handler;

	@Override
	public void run() {
		while (true) {

			Looper.prepare(); // �������̵߳�Looper����
			handler = new Handler(Looper.myLooper()) {
				public void handleMessage(android.os.Message msg) {
					Log.i("handleMessage", "" + msg.what);
				};
			};

			Looper.loop(); // ������һ����ѭ��
			// �˺�Ĵ����޷�ִ��
		}
	}
}
