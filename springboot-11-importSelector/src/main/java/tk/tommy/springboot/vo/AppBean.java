package tk.tommy.springboot.vo;

public class AppBean {

	private String message;

	public AppBean(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}