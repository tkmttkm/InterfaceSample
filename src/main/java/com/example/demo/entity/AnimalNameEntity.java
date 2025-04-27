package com.example.demo.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "animal_name")
public final class AnimalNameEntity
{
	@EmbeddedId
	private AnimalNamePrimaryKey primaryKeys;
	private String               name;
}