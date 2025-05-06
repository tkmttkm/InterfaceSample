package com.example.demo.service.animalinterface.kinds;

import org.springframework.stereotype.Component;

import com.example.demo.repository.AnimalKindsRepository;
import com.example.demo.repository.AnimalNameRepository;
import com.example.demo.service.animalinterface.Animal;

@Component
public final class Dog extends Animal
{
	public Dog(final AnimalKindsRepository animalKindsRepository, final AnimalNameRepository animalNameRepository)
	{
		super(animalKindsRepository, animalNameRepository);
	}

	@Override
	public int id()
	{
		return 2;
	}

	@Override
	public String name()
	{
		return "いぬ";
	}

}
