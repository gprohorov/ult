package pro.shag.service.group.impls;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import pro.shag.model.Group;
import pro.shag.model.Student;
import pro.shag.repository.GroupRepository;
import pro.shag.repository.StudentRepository;
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

/**/

    @Override
    public Group getByName(String name) {

                return this.getAll().stream()
                        .filter(item-> item.getName().equals(name))
                        .findFirst().orElse(null)
                        ;
    }
/*

    @Override
    public Group getByName(String name) {
        return null;
    }
*/

    @Override
    public List<Group> getAllByName(String name) {
        return repository.findByName(name);
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
        Map<Group, Double> sorted = new LinkedHashMap<>(); // создет мэп : ключ группа, значение средний балл
                                                           // пока что пустой

         Map<Group, Double> map = studentService.getAll() //  получаем список всех студентов
                 .stream()   //  делаем из него стрим
                .collect(Collectors.groupingBy(Student::getGroup,  // собираем его в МЭП, где ключ это группа как объект
                     Collectors.averagingDouble(Student::getMark)));  //  а значение оцена mark  причем агрегацмя СРЕДНЕЕ

                map.entrySet()  // из мэпа делаем Энтри Сэт
                        .stream()  // денлаем стрим
                .sorted(Map.Entry.<Group, Double>comparingByValue().reversed()) //  сортируем по ЗНАЧЕНИЮ (value) реверс
                .forEachOrdered(entry-> sorted.put(entry.getKey(), entry.getValue())); // заполняем пустой энтри сэт 91 строки


        return null;
    }



   /**/

}
