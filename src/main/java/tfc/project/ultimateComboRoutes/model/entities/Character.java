package tfc.project.ultimateComboRoutes.model.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Character {

	private Long id;

	private String name;

	private String description;

	private int weight;

	private String gravity;

	private String render;

	private Administrator admin;

	public Character() {

	}

	public Character(Long id, String name, String description, int weight, String gravity, String render,
			Administrator admin) {
		this.name = name;
		this.description = description;
		this.weight = weight;
		this.gravity = gravity;
		this.render = render;
		this.admin = admin;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getGravity() {
		return gravity;
	}

	public void setGravity(String gravity) {
		this.gravity = gravity;
	}

	public String getRender() {
		return render;
	}

	public void setRender(String render) {
		this.render = render;
	}

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "adminId")
	public Administrator getAdmin() {
		return admin;
	}

	public void setAdmin(Administrator admin) {
		this.admin = admin;
	}

	@Override
	public String toString() {
		return "Character [id=" + id + ", name=" + name + ", description=" + description + ", weight=" + weight
				+ ", gravity=" + gravity + ", render=" + render + ", admin=" + admin + "]";
	}

}
