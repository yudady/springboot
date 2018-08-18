package tk.tommy.bean;

public class PrototypeBean {

	private String name;
	private int age;

	public PrototypeBean(String name, int age) {
		this.age = age;
		this.name = name;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return this.hashCode() + " PrototypeBean{" + "name='" + name + '\'' + ", age=" + age + '}';
	}
}
