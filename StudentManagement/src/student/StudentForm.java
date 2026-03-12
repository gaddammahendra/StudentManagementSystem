package student;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class StudentForm extends JFrame implements ActionListener {

	 JTextField id,name,marks;
	    JButton save;

	    StudentForm(){

	        setTitle("Add Student");

	        JLabel l1 = new JLabel("ID");
	        JLabel l2 = new JLabel("Name");
	        JLabel l3 = new JLabel("Marks");

	        id = new JTextField();
	        name = new JTextField();
	        marks = new JTextField();

	        save = new JButton("Save");

	        l1.setBounds(50,40,100,30);
	        l2.setBounds(50,80,100,30);
	        l3.setBounds(50,120,100,30);

	        id.setBounds(150,40,150,30);
	        name.setBounds(150,80,150,30);
	        marks.setBounds(150,120,150,30);

	        save.setBounds(120,180,100,30);

	        add(l1); add(l2); add(l3);
	        add(id); add(name); add(marks);
	        add(save);

	        save.addActionListener(this);

	        setSize(350,270);
	        setLayout(null);
	        setVisible(true);
	    }

	    public void actionPerformed(ActionEvent e){

	        try{

	            Connection con = DatabaseConnection.getConnection();

	            String q = "INSERT INTO students VALUES(?,?,?)";

	            PreparedStatement ps = con.prepareStatement(q);

	            ps.setInt(1,Integer.parseInt(id.getText()));
	            ps.setString(2,name.getText());
	            ps.setInt(3,Integer.parseInt(marks.getText()));

	            ps.executeUpdate();

	            JOptionPane.showMessageDialog(this,"Student Added");

	        }
	        catch(Exception ex){
	            ex.printStackTrace();
	        }
	    }
	
	
	
}
