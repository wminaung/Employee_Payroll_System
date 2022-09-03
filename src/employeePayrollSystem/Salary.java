package employeePayrollSystem;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Salary extends JFrame {

	private JPanel contentPane;
	private JTextField nameTxt;
	private JTextField dayTxt;
	private JLabel lblNewLabel_1;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public Salary() {
		setTitle("Salary");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 470, 328);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(36, 56, 104, 25);
		contentPane.add(lblNewLabel);
		
		JLabel lblNoOfLeave = new JLabel("No of Leave day: ");
		lblNoOfLeave.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNoOfLeave.setBounds(36, 102, 104, 25);
		contentPane.add(lblNoOfLeave);
		
		nameTxt = new JTextField();
		nameTxt.setBounds(169, 59, 191, 20);
		contentPane.add(nameTxt);
		nameTxt.setColumns(10);
		
		dayTxt = new JTextField();
		dayTxt.setColumns(10);
		dayTxt.setBounds(169, 105, 191, 20);
		contentPane.add(dayTxt);
		
		lblNewLabel_1 = new JLabel("Types of Employee: ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(60, 171, 137, 20);
		contentPane.add(lblNewLabel_1);
		
		Vector<String> comp = new Vector<>();
		comp.add("Teacher");
		comp.add("salesperson");
		comp.add("Operator");
		JComboBox <String>comboBox = new JComboBox<>(comp);
		comboBox.setBounds(218, 171, 123, 22);
		contentPane.add(comboBox);
		
		JButton btnNewButton = new JButton("Calculate");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name ;
				int day ;
				String type ;
				name = nameTxt.getText();
				if(dayTxt.getText().isEmpty()) {
					day = 0;
				}else {
					day = Integer.parseInt( dayTxt.getText());
				}
				
				
				type = (String)comboBox.getSelectedItem();
				
				calculateSalary(name, day, type);
			}
		});
		btnNewButton.setBounds(157, 231, 111, 25);
		contentPane.add(btnNewButton);

		// end construct
	}
	static void calculateSalary(String name, int day, String type) {
		double salary = 0;
		salary = cal(salary, day, type);
		try {
			Connection con = DbConnection.initializeDatabase();
			String sql = "insert into employee(name, salary, today_date, emptype)"
					+ " values(?, ?, now(), ?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, name);
			stmt.setDouble(2, salary);
			stmt.setString(3, type);
			stmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "Successfully Inserted");
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	static double cal(double salary,int day, String type) {
		if(type == "Teacher") {
			salary = 600000; // 30 : 1
			if(day > 0) {
				double dd = 20000;
				dd = dd * day;
				salary -= dd;
			}
		}else if(type == "salesperson") {
			salary = 300000;
			if(day > 0) {
				double dd = 10000;
				dd = dd * day;
				salary -= dd;
			}
		}else if(type == "Operator") {
			salary = 200000; // 200 : 7
			if(day > 0) {
				double dd = 7000;
				dd = 7000 * day;
				salary -= dd;
				
			}	
			
		}else {
			System.out.println("Someting wroung");
		}
		
		return salary;
	}
	
	// end class
}
