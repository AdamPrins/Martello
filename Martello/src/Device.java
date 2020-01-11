import java.awt.Point;

public class Device {
	public static enum device_id {	AP1_1, AP1_2, AP1_3, AP1_4, 
									AP2_1, AP2_2, AP2_3,
									Elevator, Stairwell, Ice_machine};
									
									
	public static Point getPoint(device_id id) {
		Point point;
		
		if (id==device_id.AP1_1) {
			point = new Point(120,200);
		}
		else if (id==device_id.AP1_2) {
			point = new Point(350,200);
		}
		else if (id==device_id.AP1_3) {
			point = new Point(250,310);
		}
		else if (id==device_id.AP1_4) {
			point = new Point(350,200);
		}
		else if (id==device_id.AP2_1) {
			point = new Point(180,430);
		}
		else if (id==device_id.AP2_2) {
			point = new Point(450,450);
		}
		else if (id==device_id.AP2_3) {
			point = new Point(320,430);
		}
		else {
			System.out.println("device_id not found");
			point = new Point(0,0);
		}
		
		return point;
	}
}
