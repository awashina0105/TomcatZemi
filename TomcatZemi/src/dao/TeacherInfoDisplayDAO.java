package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.TeacherArrayBean;
import bean.TeacherInfoDisplayBean;

public class TeacherInfoDisplayDAO extends OpenAndCloseDAO {

	private PreparedStatement statement = null;

	public TeacherInfoDisplayDAO(){
		open();
	}

	public  TeacherArrayBean TeacherInfoGet() {
		TeacherArrayBean tarraybean = new TeacherArrayBean();
		ResultSet result = null;

		try{
		statement = connect.prepareStatement("SELECT * FROM teacher_table");

		result = statement.executeQuery();

		while(result.next()){
			TeacherInfoDisplayBean tidbean = new TeacherInfoDisplayBean();
			tidbean.setTeacherId(result.getString("TeacherId"));
			tidbean.setTeacherLname(result.getString("TeacherLname"));
			tidbean.setTeacherFname(result.getString("TeacherFname"));
			tidbean.setRole(result.getInt("Role"));

			tarraybean.addTeacherArray(tidbean);
		}
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			close();
		}
		return tarraybean;
	}



}