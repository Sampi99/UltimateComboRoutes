package tfc.project.ultimateComboRoutes.rest.dtos;

import java.util.List;
import java.util.stream.Collectors;

import tfc.project.ultimateComboRoutes.model.entities.SmashCharacter;

public class SmashCharacterConversor {

	public static final SmashCharacterDto toSmashCharacterDto(SmashCharacterDto dto) {

		return new SmashCharacterDto(dto.getId(), dto.getName(), dto.getDescription(), dto.getWeight(),
				dto.getGravity(), dto.getRender());
	}

	public static final SmashCharacterListDto toSmashCharacterListDto(SmashCharacter smashCharacter) {

		return new SmashCharacterListDto(smashCharacter.getRender(), smashCharacter.getName());
	}

	public static final List<SmashCharacterListDto> toSmashCharacterListDtos(List<SmashCharacter> smashCharacters) {

		return smashCharacters.stream().map(sc -> toSmashCharacterListDto(sc)).collect(Collectors.toList());
	}

}
