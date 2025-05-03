package com.example.demo.controller.animal;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
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
import com.example.demo.entity.AnimalKindsEntity;
import com.example.demo.form.AnimalForm;
import com.example.demo.service.animalinterface.AnimalClearService;
import com.example.demo.service.animalinterface.AnimalCommonService;
import com.example.demo.service.animalinterface.AnimalDisplayService;

import io.micrometer.common.util.StringUtils;

/** interfaceを利用した分岐コントローラー */
@Controller
@RequestMapping("/animal/interface")
public class InterfaceAnimalController
{
	/** 処理を実装するサービスクラスを格納 */
	private final Map<ACTION, IAction> action;
	/** 共通処理クラス */
	private final AnimalCommonService  animalCommonService;

	public InterfaceAnimalController(
			final AnimalCommonService animalCommonService, final AnimalDisplayService displayService,
			final AnimalClearService clearService
			)
	{
		this.animalCommonService = animalCommonService;

		this.action = new HashMap<>();
		this.action.put(ACTION.DISPLAY, displayService);
		this.action.put(ACTION.CLEAR, clearService);
	}

	// ==== ModelAttribute ====

	@ModelAttribute("animals")
	public Map<String, String> animals()
	{
		final List<AnimalKindsEntity> animal_kinds = animalCommonService.getAllAnimalKindData();

		final Map<String, String> animals = new TreeMap<String, String>();
		for (final AnimalKindsEntity animal_kind : animal_kinds)
		{
			animals.put(animal_kind.getId().toString(), animal_kind.getName());
		}

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

		// 初期表示ボタン
		if (StringUtils.isBlank(action))
		{
			return "redirect:/animal/interface/index";
		}

		// 押されたボタンにより、ACTIONを抽出
		final ACTION executeAction = ACTION.getACTION(action).get();
		// 抽出したACTIONを元にサービスクラスを取り出し、execute実行
		this.action.get(executeAction).execute(model);

		return "animal";
	}
}
