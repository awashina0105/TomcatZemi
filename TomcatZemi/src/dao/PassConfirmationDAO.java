package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.LoginBean;

public class PassConfirmationDAO extends OpenAndCloseDAO {

	private PreparedStatement statement = null;

	public PassConfirmationDAO(){
		open();
	}


	public LoginBean passConfirmation(String studentId) {
		LoginBean lbean = new LoginBean();


		try {
        	statement = connect.prepareStatement("SELECT * FROM student_table WHERE StudentId = ?");
            statement.setString(1,studentId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

            	lbean.setStudentPass(resultSet.getString("StudentPass"));
            }

        }catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

        return lbean;

	}

}