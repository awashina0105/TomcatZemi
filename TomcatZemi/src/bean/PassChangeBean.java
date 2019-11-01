package bean;

public class PassChangeBean {


	private String studentId;
	private String studentPass;
	private String studentNewPass;
	private String confirmationPass;	//確認のパスワード



	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getStudentPass() {
		return studentPass;
	}
	public void setStudentPass(String studentPass) {
		this.studentPass = studentPass;
	}
	public String getStudentNewPass() {
		return studentNewPass;
	}
	public void setStudentNewPass(String studentNewPass) {
		this.studentNewPass = studentNewPass;
	}
	public String getConfirmationPass() {
		return confirmationPass;
	}
	public void setConfirmationPass(String confirmationPass) {
		this.confirmationPass = confirmationPass;
	}


}