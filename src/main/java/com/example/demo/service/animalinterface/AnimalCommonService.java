package com.example.demo.service.animalinterface;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.AnimalKindsEntity;
import com.example.demo.repository.AnimalKindsRepository;

import lombok.RequiredArgsConstructor;

/**
 * 初期表示のデータ取得など、条件関係なく共通の処理を記述するクラス
 */
@Service
@RequiredArgsConstructor
public final class AnimalCommonService
{
	private final AnimalKindsRepository animalKindsRepository;

	/**
	 * animal_kindsテーブルのデータをすべて取得
	 *
	 * @return {@code List<AnimalKindsEntity>} animal_kindsテーブルの全データ
	 */
	public List<AnimalKindsEntity> getAllAnimalKindData()
	{
		return animalKindsRepository.findAll();
	}
}
