package model;

public class FavoritesDTO {
	private String id;			//�������̵�
	private String model;		//��ǰ�𵨸�
	private String name;		//��ǰ�̸�
	private String category;	//��ǰī�װ�
	
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
