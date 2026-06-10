package tfc.project.ultimateComboRoutes.model.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tfc.project.ultimateComboRoutes.model.entities.Administrator;
import tfc.project.ultimateComboRoutes.model.entities.AdministratorDao;
import tfc.project.ultimateComboRoutes.model.entities.Character;
import tfc.project.ultimateComboRoutes.model.entities.CharacterDao;
import tfc.project.ultimateComboRoutes.model.exceptions.DuplicateInstanceException;
import tfc.project.ultimateComboRoutes.model.exceptions.InstanceNotFoundException;

@Service
@Transactional
public class CharacterServiceImpl implements CharacterService {

	@Autowired
	private CharacterDao characterDao;

	@Autowired
	private AdministratorDao adminDao;

	@Override
	@Transactional
	public Character uploadCharacter(String name, String description, int weight, String gravity, String render,
			Long adminId) throws InstanceNotFoundException, DuplicateInstanceException {

		Optional<Administrator> admin = adminDao.findById(adminId);

		if (!admin.isPresent()) {

			throw new InstanceNotFoundException("El administrador no existe", adminId);
		}

		if (characterDao.existsByName(name)) {

			throw new DuplicateInstanceException("El personaje ya existe", name);
		}

		Character smashCharacter = new Character(name, description, weight, gravity, render, admin.get());
		characterDao.save(smashCharacter);
		return smashCharacter;
	}

	@Override
	@Transactional(readOnly = true)
	public Character showCharacterDetails(Long characterId) throws InstanceNotFoundException {

		Optional<Character> smashCharacter = characterDao.findById(characterId);

		if (!smashCharacter.isPresent()) {

			throw new InstanceNotFoundException("El personaje no existe", characterId);
		}

		return smashCharacter.get();
	}

	@Override
	@Transactional
	public Character updateCharacterData(Long characterId, String name, String description, int weight, String gravity,
			String render) throws InstanceNotFoundException, DuplicateInstanceException {

		Optional<Character> smashCharacter = characterDao.findById(characterId);

		if (!smashCharacter.isPresent()) {

			throw new InstanceNotFoundException("El personaje no existe", characterId);
		}

		if (characterDao.existsByName(name) && (!smashCharacter.get().getName().equals(name))) {

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
	public ArrayList<Character> showAllCharacters() {

		return characterDao.findAllBy();
	}

	@Override
	@Transactional(readOnly = true)
	public ArrayList<Character> filterCharactersByName(String characterName) {

		return characterDao.findByName(characterName);
	}

}
