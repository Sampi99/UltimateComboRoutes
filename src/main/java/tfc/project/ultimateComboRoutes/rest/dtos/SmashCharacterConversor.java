package tfc.project.ultimateComboRoutes.rest.dtos;

import java.util.List;
import java.util.stream.Collectors;

import tfc.project.ultimateComboRoutes.model.entities.SmashCharacter;

public class SmashCharacterConversor {

	public static final SmashCharacterDto toSmashCharacterDto(SmashCharacter character) {

		return new SmashCharacterDto(character.getId(), character.getName(), character.getDescription(),
				character.getWeight(), character.getGravity(), character.getRender());
	}

	public static final SmashCharacterListDto toSmashCharacterListDto(SmashCharacter smashCharacter) {

		return new SmashCharacterListDto(smashCharacter.getRender(), smashCharacter.getName());
	}

	public static final List<SmashCharacterListDto> toSmashCharacterListDtos(List<SmashCharacter> smashCharacters) {

		return smashCharacters.stream().map(sc -> toSmashCharacterListDto(sc)).collect(Collectors.toList());
	}

}
