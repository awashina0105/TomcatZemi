package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetSaltDAO extends OpenAndCloseDAO {

	private PreparedStatement statement = null;

	public GetSaltDAO(){
		open();
	}


	public String getSalt(String studentId) {
		ResultSet result = null;
		String salt = "";
		try{
			statement = connect.prepareStatement("SELECT salt FROM student_table WHERE StudentId = ?");
			statement.setString(1, studentId);
			result = statement.executeQuery();

			if(result.next()){
				salt = result.getString("salt");
			}

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

		return salt;

	}




}