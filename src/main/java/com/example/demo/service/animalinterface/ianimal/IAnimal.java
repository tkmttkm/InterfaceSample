package com.example.demo.service.animalinterface.ianimal;

import org.springframework.stereotype.Component;

/** 選択された動物による処理分岐のインターフェース */
@Component
public interface IAnimal
{
	/** animal_kindsのid */
	public int id();

	/** animal_nameのkinds_id */
	default int kinds_id()
	{
		return this.id();
	}
}
