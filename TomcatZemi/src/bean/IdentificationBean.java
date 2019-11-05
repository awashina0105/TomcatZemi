package bean;

public class IdentificationBean {
/* 本人確認Bean */

	private String studentId;
	private String studentMail;
	private String questionId;
	private String answer;

	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}


	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getStudentMail() {
		return studentMail;
	}
	public void setStudentMail(String studentMail) {
		this.studentMail = studentMail;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}







}