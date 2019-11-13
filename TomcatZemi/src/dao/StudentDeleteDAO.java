package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.StudentInfoBean;

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


	public StudentInfoBean StudentInfoSearch(String studentId){
		StudentInfoBean sibean = new StudentInfoBean();

		try{
			statement = connect.prepareStatement("SELECT * FROM student_table WHERE StudentId = ?");
			statement.setString(1,studentId);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {

				sibean.setStudentId(resultSet.getString("studentId"));
				sibean.setClassId(resultSet.getString("classId"));
				sibean.setStudentLname(resultSet.getString("studentLname"));
				sibean.setStudentFname(resultSet.getString("studentFname"));

			}

		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return sibean;

	}






}