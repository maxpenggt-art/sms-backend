package net.javaguides.sms.mapper;

import net.javaguides.sms.dto.StudentDto;
import net.javaguides.sms.entity.Student;
import net.javaguides.sms.entity.Sex;

public class StudentMapper {

    public static StudentDto mapToStudentDto(Student student) {
        return new StudentDto(
            student.getId(),
            student.getFirstName(),
            student.getLastName(),
            student.getEmail(),
            student.getSex() == null ? null : student.getSex().name().toLowerCase(),
            student.getScore(),
            student.getAge(),
            student.getClassName()
        );
    }

    public static Student mapToStudent(StudentDto studentDto) {
        Sex sexEnum = null;
        if (studentDto.getSex() != null) {
            String s = studentDto.getSex().trim().toUpperCase();
            if (s.equals("MALE") || s.equals("M") || s.equals("male")) sexEnum = Sex.MALE;
            else if (s.equals("FEMALE") || s.equals("F") || s.equals("female")) sexEnum = Sex.FEMALE;
        }

        return new Student(
            studentDto.getId(),
            studentDto.getFirstName(),
            studentDto.getLastName(),
            studentDto.getEmail(),
            sexEnum,
            studentDto.getScore(),
            studentDto.getAge(),
            studentDto.getClassName()
        );
    }
}
