package com.basar.restdemo.student.services;

import com.basar.restdemo.student.dtos.StudentDto;
import com.basar.restdemo.student.dtos.StudentResponseDto;
import com.basar.restdemo.student.models.Student;
import com.basar.restdemo.student.repositories.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    // The service
    @InjectMocks
    private StudentService studentService;

    // Dependencies
    @Mock
    private StudentRepository studentRepository;
    @Mock
    private StudentMapper studentMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveStudent() {
        // Given
        Student student = new Student(
                "John",
                "Doe",
                "john@mail.com",
                22
        );

        StudentDto dto = new StudentDto(
                "John",
                "Doe",
                "john@mail.com",
                1
        );

        Student savedStudent = new Student(
                "John",
                "Doe",
                "john@mail.com",
                22
        );
        savedStudent.setId(1);

        // Mock the calls
        when(studentMapper.toStudent(dto)).thenReturn(student);

        when(studentRepository.save(student)).thenReturn(savedStudent);

        when(studentMapper.toStudentResponseDto(savedStudent))
                .thenReturn(new StudentResponseDto(
                        "John",
                        "Doe",
                        "john@mail.com")
                );

        // When
        StudentResponseDto responseDto = studentService.saveStudent(dto);

        // Then
        assertEquals(dto.firstName(), responseDto.firstName());
        assertEquals(dto.lastName(), responseDto.lastName());
        assertEquals(dto.email(), responseDto.email());

        verify(studentMapper, times(1)).toStudent(dto);
        verify(studentRepository, times(1)).save(student);
        verify(studentMapper, times(1)).toStudentResponseDto(savedStudent);

    }

    @Test
    void findAllStudents() {
        // Given
        List<Student> students = new ArrayList<>();
        students.add(new Student(
                "John",
                "Doe",
                "john@mail.com",
                22
        ));

        // Mock the calls
        when(studentRepository.findAll()).thenReturn(students);
        when(studentMapper.toStudentResponseDto(any(Student.class)))
                .thenReturn(new StudentResponseDto(
                "John",
                "Doe",
                "john@mail.com"
        ));

        // When
        List<StudentResponseDto> responseDtos = studentService.findAllStudents();

        // Then
        assertEquals(students.size(), responseDtos.size());
        verify(studentRepository, times(1)).findAll();
    }

    @Test
    void findStudentById() {
        // Given
        Student student = new Student(
                "John",
                "Doe",
                "john@mail.com",
                22
        );
        student.setId(13);

        // Mock the calls
        when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
        when(studentMapper.toStudentResponseDto(any(Student.class)))
                .thenReturn(new StudentResponseDto(
                        "John",
                        "Doe",
                        "john@mail.com"
                ));

        // When
        StudentResponseDto dto = studentService.findStudentById(student.getId());

        // Then
        assertEquals(13, student.getId());
        assertEquals(dto.firstName(), student.getFirstName());
        assertEquals(dto.lastName(), student.getLastName());
        assertEquals(dto.email(), student.getEmail());

        verify(studentRepository, times(1)).findById(student.getId());
        verify(studentMapper, times(1)).toStudentResponseDto(student);

    }

    @Test
    void findStudentsByName_whenNameExists_returnsMatchingStudents() {
        String studentName = "John";
        List<Student> students = new ArrayList<>();
        students.add(new Student(
                "John",
                "Doe",
                "john@mail.com",
                22
        ));
        students.add(new Student(
                "John",
                "Smith",
                "johnSmith@mail.com",
                26
        ));

        when(studentRepository.findAllByFirstNameContaining(studentName)).thenReturn(students);
        when(studentMapper.toStudentResponseDto(any(Student.class)))
                .thenReturn(new StudentResponseDto(
                        "John",
                        "Doe",
                        "john@mail.com"
                ));

        var responseDtos = studentService.findStudentsByName(studentName);

        assertEquals(students.size(), responseDtos.size());
        assertTrue(responseDtos.stream().allMatch(dto -> dto.getFirstName().equals("John")));
        verify(studentRepository, times(1)).findAllByFirstNameContaining(studentName);
    }

    @Test
    void deleteStudentById() {
    }
}