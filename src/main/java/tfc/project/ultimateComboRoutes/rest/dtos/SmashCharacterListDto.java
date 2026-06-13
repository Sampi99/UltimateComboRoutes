package tfc.project.ultimateComboRoutes.rest.dtos;

public class SmashCharacterListDto {

	private Long id;

	private String render;

	private String name;

	public SmashCharacterListDto() {

	}

	public SmashCharacterListDto(Long id, String render, String name) {
		this.id = id;
		this.render = render;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRender() {
		return render;
	}

	public void setRender(String render) {
		this.render = render;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
