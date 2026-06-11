package tfc.project.ultimateComboRoutes.rest.common;

public class JwtInfo {

	private Long adminId;

	private String username;

	public JwtInfo(Long adminId, String username) {
		super();
		this.adminId = adminId;
		this.username = username;
	}

	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
