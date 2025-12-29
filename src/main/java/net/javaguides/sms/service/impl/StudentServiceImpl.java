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
    public StudentDto updateStudent(Long studentId, StudentDto updatedStudentDto) {
        Student student = studentRepository.findById(studentId).orElseThrow(
                () -> new ResourceNotFoundException("Student is not exist with given id: " + studentId)
        );

        student.setFirstName(updatedStudentDto.getFirstName());
        student.setLastName(updatedStudentDto.getLastName());
        student.setEmail(updatedStudentDto.getEmail());

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
}
