package tfc.project.ultimateComboRoutes.rest.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthenticatedAdminDto {

	private AdministratorDto adminDto;

	private String serviceToken;

	public AuthenticatedAdminDto() {

	}

	public AuthenticatedAdminDto(AdministratorDto adminDto, String serviceToken) {
		super();
		this.adminDto = adminDto;
		this.serviceToken = serviceToken;
	}

	@JsonProperty("administrator")
	public AdministratorDto getAdminDto() {
		return adminDto;
	}

	public void setAdminDto(AdministratorDto adminDto) {
		this.adminDto = adminDto;
	}

	public String getServiceToken() {
		return serviceToken;
	}

	public void setServiceToken(String serviceToken) {
		this.serviceToken = serviceToken;
	}

}
