package ait.cohort34.student.service;

import ait.cohort34.student.dao.StudentRepository;
import ait.cohort34.student.dto.ScoreDto;
import ait.cohort34.student.dto.StudentAddDto;
import ait.cohort34.student.dto.StudentDto;
import ait.cohort34.student.dto.StudentUpdateDto;
import ait.cohort34.student.dto.exceptions.StudentNotFoundException;
import ait.cohort34.student.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Boolean addStudent(StudentAddDto studentAddDto) {
        if (studentRepository.findById(studentAddDto.getId()).isPresent()) {
            return false;
        }
        Student student = new Student(
                studentAddDto.getId(),
                studentAddDto.getName(),
                studentAddDto.getPassword());
        studentRepository.save(student);
        return true;
    }

    @Override
    public StudentDto findStudentById(Integer id) {
        Student student = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
        return new StudentDto(id, student.getName(), student.getScores());
    }

    @Override
    public StudentDto removeStudentById(Integer id) {
        Student student = studentRepository.removeStudentById(id).orElseThrow(StudentNotFoundException::new);
        return new StudentDto(id, student.getName(), student.getScores());
    }

    @Override
    public StudentAddDto updateStudent(Integer id, StudentUpdateDto studentUpdateDto) {
        Student student = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);

        student.setName(studentUpdateDto.getName());
        student.setPassword(studentUpdateDto.getPassword());
        studentRepository.save(student);

        return new StudentAddDto(student.getId(), student.getName(), student.getPassword());
    }

    @Override
    public Boolean addScore(Integer id, ScoreDto scoreDto) {
        Student student = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
        return student.addScore(scoreDto.getExamName(), scoreDto.getScore());

    }

    @Override
    public Iterable<StudentDto> findStudentsByName(String name) {
        return studentRepository.findStudentsByName(name).stream()
                .map(res -> new StudentDto(res.getId(), res.getName(), res.getScores()))
                .collect(Collectors.toList());

    }

    @Override
    public Long getStudentsNamesQuantity(Set<String> names) {
        return studentRepository.findStudentsByNamesQuantity(names);
    }

    @Override
    public Iterable<StudentDto> findStudentsByMinScore(String exam, Integer minScore) {

        return studentRepository.findStudentsByMinScore(exam, minScore).stream()
                .map(res -> new StudentDto(res.getId(), res.getName(), res.getScores()))
                .collect(Collectors.toList());
    }
}
