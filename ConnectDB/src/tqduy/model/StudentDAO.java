package tqduy.model;

import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
	public static List<Student> getListStudent = new ArrayList<>();

	public StudentDAO() {
		DBUntils.showAll();
	}
	public Student getStudent(String rollName) {
		return getListStudent.get(Integer.parseInt(rollName));
	}
	public void getAllStudents() {
		for(Student student:getListStudent) {
			System.out.println("--------------------------");
			System.out.println("ID: " + student.getRollName());
			System.out.println("Name: " + student.getName());
		}
	}
	public void updateStudent(Student student) {
		getListStudent.get(Integer.parseInt(student.getRollName())).setName(student.getName());
		DBUntils.conn();
		DBUntils.update(student.getRollName(), student.getName());
	}
	public void deleteStudent(Student student) {
		getListStudent.remove(Integer.parseInt(student.getRollName()));
		DBUntils.conn();
		DBUntils.delete(student.getRollName());
	}
	public void addStudent(Student student) {
		getListStudent.add(student);
		DBUntils.conn();
		DBUntils.insert(student.getRollName(), student.getName());
	}
	
	public static void main(String[] args) {
//		Student student = new Student("5", "Diem");
		StudentDAO dao = new StudentDAO();
//		dao.addStudent(student);
		dao.getAllStudents();
	}
}
