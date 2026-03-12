package student;

import javax.swing.*;
import java.awt.event.*;



public class Dashboard extends JFrame implements ActionListener {

	  JButton addStudent, viewStudents, logout;

	    Dashboard(){

	        setTitle("Dashboard");

	        addStudent = new JButton("Add Student");
	        viewStudents = new JButton("View Students");
	        logout = new JButton("Logout");

	        addStudent.setBounds(100,50,150,40);
	        viewStudents.setBounds(100,110,150,40);
	        logout.setBounds(100,170,150,40);

	        add(addStudent);
	        add(viewStudents);
	        add(logout);

	        addStudent.addActionListener(this);
	        viewStudents.addActionListener(this);
	        logout.addActionListener(this);

	        setSize(350,300);
	        setLayout(null);
	        setVisible(true);
	    }

	    public void actionPerformed(ActionEvent e){

	        if(e.getSource()==addStudent){
	            new StudentForm();
	        }

	        if(e.getSource()==viewStudents){
	            new StudentTable();
	        }

	        if(e.getSource()==logout){
	            new LoginPage();
	            dispose();
	        }
	    }
	}
	

