package com.example.demo.service.animalinterface;

import java.util.Collections;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.demo.common.icommon.IAction;
import com.example.demo.form.AnimalForm;

/** 画面クリアボタン押下時の処理クラス */
@Service
public final class AnimalClearService implements IAction
{
	@Override
	public void execute(final Model model)
	{
		model.addAttribute("test", "画面クリア");

		final AnimalForm form = (AnimalForm) model.getAttribute("form");

		form.setKinds_data(null);
		form.setName_data(Collections.emptyList());
	}
}
