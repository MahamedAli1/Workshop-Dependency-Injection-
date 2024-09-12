package Management;

import Data_access.StudentDao;
import Models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import Services.UserInputService;

import java.util.List;

@Component
public class StudentManagementConsoleImpl implements StudentManagement {

    private final UserInputService userInputService;
    private final StudentDao studentDao;

    @Autowired
    public StudentManagementConsoleImpl(UserInputService userInputService, StudentDao studentDao) {
        this.userInputService = userInputService;
        this.studentDao = studentDao;
    }

    @Override
    public Student create() {
        int id;
        while (true) {
            System.out.print("Enter student ID: ");
            id = userInputService.getInt();

            // Check for duplicate ID
            if (studentDao.find(id) != null) {
                System.out.println("Student with ID " + id + " already exists. Please enter a different ID.");
            } else {
                break; // ID is unique, exit the loop
            }
        }

        System.out.print("Enter student name: ");
        String name = userInputService.getString();

        Student student = new Student();
        student.setId(id);
        student.setName(name);

        return student;
    }

    @Override
    public Student save(Student student) {
        return studentDao.save(student);
    }

    @Override
    public Student find(int id) {
        return studentDao.find(id);
    }

    @Override
    public Student remove(int id) {
        Student student = studentDao.find(id);
        if (student != null) {
            studentDao.delete(id);
        }
        return student;
    }

    @Override
    public List<Student> findAll() {
        return studentDao.findAll();
    }

    @Override
    public Student edit(Student student) {
        return null; 
    }
}
