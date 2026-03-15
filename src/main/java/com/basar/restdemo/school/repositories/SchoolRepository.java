package com.basar.restdemo.school.repositories;

import com.basar.restdemo.school.models.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School, Integer> {
}
