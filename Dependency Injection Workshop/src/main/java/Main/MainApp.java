package Main;

import Management.StudentManagement;
import Models.Student;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import Config.ComponentScanConfig;
import Services.UserInputService;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        // Initialize Spring context
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ComponentScanConfig.class);

        // Get StudentManagement bean from the context
        StudentManagement management = context.getBean(StudentManagement.class);
        UserInputService userInputService = context.getBean(UserInputService.class);

        boolean exit = false;

        while (!exit) {
            System.out.println("\nStudent Management System Menu:");
            System.out.println("1. Create Student");
            System.out.println("2. Find Student");
            System.out.println("3. Edit Student");
            System.out.println("4. Delete Student");
            System.out.println("5. List All Students");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = userInputService.getInt();  // Get user's choice

            switch (choice) {
                case 1:
                    // Create a new student
                    Student newStudent = management.create();
                    management.save(newStudent);
                    System.out.println("Student saved successfully.");
                    break;

                case 2:
                    // Find a student by ID
                    System.out.print("Enter student ID to find: ");
                    int idToFind = userInputService.getInt();
                    Student foundStudent = management.find(idToFind);
                    if (foundStudent != null) {
                        System.out.println("Student Found: ID: " + foundStudent.getId() + ", Name: " + foundStudent.getName());
                    } else {
                        System.out.println("Student with ID " + idToFind + " not found.");
                    }
                    break;

                case 3:
                    // Edit a student
                    System.out.print("Enter student ID to edit: ");
                    int idToEdit = userInputService.getInt();
                    Student studentToEdit = management.find(idToEdit);
                    if (studentToEdit != null) {
                        System.out.println("Editing Student ID: " + studentToEdit.getId());
                        System.out.print("Enter new name: ");
                        String newName = userInputService.getString();
                        studentToEdit.setName(newName);
                        management.save(studentToEdit);
                        System.out.println("Student updated successfully.");
                    } else {
                        System.out.println("Student with ID " + idToEdit + " not found.");
                    }
                    break;

                case 4:
                    // Delete a student by ID
                    System.out.print("Enter student ID to delete: ");
                    int idToDelete = userInputService.getInt();
                    Student deletedStudent = management.remove(idToDelete);
                    if (deletedStudent != null) {
                        System.out.println("Student with ID " + idToDelete + " deleted successfully.");
                    } else {
                        System.out.println("Student with ID " + idToDelete + " not found.");
                    }
                    break;

                case 5:
                    // List all students
                    List<Student> students = management.findAll();
                    if (students.isEmpty()) {
                        System.out.println("No students found.");
                    } else {
                        System.out.println("Listing all students:");
                        students.forEach(student ->
                                System.out.println("Student ID: " + student.getId() + ", Name: " + student.getName()));
                    }
                    break;

                case 6:
                    // Exit the application
                    exit = true;
                    System.out.println("Exiting the system. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
            }
        }

        // Close the context
        context.close();
    }
}
