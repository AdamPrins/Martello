package hotel.event;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import com.google.gson.*;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;

public class HotelEventString implements Comparable<HotelEventString>{
	public static final String PATH1 = "/Users/adam/git/Martello/Martello/data/team_str_date.json";
	public static final String PATH2 = "/Users/adam/git/Martello/Martello/data/team_timestamp_date.json";
	public static final String PATH3 = "/Users/adam/git/Martello/Martello/data/Murder-on-the-2nd-Floor-Raw-Data-v01.json";
	
	private long date;
	private String device;
	private String event;
	@SerializedName(value="guest-id")
	private String guest_id;
	@SerializedName(value="device-id")
	private String device_id;
	
	public HotelEventString(long date, String device, 
			String event, String guest_id, String device_id) {
		this.date=date;
		this.device=device;
		this.event=event;
		this.guest_id=guest_id;
		this.device_id=device_id;
	}
	
	public long getTime() {
		return date;
	}
	
	public void setTime(long time) {
		this.date=time;
	}
	
	public String getDevice() {
		return device;
	}
	
	public String getEvent() {
		return event;
	}
	
	public String getGuest_id() {
		return guest_id;
	}
	
	public String getDevice_id() {
		return device_id;
	}
	
	
	@Override
	public String toString() {
		String s="";
		s+="time: " +date+"\t";
		s+="device: " +device+"\t";
		s+="event: " +event+"\t";
		s+="guest_id: " +guest_id+"\t";
		s+="device_id: " +device_id+"\t";
		return s;
	}
	
	public static ArrayList<HotelEventString> importData() {
		ArrayList<HotelEventString> events = new ArrayList<HotelEventString>();
		File file = new File(PATH3);
		Gson gson = new Gson();
		
		try {
			JsonParser p = new JsonParser();
			JsonReader fileF = new JsonReader(new FileReader(file));
			JsonObject result = p.parse(fileF).getAsJsonObject();
			Set<Map.Entry<String, JsonElement>> entrySet = result.entrySet();
			for (Map.Entry<String, JsonElement> map:entrySet) {
				HotelEventString hotelEventString = gson.fromJson(map.getValue(), HotelEventString.class);
				hotelEventString.setTime( Long.parseUnsignedLong(map.getKey()));
				events.add(hotelEventString);
			}
			Collections.sort(events);
			Collections.reverse(events);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return events;
	}
	
	public int compareTo(HotelEventString event) {
		return (int) (this.date-event.date);
	}
	
}

