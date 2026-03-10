package com.basar.restdemo.Dtos;

public record StudentDto(
        String firstName,
        String lastName,
        String email,
        Integer schoolId
) {
}
