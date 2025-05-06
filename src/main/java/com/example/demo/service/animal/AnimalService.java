package com.example.demo.service.animal;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import com.example.demo.entity.AnimalKindsEntity;
import com.example.demo.entity.AnimalNameEntity;
import com.example.demo.form.AnimalForm;
import com.example.demo.repository.AnimalKindsRepository;
import com.example.demo.repository.AnimalNameRepository;
import lombok.RequiredArgsConstructor;

/** switch文を使用した分岐のサービスクラス */
@Service
@RequiredArgsConstructor
public final class AnimalService
{
	@Autowired
	private Validator springValidator;

	private final AnimalKindsRepository animalKindsRepository;
	private final AnimalNameRepository  animalNameRepository;

	/** 表示ボタン押下時の処理 */
	public void display(final Model model)
	{
		final AnimalForm form = (AnimalForm) model.getAttribute("form");

		switch (form.getAnimalAsInt())
		{
			case 1:
				model.addAttribute("test", "ねこ表示");
				break;
			case 2:
				model.addAttribute("test", "いぬ表示");
				break;
			case 3:
				model.addAttribute("test", "さる表示");
				break;
		}

		final BindingResult bindingResult = new BeanPropertyBindingResult(form, "form");
		if (!validation(model, form, bindingResult))
		{
			return;
		}

		final Optional<AnimalKindsEntity> kinds_data     = getSelectedKindsData(model, bindingResult, form);
		if (kinds_data.isEmpty())
		{
			return;
		}

		final List<AnimalNameEntity> name_data = animalNameRepository.findByPrimaryKeysKindsId(form.getAnimalAsInt());

		form.setKinds_data(kinds_data.get());
		form.setName_data(name_data);
		model.addAttribute("form", form);
	}

	private Optional<AnimalKindsEntity> getSelectedKindsData(
			final Model model, final BindingResult bindingResult, final AnimalForm form
			)
	{
		final Optional<AnimalKindsEntity> kinds_data = animalKindsRepository.findById(form.getAnimalAsInt());

		if (!kinds_data.isPresent())
		{
			bindingResult.rejectValue("animal", "form.animal.notexist");
			model.addAttribute("org.springframework.validation.BindingResult.form", bindingResult);
			return Optional.empty();
		}
		return kinds_data;
	}

	private boolean validation(final Model model, final AnimalForm form, final BindingResult bindingResult)
	{
		springValidator.validate(form, bindingResult);

		if (bindingResult.hasErrors())
		{
			model.addAttribute("org.springframework.validation.BindingResult.form", bindingResult);
			return false;
		}

		return true;
	}

	/** 画面クリアボタン押下時の処理 */
	public void clear(final Model model)
	{
		final AnimalForm form = (AnimalForm) model.getAttribute("form");

		switch (form.getAnimalAsInt())
		{
			case 1:
				model.addAttribute("test", "ねこ画面クリア");
				break;
			case 2:
				model.addAttribute("test", "いぬ画面クリア");
				break;
			case 3:
				model.addAttribute("test", "さる画面クリア");
				break;
		}

		form.setKinds_data(null);
		form.setName_data(Collections.emptyList());
	}

	/**
	 * 画面表示ModelAttribute用
	 *
	 * @return {@code List<AnimalKindsEntity>} animal_kindsテーブルの全データ
	 */
	public List<AnimalKindsEntity> getAllAnimalKindsData()
	{
		return animalKindsRepository.findAll();
	}

}
