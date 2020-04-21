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

   // @PostConstruct
    void init(){
        Group group0 = groupService.getAll().get(0);
        Group group1 = groupService.getAll().get(1);
        Group group2 = groupService.getAll().get(2);

        Student stud1 = new Student("Ivanov", group2, 70);
        Student stud2 = new Student("Tolia", group1, 88);
        Student stud3 = new Student("Yura", group2, 90);
        Student stud4 = new Student("Nazar", group0,55);

        students.add(stud1);
        students.add(stud2);
        students.add(stud3);
        students.add(stud4);

    repository.deleteAll();
      repository.saveAll(students);

    }

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
