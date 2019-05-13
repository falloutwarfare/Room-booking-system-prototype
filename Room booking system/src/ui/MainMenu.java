package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import manager.Manager;


public class MainMenu {
	
    private Manager Manager;

	private JFrame frame;
	private JTextField txtName;
	private JTextField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu window = new MainMenu();
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
	public MainMenu() {
		initialize();
		
		Manager = new Manager();
	}
	
	public void logIn() {
		
		String username = txtName.getText();
		String password = txtPassword.getText();
		
		int logInType;
		
		logInType = Manager.logIn(username, password);
		
		if(logInType == 1)
		{
			
			Manager.saveLoggedUser(username);
			String[] args = null;
			BookRoom.main(args );
		
			frame.dispose();
			
		}
		else if(logInType == 2)
		{
			
			String[] args = null;
			AdminMenu.main(args );
		
			frame.dispose();
			
		}
		else
		{
			
			JOptionPane.showMessageDialog(null, "Log in details incorrect - Please re-enter", "InfoBox: error", JOptionPane.INFORMATION_MESSAGE);
			
		}
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 800, 460);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblTitle = new JLabel("Room Booking System - Log In\r\n");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("OCR A Extended", Font.PLAIN, 26));
		lblTitle.setBounds(150, 24, 500, 60);
		frame.getContentPane().add(lblTitle);
		
		JButton btnLogIn = new JButton("LOG IN");
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
						
				logIn();
				
			}
		});
		btnLogIn.setFont(new Font("OCR A Extended", Font.PLAIN, 20));
		btnLogIn.setBounds(300, 300, 200, 40);
		frame.getContentPane().add(btnLogIn);
		
		JLabel lblName = new JLabel("Username : ");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setFont(new Font("OCR A Extended", Font.PLAIN, 20));
		lblName.setBounds(96, 129, 170, 30);
		frame.getContentPane().add(lblName);
		
		JLabel lblPassword = new JLabel("Password : ");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(new Font("OCR A Extended", Font.PLAIN, 20));
		lblPassword.setBounds(96, 217, 170, 30);
		frame.getContentPane().add(lblPassword);
		
		txtName = new JTextField();
		txtName.setFont(new Font("OCR A Extended", Font.PLAIN, 18));
		txtName.setBounds(300, 132, 300, 30);
		frame.getContentPane().add(txtName);
		txtName.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setFont(new Font("OCR A Extended", Font.PLAIN, 18));
		txtPassword.setColumns(10);
		txtPassword.setBounds(300, 217, 300, 30);
		frame.getContentPane().add(txtPassword);
	}
}
