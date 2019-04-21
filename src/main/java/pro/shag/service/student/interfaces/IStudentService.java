package pro.shag.service.student.interfaces;


import pro.shag.model.Student;

import java.util.List;

public interface IStudentService {
    Student create(Student student);
    Student get(String id);
    Student update(Student student);
    void delete(String id);
    List<Student> getAll();
}
