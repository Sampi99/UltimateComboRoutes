package tfc.project.ultimateComboRoutes.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tfc.project.ultimateComboRoutes.model.entities.Combo;
import tfc.project.ultimateComboRoutes.model.exceptions.InstanceNotFoundException;
import tfc.project.ultimateComboRoutes.model.services.ComboService;
import tfc.project.ultimateComboRoutes.rest.dtos.ComboConversor;
import tfc.project.ultimateComboRoutes.rest.dtos.ComboDto;

@RestController
@RequestMapping("/api/combos")
public class ComboController {

	@Autowired
	private ComboService comboService;

	@PostMapping("/{characterId}/addCombo")
	public ComboDto addCombo(@RequestAttribute Long adminId, @PathVariable Long characterId, ComboDto dto)
			throws InstanceNotFoundException {

		Combo combo = comboService.addCombo(adminId, characterId, dto.getSecuence(), dto.getDifficulty(),
				dto.getTotalDamage(), dto.getDemo());

		return ComboConversor.toComboDto(combo);
	}

	@PutMapping("/{id}/editCombo")
	public ComboDto editCombo(@RequestAttribute Long adminId, @PathVariable Long id, ComboDto dto)
			throws InstanceNotFoundException {

		Combo combo = comboService.editCombo(id, dto.getSecuence(), dto.getDifficulty(), dto.getTotalDamage(),
				dto.getDemo());

		return ComboConversor.toComboDto(combo);
	}

	@GetMapping("/{characterId}")
	public List<ComboDto> showCharacterCombos(@PathVariable Long characterId) throws InstanceNotFoundException {

		return ComboConversor.toComboDtos(comboService.showCharacterCombos(characterId));
	}

	@GetMapping("/{characterId}/filterByDifficulty")
	public List<ComboDto> showCharacterCombosByDifficulty(@PathVariable Long characterId,
			@RequestParam(value = "difficulty", required = false) String difficulty) throws InstanceNotFoundException {

		return ComboConversor.toComboDtos(comboService.showCharacterCombosByDifficulty(characterId, difficulty));
	}

}
