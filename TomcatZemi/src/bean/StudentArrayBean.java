package bean;

import java.util.ArrayList;

public class StudentArrayBean {

	private ArrayList<StudentDeleteBean> studentArray;

	public StudentArrayBean(){
		studentArray = new ArrayList<StudentDeleteBean>();
	}

	public ArrayList<StudentDeleteBean> getStudentArrayArray() {
		return studentArray;
	}

	public void setStudentArray(ArrayList<StudentDeleteBean> studentArray) {
		this.studentArray = studentArray;
	}

	public void addStudentArray(StudentDeleteBean sdbean){
		studentArray.add(sdbean);
	}

	public int getArrayListSize(){
		return studentArray.size();
	}

}
