package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.TeacherAccountInfoChangeBean;
import bean.TeacherAccountNewInfoChangeBean;

public class  TeacherAccountInfoChangeDAO extends OpenAndCloseDAO {

	private PreparedStatement statement = null;

	public TeacherAccountInfoChangeDAO(){
		open();
	}

	public boolean Teacher_TableAccountInfoChange(String teacherId, String teacherNewLname, String teacherNewFname, int newRole) {

		int result = 0;
        boolean exists =false;

        try {
        	statement = connect.prepareStatement("UPDATE teacher_table SET TeacherLname=? AND TeacherFname=? AND Role=? WHERE TeacherId=?");
            statement.setString(1,teacherNewLname);
            statement.setString(2,teacherNewFname);
            statement.setInt(3,newRole);
            statement.setString(4,teacherId);

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


	public boolean Class_TableAccountInfoChange(String teacherId, String classNewId) {

		int result = 0;
        boolean exists =false;

        try {
        	statement = connect.prepareStatement("UPDATE class_table SET ClassId=? WHERE TeacherId=?");
            statement.setString(1,classNewId);
            statement.setString(2,teacherId);

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


	public boolean Lesson_TableAccountInfoChange(String teacherId, String subjectNewId) {

		int result = 0;
        boolean exists =false;

        try {
        	statement = connect.prepareStatement("UPDATE lesson_table SET SubjectId=? WHERE TeacherId=?");
            statement.setString(1,subjectNewId);
            statement.setString(2,teacherId);

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

	public TeacherAccountInfoChangeBean Teacher_TableInfoSearch(String teacherId){
		TeacherAccountInfoChangeBean taicbean = new TeacherAccountInfoChangeBean();

		try{
			statement = connect.prepareStatement("SELECT * FROM teacher_table WHERE TeacherId = ?");
			statement.setString(1,teacherId);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {

				taicbean.setTeacherId(resultSet.getString("teacherId"));
				taicbean.setTeacherLname(resultSet.getString("teacherLname"));
				taicbean.setTeacherFname(resultSet.getString("teacherFname"));
				taicbean.setRole(resultSet.getInt("role"));
			}

		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return taicbean;

	}

	public TeacherAccountInfoChangeBean Class_TableInfoSearch(String teacherId){
		TeacherAccountInfoChangeBean taicbean = new TeacherAccountInfoChangeBean();

		try{
			statement = connect.prepareStatement("SELECT * FROM class_table WHERE TeacherId = ?");
			statement.setString(1,teacherId);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {

				taicbean.setClassId(resultSet.getString("classId"));
			}

		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return taicbean;

	}

	public TeacherAccountInfoChangeBean Lesson_TableInfoSearch(String teacherId){
		TeacherAccountInfoChangeBean taicbean = new TeacherAccountInfoChangeBean();

		try{
			statement = connect.prepareStatement("SELECT * FROM lesson_table WHERE TeacherId = ?");
			statement.setString(1,teacherId);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {

				taicbean.setSubjectId(resultSet.getString("subjectId"));
			}

		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return taicbean;

	}

	public TeacherAccountInfoChangeBean SubjectNameSearch(String SubjectId){
		TeacherAccountInfoChangeBean taicbean = new TeacherAccountInfoChangeBean();

		try{
			statement = connect.prepareStatement("SELECT * FROM lesson_table WHERE TeacherId = ?");
			statement.setString(1,SubjectId);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {

				taicbean.setSubjectName(resultSet.getString("SubjectName"));
			}

		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return taicbean;

	}

	public TeacherAccountNewInfoChangeBean SubjectIdSearch(String subjectNewName){
		TeacherAccountNewInfoChangeBean tanicbean = new TeacherAccountNewInfoChangeBean();

		try{
			statement = connect.prepareStatement("SELECT * FROM subject_table WHERE subjectName = ?");
			statement.setString(1,subjectNewName);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {

				tanicbean.setSubjectNewId(resultSet.getString("SubjectId"));
			}

		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return tanicbean;

	}



}