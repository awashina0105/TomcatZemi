package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentDeleteDAO extends OpenAndCloseDAO {

	private PreparedStatement statement = null;

	public StudentDeleteDAO(){
		open();
	}


	public boolean studentdelete(String studentId) {
		int result = 0;

		boolean exists = false;



		try{
			statement = connect.prepareStatement("DELETE * FROM student_table WHERE StudentId = ?");
			statement.setString(1, studentId);
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