package student;

import java.sql.*;

public class StudentDetails {

	public void addStudent(Student s) {
		try {
			Connection con = DatabaseConnection.getConnection();
			String query = "Insert INTO Students Values(?,?,?)";
			
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, s.getId());
			ps.setString(2, s.getName());
			ps.setInt(3, s.getMarks());
			
			ps.executeUpdate();
			
			System.out.println("Student Added");
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void viewStudents() {
		try {
			Connection con = DatabaseConnection.getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * From students");
			
			System.out.println("\nId\tName\tMarks");
			System.out.println("-----------------------");
			
			
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int marks = rs.getInt("marks");
				
				System.out.println(id + "\t" + name + "\t" + marks);
			}
		} 
		catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void deleteStudent(int id) {
		try {
			Connection con = DatabaseConnection.getConnection();
			String query = "DELETE FROM students WHERE id=?";
			
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, id);
			
			int rows = ps.executeUpdate();
			if (rows > 0) {
				System.out.println("Student Deleted Successfully..!!");
			}
			else {
				System.out.println("No Student Found With ID: " + id);
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
}
