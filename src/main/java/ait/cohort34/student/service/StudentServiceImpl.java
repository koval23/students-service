package ait.cohort34.student.service;

import ait.cohort34.student.dao.StudentRepository;
import ait.cohort34.student.dto.ScoreDto;
import ait.cohort34.student.dto.StudentAddDto;
import ait.cohort34.student.dto.StudentDto;
import ait.cohort34.student.dto.StudentUpdateDto;
import ait.cohort34.student.dto.exceptions.StudentNotFoundException;
import ait.cohort34.student.model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor//для создания bean для полей final
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public Boolean addStudent(StudentAddDto studentAddDto) {
        if (studentRepository.existsById((studentAddDto.getId()))) {
            return false;
        }
        Student student = new Student(studentAddDto.getId(), studentAddDto.getName(), studentAddDto.getPassword());
        studentRepository.save(student);
        return true;
    }

    @Override
    public StudentDto findStudent(Integer id) {
        Student student = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
        return new StudentDto(id, student.getName(), student.getScores());
    }

    @Override
    public StudentDto removeStudent(Integer id) {
        Student student = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
        studentRepository.deleteById(id);
        return new StudentDto(id, student.getName(), student.getScores());

    }

    @Override
    public StudentAddDto updateStudent(Integer id, StudentUpdateDto studentUpdateDto) {
        Student student = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
        if (studentUpdateDto.getName() != null) {
            student.setName(studentUpdateDto.getName());
        }
        if (studentUpdateDto.getName() != null) {
            student.setPassword(student.getPassword());
        }
        studentRepository.save(student);
        return new StudentAddDto(id, student.getName(), student.getPassword());
    }

    @Override
    public Boolean addScore(Integer id, ScoreDto scoreDto) {
        Student student = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
        boolean res = student.addScore(scoreDto.getExamName(), scoreDto.getScore());
        studentRepository.save(student);
        return res;
    }

    @Override
    public Iterable<StudentDto> findStudentsByName(String name) {
        return studentRepository.findStudentsByName(name).stream()
                .map(s -> new StudentDto(s.getId(), s.getName(), s.getScores()))
                .collect(Collectors.toList());
    }

    @Override
    public Long getStudentsNamesQuantity(Set<String> names) {
        return studentRepository.getStudentsNamesQuantity(names);
    }

    @Override
    public Iterable<StudentDto> findStudentsByExamMinScore(String exam, Integer minScore) {
        return studentRepository.findStudentsByExamMinScore(exam, minScore).stream()
                .map(s -> new StudentDto(s.getId(), s.getName(), s.getScores()))
                .collect(Collectors.toList());
    }
}
