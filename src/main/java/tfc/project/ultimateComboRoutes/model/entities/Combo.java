package tfc.project.ultimateComboRoutes.model.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Combo {

	private Long id;

	private String secuence;

	private String difficulty;

	private int totalDamage;

	private String demo;

	private Administrator admin;

	private SmashCharacter smashCharacter;

	public Combo() {

	}

	public Combo(String secuence, String difficulty, int totalDamage, String demo, Administrator admin,
			SmashCharacter smashCharacter) {
		this.secuence = secuence;
		this.difficulty = difficulty;
		this.totalDamage = totalDamage;
		this.demo = demo;
		this.admin = admin;
		this.smashCharacter = smashCharacter;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "adminId")
	public Administrator getAdmin() {
		return admin;
	}

	public void setAdmin(Administrator admin) {
		this.admin = admin;
	}

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "characterId")
	public SmashCharacter getSmashCharacter() {
		return smashCharacter;
	}

	public void setSmashCharacter(SmashCharacter smashCharacter) {
		this.smashCharacter = smashCharacter;
	}

	@Override
	public String toString() {
		return "Combo [id=" + id + ", secuence=" + secuence + ", difficulty=" + difficulty + ", totalDamage="
				+ totalDamage + ", demo=" + demo + ", admin=" + admin + ", smashCharacter=" + smashCharacter + "]";
	}

}
