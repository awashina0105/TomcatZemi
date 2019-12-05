package bean;

import java.util.ArrayList;

public class StudentArrayBean {

	private ArrayList<StudentRegistrationBean> studentArray;

	public StudentArrayBean(){
		studentArray = new ArrayList<StudentRegistrationBean>();
	}

	public ArrayList<StudentRegistrationBean> getStudentArray() {
		return studentArray;
	}

	public void setStudentArray(ArrayList<StudentRegistrationBean> studentArray) {
		this.studentArray = studentArray;
	}

	public void addStudentArray(StudentRegistrationBean srbean){
		studentArray.add(srbean);
	}

	public int getArrayListSize(){
		return studentArray.size();
	}

}
