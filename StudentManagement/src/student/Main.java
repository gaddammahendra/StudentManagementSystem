package student;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		StudentDetails sd = new StudentDetails();
		
		while (true) {
			System.out.println("\n1 Add Student");
			System.out.println("2 View Students");
			System.out.println("3 Delete Student");
			System.out.println("4 Exit");
			
			System.out.println("Enter Choice: ");
			
			int choice = -1;
			
			if (sc.hasNext()) {
				choice = sc.nextInt();
			}
			else {
				System.out.println("Invalid input! Enter a number between 1-4..");
				sc.next();
				continue;
			}
			sc.nextLine();
			
			switch (choice) {
			case 1:
				try {
				System.out.println("Enter ID: ");
				int id = sc.nextInt();
				sc.nextLine();
				
				System.out.println("Enter Name: ");
				String name = sc.nextLine();
				
				System.out.println("Enter Marks: ");
				int marks = sc.nextInt();
				
				Student s = new Student(id, name, marks);
				sd.addStudent(s);
				}
				catch (Exception e) {
					System.out.println("Invalid Input! Try again,,!");
					sc.nextLine();
				}
				break;
				
			case 2:
				sd.viewStudents();
				break;
				
			case 3:
				System.out.println("Enter ID to delete: ");
				if (sc.hasNext()) {
					int delId = sc.nextInt();
					sc.nextLine();
					sd.deleteStudent(delId);
				}
				else {
					System.out.println("Invalid ID..!!");
					sc.next();
				}
			
				break;
				
			case 4:
				System.out.println("Existing program..");
				System.exit(0);
				
				default:
					System.out.println("Invalid Choice! ENter 1-4..");
			}
		}
 	}
}
