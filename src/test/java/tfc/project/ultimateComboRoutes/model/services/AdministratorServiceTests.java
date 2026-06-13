package tfc.project.ultimateComboRoutes.model.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import tfc.project.ultimateComboRoutes.model.entities.Administrator;
import tfc.project.ultimateComboRoutes.model.entities.AdministratorDao;
import tfc.project.ultimateComboRoutes.model.exceptions.DuplicateInstanceException;
import tfc.project.ultimateComboRoutes.model.exceptions.InstanceNotFoundException;
import tfc.project.ultimateComboRoutes.model.exceptions.WrongCredentialsException;
import tfc.project.ultimateComboRoutes.model.exceptions.WrongOldPasswordException;

@SpringBootTest

@Transactional
public class AdministratorServiceTests {

	@Autowired
	private AdministratorService adminService;

	@Autowired
	private AdministratorDao dao;

	private Administrator createAdmin(String username) {

		return new Administrator(username, "contraseña", "Jorge", "Sampedro", "correo@gmail.com");
	}

	@Test
	public void signUpTest() throws DuplicateInstanceException {

		Administrator admin = createAdmin("Sampi");
		adminService.signUp(admin);

		assertTrue(dao.existsByUsername("Sampi"));
	}

	@Test
	public void signUpWithUsedUsernameTest() throws DuplicateInstanceException {

		Administrator admin = createAdmin("Sampi");
		adminService.signUp(admin);

		assertThrows(DuplicateInstanceException.class, () -> adminService.signUp(admin));
	}

	@Test
	public void loginTest() throws DuplicateInstanceException, InstanceNotFoundException, WrongCredentialsException {

		Administrator admin = createAdmin("Sampi");
		adminService.signUp(admin);

		Administrator loggedUpAdmin = adminService.login("Sampi", "contraseña");

		assertEquals(loggedUpAdmin, dao.findByUsername("Sampi").get());
	}

	@Test
	public void loginWithWrongCredentialsTest() throws DuplicateInstanceException, InstanceNotFoundException {

		Administrator admin = createAdmin("Sampi");
		adminService.signUp(admin);

		assertThrows(WrongCredentialsException.class, () -> adminService.login("No soy Sampi", "contraseña"));
		assertThrows(WrongCredentialsException.class, () -> adminService.login("Sampi", "contraseñaIncorrecta"));
	}

	@Test
	public void updateProfileTest() throws DuplicateInstanceException, InstanceNotFoundException {

		Administrator admin = createAdmin("Sampi");
		adminService.signUp(admin);

		Administrator adminToUpdate = dao.findByUsername("Sampi").get();

		Administrator updatedAdmin = adminService.updateProfile(adminToUpdate.getId(), "Nuevo Sampi", "Marcos",
				"González", "marcos@gmail.com");

		assertEquals(updatedAdmin.getUsername(), "Nuevo Sampi");
		assertEquals(updatedAdmin.getName(), "Marcos");
		assertEquals(updatedAdmin.getSurname(), "González");
		assertEquals(updatedAdmin.getEmail(), "marcos@gmail.com");
	}

	@Test
	public void updateNonExistentProfileTest() throws DuplicateInstanceException, InstanceNotFoundException {

		assertThrows(InstanceNotFoundException.class, () -> adminService.updateProfile(Long.valueOf(200), "Nuevo Sampi",
				"Marcos", "González", "marcos@gmail.com"));
	}

	@Test
	public void updateProfileWithTakenUsername() throws DuplicateInstanceException {

		Administrator admin1 = createAdmin("Sampi");
		adminService.signUp(admin1);
		Administrator admin2 = createAdmin("Nuevo Sampi");
		adminService.signUp(admin2);

		assertThrows(DuplicateInstanceException.class,
				() -> adminService.updateProfile(dao.findByUsername("Sampi").get().getId(), "Nuevo Sampi", "Marcos",
						"González", "marcos@gmail.com"));

	}

	@Test
	public void changePasswordButAdminDoesNotExistTest() {

		assertThrows(InstanceNotFoundException.class,
				() -> adminService.changePassword(Long.valueOf(20), "pass", "nueva pass"));
	}

	@Test
	public void changePasswordButOldPasswordDoesNotMatch() throws DuplicateInstanceException {

		Administrator admin = createAdmin("Sampi");
		adminService.signUp(admin);

		assertThrows(WrongOldPasswordException.class, () -> adminService
				.changePassword(dao.findByUsername("Sampi").get().getId(), "Contraseña incorrecta", "nueva pass"));
	}
}
