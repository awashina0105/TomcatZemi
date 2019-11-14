package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class  AccountInfoChangeDAO extends OpenAndCloseDAO {

	private PreparedStatement statement = null;

	public AccountInfoChangeDAO(){
		open();
	}

	public boolean AccountInfoChange(String studentId, String studentNewLname, String studentNewFname, String classNewId) {

		int result = 0;
        boolean exists =false;

        try {
        	statement = connect.prepareStatement("UPDATE student_table SET StudentLname=? AND StudentFname=? AND ClassId=? WHERE StudentId=?");
            statement.setString(1,studentNewLname);
            statement.setString(2,studentNewFname);
            statement.setString(3,classNewId);
            statement.setString(4,studentId);

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