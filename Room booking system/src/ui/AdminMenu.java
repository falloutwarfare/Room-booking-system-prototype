package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class AdminMenu {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminMenu window = new AdminMenu();
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
	public AdminMenu() {
		initialize();
		
		
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
		
		JLabel lblTitle = new JLabel("Room Booking System - Admin\r\n");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("OCR A Extended", Font.PLAIN, 26));
		lblTitle.setBounds(150, 24, 500, 60);
		frame.getContentPane().add(lblTitle);
		
		JButton btnAdd = new JButton("Add new user");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String[] args = null;
				AddUser.main(args );
				
				frame.dispose();

			}
		});
		btnAdd.setBounds(60, 250, 130, 40);
		frame.getContentPane().add(btnAdd);
		
		JButton btnReset = new JButton("Reset password");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String[] args = null;
				ResetPassword.main(args );
				
				frame.dispose();
				
			}
		});
		btnReset.setBounds(230, 250, 130, 40);
		frame.getContentPane().add(btnReset);
		
		JButton btnDelete = new JButton("Delete user");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String[] args = null;
				DeleteUser.main(args );
				
				frame.dispose();
				
			}
		});
		btnDelete.setBounds(410, 250, 130, 40);
		frame.getContentPane().add(btnDelete);
		
		JButton btnReport = new JButton("generate report");
		btnReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String[] args = null;
				GenerateReport.main(args );
				
				frame.dispose();
				
			}
		});
		btnReport.setBounds(590, 250, 130, 40);
		frame.getContentPane().add(btnReport);
		
		JButton btnLogOut = new JButton("LOG OUT");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String[] args = null;
				MainMenu.main(args );
				
				frame.dispose();
				
			}
		});
		btnLogOut.setBounds(324, 341, 130, 40);
		frame.getContentPane().add(btnLogOut);
	}
}
