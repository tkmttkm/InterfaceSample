package com.example.demo.service.animalinterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import com.example.demo.common.icommon.IAction;
import com.example.demo.entity.AnimalKindsEntity;
import com.example.demo.entity.AnimalNameEntity;
import com.example.demo.form.AnimalForm;
import com.example.demo.service.animalinterface.kinds.Cat;
import com.example.demo.service.animalinterface.kinds.Dog;
import com.example.demo.service.animalinterface.kinds.Monkey;

/** 表示ボタン押下時の処理クラス */
@Service
public final class AnimalDisplayService implements IAction
{
	@Autowired
	private Validator springValidator;

	private final Map<Integer, Animal> animals = new HashMap<Integer, Animal>();

	public AnimalDisplayService(final Cat cat, final Dog dog, final Monkey monkey)
	{
		animals.put(cat.kinds_id(), cat);
		animals.put(dog.kinds_id(), dog);
		animals.put(monkey.kinds_id(), monkey);
	}

	@Override
	public void execute(final Model model)
	{
		final AnimalForm form              = (AnimalForm) model.getAttribute("form");
		final Animal     selected_animal   = AnimalCommonService.getSelectedAnimal(form, this.animals);
		final String     animal_kinds_name = selected_animal == null ? "" : selected_animal.name();

		model.addAttribute("test", animal_kinds_name + "表示");

		final BindingResult bindingResult = new BeanPropertyBindingResult(form, "form");

		if (!validation(model, form, bindingResult))
		{
			return;
		}


		final Optional<AnimalKindsEntity> kinds_data = getSelectedKindsData(model, bindingResult, selected_animal);
		if (kinds_data.isEmpty())
		{
			return;
		}

		final List<AnimalNameEntity> name_data = selected_animal.getAnimalNameDataByKindsId();

		form.setKinds_data(kinds_data.get());
		form.setName_data(name_data);
		model.addAttribute("form", form);
	}

	private Optional<AnimalKindsEntity> getSelectedKindsData(
			final Model model, final BindingResult bindingResult, final Animal selected_animal
			)
	{
		final Optional<AnimalKindsEntity> kinds_data = selected_animal.getAnimalKindsDataByPrimaryKey();
		if (kinds_data.isEmpty())
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

}
