package com.example.demo.service.animalinterface;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.demo.common.icommon.IAction;
import com.example.demo.form.AnimalForm;
import com.example.demo.service.animalinterface.kinds.Cat;
import com.example.demo.service.animalinterface.kinds.Dog;
import com.example.demo.service.animalinterface.kinds.Monkey;

/** 画面クリアボタン押下時の処理クラス */
@Service
public final class AnimalClearService implements IAction
{
	private final Map<Integer, Animal> animals = new HashMap<Integer, Animal>();

	public AnimalClearService(final Cat cat, final Dog dog, final Monkey monkey)
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

		model.addAttribute("test", animal_kinds_name + "画面クリア");
		form.setKinds_data(null);
		form.setName_data(Collections.emptyList());
	}
}
