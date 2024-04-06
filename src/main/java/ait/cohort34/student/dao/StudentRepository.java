package ait.cohort34.student.dao;

import ait.cohort34.student.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Set;

public interface StudentRepository extends MongoRepository<Student, Integer> {

    @Query("{'name': {$regex : ?0, $options: 'i'}}")
    List<Student> findStudentsByName(String name);


    @Query("{'name': {$in: ?0}}")
    Long getStudentsNamesQuantity(Set<String> names);

    @Query(value = "{'scores.?0': { $gt: ?1 }}", fields = "{'scores': 1}")
    List<Student> findStudentsByExamMinScore(String exam, Integer minScore);

}

