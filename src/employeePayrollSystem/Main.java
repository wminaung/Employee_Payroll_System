package employeePayrollSystem;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setForeground(Color.RED);
		setBackground(Color.DARK_GRAY);
		setTitle("Welcome");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 496, 325);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.controlHighlight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 94, 22);
		contentPane.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("Main");
		menuBar.add(mnNewMenu);
		
		JMenuItem menuCalculateSalary = new JMenuItem("Calculate Salary");
		menuCalculateSalary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goSalaryPage();
			}
		});
		mnNewMenu.add(menuCalculateSalary);
		
		JMenuItem mntmQuit = new JMenuItem("Quit");
		mntmQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quite();
			}
		});
		mnNewMenu.add(mntmQuit);
		
		JMenu mnNewMenu_1 = new JMenu("Show");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem empList = new JMenuItem("Employee List");
		empList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goEmpListPage();
			}
		});
		mnNewMenu_1.add(empList);
		
		
		// main()
	}
	
	static void goSalaryPage() {
		Salary salary = new Salary();
		salary.setVisible(true);
	}
	
	static void quite() {
		System.exit(1);
	}
	
	static void goEmpListPage() {
		EmpList empList = new EmpList();
		empList.setVisible(true);
	}
	
	// end Class
}
