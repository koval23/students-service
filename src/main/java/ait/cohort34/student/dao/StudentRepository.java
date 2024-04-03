package ait.cohort34.student.dao;

import ait.cohort34.student.dto.StudentDto;
import ait.cohort34.student.model.Student;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface StudentRepository {

    Student save(Student student);

    Optional<Student> findById(int id);

    Collection<Student> findAll();

    Optional<Student> removeStudentById(int id);

    List<Student> findStudentsByName(String name);

    Long findStudentsByNamesQuantity(Set<String> names);
    List<Student> findStudentsByMinScore(String exam, Integer minScore);

}
