package ui;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;

import manager.Manager;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class AddUser {

    private Manager Manager;
	
	private JFrame frame;
	private JTextField txtName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddUser window = new AddUser();
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
	public AddUser() {
		initialize();
		
		Manager = new Manager();
		
	}
	
	public boolean addNewUser() throws IOException {
		
		boolean nameAdded = false;
		
		String username = txtName.getText();
		String password = "default";
		
		nameAdded = Manager.addNewUser(username, password);
		
		return nameAdded;
		
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
		
		JLabel lblTitle = new JLabel("Add New User");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("OCR A Extended", Font.PLAIN, 26));
		lblTitle.setBounds(150, 24, 500, 60);
		frame.getContentPane().add(lblTitle);
		
		JLabel lblName = new JLabel("Username : ");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setFont(new Font("OCR A Extended", Font.PLAIN, 20));
		lblName.setBounds(115, 151, 150, 30);
		frame.getContentPane().add(lblName);
		
		txtName = new JTextField();
		txtName.setFont(new Font("OCR A Extended", Font.PLAIN, 18));
		txtName.setColumns(10);
		txtName.setBounds(300, 154, 300, 30);
		frame.getContentPane().add(txtName);
		
		JButton btnLogIn = new JButton("ADD USER");
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				boolean nameAdded = false;
				
				try {
					nameAdded = addNewUser();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if(nameAdded == true) {
					String[] args = null;
					AdminMenu.main(args );
				
					frame.dispose();
				}
				else if(nameAdded == false)
				{
					
					JOptionPane.showMessageDialog(null, "The entered username already exists  -  Please enter another name", "InfoBox: error", JOptionPane.INFORMATION_MESSAGE);
					
				}
			}
		});
		btnLogIn.setFont(new Font("OCR A Extended", Font.PLAIN, 18));
		btnLogIn.setBounds(300, 250, 200, 40);
		frame.getContentPane().add(btnLogIn);
		
		JButton button = new JButton("RETURN TO MENU");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String[] args = null;
				AdminMenu.main(args );
			
				frame.dispose();
				
			}
		});
		button.setFont(new Font("OCR A Extended", Font.PLAIN, 18));
		button.setBounds(300, 301, 200, 40);
		frame.getContentPane().add(button);
	}

}
