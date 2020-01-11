
public class SensorData {
	int time;
	String device;
	String device_id;
	String event;
	String guest_id;
	
	public SensorData(int time, String device, String device_id, String event, String guest_id) {
		this.time=time;
		this.device=device;
		this.device_id=device_id;
		this.event=event;
		this.guest_id=guest_id;
	}
	
}
