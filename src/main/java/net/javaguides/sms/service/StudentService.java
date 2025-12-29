package net.javaguides.sms.service;

import net.javaguides.sms.dto.StudentDto;
import java.util.List;

public interface StudentService {
    StudentDto createStudent(StudentDto studentDto);
    
    StudentDto getStudentById(Long studentId);

    List<StudentDto> getAllStudents();

    StudentDto updateStudent(Long studentId, StudentDto updateStudent);

    void deleteStudent(Long studentId);

    List<StudentDto> getStudentsByClassName(String className);

    Double getStudentScore(Long studentId);

    List<StudentDto> getStudentsWithScoreGreaterThan(Double minScore);

    java.util.Map<Integer, Long> getAgeCounts();

    java.util.Map<String, Long> getSexCounts();
}
