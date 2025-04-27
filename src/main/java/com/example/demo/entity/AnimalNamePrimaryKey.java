package com.example.demo.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public final class AnimalNamePrimaryKey implements Serializable
{
	private Integer id;
	@Column(name = "kinds_id")
	private Integer kindsId;

	@Override
	public boolean equals(final Object o)
	{
		if (this == o)
		{
			return true;
		}

		if (!(o instanceof AnimalNamePrimaryKey))
		{
			return false;
		}

		final AnimalNamePrimaryKey that = (AnimalNamePrimaryKey) o;

		return Objects.equals(id, that.id) &&
				Objects.equals(kindsId, that.kindsId);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(id, kindsId);
	}

}
