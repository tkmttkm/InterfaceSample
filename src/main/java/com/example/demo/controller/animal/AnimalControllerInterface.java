package com.example.demo.controller.animal;

import java.util.Collections;
import java.util.HashMap;
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
import com.example.demo.common.icommon.IAction;
import com.example.demo.common.icommon.IAction.ACTION;
import com.example.demo.form.AnimalForm;
import com.example.demo.service.animalinterface.AnimalClearService;
import com.example.demo.service.animalinterface.AnimalDisplayService;

import io.micrometer.common.util.StringUtils;

/** interfaceを利用した分岐コントローラー */
@Controller
@RequestMapping("/animal/interface")
public class AnimalControllerInterface
{
	private final Map<ACTION, IAction> action;

	public AnimalControllerInterface(final AnimalDisplayService displayService, final AnimalClearService clearService)
	{
		this.action = new HashMap<>();
		this.action.put(ACTION.DISPLAY, displayService);
		this.action.put(ACTION.CLEAR, clearService);
	}

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
		return ACTION.DISPLAY.getAction();
	}

	@ModelAttribute("clear")
	public String clear()
	{
		return ACTION.CLEAR.getAction();
	}

	@ModelAttribute("action")
	public String action()
	{
		return "/animal/interface/submit";
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

		if (StringUtils.isBlank(action))
		{
			return "redirect:/animal/interface/index";
		}

		final ACTION executeAction = ACTION.getACTION(action).get();
		this.action.get(executeAction).execute(model);

		return "animal";
	}
}
