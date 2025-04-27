package com.example.demo.service.animalinterface.kinds;

import org.springframework.stereotype.Component;

import com.example.demo.repository.AnimalKindsRepository;
import com.example.demo.repository.AnimalNameRepository;
import com.example.demo.service.animalinterface.Animal;

@Component
public final class Monkey extends Animal
{
	public Monkey(final AnimalKindsRepository animalKindsRepository, final AnimalNameRepository animalNameRepository)
	{
		super(animalKindsRepository, animalNameRepository);
	}

	@Override
	public int id()
	{
		return 3;
	}

}
