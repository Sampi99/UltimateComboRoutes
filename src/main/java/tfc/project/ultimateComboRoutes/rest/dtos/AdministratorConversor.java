package tfc.project.ultimateComboRoutes.rest.dtos;

import tfc.project.ultimateComboRoutes.model.entities.Administrator;

public class AdministratorConversor {

	public AdministratorConversor() {

	}

	public static final Administrator toAdministrator(AdministratorDto adminDto) {

		return new Administrator(adminDto.getId(), adminDto.getUsername(), adminDto.getPassword(), adminDto.getName(),
				adminDto.getSurname(), adminDto.getEmail());
	}

	public static final AdministratorDto toAdministratorDto(Administrator admin) {

		return new AdministratorDto(admin.getId(), admin.getUsername(), admin.getPassword(), admin.getName(),
				admin.getSurname(), admin.getEmail());
	}

	public static final AuthenticatedAdminDto toAuthenticatedAdminDto(String serviceToken, Administrator admin) {

		return new AuthenticatedAdminDto(serviceToken, toAdministratorDto(admin));

	}
}
