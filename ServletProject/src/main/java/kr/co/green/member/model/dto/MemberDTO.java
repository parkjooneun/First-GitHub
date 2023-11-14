package kr.co.green.member.model.dto;

public class MemberDTO {
	private String id;
	private String name;
	private String pwd;
	private String indate;
	
	
	
	
	@Override
	public String toString() {
		return "MemberDTO [id=" + id + ", name=" + name + ", pwd=" + pwd + ", indate=" + indate + "]";
	}

	
	public MemberDTO() {
		super();
	}

	public MemberDTO(String id, String name, String pwd) {
		super();
		this.id = id;
		this.name = name;
		this.pwd = pwd;
	}

	public MemberDTO(String id, String name, String pwd, String indate) {
		super();
		this.id = id;
		this.name = name;
		this.pwd = pwd;
		this.indate = indate;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getIndate() {
		return indate;
	}
	public void setIndate(String indate) {
		this.indate = indate;
	}
	
	
}
