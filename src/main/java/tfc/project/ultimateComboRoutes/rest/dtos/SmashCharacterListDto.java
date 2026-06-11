package tfc.project.ultimateComboRoutes.rest.dtos;

public class SmashCharacterListDto {

	private String render;

	private String name;

	public SmashCharacterListDto() {

	}

	public SmashCharacterListDto(String render, String name) {
		this.render = render;
		this.name = name;
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
