package com.example.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.task.model.DoctorsModel;

@Repository
public interface DoctorsRepository extends JpaRepository<DoctorsModel, Integer>{

}
