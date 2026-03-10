package com.basar.restdemo.Controllers;

import com.basar.restdemo.Dtos.SchoolDto;
import com.basar.restdemo.Models.School;
import com.basar.restdemo.Repositories.SchoolRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SchoolController {

    private final SchoolRepository schoolRepository;

    public SchoolController(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    private School toSchool(SchoolDto dto){
        return new School(
                dto.name()
        );
    }

    private SchoolDto toSchoolDto(School school){
        return new SchoolDto(
                school.getName()
        );
    }

    @PostMapping("/schools")
    public SchoolDto create(
            @RequestBody SchoolDto dto
    ){
        var school = toSchool(dto);
        schoolRepository.save(school);
        return dto;
    }

    @GetMapping("/schools")
    public List<SchoolDto> findAll(){
        return schoolRepository
                .findAll()
                .stream()
                .map(this::toSchoolDto)
                .collect(Collectors.toList());
    }
}
