package student;

import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;

public class StudentTable extends JFrame {

	JTable table;
    DefaultTableModel model;

    StudentTable(){

        setTitle("Student List");

        model = new DefaultTableModel();

        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Marks");

        table = new JTable(model);

        add(new JScrollPane(table));

        loadData();

        setSize(400,300);
        setVisible(true);
    }

    void loadData(){

        try{

            Connection con = DatabaseConnection.getConnection();

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM students");

            while(rs.next()){

                model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("marks")
                });
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

	
	
}
