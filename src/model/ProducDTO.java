package model;

public class ProducDTO {
	private String model;		//제품모델명
	private String name;		//제품이름
	private String category;	//제품카테고리
	private int pClass;			//제품등급
	private int maxEv;			//제품최대소비전력
	private int eCost;			//제품연간에너지비용
	private int price;			//제품금액
	private String img;			//제품이미지파일경로
	private int refund;			//제품환급여부
	
	public ProducDTO(String model, String name, String category, int pClass, int maxEv, int eCost, int price,
			String img, int refund) {
		this.model = model;
		this.name = name;
		this.category = category;
		this.pClass = pClass;
		this.maxEv = maxEv;
		this.eCost = eCost;
		this.price = price;
		this.img = img;
		this.refund = refund;
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

	public int getpClass() {
		return pClass;
	}

	public int getMaxEv() {
		return maxEv;
	}

	public int geteCost() {
		return eCost;
	}

	public int getPrice() {
		return price;
	}

	public String getImg() {
		return img;
	}

	public int getrefund() {
		return refund;
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

	public void setpClass(int pClass) {
		this.pClass = pClass;
	}

	public void setMaxEv(int maxEv) {
		this.maxEv = maxEv;
	}

	public void seteCost(int eCost) {
		this.eCost = eCost;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public void setrefund(int refund) {
		this.refund = refund;
	}
}
