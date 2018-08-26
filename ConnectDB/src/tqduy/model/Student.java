package tqduy.model;

public class Student {
	private String rollName;
	private String name;
	
	public Student() {
	}
	public Student(String rollName, String name) {
		this.rollName = rollName;
		this.name = name;
	}
	public String getRollName() {
		return rollName;
	}
	public void setRollName(String rollName) {
		this.rollName = rollName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
