package com.basar.restdemo.student.services;

import com.basar.restdemo.student.dtos.StudentDto;
import com.basar.restdemo.student.dtos.StudentResponseDto;
import com.basar.restdemo.student.models.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentMapperTest {

    private StudentMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new StudentMapper();
    }

    @Test
    public void shouldMapToStudent(){
        StudentDto dto = new StudentDto(
                "John",
                "Doe",
                "johndoe@mail.com",
                1
        );

        Student student = mapper.toStudent(dto);

        assertEquals(dto.firstName(), student.getFirstName());
        assertEquals(dto.lastName(), student.getLastName());
        assertEquals(dto.email(), student.getEmail());
        assertNotNull(student.getSchool());
        assertEquals(dto.schoolId(), student.getSchool().getId());
    }

    @Test
    public void should_throw_null_pointer_exception_when_studentDto_is_null(){
        var exception = assertThrows(NullPointerException.class, () -> mapper.toStudent(null));
        assertEquals("The student dto should not be null.", exception.getMessage());
    }

    @Test
    public void shouldMapToStudentResponseDto(){
        // Given
        Student student = new Student(
                "John",
                "Doe",
                "johndoe@mail.com",
                22
        );

        // When
        StudentResponseDto response = mapper.toStudentResponseDto(student);

        // Then
        assertEquals(student.getFirstName(), response.firstName());
        assertEquals(student.getLastName(), response.lastName());
        assertEquals(student.getEmail(), response.email());

    }
}