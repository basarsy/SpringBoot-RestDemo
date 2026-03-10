package com.basar.restdemo.Repositories;

import com.basar.restdemo.Models.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School, Integer> {
}
