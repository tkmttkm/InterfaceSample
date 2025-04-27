package com.example.demo.controller.animal;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.form.AnimalForm;
import com.example.demo.service.animal.AnimalService;

import lombok.RequiredArgsConstructor;

/** switch文を使用した分岐コントローラー */
@Controller
@RequiredArgsConstructor
@RequestMapping("/animal")
public final class AnimalController
{

	private final AnimalService animalService;

	// ==== ModelAttribute ====

	@ModelAttribute("animals")
	public Map<String, String> animals()
	{
		final Map<String, String> animals = new TreeMap<String, String>();
		animals.put("1", "ねこ");
		animals.put("2", "いぬ");
		animals.put("3", "さる");
		return animals;
	}

	@ModelAttribute("form")
	public AnimalForm form()
	{
		return new AnimalForm("", null, Collections.emptyList());
	}

	@ModelAttribute("display")
	public String display()
	{
		return "display";
	}

	@ModelAttribute("clear")
	public String clear()
	{
		return "clear";
	}

	@ModelAttribute("action")
	public String action()
	{
		return "/animal/submit";
	}

	// ==========================


	/** 初期表示 */
	@GetMapping("/index")
	public String index(final Model model)
	{
		model.addAttribute("test", "初期表示");
		return "animal";
	}

	@PostMapping("/submit")
	public String execute(
			@RequestParam final String action,
			@ModelAttribute("form") final AnimalForm animalForm,
			final BindingResult error,
			final Model model
			)
	{
		model.addAttribute("form", animalForm);

		switch (action)
		{
			case "display":
				animalService.display(model);
				break;
			case "clear":
				animalService.clear(model);
				break;
			default:
				return "redirect:/animal/index";
		}

		return "animal";
	}
}