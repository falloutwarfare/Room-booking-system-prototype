package manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import objects.User;
import objects.Room;
import objects.Admin;
import objects.Booking;

public class Manager {

    /** The required format for date string inputs */
    public static final String DATE_FORMAT_STRING = "dd/MM/yyyy";
    /** The required format for time string inputs */
    public static final String TIME_FORMAT_STRING = "HH:mm";
    
    /** The formatter for processing user date input */
    private DateTimeFormatter dateFormatter;
    /** The formatter for processing user time input */
    private DateTimeFormatter timeFormatter;
	
    /** Stores  Room object's index though their id number*/
    private Map<Integer, Room> roomList;
    
    /** Stores User, Admin and Booking objects in seperate ArrayLists */
	public ArrayList<User> userList;
	public ArrayList<Admin> adminList;
	public ArrayList<Booking> bookingList;
	
	/** counter to track next room ID */
    private int roomIdCounter = 100;
    
    /** an instance of a Room object */
    private Room currentRoom;
	
	/**
	 * 
	 * Constructs a new Manager
	 * 
	 */
	public Manager() {
		
		roomList = new HashMap<>();
		userList = new ArrayList<>();
		adminList = new ArrayList<>();
		bookingList = new ArrayList<>();
		
		addRooms();
		addAdmins();
		
        dateFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT_STRING);
        timeFormatter = DateTimeFormatter.ofPattern(TIME_FORMAT_STRING);
        
        
	}
	
    /**
     * 
     * Add new admin accounts to the system with the given details.
     * 
     */
	public void addAdmins() 
	{
		
		Admin admin1 = new Admin("ADMIN01", "ADMINISTRATION01");
		adminList.add(admin1);
		
		Admin admin2 = new Admin("ADMIN02", "ADMINISTRATION02");
		adminList.add(admin2);
		
	}
	
    /**
     * 
     * Add new rooms to the system with the given details.
     * 
     */
    public void addRooms()
    {
    	
        roomIdCounter++;
        
        Room room = new Room(roomIdCounter, 10, 10, 10, true, true, true, true);
        roomList.put(roomIdCounter, room);
        
        roomIdCounter++;
        
        Room room2 = new Room(roomIdCounter, 10, 10, 0, true, false, true, true);
        roomList.put(roomIdCounter, room2);
        
        roomIdCounter++;
        
        Room room3 = new Room(roomIdCounter, 20, 15, 5, true, true, false, true);
        roomList.put(roomIdCounter, room3);
        
        roomIdCounter++;
        
        Room room4 = new Room(roomIdCounter, 15, 15, 0, true, true, true, true);
        roomList.put(roomIdCounter, room4);
        
    }
    
    /**
     * 
     * Adds a new booking to the system with the given details
     * 
     * @param roomId the ID of the room used for the booking
     * @param bookedDate the date of the booking
     * @param bookedTime the time of the booking
     * @param bookedDuration the duration of the booking 
     * 
     **/
    public void addBooking(int roomId, LocalDate bookedDate, LocalTime bookedTime, long bookedDuration) 
    {
    	
    	loadBookings();
    	String bookedUser = loadLoggedUser();
    	
		Booking booking = new Booking(roomId, bookedUser, bookedDate, bookedTime , bookedDuration);
		bookingList.add(booking);
		
		saveBookings();
    	
    }
    
    /**
     * 
     * Not used will be removed from final product
     * 
     * */
    public void addDefaultBooking(int key, LocalDate bookedDate, LocalTime bookedTime, long bookedDuration) 
    {
    	
    	String bookedUser = loadLoggedUser();
    	
		Booking booking = new Booking(key, bookedUser, bookedDate, bookedTime , bookedDuration);
		bookingList.add(booking);
		
		saveBookings();
    	
    }
    
    /**
     * Add a new user to the system with the given details. There is a
     * check to see if there is an existing student with same details
     * 
     * @param username the name assigned to the user
     * @param password the password assigned to the user
     * 
     * @return {@code true} if the user was created, and
     *         {@code false} otherwise.
     * 
     */
    public boolean addNewUser(String username, String password) throws IOException
    {
    	
    	int occurences = 0;
    	
    	loadUsers();
    	
		for(User user: userList) 
		{
			String name = user.getUsername(); 
			
			if(name.equals(username)) 
			{
				occurences++;
			}
			
		}
		
		if(occurences == 0){
			
			User user = new User(username, password);
			userList.add(user);
			saveUsers();
			
			return true;
			
		}
		else
		{
			
			return false;
			
		}
    }
    
    /**
     * Delete a given user record from the system.
     * 
     * @param username the identifier of the user to delete.
     * 
     * @return {@code true} if the given user record was deleted, and
     *         {@code false} otherwise.
     */
    public Boolean deleteUser(String username) throws IOException
    {
    	
    	int index = 0;
    	Boolean deleted = false;
    	
    	loadUsers();
    	
		for(User user: userList)
		{
			
			String name = user.getUsername();
			
			if(name.equals(username))
			{
				
				userList.remove(index);
				deleted = true;
				break;

			}
			else
			{
				
				index++;
				
			}
		}
		
		saveUsers();
		return deleted;
    }
    
    /**
     * Resets the password for a given user
     * 
     * @param username the identifier of the user to have their password reset.
     * 
     * @return {@code true} if the user password was reset, and
     *         {@code false} otherwise.
     */
    public Boolean resetPassword(String username) throws IOException 
    {
    	
//    	int index = 0;
    	String defaultPassword = "defaultPassword";
    	Boolean reset = false;
    	
    	loadUsers();
    	
		for(User user: userList)
		{
			String name = user.getUsername();
			
			if(name.equals(username))
			{
				
				user.setPassword(defaultPassword);
				reset = true;
				break;

			}
			else
			{
				
//				index++;
				
			}
		}
		
		saveUsers();
		return reset;
		
    }
    
    /**
     * 
     * Determines the type of log in that the user of the system has attempted.
     * Checks users and then admins for matching credentials and returns a value depending
     * on the outcome.
     * 
     * @param username the entered usename to be checked for by the system
     * @param password the entered password to be checked for by the system
     * 
     * 
     * @return the type of log in that the user of the has attempted
     * 
     * */
    public int logIn(String username, String password)
    {
   
    	int logInType = 0;

    	loadUsers();
    	
		for(User user: userList)
		{
			
			String name = user.getUsername();
			String pass = user.getPassword();
			
			if(name.equals(username) && pass.equals(password))
			{
				
				logInType = 1;
				break;

			}

		}
		
		for(Admin admin: adminList)
		{
			
			String name = admin.getUsername();
			String pass = admin.getPassword();
			
			if(logInType == 1)
			{
				
				break;
				
			}
			else if(name.equals(username) && pass.equals(password))
			{
				
				logInType = 2;
				break;
				
			}
		}
		
		return logInType;
		
    }
    
    /**
     * 
     * checks if there is a room in the system that matches the users criteria. 
     * If a room is found the method checkBookings if called. 
     * The value returned by checkBookings influences the next action.
     * if value is true then addBooking is called and the for loop is broken
     * if value is false the loop continues. 
     * The boolean validBooking is returned at the end 
     *
     * 
     * @param stations the entered number of work stations to be checked against
     * room records in the system
     * 
     * @return the key of a room found by the system 
     * OR a default value if no room is found
     * 
     * */
    public Boolean bookRoom(int stations, LocalDate userDate, LocalTime userTime, long userDuration) 
    {
    	
    	int key = 0;
    	Boolean validBooking = false;
    	
    	for (Map.Entry<Integer, Room> room : roomList.entrySet()) {
 
    		key = room.getKey();
    		
    		currentRoom = roomList.get(key);
    		int currentStations = currentRoom.getStations();
    		
    		if(stations <= currentStations)
    		{
    			
    			validBooking = checkBookings(key, userDate, userTime, userDuration);
    			
    			if(validBooking == true)
    			{
    				
    				addBooking(key, userDate, userTime, userDuration);
    				JOptionPane.showMessageDialog(null, "your room is : " + key, "InfoBox: error", JOptionPane.INFORMATION_MESSAGE);
        			break;
    				
    			}
 
    		}

    	}
    		
    	return validBooking;
    	
    }
    
    /**
     * 
     * checks for conflicting bookings based on data entered by the user
     * and data returned elsewhere in the system.
     * 
     * @param roomId the id of the room returned by the method checkRooms
     * @param userDate the booking date entered into the system by the user
     * @param userTime the booking time entered into the system by the user]
     * @param userDuration the booking duration entered into the system by the user
     * 
     * @return returns a Boolean value which depends on the current room being acceptable or not
     * 
     * */
    public Boolean checkBookings(int roomId, LocalDate userDate, LocalTime userTime, long userDuration) 
    {
//    	Boolean sameDate;
    	Boolean validTime;
    	Boolean validBooking = true;
    	int bookingId;
    	LocalDate bookingDate;
    	LocalTime bookingTime;
    	long bookingDuration;
    	
    	loadBookings();
    	
    	for(Booking booking: bookingList) {
    		
    		bookingId = booking.getRoom();
    		bookingDate = booking.getDate();
    		bookingTime = booking.getTime();
    		bookingDuration = booking.getDuration();
    		
    		if((roomId == bookingId) && (userDate.equals(bookingDate)))
    		{
    			
    			validTime = checkBookingTime(userTime, userDuration, bookingTime, bookingDuration);
    			
    			if(validTime == false) 
    			{
    				
    				validBooking = false;
    				
    			}
    			else if(validTime == true)
    			{
    				
    				validBooking = true;
//    				break;
    				
    			}
    		}
    	}
    	
    	return validBooking;
    	
    }
    
    /**
     * 
     * checks if the date entered by the user is equal to the current booking records date
     * 
     * @param userDate the date entered by the user
     * @param bookingDate the date from the current booking record
     * 
     * @return Boolean for the date being the same or not
     * 
     * */
    public Boolean checkBookingDate(LocalDate userDate, LocalDate bookingDate)
    {
    	
    	Boolean sameDate = false;
    	
    	if(userDate.equals(bookingDate))
    	{
    		
    		sameDate = true;
    		
    	}
    	
    	return sameDate;
    	
    }
    
    /**
     * 
     * checks if the time entered by the user overlaps with the current booking records time
     * 
     * @param userDate the date entered by the user
     * @param userDuration the duration entered by the user
     * @param bookingDate the date from the current booking record
     * @param bookingDuration the booking duration from the current booking record
     * 
     * @return Boolean for the time being valid or not
     * 
     * */
    public Boolean checkBookingTime(LocalTime userTime, long userDuration, LocalTime bookingTime, long bookingDuration)
    {
    	
    	Boolean validTime = true;
    	LocalTime bookingEndTime = bookingTime.plusHours(bookingDuration);
    	LocalTime userEndTime = userTime.plusHours(userDuration);
    	
    	if(userTime.isAfter(bookingTime) && userTime.isBefore(bookingEndTime) || (userEndTime.isAfter(bookingTime) && userEndTime.isBefore(bookingEndTime))) 
    	{
    	
    		validTime = false;
    		
    	}
    	else if(userTime.isBefore(bookingTime) && userEndTime.isAfter(bookingEndTime))
    	{
    		
    		validTime = false;
    		
    	}
    	else if(userTime.equals(bookingTime) || userEndTime.equals(bookingEndTime))
    	{
    		
    		validTime = false;
    		
    	}

    	return validTime;
    	
    }
    
    /**
     * 
     * takes a string and transforms is into a LocalDate data type
     * and returns the value
     * 
     * @param date the string value representation of a date entered by the user
     * 
     * @return returns the LocalDate data value created in this method
     * 
     * */
    public LocalDate getBookingDate(String date)
    {
        LocalDate bookingDate = null;

        try {
            bookingDate = LocalDate.parse(date, dateFormatter);
        }
        catch (Exception x) {
        	System.out.println("date exception");
            bookingDate = null;
        }

        return bookingDate;
    }
    
    /**
     * 
     * takes a string and transforms is into a LocalDate data type
     * and returns the value
     * 
     * @param time the string value representation of a time entered by the user
     * 
     * @return returns the LocalTime data value created in this method
     * 
     * */
    public LocalTime getBookingTime(String time)
    {
        LocalTime bookingTime = null;

        try {
            bookingTime = LocalTime.parse(time, timeFormatter);
        }
        catch (Exception x) {
            bookingTime = null;
        }

        return bookingTime;
    }
    

    /**
     * 
     * creates a StringBuilder to store
     * 
     * */
    public String generateReport(String username) 
    {

    	int roomId;
    	String id;
    	long bookedDuration;
      	String duration;
      	String time;
      	String date;
      	
        StringBuilder sb = new StringBuilder();
    	
    	loadBookings();
    	
    	for(Booking booking: bookingList)
    	{
    		
    		if(booking.getUser().equals(username))
    		{
    			
    		roomId = booking.getRoom();
    		id = Integer.toString(roomId);
    		bookedDuration = booking.getDuration();
    		duration = Long.toString(bookedDuration);
    		time = booking.getTime().toString();
    		date = booking.getDate().toString();

            sb.append("Room ID : ");
            sb.append(id);
            sb.append("   ||   Username : ");
            sb.append(username);
            sb.append("   ||   Date : ");
            sb.append(date);
            sb.append("   ||   Time : ");
            sb.append(time);
            sb.append("   ||   Duration : ");
            sb.append(duration + " hours");
            sb.append("\n");
            
    		}
    		
    	}
    	
    		return sb.toString();
    		
    }
    
    public Boolean validateUsername(String username)
    {
    	
    	Boolean validUser = false;
    	
    	loadBookings();
    	
    	for(Booking booking: bookingList)
		{
			if(booking.getUser().equals(username))
			{
				
				validUser = true;
				break;
				
			}	
		}
    	
    	return validUser;
    	
    }
    
    
    
    /*
     * 
     * Methods for saving and loading data
     * 
     */
    	
	private void saveUsers() throws IOException
	{
		
		try(ObjectOutputStream oos = new ObjectOutputStream( new FileOutputStream(new File("Users.bin")))){
			oos.writeObject(userList);
			oos.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@SuppressWarnings("unchecked")
	private void loadUsers() 
	{
		File file = new File("Users.bin");
		
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))){
			
			userList = (ArrayList<User>) ois.readObject();
			
		}
		catch(IOException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/*
	 * 
	 * Start of problems
	 * 
	 */
	
	private void saveBookings() 
	{
		
		try(ObjectOutputStream oos = new ObjectOutputStream( new FileOutputStream(new File("Bookings.bin")))){
			oos.writeObject(bookingList);
			oos.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@SuppressWarnings("unchecked")
	private void loadBookings() 
	{
		
		File file = new File("Bookings.bin");
		
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))){
			
			bookingList = (ArrayList<Booking>) ois.readObject();
			
		}
		catch(IOException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 
	 * End of problems
	 * 
	 * */
	
	
	
    public void saveLoggedUser(String username) {
    	
		try(ObjectOutputStream oos = new ObjectOutputStream( new FileOutputStream(new File("LoggedUser.bin")))){
			oos.writeObject(username);
			oos.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
    	
    }
    
	private String loadLoggedUser() 
	{
		File file = new File("LoggedUser.bin");
		String username = "";
		
		
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))){
			
			username = (String) ois.readObject();
			
		}
		catch(IOException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return username;
		
	}
	
	
}
