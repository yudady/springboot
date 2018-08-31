package tk.yudady.pojo;

import tk.yudady.pojo.NameView;
import com.fasterxml.jackson.annotation.JsonView;

public class Name {
	@JsonView(NameView.FirstName.class)
	private String firstName;
	private String middleName;
	@JsonView(NameView.FirstLastName.class)	
	private String lastName;
	public Name(String firstName, String middleName, String lastName) {
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
