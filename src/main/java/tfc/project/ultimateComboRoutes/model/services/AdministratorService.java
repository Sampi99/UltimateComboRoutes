package tfc.project.ultimateComboRoutes.model.services;

import tfc.project.ultimateComboRoutes.model.entities.Administrator;
import tfc.project.ultimateComboRoutes.model.exceptions.DuplicateInstanceException;
import tfc.project.ultimateComboRoutes.model.exceptions.InstanceNotFoundException;
import tfc.project.ultimateComboRoutes.model.exceptions.WrongCredentialsException;
import tfc.project.ultimateComboRoutes.model.exceptions.WrongOldPasswordException;

public interface AdministratorService {

	void signUp(Administrator admin) throws DuplicateInstanceException;

	Administrator login(String username, String password) throws InstanceNotFoundException, WrongCredentialsException;

	Administrator loginFromId(Long adminId) throws InstanceNotFoundException;

	Administrator updateProfile(Long adminId, String username, String name, String surname, String email)
			throws DuplicateInstanceException, InstanceNotFoundException;

	void changePassword(Long adminId, String oldPassword, String newPassword)
			throws InstanceNotFoundException, WrongOldPasswordException;
}
