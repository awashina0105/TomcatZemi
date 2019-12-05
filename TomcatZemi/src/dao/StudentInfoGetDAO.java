package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.StudentArrayBean;
import bean.StudentRegistrationBean;

public class StudentInfoGetDAO extends OpenAndCloseDAO {

	private PreparedStatement statement = null;

	public StudentInfoGetDAO(){
		open();
	}

	public  StudentArrayBean studentInfoGet() {
		StudentArrayBean arraybean = new StudentArrayBean();
		ResultSet result = null;

		try{
		statement = connect.prepareStatement("SELECT * FROM student_table");

		result = statement.executeQuery();

		while(result.next()){
			StudentRegistrationBean srbean = new StudentRegistrationBean();
			srbean.setStudentId(result.getString("studentId"));
			srbean.setClassId(result.getString("classId"));
			srbean.setStudentFname(result.getString("studentFname"));
			srbean.setStudentLname(result.getString("studentLname"));

			arraybean.addStudentArray(srbean);
		}
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			close();
		}
		return arraybean;
	}





}