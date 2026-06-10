package tfc.project.ultimateComboRoutes.model.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import tfc.project.ultimateComboRoutes.model.entities.Administrator;
import tfc.project.ultimateComboRoutes.model.entities.Combo;
import tfc.project.ultimateComboRoutes.model.entities.ComboDao;
import tfc.project.ultimateComboRoutes.model.entities.SmashCharacter;
import tfc.project.ultimateComboRoutes.model.exceptions.DuplicateInstanceException;
import tfc.project.ultimateComboRoutes.model.exceptions.InstanceNotFoundException;
import tfc.project.ultimateComboRoutes.model.exceptions.WrongCredentialsException;

@SpringBootTest

@Transactional
public class ComboServiceTests {

	@Autowired
	private ComboService comboService;

	@Autowired
	private SmashCharacterService characterService;

	@Autowired
	private AdministratorService adminService;

	@Autowired
	private ComboDao comboDao;

	private Administrator signUpAdmin(String username)
			throws DuplicateInstanceException, InstanceNotFoundException, WrongCredentialsException {

		adminService.signUp(username, "pass", "nombre", "apellido", "correo@gmail.com");

		return adminService.login(username, "pass");
	}

	private SmashCharacter registerCharacter(String name, Long adminId)
			throws InstanceNotFoundException, DuplicateInstanceException {

		return characterService.uploadSmashCharacter(name, "Descripción", 83, "Peso medio", "imagen", adminId);

	}

	@Test
	public void addComboTest() throws DuplicateInstanceException, InstanceNotFoundException, WrongCredentialsException {

		Administrator admin = signUpAdmin("Sampi");
		SmashCharacter character = registerCharacter("Mario", admin.getId());

		Combo combo = comboService.addCombo(admin.getId(), character.getId(), "Nair + DA", "Fácil", 40, "demo");

		assertEquals(combo, comboDao.findById(combo.getId()).get());
	}

	@Test
	public void addComboButAdminOrCharacterDoNotExistTest()
			throws DuplicateInstanceException, InstanceNotFoundException, WrongCredentialsException {

		Administrator admin = signUpAdmin("Sampi");
		SmashCharacter character = registerCharacter("Mario", admin.getId());

		assertThrows(InstanceNotFoundException.class,
				() -> comboService.addCombo(admin.getId(), Long.valueOf(50), "Nair + DA", "Fácil", 40, "demo"));

		assertThrows(InstanceNotFoundException.class,
				() -> comboService.addCombo(Long.valueOf(50), character.getId(), "Nair + DA", "Fácil", 40, "demo"));
	}

	@Test
	public void editComboTest()
			throws DuplicateInstanceException, InstanceNotFoundException, WrongCredentialsException {

		Administrator admin = signUpAdmin("Sampi");
		SmashCharacter character = registerCharacter("Mario", admin.getId());

		Combo combo = comboService.addCombo(admin.getId(), character.getId(), "Nair + DA", "Fácil", 40, "demo");

		Combo editedCombo = comboService.editCombo(combo.getId(), "Nueva secuencia", "Intermedia", 32, "Nueva demo");

		assertEquals(editedCombo.getSecuence(), "Nueva secuencia");
		assertEquals(editedCombo.getDifficulty(), "Intermedia");
		assertEquals(editedCombo.getTotalDamage(), 32);
		assertEquals(editedCombo.getDemo(), "Nueva demo");
	}

	@Test
	public void editNonExistentComboTest()
			throws DuplicateInstanceException, InstanceNotFoundException, WrongCredentialsException {

		assertThrows(InstanceNotFoundException.class,
				() -> comboService.editCombo(Long.valueOf(0), "Nueva secuencia", "Intermedia", 32, "Nueva demo"));
	}

	@Test
	public void showCharacterCombosTest()
			throws DuplicateInstanceException, InstanceNotFoundException, WrongCredentialsException {

		Administrator admin = signUpAdmin("Sampi");
		SmashCharacter character = registerCharacter("Mario", admin.getId());
		ArrayList<Combo> list = new ArrayList<Combo>();

		Combo combo1 = comboService.addCombo(admin.getId(), character.getId(), "Nair + DA", "Fácil", 40, "demo");
		Combo combo2 = comboService.addCombo(admin.getId(), character.getId(), "Nair + FSmash", "Fácil", 40, "demo");
		Combo combo3 = comboService.addCombo(admin.getId(), character.getId(), "Bair + FSmash", "Fácil", 40, "demo");
		Combo combo4 = comboService.addCombo(admin.getId(), character.getId(), "Uair + FSmash", "Fácil", 40, "demo");

		list = comboService.showCharacterCombos(character.getId());

		assertEquals(list.get(0), combo1);
		assertEquals(list.get(1), combo2);
		assertEquals(list.get(2), combo3);
		assertEquals(list.get(3), combo4);

	}

	@Test
	public void showNonExistentCharacterCombosTest() {

		assertThrows(InstanceNotFoundException.class, () -> comboService.showCharacterCombos(Long.valueOf(31)));
	}

	@Test
	public void filterCombosByDifficultyTest()
			throws DuplicateInstanceException, InstanceNotFoundException, WrongCredentialsException {

		Administrator admin = signUpAdmin("Sampi");
		SmashCharacter character = registerCharacter("Mario", admin.getId());
		ArrayList<Combo> list = new ArrayList<Combo>();

		comboService.addCombo(admin.getId(), character.getId(), "Nair + DA", "Fácil", 40, "demo");
		Combo combo2 = comboService.addCombo(admin.getId(), character.getId(), "Nair + FSmash", "Difícil", 40, "demo");
		comboService.addCombo(admin.getId(), character.getId(), "Bair + FSmash", "Intermedio", 40, "demo");
		Combo combo4 = comboService.addCombo(admin.getId(), character.getId(), "Uair + FSmash", "Difícil", 40, "demo");

		list = comboService.showCharacterCombosByDifficulty(character.getId(), "Difícil");

		assertEquals(list.get(0), combo2);
		assertEquals(list.get(1), combo4);
	}

	@Test
	public void showNonExistentCharacterCombosByDifficultyTest() {

		assertThrows(InstanceNotFoundException.class,
				() -> comboService.showCharacterCombosByDifficulty(Long.valueOf(31), "Difícil"));
	}
}
