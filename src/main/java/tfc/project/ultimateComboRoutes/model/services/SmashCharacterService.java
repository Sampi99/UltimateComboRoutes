package tfc.project.ultimateComboRoutes.model.services;

import java.util.ArrayList;

import tfc.project.ultimateComboRoutes.model.entities.SmashCharacter;
import tfc.project.ultimateComboRoutes.model.exceptions.DuplicateInstanceException;
import tfc.project.ultimateComboRoutes.model.exceptions.InstanceNotFoundException;

public interface SmashCharacterService {

	SmashCharacter uploadSmashCharacter(String name, String description, int weight, String gravity, String render,
			Long adminId) throws InstanceNotFoundException, DuplicateInstanceException;

	SmashCharacter showSmashCharacterDetails(Long characterId) throws InstanceNotFoundException;

	SmashCharacter updateSmashCharacterData(Long characterId, String name, String description, int weight,
			String gravity, String render) throws InstanceNotFoundException, DuplicateInstanceException;

	ArrayList<SmashCharacter> showAllSmashCharacters();

	ArrayList<SmashCharacter> filterSmashCharactersByName(String characterName);

}
