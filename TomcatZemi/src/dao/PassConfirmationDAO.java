package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import sha2.SaltUserPassword;
import sha2.ToSHA2;

public class PassConfirmationDAO extends OpenAndCloseDAO {

	private PreparedStatement statement = null;

	public PassConfirmationDAO(){
		open();
	}




	public boolean passConfirmation(String studentId, String studentPass, String salt) {
		ResultSet result = null;

		boolean exists = false;


		ToSHA2 SHA = new ToSHA2();
		SaltUserPassword sa = new SaltUserPassword();
		String studentIdBox = SHA.getDigest(studentId);
		String studentPassBox = SHA.getDigest(studentPass);
		String passHash = sa.getDigest(studentIdBox, studentPassBox, salt);


		try{
			statement = connect.prepareStatement("SELECT * FROM student_table WHERE StudentId = ? AND StudentPass = ?");
			statement.setString(1, studentId);
			statement.setString(2, passHash);
			ResultSet resultSet = statement.executeQuery();

			exists = resultSet.next();


		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			if(result != null){
				try{
					result.close();
				}catch (SQLException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
		return exists;

	}

	public boolean teacherPassConfirmation(String teacherId, String teacherPass, String salt) {
		ResultSet result = null;

		boolean exists = false;


		ToSHA2 SHA = new ToSHA2();
		SaltUserPassword sa = new SaltUserPassword();
		String teacherIdBox = SHA.getDigest(teacherId);
		String teacherPassBox = SHA.getDigest(teacherPass);
		String passHash = sa.getDigest(teacherIdBox, teacherPassBox, salt);


		try{
			statement = connect.prepareStatement("SELECT * FROM teacher_table WHERE TeacherId = ? AND TeacherPass = ?");
			statement.setString(1, teacherId);
			statement.setString(2, passHash);
			ResultSet resultSet = statement.executeQuery();

			exists = resultSet.next();


		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			if(result != null){
				try{
					result.close();
				}catch (SQLException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
		return exists;

	}


}