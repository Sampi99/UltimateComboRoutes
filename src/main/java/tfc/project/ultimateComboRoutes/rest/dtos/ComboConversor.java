package tfc.project.ultimateComboRoutes.rest.dtos;

import java.util.List;
import java.util.stream.Collectors;

import tfc.project.ultimateComboRoutes.model.entities.Combo;

public class ComboConversor {

	public static final ComboDto toComboDto(Combo combo) {

		return new ComboDto(combo.getId(), combo.getSecuence(), combo.getDifficulty(), combo.getTotalDamage(),
				combo.getDemo());
	}

	public static final List<ComboDto> toComboDtos(List<Combo> combos) {

		return combos.stream().map(sc -> toComboDto(sc)).collect(Collectors.toList());
	}
}
