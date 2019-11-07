package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.StudentArrayBean;
import bean.StudentDeleteBean;

public class StudentSearchDAO extends OpenAndCloseDAO {

	private PreparedStatement statement = null;

	public StudentSearchDAO(){
		open();
	}

	public StudentDeleteBean StudentIdSearch(String studentId){
		StudentDeleteBean sdbean = new StudentDeleteBean();

		try{
			statement = connect.prepareStatement("SELECT * FROM student_table WHERE StudentId = ?");
			statement.setString(1,studentId);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {

				sdbean.setStudentId(resultSet.getString("StudentId"));
				sdbean.setClassId(resultSet.getString("ClassId"));
				sdbean.setStudentLname(resultSet.getString("StudentLname"));
				sdbean.setStudentFname(resultSet.getString("StudentFname"));
			}

		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return sdbean;

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


	public StudentArrayBean StudentClassSearch(String classid){
		StudentArrayBean arraybean = new StudentArrayBean();
		ResultSet result = null;

		try{
			statement = connect.prepareStatement("SELECT * FROM student_table WHERE Classid = ?");
			statement.setString(1,classid);
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