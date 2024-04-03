package ait.cohort34.student.dao;

import ait.cohort34.student.model.Student;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class StudentRepositoryImpl implements StudentRepository {

    private Map<Integer, Student> students = new HashMap<>();

    @Override
    public Student save(Student student) {
        students.put(student.getId(), student);
        return student;
    }

    @Override
    public Optional<Student> findById(int id) {
        return Optional.ofNullable(students.get(id));
    }

    @Override
    public Collection<Student> findAll() {
        return students.values();
    }

    @Override
    public Optional<Student> removeStudentById(int id) {
        return Optional.ofNullable(students.remove(id));
    }

    @Override
    public Long findStudentsByNamesQuantity(Set<String> names) {
        return students.values().stream()
                .filter(student -> names.contains(student.getName()))
                .count();
    }

    public List<Student> findStudentsByName(String name) {
        return students.values().stream()
                .filter(student -> student.getName().equals(name))
                .collect(Collectors.toList());
    }

    @Override
    public List<Student> findStudentsByMinScore(String exam, Integer minScore) {
        return students.values().stream()
                .filter(student -> student.getScores().containsKey(exam) && student.getScores().get(exam) > minScore)
                .collect(Collectors.toList());
    }

}
