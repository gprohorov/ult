package pro.shag.service.group.impls;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import pro.shag.model.Group;
import pro.shag.model.Student;
import pro.shag.repository.GroupRepository;
import pro.shag.service.group.interfaces.IGroupService;
import pro.shag.service.student.impls.StudentServiceImpl;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class GroupServiceMongoImpl implements IGroupService {

    @Autowired
    GroupRepository repository;

    @Autowired
    StudentServiceImpl studentService;

    private List<Group> groups = new ArrayList<>(
            Arrays.asList(
                    new Group( "bbv1", "Vova"),
                    new Group("zxc2", "Ivan"),
                    new Group( "asd3", "Nata"),
                    new Group( "ghj4", "Olya")
            )
    );

    @PostConstruct
    void init(){
       // this.repository.saveAll(groups);
    }

    @Override
    public Group getByName(String name) {
        return null;
    }

    @Override
    public List<Group> getAllByName(String name) {
        return null;
    }

    public Group create(Group group) {
        return this.repository.save(group);
    }

    public Group get(String id) {
        return this.repository.findById(id).get();
    }

    public Group update(Group group) {
        return this.repository.save(group);
    }

    public Group delete(String id) {
        this.repository.deleteById(id);
        return null;
    }

    public List<Group> getAll() {
        return this.repository.findAll();
    }

    public Group getGroupByCurator(String curator){
        return this.repository.findByCuratorName(curator).get(0);
    }

    public Map<Group, Integer> getGroupsByAverageMark(){
        Map<Group, Double> sorted = new LinkedHashMap<>();
         Map<Group, Double> map = studentService.getAll().stream()
                .collect(Collectors.groupingBy(Student::getGroup,
                     Collectors.averagingDouble(Student::getMark)));
                map.entrySet().stream()
                .sorted(Map.Entry.<Group, Double>comparingByValue().reversed())
                .forEachOrdered(entry-> sorted.put(entry.getKey(), entry.getValue()));




        return null;
    }
}
