package tk.yudady.vo;

import javax.validation.constraints.NotEmpty;
public class User {

	private int id;

	@NotEmpty(message = "Please provide an username")
	private String username;

	@NotEmpty(message = "Please provide an password")
	private String password;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}