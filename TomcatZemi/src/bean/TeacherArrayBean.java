package bean;

import java.util.ArrayList;

public class TeacherArrayBean {

	private ArrayList<TeacherInfoDisplayBean> teacherArray;

	public TeacherArrayBean(){
		teacherArray = new ArrayList<TeacherInfoDisplayBean>();
	}

	public ArrayList<TeacherInfoDisplayBean> getTeacherArray() {
		return teacherArray;
	}

	public void setTeacherArray(ArrayList<TeacherInfoDisplayBean> teacherArray) {
		this.teacherArray = teacherArray;
	}

	public void addTeacherArray(TeacherInfoDisplayBean tidbean){
		teacherArray.add(tidbean);
	}

	public int getArrayListSize(){
		return teacherArray.size();
	}

}
