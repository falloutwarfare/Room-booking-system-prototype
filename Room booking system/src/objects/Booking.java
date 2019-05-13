package objects;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class Booking implements Serializable {

	private int bookedRoom;
	private String bookedUser;
	private LocalDate bookedDate;
	private LocalTime bookedTime;
	private long bookedDuration;
	
	public Booking(int bookedRoom, String bookedUser, LocalDate bookedDate, LocalTime bookedTime, long bookedDuration) {
		
		this.bookedRoom = bookedRoom;
		this.bookedUser = bookedUser;
		this.bookedDate = bookedDate;
		this.bookedTime = bookedTime;
		this.bookedDuration = bookedDuration;
		
	}
	
	public int getRoom() {
		
		return bookedRoom;
	}
	public String getUser() {
		
		return bookedUser;
	}
	public LocalDate getDate() {
		
		return bookedDate;
	}
	public LocalTime getTime() {
		
		return bookedTime;
	}
	public long getDuration() {
		
		return bookedDuration;
	}
	
}
