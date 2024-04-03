package ait.cohort34.student.dao;

import ait.cohort34.student.model.Student;
import org.springframework.stereotype.Component;

import java.util.*;

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
    public List<Student> findStudentsByName(String name) {
        List<Student> studentList = new ArrayList<>();
        for (Integer id : students.keySet()) {
            Student student = students.get(id);
            if (student.getName().equalsIgnoreCase(name)) {
                studentList.add(student);
            }
        }
        return studentList;
    }

    @Override
    public Long findStudentsByNamesQuantity(Set<String> names) {
        Long countStudeny = 0L;
        for (Integer id : students.keySet()) {
            Student student = students.get(id);
            for (String name : names) {
                if (student.getName().equals(name)) {
                    countStudeny++;
                }
            }
        }
        return countStudeny;
    }

    @Override
    public List<Student> findStudentsByMinScore(String exam, Integer minScore) {
        List<Student> studentList = new ArrayList<>();
        for (Integer id : students.keySet()) {
            Student student = students.get(id);
            if (student.getScores().containsKey(exam) && student.getScores().get(exam) > minScore) {
                studentList.add(student);
            }
        }
        return studentList;
    }

}
