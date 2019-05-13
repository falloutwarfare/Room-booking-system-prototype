package objects;

import java.io.Serializable;

public class Room implements Serializable  {

	

//	private static final long serialVersionUID = 1L;
	private int roomID;
	private int maxCapacity;
	private int stations;
	private int chairs;
	private Boolean smartboard;
	private Boolean colorPrinter;
	private Boolean BWPrinter;
	private Boolean scanner;
	
	public Room(int roomIdCounter, int maxCapacity, int stations) {
		
		this.roomID = roomIdCounter;
		this.maxCapacity = maxCapacity;
		this.stations = stations;
		
	}
	
	public Room(int roomIdCounter, int maxCapacity, int stations, int chairs, Boolean smartboard, Boolean colorPrinter, Boolean BWPrinter, Boolean scanner) {
		
		this.roomID = roomIdCounter;
		this.maxCapacity = maxCapacity;
		this.stations = stations;
		this.chairs = chairs;
		this.smartboard = smartboard;
		this.colorPrinter = colorPrinter;
		this.BWPrinter = BWPrinter;
		this.scanner = scanner;
		
	}
	
	public int getRoomID() {
		return roomID;
		
	}
	
	public int getCapacity() {
		return maxCapacity;
		
	}
	
	public int getStations() {
		return stations;
		
	}
	
	public int getChairs() {
		return chairs;
		
	}
	
	public Boolean getSmartboards() {
		return smartboard;
		
	}
	
	public Boolean getColorPrinters() {
		return colorPrinter;
		
		}
	
	public Boolean getBWPrinters() {
		return BWPrinter;
		
	}
	
	public Boolean getScanners() {
		return scanner;
		
	}
	

	
	
	
	
}
