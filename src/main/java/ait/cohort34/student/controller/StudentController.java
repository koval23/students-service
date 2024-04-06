package ait.cohort34.student.controller;

import ait.cohort34.student.dto.ScoreDto;
import ait.cohort34.student.dto.StudentAddDto;
import ait.cohort34.student.dto.StudentDto;
import ait.cohort34.student.dto.StudentUpdateDto;
import ait.cohort34.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/student")
    public Boolean addStudent(@RequestBody StudentAddDto studentAddDto) {
        return studentService.addStudent(studentAddDto);
    }

    @GetMapping("/student/{id}")
    public StudentDto findStudentById(@PathVariable Integer id) {
        return studentService.findStudent(id);
    }

    @DeleteMapping("/student/{id}")
    public StudentDto removeStudentById(@PathVariable Integer id) {
        return studentService.removeStudent(id);
    }

    @PutMapping("/student/{id}")
    public StudentAddDto updateStudent(@PathVariable Integer id,
                                       @RequestBody StudentUpdateDto studentUpdateDto) {
        return studentService.updateStudent(id, studentUpdateDto);
    }

    @PutMapping("/score/student/{id}")
    public Boolean addScore(@PathVariable Integer id,
                            @RequestBody ScoreDto studentScoreDto) {
        return studentService.addScore(id, studentScoreDto);
    }

    @GetMapping("/students/name/{name}")
    public Iterable<StudentDto> findStudentsByName(@PathVariable String name) {
        return studentService.findStudentsByName(name);
    }

    @PostMapping("/quantity/students")
    public Long getStudentsNamesQuantity(@RequestBody Set<String> names) {
        return studentService.getStudentsNamesQuantity(names);
    }

    @GetMapping("/students/exam/{exam}/minscore/{minScore}")
    public Iterable<StudentDto> findStudentsByMinScore(@PathVariable String exam,
                                                       @PathVariable Integer minScore) {
        return studentService.findStudentsByExamMinScore(exam, minScore);
    }

}
