package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.TeacherInfoBean;
import sha2.SaltUserPassword;
import sha2.ToSHA2;

public class PassResetDAO extends OpenAndCloseDAO {

	private PreparedStatement statement = null;

	public PassResetDAO(){
		open();
	}

	public boolean passReset(String teacherId, String salt) {

		int result = 0;
        boolean exists =false;
        ToSHA2 SHA = new ToSHA2();
		SaltUserPassword sa = new SaltUserPassword();
		String teacherIdBox = SHA.getDigest(teacherId);
		String teacherResetPassBox = SHA.getDigest(teacherId);
		String passHash = sa.getDigest(teacherIdBox, teacherResetPassBox, salt);




		try{
			statement = connect.prepareStatement("UPDATE teacher_table SET TeacherPass=? WHERE TeacherId=?");
			statement.setString(1, passHash);
			statement.setString(2, teacherId);
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


	public TeacherInfoBean TeacherIdSearch(String teacherId){
		TeacherInfoBean tibean = new TeacherInfoBean();

		try{
			statement = connect.prepareStatement("SELECT * FROM teacher_table WHERE TeacherId = ?");
			statement.setString(1,teacherId);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {

				tibean.setTeacherId(resultSet.getString("teacherId"));
				tibean.setTeacherLname(resultSet.getString("teacherLname"));
				tibean.setTeacherFname(resultSet.getString("teacherFname"));
				tibean.setRole(resultSet.getInt("role"));
			}

		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return tibean;

	}




}