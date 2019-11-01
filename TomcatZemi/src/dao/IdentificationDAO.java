package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.IdentificationBean;
import sha2.SaltUserPassword;
import sha2.ToSHA2;

public class IdentificationDAO extends OpenAndCloseDAO {

	private PreparedStatement statement = null;

	public IdentificationDAO(){
		open();
	}


	public boolean identification(String studentId, String studentMail, String answer, String salt) {

		ResultSet result = null;

		boolean exists = false;

		IdentificationBean ibean = new IdentificationBean();

		ToSHA2 SHA = new ToSHA2();
		SaltUserPassword sa = new SaltUserPassword();
		String studentIdBox = SHA.getDigest(studentId);
		String answerBox = SHA.getDigest(answer);
		String answerHash = sa.getDigest(studentIdBox, answerBox, salt);

		try{
			statement = connect.prepareStatement("SELECT * FROM student_table WHERE studentId = ? AND studentMail = ? AND answer = ?");
			statement.setString(1, studentId);
			statement.setString(2, studentMail);
			statement.setString(3, answerHash);
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