package ui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.swing.SwingConstants;
import manager.Manager;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class BookRoom {

    private Manager Manager;
	
	private JFrame frame;
	private JTextField txtStations;
	private JTextField txtDate;
	private JTextField txtTime;
	private JTextField txtDuration;
	private JTextField txtStationsError;
	private JTextField txtDateError;
	private JTextField txtTimeError;
	private JTextField txtDurationError;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookRoom window = new BookRoom();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BookRoom() {
		initialize();
		
		Manager = new Manager();
	}
	
	public void roomBooking() {
		

		int stations = getWorkstations();
		LocalDate date = getBookingDate();
		LocalTime time = getBookingTime();
		long duration = getDuration();
		Boolean validBooking;
		
//		Manager.addBooking(101, date, time, duration);
		
		
		if(stations != 0 ||date != null || time != null || duration != 0)
		{
			
			validBooking = Manager.bookRoom(stations, date, time, duration);
			
			if(validBooking == true)
			{

				JOptionPane.showMessageDialog(null, "Room successfully booked - book another room or exit the system", "InfoBox: error", JOptionPane.INFORMATION_MESSAGE);
					
			}
			else if(validBooking == false)
			{
					
				JOptionPane.showMessageDialog(null, "The system failed to find a suitable room for your criteria - please re-enter details", "InfoBox: error", JOptionPane.INFORMATION_MESSAGE);
					
			}
			
		}
		else if(stations == 0 ||date == null || time == null || duration == 0) 
		{
			
			JOptionPane.showMessageDialog(null, "The box(s) with X have invalid details  -  Please re-enter these details", "InfoBox: error", JOptionPane.INFORMATION_MESSAGE);
			
		}
		


	
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
//		String formattedString = time.format(formatter);
//		txtStations.setText(formattedString);
		
	}
	
	
	public int getWorkstations() {
		
		int stations = 0;
		
		if(txtStations.getText().equals("")){
			
			txtStations.setText("0");
			
		}
		
        try{

			stations = Integer.parseInt(txtStations.getText());
        	
        }catch(NumberFormatException nfe){
        	
			txtStations.setText("0");
        	
        }
        
		if(stations < 1)
		{
			
			stations = 0;
			txtStationsError.setText(" X ");
			
		}
		else
		{
			txtStationsError.setText("");
			
		}
		
		return stations;
		
	}
	
	
	public LocalDate getBookingDate() {
		
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//		String formattedString = bookingDate.format(formatter);
//		txtTime.setText(formattedString);
		
		String date = txtDate.getText(); 
		LocalDate bookingDate;

		bookingDate = Manager.getBookingDate(date);
		
		if(bookingDate == null)
		{
			
			txtDateError.setText(" X ");
			
		}
		else 
		{
			
			txtDateError.setText("");
			
		}
		
		return bookingDate;
		
	}
	
	
	public LocalTime getBookingTime() {
		
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
//		String formattedString = bookingTime.format(formatter);
//		txtStations.setText(formattedString);
		
		String time = txtTime.getText(); 
		LocalTime bookingTime;


		bookingTime = Manager.getBookingTime(time);
		
		if(bookingTime == null)
		{
			
			txtTimeError.setText(" X ");
			
		}
		else
		{
			
			txtTimeError.setText("");
			
		}
		
		return bookingTime;
		
		
	}
	
	public long getDuration() {
		
		long bookingDuration = 0;
		
		if(txtDuration.getText().equals("")){
			
			txtDuration.setText("0");
			
		}
		
        try{

    		bookingDuration = Integer.parseInt(txtDuration.getText());
        	
        }catch(NumberFormatException nfe){
        	
			txtDuration.setText("0");
        	
        }

		if(bookingDuration < 1 || bookingDuration > 6)
		{
			
			txtDurationError.setText(" X ");
			
		}
		else
		{
			
			txtDurationError.setText("");
			
		}
		
		return bookingDuration;
	}

	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("Room Booking System - Log In\r\n");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("OCR A Extended", Font.PLAIN, 26));
		label.setBounds(150, 24, 500, 60);
		frame.getContentPane().add(label);
		
		txtStations = new JTextField();
		txtStations.setFont(new Font("OCR A Extended", Font.PLAIN, 18));
		txtStations.setColumns(10);
		txtStations.setBounds(319, 133, 300, 30);
		frame.getContentPane().add(txtStations);
		
		JLabel lblWorkstations = new JLabel("workstations :");
		lblWorkstations.setHorizontalAlignment(SwingConstants.CENTER);
		lblWorkstations.setFont(new Font("OCR A Extended", Font.PLAIN, 18));
		lblWorkstations.setBounds(115, 130, 170, 30);
		frame.getContentPane().add(lblWorkstations);
		
		JLabel lblPleaseEnterThe = new JLabel("Please enter the desired number of workstations (MAX 15)");
		lblPleaseEnterThe.setHorizontalAlignment(SwingConstants.CENTER);
		lblPleaseEnterThe.setFont(new Font("OCR A Extended", Font.PLAIN, 18));
		lblPleaseEnterThe.setBounds(100, 89, 625, 30);
		frame.getContentPane().add(lblPleaseEnterThe);
		
		txtDate = new JTextField();
		txtDate.setFont(new Font("OCR A Extended", Font.PLAIN, 18));
		txtDate.setColumns(10);
		txtDate.setBounds(319, 218, 300, 30);
		frame.getContentPane().add(txtDate);
		
		JLabel lblBookingDate = new JLabel("Booking date :");
		lblBookingDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblBookingDate.setFont(new Font("OCR A Extended", Font.PLAIN, 18));
		lblBookingDate.setBounds(115, 215, 170, 30);
		frame.getContentPane().add(lblBookingDate);
		
		JLabel lblPleaseEnterThe_1 = new JLabel("Please enter the desired booking date (dd/mm/yyyy)");
		lblPleaseEnterThe_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblPleaseEnterThe_1.setFont(new Font("OCR A Extended", Font.PLAIN, 18));
		lblPleaseEnterThe_1.setBounds(100, 174, 575, 30);
		frame.getContentPane().add(lblPleaseEnterThe_1);
		
		txtTime = new JTextField();
		txtTime.setFont(new Font("OCR A Extended", Font.PLAIN, 18));
		txtTime.setColumns(10);
		txtTime.setBounds(319, 307, 300, 30);
		frame.getContentPane().add(txtTime);
		
		JLabel lblBookingTime = new JLabel("Booking time :");
		lblBookingTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblBookingTime.setFont(new Font("OCR A Extended", Font.PLAIN, 18));
		lblBookingTime.setBounds(115, 304, 170, 30);
		frame.getContentPane().add(lblBookingTime);
		
		JLabel lblPleaseEnterThe_2 = new JLabel("Please enter the desired booking time (hh:mm)");
		lblPleaseEnterThe_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblPleaseEnterThe_2.setFont(new Font("OCR A Extended", Font.PLAIN, 18));
		lblPleaseEnterThe_2.setBounds(100, 263, 575, 30);
		frame.getContentPane().add(lblPleaseEnterThe_2);
		
		JButton btnBookRoom = new JButton("BOOK ROOM");
		btnBookRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				roomBooking();
				
			}
		});
		btnBookRoom.setFont(new Font("OCR A Extended", Font.PLAIN, 20));
		btnBookRoom.setBounds(300, 460, 200, 40);
		frame.getContentPane().add(btnBookRoom);
		
		txtDuration = new JTextField();
		txtDuration.setFont(new Font("OCR A Extended", Font.PLAIN, 18));
		txtDuration.setColumns(10);
		txtDuration.setBounds(319, 406, 300, 30);
		frame.getContentPane().add(txtDuration);
		
		JLabel lblDuration = new JLabel("Please enter the desired booking duration (MAX 6 hours)");
		lblDuration.setHorizontalAlignment(SwingConstants.CENTER);
		lblDuration.setFont(new Font("OCR A Extended", Font.PLAIN, 18));
		lblDuration.setBounds(75, 362, 650, 30);
		frame.getContentPane().add(lblDuration);
		
		JLabel lblBookingDuration = new JLabel("Booking duration :");
		lblBookingDuration.setHorizontalAlignment(SwingConstants.CENTER);
		lblBookingDuration.setFont(new Font("OCR A Extended", Font.PLAIN, 18));
		lblBookingDuration.setBounds(70, 403, 213, 30);
		frame.getContentPane().add(lblBookingDuration);
		
		txtStationsError = new JTextField();
		txtStationsError.setEditable(false);
		txtStationsError.setHorizontalAlignment(SwingConstants.CENTER);
		txtStationsError.setFont(new Font("OCR A Extended", Font.PLAIN, 18));
		txtStationsError.setColumns(10);
		txtStationsError.setBounds(629, 133, 40, 30);
		frame.getContentPane().add(txtStationsError);
		
		txtDateError = new JTextField();
		txtDateError.setHorizontalAlignment(SwingConstants.CENTER);
		txtDateError.setFont(new Font("OCR A Extended", Font.PLAIN, 18));
		txtDateError.setEditable(false);
		txtDateError.setColumns(10);
		txtDateError.setBounds(629, 218, 40, 30);
		frame.getContentPane().add(txtDateError);
		
		txtTimeError = new JTextField();
		txtTimeError.setHorizontalAlignment(SwingConstants.CENTER);
		txtTimeError.setFont(new Font("OCR A Extended", Font.PLAIN, 18));
		txtTimeError.setEditable(false);
		txtTimeError.setColumns(10);
		txtTimeError.setBounds(629, 307, 40, 30);
		frame.getContentPane().add(txtTimeError);
		
		txtDurationError = new JTextField();
		txtDurationError.setHorizontalAlignment(SwingConstants.CENTER);
		txtDurationError.setFont(new Font("OCR A Extended", Font.PLAIN, 18));
		txtDurationError.setEditable(false);
		txtDurationError.setColumns(10);
		txtDurationError.setBounds(629, 406, 40, 30);
		frame.getContentPane().add(txtDurationError);
		
		JButton btnLogOut = new JButton("LOG OUT");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String[] args = null;
				MainMenu.main(args );
			
				frame.dispose();
				
			}
		});
		btnLogOut.setFont(new Font("OCR A Extended", Font.PLAIN, 20));
		btnLogOut.setBounds(300, 511, 200, 40);
		frame.getContentPane().add(btnLogOut);
	}
}
