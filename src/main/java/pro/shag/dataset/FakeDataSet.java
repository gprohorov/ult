package pro.shag.dataset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pro.shag.model.Group;
import pro.shag.model.Student;
import pro.shag.repository.GroupRepository;
import pro.shag.repository.StudentRepository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class FakeDataSet {

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    StudentRepository studentRepository;

    private List<Group> groups = new ArrayList<>(
            Arrays.asList(
                    new Group( "243 cк ПЗКС", "Vova"),
                    new Group("241 МПУИК", "Ivan"),
                    new Group( " 131 Психология", "Nata"),
                    new Group( "543 Магистратура", "Olya")
            )
    );

    private static List<Student> students = new ArrayList<>();

 //   @PostConstruct
    void init(){
        groupRepository.deleteAll();
        groupRepository.saveAll(groups);

        Student stud1 = new Student("Ivanov", groups.get(0), 70);
        Student stud2 = new Student("Petrov", groups.get(1), 88);
        Student stud3 = new Student("Sidorov", groups.get(2), 90);
        Student stud4 = new Student("Popov", groups.get(0),55);

        students.add(stud1);
        students.add(stud2);
        students.add(stud3);
        students.add(stud4);

        studentRepository.deleteAll();
        studentRepository.saveAll(students);

    }

}
