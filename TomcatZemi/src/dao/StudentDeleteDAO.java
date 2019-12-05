package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.StudentRegistrationBean;

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


	public StudentRegistrationBean StudentInfoSearch(String studentId){
		StudentRegistrationBean srbean = new StudentRegistrationBean();

		try{
			statement = connect.prepareStatement("SELECT * FROM student_table WHERE StudentId = ?");
			statement.setString(1,studentId);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {

				srbean.setStudentId(resultSet.getString("studentId"));
				srbean.setClassId(resultSet.getString("classId"));
				srbean.setStudentLname(resultSet.getString("studentLname"));
				srbean.setStudentFname(resultSet.getString("studentFname"));

			}

		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return srbean;

	}






}