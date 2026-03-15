package com.basar.restdemo.student.dtos;

public record StudentDto(
        String firstName,
        String lastName,
        String email,
        Integer schoolId
) {
}
