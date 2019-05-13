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
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class GenerateReport {

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
					GenerateReport window = new GenerateReport();
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
	public GenerateReport() {
		
		initialize();
		
		Manager = new Manager();
		
	}
	
	public String reportGeneration() 
	{
		
		String username = txtName.getText();
		String report = "";
		Boolean validUser = Manager.validateUsername(username);
		
		if(validUser == true)
		{
			
			report = Manager.generateReport(username);
			return report;
			
		}
		else
		{
		
			return report;
			
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
		
		JLabel lblReportSystem = new JLabel("REPORT SYSTEM");
		lblReportSystem.setHorizontalAlignment(SwingConstants.CENTER);
		lblReportSystem.setFont(new Font("OCR A Extended", Font.PLAIN, 26));
		lblReportSystem.setBounds(0, 0, 269, 60);
		frame.getContentPane().add(lblReportSystem);
		
		txtName = new JTextField();
		txtName.setFont(new Font("OCR A Extended", Font.PLAIN, 18));
		txtName.setColumns(10);
		txtName.setBounds(205, 62, 300, 30);
		frame.getContentPane().add(txtName);
		
		JLabel lblName = new JLabel("Username : ");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setFont(new Font("OCR A Extended", Font.PLAIN, 20));
		lblName.setBounds(10, 59, 160, 30);
		frame.getContentPane().add(lblName);
		
		JTextArea txtReport = new JTextArea();
		txtReport.setRows(20);
		txtReport.setBackground(Color.WHITE);
		txtReport.setForeground(Color.BLACK);
		txtReport.setEditable(false);
		txtReport.setBounds(10, 113, 774, 307);
		frame.getContentPane().add(txtReport);
		
		JButton btnGenerate = new JButton("Generate");
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				String report = reportGeneration();
				
				if(!report.equals(""))
				{
					
					txtReport.setText("" + report);	
					
				}
				else
				{
					
					JOptionPane.showMessageDialog(null, "No booking for this username exists - Please re-enter", "InfoBox: error", JOptionPane.INFORMATION_MESSAGE);
					
				}
				
			}
		});
		btnGenerate.setFont(new Font("OCR A Extended", Font.PLAIN, 18));
		btnGenerate.setBounds(562, 57, 200, 45);
		frame.getContentPane().add(btnGenerate);
		
		JButton btnAdminMenu = new JButton("Admin Menu");
		btnAdminMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String[] args = null;
				AdminMenu.main(args );
			
				frame.dispose();
				
			}
		});
		btnAdminMenu.setFont(new Font("OCR A Extended", Font.PLAIN, 18));
		btnAdminMenu.setBounds(562, 13, 200, 35);
		frame.getContentPane().add(btnAdminMenu);
		

	}
}
