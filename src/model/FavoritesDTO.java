package model;

public class FavoritesDTO {
	private String id;			//유저아이디
	private String model;		//제품모델명
	private String name;		//제품이름
	private String category;	//제품카테고리
	
	public FavoritesDTO(String id, String model, String name, String category) {
		super();
		this.id = id;
		this.model = model;
		this.name = name;
		this.category = category;
	}
	
	public String getId() {
		return id;
	}
	public String getModel() {
		return model;
	}
	public String getName() {
		return name;
	}
	public String getCategory() {
		return category;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setCategory(String category) {
		this.category = category;
	}
}
