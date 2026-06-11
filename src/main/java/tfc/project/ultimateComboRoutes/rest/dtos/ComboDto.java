package tfc.project.ultimateComboRoutes.rest.dtos;

public class ComboDto {

	private Long id;

	private String secuence;

	private String difficulty;

	private int totalDamage;

	private String demo;

	public ComboDto() {

	}

	public ComboDto(Long id, String secuence, String difficulty, int totalDamage, String demo) {
		this.id = id;
		this.secuence = secuence;
		this.difficulty = difficulty;
		this.totalDamage = totalDamage;
		this.demo = demo;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSecuence() {
		return secuence;
	}

	public void setSecuence(String secuence) {
		this.secuence = secuence;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	public int getTotalDamage() {
		return totalDamage;
	}

	public void setTotalDamage(int totalDamage) {
		this.totalDamage = totalDamage;
	}

	public String getDemo() {
		return demo;
	}

	public void setDemo(String demo) {
		this.demo = demo;
	}

}
