package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.AnimalNameEntity;
import com.example.demo.entity.AnimalNamePrimaryKey;

public interface AnimalNameRepository extends JpaRepository<AnimalNameEntity, AnimalNamePrimaryKey>
{
	List<AnimalNameEntity> findByPrimaryKeysKindsId(Integer kindsId);
}
