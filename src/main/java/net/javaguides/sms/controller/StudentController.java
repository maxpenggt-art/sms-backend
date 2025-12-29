package net.javaguides.sms.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;


import net.javaguides.sms.dto.StudentDto;
import net.javaguides.sms.service.StudentService;


@AllArgsConstructor
@RestController
@RequestMapping("/api/students")
public class StudentController {

    private StudentService studentService;

    @PostMapping
    public ResponseEntity<StudentDto> createStudent(@RequestBody StudentDto studentDto) {
        StudentDto savedStudent = studentService.createStudent(studentDto);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable("id") Long studentId) {
        StudentDto studentDto = studentService.getStudentById(studentId);
        return ResponseEntity.ok(studentDto);
    }

    @GetMapping
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        List<StudentDto> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @PutMapping("{id}")
    public  ResponseEntity<StudentDto> updateStudent(@PathVariable("id") Long studentId, @RequestBody StudentDto updatedStudent) {
        StudentDto studentDto = studentService.updateStudent(studentId, updatedStudent);
        return ResponseEntity.ok(studentDto);
    }

    @DeleteMapping("{id}")
    public  ResponseEntity<String> deleteStudent(@PathVariable("id") Long studentId) {
        studentService.deleteStudent(studentId);
        return ResponseEntity.ok("Student deleted successfully!");
    }

    @GetMapping("/class/{className}")
    public ResponseEntity<List<StudentDto>> getStudentsByClass(@PathVariable("className") String className) {
        List<StudentDto> students = studentService.getStudentsByClassName(className);
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}/score")
    public ResponseEntity<Double> getStudentScore(@PathVariable("id") Long studentId) {
        Double score = studentService.getStudentScore(studentId);
        return ResponseEntity.ok(score);
    }

    @GetMapping("/score-above")
    public ResponseEntity<List<StudentDto>> getStudentsWithScoreAbove(@RequestParam("min") Double min) {
        List<StudentDto> students = studentService.getStudentsWithScoreGreaterThan(min);
        return ResponseEntity.ok(students);
    }

    @GetMapping("/age-counts")
    public ResponseEntity<Map<Integer, Long>> getAgeCounts() {
        Map<Integer, Long> counts = studentService.getAgeCounts();
        return ResponseEntity.ok(counts);
    }

    @GetMapping("/sex-counts")
    public ResponseEntity<Map<String, Long>> getSexCounts() {
        Map<String, Long> counts = studentService.getSexCounts();
        return ResponseEntity.ok(counts);
    }
}
