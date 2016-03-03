package cb.fragmentZigbee;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

public class MyThread extends Thread {
	{
		start(); // 类加载完成后直接启动
	}
	Handler handler;

	@Override
	public void run() {
		while (true) {

			Looper.prepare(); // 创建该线程的Looper对象
			handler = new Handler(Looper.myLooper()) {
				public void handleMessage(android.os.Message msg) {
					Log.i("handleMessage", "" + msg.what);
				};
			};

			Looper.loop(); // 这里是一个死循环
			// 此后的代码无法执行
		}
	}
}
