package student;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;

public class LoginPage extends JFrame implements ActionListener {
	JLabel l1, l2;
	JTextField username;
	JPasswordField password;
	JButton loginBtn, resetBtn;
	
	
	LoginPage(){
		setTitle("Login - Student Management System");
		
		JLabel l1 = new JLabel("Username");
		JLabel l2 = new JLabel("Password");
		
		username = new JTextField();
		password = new JPasswordField();
		
		loginBtn = new JButton("Login");
		resetBtn = new JButton("Reset");
		
		
		l1.setBounds(40,40,100,30);
		l2.setBounds(40,90,100,30);
		
		username.setBounds(140, 40, 150, 30);
		password.setBounds(140, 90, 150, 30);
		
		loginBtn.setBounds(60,150,100,30);
		resetBtn.setBounds(180,150,100,30);
		
		add(l1);
		add(l2);
		add(username);
		add(password);
		add(loginBtn);
		add(resetBtn);
		
		loginBtn.addActionListener(this);
		resetBtn.addActionListener(this);
		
		setSize(350,250);
		setLayout(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == loginBtn) {
		try {
			
			Connection con = DatabaseConnection.getConnection();
			String query = "SELECT * FROM login WHERE username=? AND password=?";
			
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setString(1, username.getText());
			ps.setString(2, new String(password.getPassword()));
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				JOptionPane.showMessageDialog(this, "Login Successful");
				
				new StudentGUI();
				dispose();
				}
			else {
				JOptionPane.showMessageDialog(this, "Invalid Username or Password");
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	if(e.getSource() == resetBtn) {
		username.setText("");
		password.setText("");
	}
	}
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(() -> {
		new LoginPage();
	});

	}
}
