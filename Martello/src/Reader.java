import java.io.FileReader;
import com.google.gson.*;

public class Reader {

	public static void readFile(String path) {
		try {
		Gson gson = new Gson();
		SensorData sensorData = gson.fromJson(new FileReader(path), SensorData.class);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
