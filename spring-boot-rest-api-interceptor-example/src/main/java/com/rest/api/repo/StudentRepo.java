package com.rest.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rest.api.entity.Student;


@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {

}
