package com.basar.restdemo.Controllers;

import com.basar.restdemo.Dtos.StudentDto;
import com.basar.restdemo.Dtos.StudentResponseDto;
import com.basar.restdemo.Models.School;
import com.basar.restdemo.Models.Student;
import com.basar.restdemo.Repositories.StudentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    private final StudentRepository repository;

    public StudentController(StudentRepository repository) {
        this.repository = repository;
    }

    private Student toStudent(StudentDto dto){
        var student = new Student();
        student.setFirstName(dto.firstName());
        student.setLastName(dto.lastName());
        student.setEmail(dto.email());

        var school = new School();
        school.setId(dto.schoolId());
        student.setSchool(school);

        return student;
    }

    private StudentResponseDto studentResponseDto(Student student){
        return new StudentResponseDto(
                student.getFirstName(),
                student.getLastName(),
                student.getEmail()
        );
    }

    @PostMapping("/students")
    public StudentResponseDto post(
            @RequestBody StudentDto dto){
        var student = toStudent(dto);
        var savedStudent = repository.save(student);
        return studentResponseDto(savedStudent);
    }

    @GetMapping("/students")
    public List<Student> getStudents(){
        return repository.findAll();
    }

    @GetMapping("/students/{student-id}")
    public Student getStudentById(
            @PathVariable("student-id") Integer id
    ){
        return repository.findById(id).orElse(new Student());
    }

    @GetMapping("/students/search/{student-name}")
    public List<Student> getStudentByName(
            @PathVariable("student-name") String name
    ){
        return repository.findAllByFirstNameContaining(name);
    }

    @DeleteMapping("/students/{student-id}")
    public String deleteStudentById(
            @PathVariable("student-id") Integer id
    ){
        repository.deleteById(id);
        return "Student with id: " + id + " has been deleted successfully.";
    }
}
