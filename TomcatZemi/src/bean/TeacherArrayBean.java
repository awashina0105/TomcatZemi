package bean;

import java.util.ArrayList;

public class TeacherArrayBean {

	private ArrayList<TeacherAccountInfoChangeBean> teacherArray;

	public TeacherArrayBean(){
		teacherArray = new ArrayList<TeacherAccountInfoChangeBean>();
	}

	public ArrayList<TeacherAccountInfoChangeBean> getTeacherArray() {
		return teacherArray;
	}

	public void setTeacherArray(ArrayList<TeacherAccountInfoChangeBean> teacherArray) {
		this.teacherArray = teacherArray;
	}

	public void addTeacherArray(TeacherAccountInfoChangeBean tidbean){
		teacherArray.add(tidbean);
	}

	public int getArrayListSize(){
		return teacherArray.size();
	}

}
