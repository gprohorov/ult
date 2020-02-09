package pro.shag.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pro.shag.model.Student;

@Repository
public interface StudentRepository  extends MongoRepository<Student, String> {
}
