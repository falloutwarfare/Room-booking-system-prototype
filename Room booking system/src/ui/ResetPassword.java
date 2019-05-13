package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.io.IOException;

import javax.swing.SwingConstants;

import manager.Manager;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ResetPassword {

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
					ResetPassword window = new ResetPassword();
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
	public ResetPassword() {
		
		initialize();
		
		Manager = new Manager();
		
	}
	
	public Boolean passwordReset() throws IOException {
		
		Boolean deleted = false;
		String userName = txtName.getText();
		
		deleted = Manager.resetPassword(userName);
		
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
		
		JLabel lblPasswordReset = new JLabel("Password Reset\r\n");
		lblPasswordReset.setHorizontalAlignment(SwingConstants.CENTER);
		lblPasswordReset.setFont(new Font("OCR A Extended", Font.PLAIN, 26));
		lblPasswordReset.setBounds(150, 24, 500, 60);
		frame.getContentPane().add(lblPasswordReset);
		
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
		
		JButton button = new JButton("RESET PASSWORD");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Boolean reset = false;
				
				try {
					reset = passwordReset();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				if(reset == true)
				{
					
					String[] args = null;
					AdminMenu.main(args );
				
					frame.dispose();
					
					
				}
				else if(reset == false)
				{
					
					JOptionPane.showMessageDialog(null, "The entered username Doesn't exist  -  Please enter another name", "InfoBox: error", JOptionPane.INFORMATION_MESSAGE);
					
				}
				
				
			}
		});
		button.setFont(new Font("OCR A Extended", Font.PLAIN, 18));
		button.setBounds(300, 250, 200, 40);
		frame.getContentPane().add(button);
		
		JButton btnMenu = new JButton("RETURN TO MENU");
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String[] args = null;
				AdminMenu.main(args );
			
				frame.dispose();
				
			}
		});
		btnMenu.setFont(new Font("OCR A Extended", Font.PLAIN, 18));
		btnMenu.setBounds(300, 301, 200, 40);
		frame.getContentPane().add(btnMenu);
	}
}
