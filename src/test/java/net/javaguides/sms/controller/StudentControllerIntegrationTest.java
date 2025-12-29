package net.javaguides.sms.controller;

import net.javaguides.sms.dto.StudentDto;
import net.javaguides.sms.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class StudentControllerIntegrationTest {

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
    }

    @Test
    void postAndGetStudent() throws Exception {
        StudentDto created = new StudentDto();
        created.setId(10L);
        created.setFirstName("IT");
        created.setLastName("Tester");
        created.setEmail("it.tester@example.com");

        when(studentService.createStudent(org.mockito.ArgumentMatchers.any())).thenReturn(created);
        when(studentService.getAllStudents()).thenReturn(List.of(created));

        String json = "{\"firstName\":\"IT\",\"lastName\":\"Tester\",\"email\":\"it.tester@example.com\"}";

        mockMvc.perform(post("/api/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(10));

        mockMvc.perform(get("/api/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("IT"));
    }
}
