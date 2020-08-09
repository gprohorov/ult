package pro.shag.service.student.impls;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.shag.model.Group;
import pro.shag.model.Student;
import pro.shag.repository.StudentRepository;
import pro.shag.service.group.impls.GroupServiceMongoImpl;
import pro.shag.service.student.interfaces.IStudentService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements IStudentService {

    private static List<Student> students = new ArrayList<>();

    @Autowired
    GroupServiceMongoImpl groupService;

    @Autowired
    StudentRepository repository;



    public List<Student>  straightA(){

        return  repository.findByMarkGreaterThanEqual(90);
    }

    @Override
    public Student create(Student student) {
        return this.repository.save(student);
    }

    @Override
    public Student get(String id) {
        return this.repository.findById(id).orElse(null);
    }

    @Override
    public Student update(Student student) {
        return this.repository.save(student);
    }

    @Override
    public void delete(String id) {
        this.repository.deleteById(id);
    }

    @Override
    public List<Student> getAll() {
        return this.repository.findAll();
    }
}
