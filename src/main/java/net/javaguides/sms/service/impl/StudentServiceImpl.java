package net.javaguides.sms.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.sms.dto.StudentDto;
import net.javaguides.sms.entity.Student;
import net.javaguides.sms.mapper.StudentMapper;
import net.javaguides.sms.repository.StudentRepository;
import net.javaguides.sms.service.StudentService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

import net.javaguides.sms.exception.ResourceNotFoundException;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;

    @Override
    public StudentDto createStudent(StudentDto studentDto) {

        Student student = StudentMapper.mapToStudent(studentDto);
        Student savedStudent = studentRepository.save(student);

        return StudentMapper.mapToStudentDto(savedStudent);
    }

    @Override
    public StudentDto getStudentById(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student is not exist with given id: " + studentId));
        return StudentMapper.mapToStudentDto(student);
    }

    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream().map((student) -> StudentMapper.mapToStudentDto(student))
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentDto> getStudentsByClassName(String className) {
        List<Student> students = studentRepository.findByClassName(className);
        return students.stream().map(StudentMapper::mapToStudentDto).collect(Collectors.toList());
    }

    @Override
    public Double getStudentScore(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student is not exist with given id: " + studentId));
        return student.getScore();
    }

    @Override
    public StudentDto updateStudent(Long studentId, StudentDto updatedStudentDto) {
        Student student = studentRepository.findById(studentId).orElseThrow(
                () -> new ResourceNotFoundException("Student is not exist with given id: " + studentId)
        );

        student.setFirstName(updatedStudentDto.getFirstName());
        student.setLastName(updatedStudentDto.getLastName());
        student.setEmail(updatedStudentDto.getEmail());
        // map sex string to enum safely
        if (updatedStudentDto.getSex() != null) {
            String s = updatedStudentDto.getSex().trim().toUpperCase();
            if (s.equals("MALE") || s.equals("M")) student.setSex(net.javaguides.sms.entity.Sex.MALE);
            else if (s.equals("FEMALE") || s.equals("F")) student.setSex(net.javaguides.sms.entity.Sex.FEMALE);
            else student.setSex(null);
        } else {
            student.setSex(null);
        }
        student.setScore(updatedStudentDto.getScore());
        student.setAge(updatedStudentDto.getAge());
        student.setClassName(updatedStudentDto.getClassName());

        Student updatedStudent = studentRepository.save(student);

        return StudentMapper.mapToStudentDto(updatedStudent);
    }

    @Override
    public void deleteStudent(Long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(
                () -> new ResourceNotFoundException("Student is not exist with given id: " + studentId)
        );

        studentRepository.deleteById(studentId);
    }

    @Override
    public List<StudentDto> getStudentsWithScoreGreaterThan(Double minScore) {
        List<Student> students = studentRepository.findByScoreGreaterThan(minScore);
        return students.stream().map(StudentMapper::mapToStudentDto).collect(Collectors.toList());
    }

    @Override
    public java.util.Map<Integer, Long> getAgeCounts() {
        java.util.Map<Integer, Long> result = new java.util.HashMap<>();
        List<Object[]> rows = studentRepository.countByAgeGroup();
        for (Object[] row : rows) {
            Integer age = (Integer) row[0];
            Long count = (Long) row[1];
            if (age != null) {
                result.put(age, count);
            }
        }
        return result;
    }

    @Override
    public java.util.Map<String, Long> getSexCounts() {
        java.util.Map<String, Long> result = new java.util.HashMap<>();
        List<Object[]> rows = studentRepository.countBySexGroup();
        for (Object[] row : rows) {
            net.javaguides.sms.entity.Sex sex = (net.javaguides.sms.entity.Sex) row[0];
            Long count = (Long) row[1];
            String key = sex == null ? "UNKNOWN" : sex.name();
            result.put(key, count);
        }
        return result;
    }
}
