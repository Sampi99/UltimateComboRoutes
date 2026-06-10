package tfc.project.ultimateComboRoutes.model.services;

import java.util.ArrayList;

import tfc.project.ultimateComboRoutes.model.entities.Combo;
import tfc.project.ultimateComboRoutes.model.exceptions.InstanceNotFoundException;

public interface ComboService {

	Combo addCombo(Long adminId, Long characterId, String secuence, String difficulty, int totalDamage, String demo)
			throws InstanceNotFoundException;

	Combo editCombo(Long comboId, String secuence, String difficulty, int totalDamage, String demo)
			throws InstanceNotFoundException;

	ArrayList<Combo> showCharacterCombos(Long characterId) throws InstanceNotFoundException;

	ArrayList<Combo> showCharacterCombosByDifficulty(Long characterId, String difficulty)
			throws InstanceNotFoundException;
}
