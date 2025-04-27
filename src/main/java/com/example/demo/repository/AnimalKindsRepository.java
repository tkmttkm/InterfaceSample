package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.AnimalKindsEntity;

public interface AnimalKindsRepository extends JpaRepository<AnimalKindsEntity, Integer>
{
}