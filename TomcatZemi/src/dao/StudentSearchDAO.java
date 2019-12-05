package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.StudentArrayBean;
import bean.StudentRegistrationBean;

public class StudentSearchDAO extends OpenAndCloseDAO {

	private PreparedStatement statement = null;

	public StudentSearchDAO(){
		open();
	}

	public StudentRegistrationBean StudentIdSearch(String name){
		StudentRegistrationBean srbean = new StudentRegistrationBean();

		try{
			statement = connect.prepareStatement("SELECT * FROM student_table WHERE StudentId = ?");
			statement.setString(1,name);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {

				srbean.setStudentId(resultSet.getString("StudentId"));
				srbean.setClassId(resultSet.getString("ClassId"));
				srbean.setStudentLname(resultSet.getString("StudentLname"));
				srbean.setStudentFname(resultSet.getString("StudentFname"));
			}

		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return srbean;

	}

	public StudentArrayBean StudentNameSearch(String name){
		StudentArrayBean arraybean = new StudentArrayBean();
		ResultSet result = null;

		try{
			statement = connect.prepareStatement("SELECT * FROM student_table WHERE StudentFname LIKE ? OR StudentLname LIKE ?");
			statement.setString(1, "%" + name +"%"); //仮の名前
			statement.setString(2, "%" + name +"%"); //仮の名前
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


	public StudentArrayBean StudentClassSearch(String name){
		StudentArrayBean arraybean = new StudentArrayBean();
		ResultSet result = null;

		try{
			statement = connect.prepareStatement("SELECT * FROM student_table WHERE Classid = ?");
			statement.setString(1,name);
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