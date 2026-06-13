package tfc.project.ultimateComboRoutes.model.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tfc.project.ultimateComboRoutes.model.entities.Administrator;
import tfc.project.ultimateComboRoutes.model.entities.AdministratorDao;
import tfc.project.ultimateComboRoutes.model.exceptions.DuplicateInstanceException;
import tfc.project.ultimateComboRoutes.model.exceptions.InstanceNotFoundException;
import tfc.project.ultimateComboRoutes.model.exceptions.WrongCredentialsException;
import tfc.project.ultimateComboRoutes.model.exceptions.WrongOldPasswordException;

@Service
@Transactional
public class AdministratorServiceImpl implements AdministratorService {

	@Autowired
	private AdministratorDao dao;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Override
	public void signUp(Administrator admin) throws DuplicateInstanceException {

		if (dao.existsByUsername(admin.getUsername())) {
			throw new DuplicateInstanceException(admin.getUsername(), admin.getUsername());
		}

		admin.setPassword(encoder.encode(admin.getPassword()));
		dao.save(admin);
	}

	@Override
	@Transactional
	public Administrator login(String username, String password) throws WrongCredentialsException {

		Optional<Administrator> admin = dao.findByUsername(username);

		if ((!admin.isPresent()) || (!encoder.matches(password, admin.get().getPassword()))) {
			throw new WrongCredentialsException();
		}

		return admin.get();
	}

	@Override
	@Transactional(readOnly = true)
	public Administrator loginFromId(Long adminId) throws InstanceNotFoundException {

		Optional<Administrator> admin = dao.findById(adminId);

		if (!admin.isPresent()) {
			throw new InstanceNotFoundException("El id no existe", adminId);
		}

		return admin.get();
	}

	@Override
	@Transactional
	public Administrator updateProfile(Long adminId, String username, String name, String surname, String email)
			throws DuplicateInstanceException, InstanceNotFoundException {

		Optional<Administrator> admin = dao.findById(adminId);

		if (!admin.isPresent()) {
			throw new InstanceNotFoundException(username, username);
		}

		if ((dao.existsByUsername(username)) && (!admin.get().getUsername().equals(username))) {
			throw new DuplicateInstanceException(username, username);
		}

		admin.get().setUsername(username);
		admin.get().setName(name);
		admin.get().setSurname(surname);
		admin.get().setEmail(email);
		return admin.get();
	}

	@Override
	@Transactional
	public void changePassword(Long adminId, String oldPassword, String newPassword)
			throws InstanceNotFoundException, WrongOldPasswordException {

		Optional<Administrator> admin = dao.findById(adminId);

		if (!admin.isPresent()) {
			throw new InstanceNotFoundException(adminId.toString(), adminId.toString());
		}

		if (!encoder.matches(oldPassword, admin.get().getPassword())) {
			throw new WrongOldPasswordException();
		}

		admin.get().setPassword(encoder.encode(newPassword));
	}
}
