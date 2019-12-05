package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.StudentRegistrationBean;
import sha2.SaltUserPassword;
import sha2.ToSHA2;

public class StudentRegistrationDAO extends OpenAndCloseDAO {

	private PreparedStatement statement = null;

	public StudentRegistrationDAO(){
		open();
	}


	public boolean studentRegistration(String studentId, String studentPass, String studentFname, String studentLname, String studentMail, String classId, int majorId, String questionId, String answer, String salt) {
		int result = 0;

		boolean exists = false;

		ToSHA2 SHA = new ToSHA2();


		SaltUserPassword sa = new SaltUserPassword();
		String studentIdBox = SHA.getDigest(studentId);
		String studentPassBox = SHA.getDigest(studentPass);
		String passHash = sa.getDigest(studentIdBox, studentPassBox, salt);
		String answerBox = SHA.getDigest(answer);
		String answerHash = sa.getDigest(studentIdBox, answerBox, salt);




		try{
			statement = connect.prepareStatement("INSERT INTO student_table (StudentId, StudentPass, StudentFname, StudentLname, StudentMail, ClassId, MajorId, QuestionId, Answer, Salt) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			statement.setString(1, studentId);
            statement.setString(2, passHash);
            statement.setString(3, studentFname);
			statement.setString(4, studentLname);
            statement.setString(5, studentMail);
            statement.setString(6, classId);
            statement.setInt(7, majorId);
            statement.setString(8, questionId);
            statement.setString(9, answerHash);
            statement.setString(10, salt);
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


	public StudentRegistrationBean studentMajorGet(String classId) {
		StudentRegistrationBean srbean = new StudentRegistrationBean();


		try {
        	statement = connect.prepareStatement("SELECT * FROM major_table WHERE classId = ?");
            statement.setString(1,classId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

            	srbean.setMajorId(resultSet.getInt("MajorId"));
            }

        }catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

        return srbean;

	}

	public boolean findId(String studentId) {

		boolean flag = false;
		try{
            statement = connect.prepareStatement("SELECT * FROM student_table WHERE StudentId = ?");
            statement.setString(1, studentId);
            ResultSet resultSet = statement.executeQuery();

            flag = resultSet.next();


		 }catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		return flag;

	}





}