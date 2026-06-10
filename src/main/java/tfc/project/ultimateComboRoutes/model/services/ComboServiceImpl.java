package tfc.project.ultimateComboRoutes.model.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tfc.project.ultimateComboRoutes.model.entities.Administrator;
import tfc.project.ultimateComboRoutes.model.entities.AdministratorDao;
import tfc.project.ultimateComboRoutes.model.entities.Combo;
import tfc.project.ultimateComboRoutes.model.entities.ComboDao;
import tfc.project.ultimateComboRoutes.model.entities.SmashCharacter;
import tfc.project.ultimateComboRoutes.model.entities.SmashCharacterDao;
import tfc.project.ultimateComboRoutes.model.exceptions.InstanceNotFoundException;

@Service
@Transactional
public class ComboServiceImpl implements ComboService {

	@Autowired
	private ComboDao comboDao;

	@Autowired
	private SmashCharacterDao smashCharacterDao;

	@Autowired
	private AdministratorDao adminDao;

	@Override
	@Transactional
	public Combo addCombo(Long adminId, Long characterId, String secuence, String difficulty, int totalDamage,
			String demo) throws InstanceNotFoundException {

		Optional<Administrator> admin = adminDao.findById(adminId);
		Optional<SmashCharacter> smashCharacter = smashCharacterDao.findById(characterId);

		if (!admin.isPresent() || !smashCharacter.isPresent()) {

			throw new InstanceNotFoundException("El administrador o el personaje no existe", adminId);
		}

		Combo combo = new Combo(secuence, difficulty, totalDamage, demo, admin.get(), smashCharacter.get());
		comboDao.save(combo);

		return combo;

	}

	@Override
	@Transactional
	public Combo editCombo(Long comboId, String secuence, String difficulty, int totalDamage, String demo)
			throws InstanceNotFoundException {

		Optional<Combo> combo = comboDao.findById(comboId);

		if (!combo.isPresent()) {

			throw new InstanceNotFoundException("El combo no existe", comboId);
		}

		combo.get().setSecuence(secuence);
		combo.get().setDifficulty(difficulty);
		combo.get().setTotalDamage(totalDamage);
		combo.get().setDemo(demo);
		return combo.get();

	}

	@Override
	@Transactional(readOnly = true)
	public ArrayList<Combo> showCharacterCombos(Long characterId) throws InstanceNotFoundException {

		return comboDao.findByCharacterId(characterId);
	}

	@Override
	@Transactional(readOnly = true)
	public ArrayList<Combo> showCharacterCombosByDifficulty(Long characterId, String difficulty)
			throws InstanceNotFoundException {

		return comboDao.findByCharacterIdAndDifficulty(characterId, difficulty);
	}
}
