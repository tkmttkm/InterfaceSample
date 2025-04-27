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
		model.addAttribute("test", "表示");

		final AnimalForm form = (AnimalForm) model.getAttribute("form");

		final BindingResult bindingResult = new BeanPropertyBindingResult(form, "form");
		springValidator.validate(form, bindingResult);

		if (bindingResult.hasErrors())
		{
			model.addAttribute("org.springframework.validation.BindingResult.form", bindingResult);
			return;
		}

		final int                         selectedAnimal = form.getAnimalAsInt();
		final Optional<AnimalKindsEntity> kinds_data     = animalKindsRepository.findById(selectedAnimal);

		if (!kinds_data.isPresent())
		{
			bindingResult.rejectValue("animal", "form.animal.notexist");
			model.addAttribute("org.springframework.validation.BindingResult.form", bindingResult);
			return;
		}

		final List<AnimalNameEntity> name_data = animalNameRepository.findByPrimaryKeysKindsId(selectedAnimal);
		form.setKinds_data(kinds_data.get());
		form.setName_data(name_data);
		model.addAttribute("form", form);
	}

	/** 画面クリアボタン押下時の処理 */
	public void clear(final Model model)
	{
		model.addAttribute("test", "画面クリア");

		final AnimalForm form = (AnimalForm) model.getAttribute("form");

		form.setKinds_data(null);
		form.setName_data(Collections.emptyList());
	}

}
