package model;

public class UserDTO {
	private String id;		//유저아이디
	private String pw;		//유저비밀번호
	private String name;	//유저이름
	private String tell;	//유저전화번호
	
	public UserDTO(String id, String pw, String name, String tell) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.tell = tell;
	}

	public String getId() {
		return id;
	}

	public String getPw() {
		return pw;
	}

	public String getName() {
		return name;
	}

	public String getCell() {
		return tell;
	}
}
