package ui;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import manager.Manager;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class DeleteUser {
	
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
					DeleteUser window = new DeleteUser();
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
	public DeleteUser() {
		initialize();
		
		Manager = new Manager();
		
	}
	
	public Boolean userDeletion() throws IOException {
		
		Boolean deleted = false;
		String userName = txtName.getText();
		
		deleted = Manager.deleteUser(userName);
		
		return deleted;
		
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
		
		JLabel lblDeleteUser = new JLabel("DELETE USER\r\n");
		lblDeleteUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeleteUser.setFont(new Font("OCR A Extended", Font.PLAIN, 26));
		lblDeleteUser.setBounds(150, 24, 500, 60);
		frame.getContentPane().add(lblDeleteUser);
		
		JLabel lblName = new JLabel("Username : ");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setFont(new Font("OCR A Extended", Font.PLAIN, 20));
		lblName.setBounds(105, 151, 160, 30);
		frame.getContentPane().add(lblName);
		
		txtName = new JTextField();
		txtName.setFont(new Font("OCR A Extended", Font.PLAIN, 18));
		txtName.setColumns(10);
		txtName.setBounds(300, 154, 300, 30);
		frame.getContentPane().add(txtName);
		
		JButton button = new JButton("DELETE USER");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Boolean deleted = false;
				
				try {
					deleted = userDeletion();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				if(deleted == true)
				{
					
					String[] args = null;
					AdminMenu.main(args );
				
					frame.dispose();
					
					
				}
				else if(deleted == false)
				{
					
					//error message goes here
					
				}
				
				
			}
		});
		button.setFont(new Font("OCR A Extended", Font.PLAIN, 18));
		button.setBounds(300, 250, 200, 40);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("RETURN TO MENU");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String[] args = null;
				AdminMenu.main(args );
			
				frame.dispose();
				
			}
		});
		button_1.setFont(new Font("OCR A Extended", Font.PLAIN, 18));
		button_1.setBounds(300, 301, 200, 40);
		frame.getContentPane().add(button_1);
	}

}
