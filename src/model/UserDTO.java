package model;

public class UserDTO {
	private String id;		//�������̵�
	private String pw;		//������й�ȣ
	private String name;	//�����̸�
	private String tell;	//������ȭ��ȣ
	
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
