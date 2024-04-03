package ait.cohort34.student.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {

    private int id;
    private String name;
    private Map<String, Integer> scores;

}
