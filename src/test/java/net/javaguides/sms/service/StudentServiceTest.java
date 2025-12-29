package net.javaguides.sms.service;

import net.javaguides.sms.entity.Student;
import net.javaguides.sms.mapper.StudentMapper;
import net.javaguides.sms.repository.StudentRepository;
import net.javaguides.sms.service.impl.StudentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentServiceImpl studentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllStudents_returnsMappedList() {
        Student s1 = new Student(1L, "A", "B", "a.b@example.com");
        Student s2 = new Student(2L, "C", "D", "c.d@example.com");
        when(studentRepository.findAll()).thenReturn(List.of(s1, s2));

        var dtos = studentService.getAllStudents();

        assertThat(dtos).hasSize(2);
        assertThat(dtos.get(0).getEmail()).isEqualTo(s1.getEmail());
    }

    @Test
    void getStudentById_returnsOptionalDto() {
        Student s = new Student(5L, "First", "Last", "f.l@example.com");
        when(studentRepository.findById(5L)).thenReturn(Optional.of(s));

        var dto = studentService.getStudentById(5L);

        assertThat(dto).isNotNull();
        assertThat(dto.getFirstName()).isEqualTo("First");
    }
}
