package tfc.project.ultimateComboRoutes.rest.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthenticatedAdminDto {

	private String serviceToken;

	private AdministratorDto adminDto;

	public AuthenticatedAdminDto() {
	}

	public AuthenticatedAdminDto(String serviceToken, AdministratorDto adminDto) {

		setServiceToken(serviceToken);
		setAdministratorDto(adminDto);

	}

	public String getServiceToken() {
		return serviceToken;
	}

	public void setServiceToken(String serviceToken) {
		this.serviceToken = serviceToken;
	}

	@JsonProperty("administrator")
	public AdministratorDto getAdministratorDto() {
		return adminDto;
	}

	public void setAdministratorDto(AdministratorDto adminDto) {
		this.adminDto = adminDto;
	}

}
