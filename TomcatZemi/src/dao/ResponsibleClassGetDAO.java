package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.TeacherLoginBean;

public class ResponsibleClassGetDAO extends OpenAndCloseDAO {

	private PreparedStatement statement = null;

	public ResponsibleClassGetDAO(){
		open();
	}


	public TeacherLoginBean responsibleClassGet(String teacherId) {
		TeacherLoginBean tlbean = new TeacherLoginBean();;
		try{
			statement = connect.prepareStatement("SELECT ClassId FROM class_table WHERE teacherId = ?");
			statement.setString(1, teacherId);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {

				tlbean.setClassId(resultSet.getString("ClassId"));
			}

		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return tlbean;

	}
}