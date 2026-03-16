package com.basar.restdemo.student.dtos;

public record StudentResponseDto(
        String firstName,
        String lastName,
        String email) {

    public Object getFirstName() {
        return firstName;
    }
}
