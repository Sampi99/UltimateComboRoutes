package tfc.project.ultimateComboRoutes.model.services;

import java.util.ArrayList;

import tfc.project.ultimateComboRoutes.model.entities.Character;
import tfc.project.ultimateComboRoutes.model.exceptions.DuplicateInstanceException;
import tfc.project.ultimateComboRoutes.model.exceptions.InstanceNotFoundException;

public interface CharacterService {

	Character uploadCharacter(String name, String description, int weight, String gravity, String render, Long adminId)
			throws InstanceNotFoundException, DuplicateInstanceException;

	Character showCharacterDetails(Long characterId) throws InstanceNotFoundException;

	Character updateCharacterData(Long characterId, String name, String description, int weight, String gravity,
			String render) throws InstanceNotFoundException, DuplicateInstanceException;

	ArrayList<Character> showAllCharacters();

	ArrayList<Character> filterCharactersByName(String characterName);

}
