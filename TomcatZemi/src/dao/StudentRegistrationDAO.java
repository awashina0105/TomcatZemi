package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import bean.StudentRegistrationBean;
import sha2.SaltUserPassword;
import sha2.ToSHA2;

public class StudentRegistrationDAO extends OpenAndCloseDAO {

	private PreparedStatement statement = null;

	public StudentRegistrationDAO(){
		open();
	}


	public boolean studentRegistration(String studentId, String studentPass, String studentFname, String studentLname, String studentMail, String classId, String questionId, String answer, String salt) {
		int result = 0;

		boolean exists = false;

		ToSHA2 SHA = new ToSHA2();


		SaltUserPassword sa = new SaltUserPassword();
		String studentIdBox = SHA.getDigest(studentId);
		String studentPassBox = SHA.getDigest(studentPass);
		String passHash = sa.getDigest(studentIdBox, studentPassBox, salt);
		String answerBox = SHA.getDigest(answer);
		String answerHash = sa.getDigest(studentIdBox, answerBox, salt);


		StudentRegistrationBean sbean = new StudentRegistrationBean();


		try{
			statement = connect.prepareStatement("INSERT INTO student_table (StudentId, StudentPass, StudentFname, StudentLname, StudentMail, ClassId, QuestionId, Answer) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
			statement.setString(1, studentId);
            statement.setString(2, passHash);
            statement.setString(3, studentFname);
			statement.setString(4, studentLname);
            statement.setString(5, studentMail);
            statement.setString(6, classId);
            statement.setString(7, questionId);
            statement.setString(8, answerHash);
            result = statement.executeUpdate();

            if(result != 0) {
            	exists = true;
            }


		 }catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		return exists;

	}






}