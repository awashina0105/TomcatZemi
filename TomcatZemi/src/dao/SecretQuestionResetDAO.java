package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SecretQuestionResetDAO extends OpenAndCloseDAO {

	private PreparedStatement statement = null;

	public SecretQuestionResetDAO(){
		open();
	}





	public boolean SecretQuestionReset(String studentId, String questionNumber) {

		int result = 0;
        boolean exists =false;

        try {
        	statement = connect.prepareStatement("UPDATE student_table SET Answer=? AND QuestionId=? WHERE StudentId=?");
            statement.setString(1,studentId);
            statement.setString(2,questionNumber);
            statement.setString(3,studentId);

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