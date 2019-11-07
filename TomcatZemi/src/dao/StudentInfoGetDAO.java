package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.StudentArrayBean;
import bean.StudentDeleteBean;

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
			StudentDeleteBean sdbean = new StudentDeleteBean();
			sdbean.setStudentId(result.getString("studentId"));
			sdbean.setClassId(result.getString("classId"));
			sdbean.setStudentFname(result.getString("studentFname"));
			sdbean.setStudentLname(result.getString("studentLname"));

			arraybean.addStudentArray(sdbean);
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