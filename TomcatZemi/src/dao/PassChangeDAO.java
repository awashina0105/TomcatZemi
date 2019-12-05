package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import sha2.SaltUserPassword;
import sha2.ToSHA2;

public class PassChangeDAO extends OpenAndCloseDAO {

	private PreparedStatement statement = null;

	public PassChangeDAO(){
		open();
	}





	public boolean passChange(String studentId, String studentNewPass, String salt) {

		int result = 0;
        boolean exists =false;
        ToSHA2 SHA = new ToSHA2();
		SaltUserPassword sa = new SaltUserPassword();
		String studentIdBox = SHA.getDigest(studentId);
		String studentNewPassBox = SHA.getDigest(studentNewPass);
		String passHash = sa.getDigest(studentIdBox, studentNewPassBox, salt);

        try {
        	statement = connect.prepareStatement("UPDATE student_table SET StudentPass=? WHERE StudentId=?");
            statement.setString(1,passHash);
            statement.setString(2,studentId);

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




	public boolean teacherPassChange(String teacherId, String teacherNewPass, String salt) {

		int result = 0;
        boolean exists =false;
        ToSHA2 SHA = new ToSHA2();
		SaltUserPassword sa = new SaltUserPassword();
		String teacherIdBox = SHA.getDigest(teacherId);
		String teacherNewPassBox = SHA.getDigest(teacherNewPass);
		String passHash = sa.getDigest(teacherIdBox, teacherNewPassBox, salt);

        try {
        	statement = connect.prepareStatement("UPDATE teacher_table SET TeacherPass=? WHERE TeacherId=?");
            statement.setString(1,passHash);
            statement.setString(2,teacherId);

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