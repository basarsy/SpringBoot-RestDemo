package com.basar.restdemo.school.services;

import com.basar.restdemo.school.dtos.SchoolDto;
import com.basar.restdemo.school.models.School;
import org.springframework.stereotype.Service;

@Service
public class SchoolMapper {
    public School toSchool(SchoolDto dto){
        return new School(
                dto.name()
        );
    }

    public SchoolDto toSchoolDto(School school){
        return new SchoolDto(
                school.getName()
        );
    }
}
