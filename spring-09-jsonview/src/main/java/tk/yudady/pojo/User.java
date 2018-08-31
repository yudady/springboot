package tk.yudady.pojo;

import com.fasterxml.jackson.annotation.JsonView;
import tk.yudady.config.Profile;

public class User {
	@JsonView(Profile.PublicView.class)
	private String userId;
	private String password;	
	private int age;			
	@JsonView(Profile.FamilyView.class)	
	private long mobnum;		
	@JsonView(Profile.FriendsView.class)
	private String mailId;
	@JsonView(Profile.PublicView.class)	
	private String name;	
	@JsonView(Profile.PublicView.class)		
	private College college;
	@JsonView(Profile.PublicView.class)		
	private Address address;
	public User(String userId, String password, int age, long mobnum, String mailId, 
			String name, College college, Address address) {
		this.userId = userId;
		this.password = password;
		this.age = age;
		this.mobnum = mobnum;
		this.mailId = mailId;
		this.name = name;
		this.college = college;
		this.address = address;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public long getMobnum() {
		return mobnum;
	}
	public void setMobnum(long mobnum) {
		this.mobnum = mobnum;
	}
	public String getMailId() {
		return mailId;
	}
	public void setMailId(String mailId) {
		this.mailId = mailId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public College getCollege() {
		return college;
	}
	public void setCollege(College college) {
		this.college = college;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
}
