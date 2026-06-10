package tfc.project.ultimateComboRoutes.model.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tfc.project.ultimateComboRoutes.model.entities.Administrator;
import tfc.project.ultimateComboRoutes.model.entities.AdministratorDao;
import tfc.project.ultimateComboRoutes.model.entities.SmashCharacter;
import tfc.project.ultimateComboRoutes.model.entities.SmashCharacterDao;
import tfc.project.ultimateComboRoutes.model.exceptions.DuplicateInstanceException;
import tfc.project.ultimateComboRoutes.model.exceptions.InstanceNotFoundException;

@Service
@Transactional
public class SmashCharacterServiceImpl implements SmashCharacterService {

	@Autowired
	private SmashCharacterDao smashCharacterDao;

	@Autowired
	private AdministratorDao adminDao;

	@Override
	@Transactional
	public SmashCharacter uploadSmashCharacter(String name, String description, int weight, String gravity,
			String render, Long adminId) throws InstanceNotFoundException, DuplicateInstanceException {

		Optional<Administrator> admin = adminDao.findById(adminId);

		if (!admin.isPresent()) {

			throw new InstanceNotFoundException("El administrador no existe", adminId);
		}

		if (smashCharacterDao.existsByName(name)) {

			throw new DuplicateInstanceException("El personaje ya existe", name);
		}

		SmashCharacter smashCharacter = new SmashCharacter(name, description, weight, gravity, render, admin.get());
		smashCharacterDao.save(smashCharacter);
		return smashCharacter;
	}

	@Override
	@Transactional(readOnly = true)
	public SmashCharacter showSmashCharacterDetails(Long characterId) throws InstanceNotFoundException {

		Optional<SmashCharacter> smashCharacter = smashCharacterDao.findById(characterId);

		if (!smashCharacter.isPresent()) {

			throw new InstanceNotFoundException("El personaje no existe", characterId);
		}

		return smashCharacter.get();
	}

	@Override
	@Transactional
	public SmashCharacter updateSmashCharacterData(Long characterId, String name, String description, int weight,
			String gravity, String render) throws InstanceNotFoundException, DuplicateInstanceException {

		Optional<SmashCharacter> smashCharacter = smashCharacterDao.findById(characterId);

		if (!smashCharacter.isPresent()) {

			throw new InstanceNotFoundException("El personaje no existe", characterId);
		}

		if (smashCharacterDao.existsByName(name) && (!smashCharacter.get().getName().equals(name))) {

			throw new DuplicateInstanceException("El personaje ya existe", name);
		}

		smashCharacter.get().setName(name);
		smashCharacter.get().setDescription(description);
		smashCharacter.get().setWeight(weight);
		smashCharacter.get().setGravity(gravity);
		smashCharacter.get().setRender(render);

		return smashCharacter.get();
	}

	@Override
	@Transactional(readOnly = true)
	public ArrayList<SmashCharacter> showAllSmashCharacters() {

		return smashCharacterDao.findAllBy();
	}

	@Override
	@Transactional(readOnly = true)
	public ArrayList<SmashCharacter> filterSmashCharactersByName(String characterName) {

		return smashCharacterDao.findByName(characterName);
	}

}
