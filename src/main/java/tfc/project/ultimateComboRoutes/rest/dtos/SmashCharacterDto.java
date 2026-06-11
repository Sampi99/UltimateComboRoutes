package tfc.project.ultimateComboRoutes.rest.dtos;

public class SmashCharacterDto {

	private Long id;

	private String name;

	private String description;

	private int weight;

	private String gravity;

	private String render;

	public SmashCharacterDto() {

	}

	public SmashCharacterDto(Long id, String name, String description, int weight, String gravity, String render) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.weight = weight;
		this.gravity = gravity;
		this.render = render;
	}

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
}
