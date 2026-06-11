package tfc.project.ultimateComboRoutes.rest.dtos;

public class ChangePasswordParamsDto {

	private String oldPassword;

	private String newPassword;

	public ChangePasswordParamsDto() {

	}

	public ChangePasswordParamsDto(String oldPassword, String newPassword) {
		super();
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

}
