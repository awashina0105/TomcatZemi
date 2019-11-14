package bean;

public class TeacherAccountInfoChangeBean {


	private String teacherId;
	private String teacherLname;
	private String teacherFname;
	private int role;
	private String classId;
	private String subjectId;
	private String SubjectName;


	public String getSubjectName() {
		return SubjectName;
	}
	public void setSubjectName(String subjectName) {
		SubjectName = subjectName;
	}
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	public String getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}
	public String getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	public String getTeacherLname() {
		return teacherLname;
	}
	public void setTeacherLname(String teacherLname) {
		this.teacherLname = teacherLname;
	}
	public String getTeacherFname() {
		return teacherFname;
	}
	public void setTeacherFname(String teacherFname) {
		this.teacherFname = teacherFname;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}


}