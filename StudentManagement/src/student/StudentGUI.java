package student;

import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class StudentGUI extends JFrame implements ActionListener {

    JTextField t1, t2, t3;
    JButton add, view, delete, update;

    StudentGUI() {

        setTitle("Student Management System");

        JLabel l1 = new JLabel("ID");
        JLabel l2 = new JLabel("Name");
        JLabel l3 = new JLabel("Marks");

        l1.setBounds(30, 30, 100, 30);
        l2.setBounds(30, 70, 100, 30);
        l3.setBounds(30, 110, 100, 30);

        t1 = new JTextField();
        t2 = new JTextField();
        t3 = new JTextField();

        t1.setBounds(120, 30, 150, 30);
        t2.setBounds(120, 70, 150, 30);
        t3.setBounds(120, 110, 150, 30);

        add = new JButton("Add");
        view = new JButton("View");
        update = new JButton("Update");
        delete = new JButton("Delete");

        add.setBounds(30, 170, 80, 30);
        view.setBounds(120, 170, 80, 30);
        update.setBounds(210, 170, 90, 30);
        delete.setBounds(310, 170, 90, 30);

        add.addActionListener(this);
        view.addActionListener(this);
        update.addActionListener(this);
        delete.addActionListener(this);

        add(l1); add(l2); add(l3);
        add(t1); add(t2); add(t3);
        add(add); add(view); add(update); add(delete);

        setSize(450, 300);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {

        try {
            Connection con = DatabaseConnection.getConnection();

            // --- ADD ---
            if (e.getSource() == add) {

                if (t1.getText().trim().isEmpty() || t2.getText().trim().isEmpty() || t3.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please fill all fields.");
                    return;
                }

                int id = Integer.parseInt(t1.getText().trim());
                String name = t2.getText().trim();
                int marks = Integer.parseInt(t3.getText().trim());

                String query = "INSERT INTO students VALUES(?,?,?)";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, id);
                ps.setString(2, name);
                ps.setInt(3, marks);

                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "Student Added Successfully");
                ps.close();
            }

            // --- VIEW ---
            if (e.getSource() == view) {
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM students");
                String data = "";

                while (rs.next()) {
                    data += rs.getInt("id") + " " + rs.getString("name") + " " + rs.getInt("marks") + "\n";
                }

                if (data.isEmpty())
                    data = "No students found.";

                JOptionPane.showMessageDialog(this, data);
                rs.close();
                st.close();
            }

            // --- DELETE ---
            if (e.getSource() == delete) {

                if (t1.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please enter ID to delete.");
                    return;
                }

                int id = Integer.parseInt(t1.getText().trim());
                String query = "DELETE FROM students WHERE id=?";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, id);

                int rows = ps.executeUpdate();
                if (rows > 0)
                    JOptionPane.showMessageDialog(this, "Student Deleted Successfully");
                else
                    JOptionPane.showMessageDialog(this, "Student Not Found");

                ps.close();
            }

            // --- UPDATE ---
            if (e.getSource() == update) {

                if (t1.getText().trim().isEmpty() || t2.getText().trim().isEmpty() || t3.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please fill all fields to update.");
                    return;
                }

                int id = Integer.parseInt(t1.getText().trim());
                String name = t2.getText().trim();
                int marks = Integer.parseInt(t3.getText().trim());

                String query = "UPDATE students SET name=?, marks=? WHERE id=?";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, name);
                ps.setInt(2, marks);
                ps.setInt(3, id);

                int rows = ps.executeUpdate();
                if (rows > 0)
                    JOptionPane.showMessageDialog(this, "Student Updated Successfully");
                else
                    JOptionPane.showMessageDialog(this, "Student Not Found");

                ps.close();
            }

            con.close();

        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "ID and Marks must be numeric.");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new StudentGUI();
    }
}