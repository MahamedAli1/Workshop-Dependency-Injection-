package Data_access;

import java.util.List;
import Models.Student;

public interface StudentDao {
    Student find(int id);
    Student save(Student student);
    List<Student> findAll();
    void delete(int id);
}
