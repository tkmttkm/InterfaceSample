package com.example.demo.service.animalinterface;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.AnimalKindsEntity;
import com.example.demo.entity.AnimalNameEntity;
import com.example.demo.repository.AnimalKindsRepository;
import com.example.demo.repository.AnimalNameRepository;
import com.example.demo.service.animalinterface.ianimal.IAnimal;

import lombok.RequiredArgsConstructor;

/** 選択された動物による処理分岐のクラス。このクラスを継承して使用 */
@RequiredArgsConstructor
public abstract class Animal implements IAnimal
{
	private final AnimalKindsRepository animalKindsRepository;
	private final AnimalNameRepository  animalNameRepository;

	public List<AnimalKindsEntity> getAnimalKindsData()
	{
		return this.animalKindsRepository.findAll();
	}

	public List<AnimalNameEntity> getAnimalNameData()
	{
		return this.animalNameRepository.findAll();
	}

	public Optional<AnimalKindsEntity> getAnimalKindsDataByPrimaryKey()
	{
		return animalKindsRepository.findById(this.id());
	}

	public List<AnimalNameEntity> getAnimalNameDataByKindsId()
	{
		return animalNameRepository.findByPrimaryKeysKindsId(kinds_id());
	}
}
