package tfc.project.ultimateComboRoutes.model.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import tfc.project.ultimateComboRoutes.model.entities.Administrator;
import tfc.project.ultimateComboRoutes.model.entities.SmashCharacter;
import tfc.project.ultimateComboRoutes.model.entities.SmashCharacterDao;
import tfc.project.ultimateComboRoutes.model.exceptions.DuplicateInstanceException;
import tfc.project.ultimateComboRoutes.model.exceptions.InstanceNotFoundException;
import tfc.project.ultimateComboRoutes.model.exceptions.WrongCredentialsException;

@SpringBootTest

@Transactional
public class SmashCharacterServiceTests {

	@Autowired
	private SmashCharacterService characterService;

	@Autowired
	private AdministratorService adminService;

	@Autowired
	private SmashCharacterDao characterDao;

	private Administrator signUpAdmin(String username)
			throws DuplicateInstanceException, InstanceNotFoundException, WrongCredentialsException {

		adminService.signUp(username, "pass", "nombre", "apellido", "correo@gmail.com");

		return adminService.login(username, "pass");
	}

	@Test
	public void uploadCharacterTest()
			throws DuplicateInstanceException, InstanceNotFoundException, WrongCredentialsException {

		Administrator admin = signUpAdmin("Sampi");

		SmashCharacter character = characterService.uploadSmashCharacter("Mario", "Descripción breve", 83, "Peso medio",
				"https://images.wikidexcdn.net/mwuploads/esssbwiki/thumb/4/44/latest/20180613001020/Mario_SSBU.png/800px-Mario_SSBU.png",
				admin.getId());

		assertEquals(character, characterDao.findById(character.getId()).get());
	}

	@Test
	public void uploadAlreadyRegisteredCharacterTest()
			throws InstanceNotFoundException, DuplicateInstanceException, WrongCredentialsException {

		Administrator admin = signUpAdmin("Sampi");

		characterService.uploadSmashCharacter("Mario", "Descripción breve", 83, "Peso medio",
				"https://images.wikidexcdn.net/mwuploads/esssbwiki/thumb/4/44/latest/20180613001020/Mario_SSBU.png/800px-Mario_SSBU.png",
				admin.getId());

		assertThrows(DuplicateInstanceException.class, () -> characterService.uploadSmashCharacter("Mario",
				"Descripción breve", 83, "Peso medio",
				"https://images.wikidexcdn.net/mwuploads/esssbwiki/thumb/4/44/latest/20180613001020/Mario_SSBU.png/800px-Mario_SSBU.png",
				admin.getId()));
	}

	@Test
	public void uploadCharacterButAdminDoesNotExist() {

		assertThrows(InstanceNotFoundException.class, () -> characterService.uploadSmashCharacter("Mario",
				"Descripción breve", 83, "Peso medio",
				"https://images.wikidexcdn.net/mwuploads/esssbwiki/thumb/4/44/latest/20180613001020/Mario_SSBU.png/800px-Mario_SSBU.png",
				Long.valueOf(42)));
	}

	@Test
	public void showCharacterDetailsTest()
			throws InstanceNotFoundException, DuplicateInstanceException, WrongCredentialsException {

		Administrator admin = signUpAdmin("Sampi");

		SmashCharacter character = characterService.uploadSmashCharacter("Mario", "Descripción breve", 83, "Peso medio",
				"https://images.wikidexcdn.net/mwuploads/esssbwiki/thumb/4/44/latest/20180613001020/Mario_SSBU.png/800px-Mario_SSBU.png",
				admin.getId());

		SmashCharacter uploadedCharacter = characterService.showSmashCharacterDetails(character.getId());

		assertEquals(character, uploadedCharacter);
	}

	@Test
	public void showNonExistentCharacterDetailsTest() {

		assertThrows(InstanceNotFoundException.class,
				() -> characterService.showSmashCharacterDetails(Long.valueOf(41)));
	}

	@Test
	public void updateCharacterDataTest()
			throws DuplicateInstanceException, InstanceNotFoundException, WrongCredentialsException {

		Administrator admin = signUpAdmin("Sampi");

		SmashCharacter character = characterService.uploadSmashCharacter("Mario", "Descripción breve", 83, "Peso medio",
				"https://images.wikidexcdn.net/mwuploads/esssbwiki/thumb/4/44/latest/20180613001020/Mario_SSBU.png/800px-Mario_SSBU.png",
				admin.getId());

		SmashCharacter updatedCharacter = characterService.updateSmashCharacterData(character.getId(), "Luigi",
				"Descripción", 82, "Pesado", "imagen");

		assertEquals(updatedCharacter.getName(), "Luigi");
		assertEquals(updatedCharacter.getDescription(), "Descripción");
		assertEquals(updatedCharacter.getWeight(), 82);
		assertEquals(updatedCharacter.getGravity(), "Pesado");
		assertEquals(updatedCharacter.getRender(), "imagen");
	}

	@Test
	public void updateCharacterDataWithUsedNameTest()
			throws DuplicateInstanceException, InstanceNotFoundException, WrongCredentialsException {

		Administrator admin = signUpAdmin("Sampi");

		characterService.uploadSmashCharacter("Luigi", "Descripción", 82, "Pesado", "imagen", admin.getId());

		SmashCharacter characterToUpdate = characterService.uploadSmashCharacter("Mario", "Descripción", 82, "Pesado",
				"imagen", admin.getId());

		assertThrows(DuplicateInstanceException.class,
				() -> characterService.updateSmashCharacterData(characterToUpdate.getId(), "Luigi", "Nueva descripción",
						0, "Pesado", "nueva imagen"));
	}

	@Test
	public void showAllSmashCharactersTest()
			throws DuplicateInstanceException, InstanceNotFoundException, WrongCredentialsException {

		Administrator admin = signUpAdmin("Sampi");
		ArrayList<SmashCharacter> list = new ArrayList<SmashCharacter>();

		SmashCharacter firstCharacter = characterService.uploadSmashCharacter("Mario", "Descripción", 82, "Peso medio",
				"imagen", admin.getId());
		SmashCharacter secondCharacter = characterService.uploadSmashCharacter("Luigi", "Descripción", 82, "Peso medio",
				"imagen", admin.getId());
		SmashCharacter thirdCharacter = characterService.uploadSmashCharacter("Peach", "Descripción", 82, "Ligero",
				"imagen", admin.getId());
		SmashCharacter fourthCharacter = characterService.uploadSmashCharacter("Bowser", "Descripción", 82, "Pesado",
				"imagen", admin.getId());

		list = characterService.showAllSmashCharacters();

		assertEquals(list.get(0), firstCharacter);
		assertEquals(list.get(1), secondCharacter);
		assertEquals(list.get(2), thirdCharacter);
		assertEquals(list.get(3), fourthCharacter);
	}

	@Test
	public void filterSmashCharactersByNameTest()
			throws DuplicateInstanceException, InstanceNotFoundException, WrongCredentialsException {

		Administrator admin = signUpAdmin("Sampi");
		ArrayList<SmashCharacter> list = new ArrayList<SmashCharacter>();

		SmashCharacter firstCharacter = characterService.uploadSmashCharacter("Mario", "Descripción", 82, "Peso medio",
				"imagen", admin.getId());
		SmashCharacter secondCharacter = characterService.uploadSmashCharacter("Luigi", "Descripción", 82, "Peso medio",
				"imagen", admin.getId());
		SmashCharacter thirdCharacter = characterService.uploadSmashCharacter("Peach", "Descripción", 82, "Ligero",
				"imagen", admin.getId());
		SmashCharacter fourthCharacter = characterService.uploadSmashCharacter("Bowser", "Descripción", 82, "Pesado",
				"imagen", admin.getId());
		SmashCharacter fifthCharacter = characterService.uploadSmashCharacter("Dr.Mario", "Descripción", 82, "Pesado",
				"imagen", admin.getId());

		list = characterService.filterSmashCharactersByName("Mar");

		assertEquals(list.get(0), firstCharacter);
		assertEquals(list.get(1), fifthCharacter);

	}
}
