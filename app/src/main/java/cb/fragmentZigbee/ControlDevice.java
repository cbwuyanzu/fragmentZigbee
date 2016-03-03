package cb.fragmentZigbee;

public class ControlDevice{
	private String deviceName;
	private String deviceId;
	private int deviceBrightness;
	private boolean devicePower;
	public ControlDevice(String name,String id){
		this.deviceId=id;
		this.deviceName=name;
		this.deviceBrightness=0;
		this.devicePower=false;
	}
	public ControlDevice(String id){
		this.deviceId=id;
		this.deviceName="";
		this.deviceBrightness=0;
		this.devicePower=false;
	}

	public ControlDevice getControlDevice(String id){
		return this;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public int getDeviceBrightness() {
		return deviceBrightness;
	}
	public void setDeviceBrightness(int deviceBrightness) {
		this.deviceBrightness = deviceBrightness;
	}
	public boolean getDevicePower() {
		return devicePower;
	}
	public void setDevicePower(boolean devicePower) {
		this.devicePower = devicePower;
	}
		

	
}