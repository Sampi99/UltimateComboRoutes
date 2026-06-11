package tfc.project.ultimateComboRoutes.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tfc.project.ultimateComboRoutes.model.entities.SmashCharacter;
import tfc.project.ultimateComboRoutes.model.exceptions.DuplicateInstanceException;
import tfc.project.ultimateComboRoutes.model.exceptions.InstanceNotFoundException;
import tfc.project.ultimateComboRoutes.model.services.SmashCharacterService;
import tfc.project.ultimateComboRoutes.rest.dtos.SmashCharacterConversor;
import tfc.project.ultimateComboRoutes.rest.dtos.SmashCharacterDto;
import tfc.project.ultimateComboRoutes.rest.dtos.SmashCharacterListDto;

@RestController
@RequestMapping("/api/smashCharacters")
public class SmashCharacterController {

	@Autowired
	private String messageSource;

	@Autowired
	private SmashCharacterService characterService;

	@PostMapping("/uploadCharacter")
	public SmashCharacterDto uploadSmashCharacter(@RequestAttribute Long adminId,
			@Validated @RequestBody SmashCharacterDto dto)
			throws InstanceNotFoundException, DuplicateInstanceException {

		SmashCharacter smashCharacter = characterService.uploadSmashCharacter(dto.getName(), dto.getDescription(),
				dto.getWeight(), dto.getGravity(), dto.getRender(), adminId);

		return SmashCharacterConversor.toSmashCharacterDto(smashCharacter);
	}

	@PutMapping("/{id}/updateCharacter")
	public SmashCharacterDto updateSmashCharacterData(@RequestAttribute Long adminId, @PathVariable Long id,
			@Validated @RequestBody SmashCharacterDto dto)
			throws InstanceNotFoundException, DuplicateInstanceException {

		SmashCharacter smashCharacter = characterService.updateSmashCharacterData(id, dto.getName(),
				dto.getDescription(), dto.getWeight(), dto.getGravity(), dto.getRender());

		return SmashCharacterConversor.toSmashCharacterDto(smashCharacter);
	}

	@GetMapping("/{id}")
	public SmashCharacterDto showSmashCharacterDetails(@PathVariable Long id) throws InstanceNotFoundException {

		return SmashCharacterConversor.toSmashCharacterDto(characterService.showSmashCharacterDetails(id));
	}

	@GetMapping("")
	public List<SmashCharacterListDto> showAllSmashCharacters() {

		return SmashCharacterConversor.toSmashCharacterListDtos(characterService.showAllSmashCharacters());
	}

	@GetMapping("/filterCharacters")
	public List<SmashCharacterListDto> filterSmashCharactersByName(
			@RequestParam(value = "name", required = false) String name) {

		return SmashCharacterConversor.toSmashCharacterListDtos(characterService.filterSmashCharactersByName(name));
	}

}
