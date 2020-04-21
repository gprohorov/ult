package pro.shag.repository;


import org.hibernate.validator.constraints.Range;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pro.shag.model.Group;

import java.util.List;

@Repository
public interface GroupRepository extends MongoRepository<Group, String> {

   // List<Group> findByName(String name);

 //   List<Group> findByNameIsLike(String regex);
    List<Group> findByCuratorName(String curator);


    List<Group> findByName(String name);
    List<Group> findByNameIsLike(String regex);
  //  List<Group> findByNameAndAndCuratorNameAndCuratorNameIsContaining


}
