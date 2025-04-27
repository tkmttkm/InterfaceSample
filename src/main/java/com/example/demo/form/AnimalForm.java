package com.example.demo.form;

import java.util.List;

import org.thymeleaf.util.StringUtils;

import com.example.demo.entity.AnimalKindsEntity;
import com.example.demo.entity.AnimalNameEntity;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public final class AnimalForm
{
	/** 選択されたradioボタン(数値) */
	@NotBlank(message = "{form.animal.notblank}")
	private String animal;

	private AnimalKindsEntity kinds_data;

	private List<AnimalNameEntity> name_data;

	public int getAnimalAsInt()
	{
		if (StringUtils.isEmptyOrWhitespace(animal))
		{
			return 0;
		}

		return Integer.parseInt(this.animal);
	}

}
