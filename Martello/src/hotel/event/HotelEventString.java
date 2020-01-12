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

/**
 * 
 * 
 * @author Adam Prins, Han Tran
 */
public class HotelEventString implements Comparable<HotelEventString>{
	
	public static final String PATH = "/Users/adam/git/Martello/Martello/data/Murder-on-the-2nd-Floor-Raw-Data-v01.json";
	
	//Date in epoch time
	private long date;
	private String device;
	private String event;
	@SerializedName(value="guest-id")
	private String guest_id;
	@SerializedName(value="device-id")
	private String device_id;
	
	/**
	 * Constructor for a HotelEventString
	 * 
	 * @param date as a long in epoch time
	 * @param device the triggering device as a string
	 * @param event the triggering event as a string
	 * @param guest_id the guest-id that triggered the event
	 * @param device_id the device-id that triggered the event
	 */
	public HotelEventString(long date, String device, String event, 
							String guest_id, String device_id) {
		this.date=date;
		this.device=device;
		this.event=event;
		this.guest_id=guest_id;
		this.device_id=device_id;
	}
	
	/**
	 * Gets the time of the event in epoch time 
	 * 
	 * @return the time in epoch as a long
	 */
	public long getTime() {
		return date;
	}
	
	/**
	 * Sets the time to the given long
	 * 
	 * @param time as a long in epoch
	 */
	public void setTime(long time) {
		this.date=time;
	}
	
	/**
	 * The device that triggered the event
	 * 
	 * @return the string of the device that triggered the event
	 */
	public String getDevice() {
		return device;
	}
	
	/**
	 * The event on the device
	 * 
	 * @return the String representation of the triggering event
	 */
	public String getEvent() {
		return event;
	}
	
	/**
	 * The guest-id that is linked with the event
	 * 
	 * @return the guest-id as a String
	 */
	public String getGuest_id() {
		return guest_id;
	}
	
	/**
	 * The device-id that is linked with the event
	 * 
	 * @return the device-id as a String
	 */
	public String getDevice_id() {
		return device_id;
	}
	
	
	/**
	 * Returns the HotelEventString as a single string
	 * 
	 * @return a String representation of the HotelEventString
	 */
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
	
	/**
	 * Imports the pathed JSON file as an ArrayList of HotelEventString
	 * 
	 * @return An ArrayList of all events in the hotel
	 */
	public static ArrayList<HotelEventString> importData() {
		ArrayList<HotelEventString> events = new ArrayList<HotelEventString>();
		File file = new File(PATH);
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
	
	/**
	 * Gives the ability to compare and sort HotelEventString objects
	 * 
	 * @return negative id this is less than the given object, 0 if they are the same, 
	 * and positive if this is greater than the given object
	 */
	public int compareTo(HotelEventString event) {
		return (int) (this.date-event.date);
	}
	
}

