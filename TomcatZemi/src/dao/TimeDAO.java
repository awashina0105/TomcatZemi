package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.TimeBean;
import bean.TimeTable_ArrayBean;

public class TimeDAO extends OpenAndCloseDAO {

	private PreparedStatement statement = null;

	public TimeDAO(){
		open();
	}


	public boolean time(int majorId, int yearMonth, String dayTime, String classId, String room, String lessonId) {
		int result = 0;

		boolean exists = false;



		try{
			statement = connect.prepareStatement("INSERT INTO time_table (MajorId, YearMonth, DayTime, ClassId, Room, LessonId) VALUES (?, ?, ?, ?, ?, ?)");
			statement.setInt(1, majorId);
			statement.setInt(2, yearMonth);
			statement.setString(3, dayTime);
            statement.setString(4, classId);
            statement.setString(5, room);
            statement.setString(6, lessonId);
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


	public TimeBean user_Info_Search(String studentId){
		TimeBean tbean = new TimeBean();

		try{
			statement = connect.prepareStatement("SELECT * FROM teacher_table WHERE StudentId = ?");
			statement.setString(1,studentId);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {

				tbean.setMajorId(resultSet.getInt("majorId"));
				tbean.setClassId(resultSet.getString("classId"));
			}

		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return tbean;

	}

	public  TimeTable_ArrayBean TimeTable_InfoGet(int majorId, String classId, Date today) {
		TimeTable_ArrayBean ttarraybean = new TimeTable_ArrayBean();
		ResultSet result = null;

		try{
		statement = connect.prepareStatement("SELECT * FROM time_table WHERE MajorId = ? AND ClassId = ? AND YearMonth = ?");
		statement.setInt(1,majorId);
        statement.setString(2,classId);
        statement.setDate(3,today);
		result = statement.executeQuery();

		while(result.next()){
			TimeBean timebean = new TimeBean();
			timebean.setDayTime(result.getString("DayTime"));
			timebean.setRoom(result.getString("Room"));
			timebean.setClassId(result.getString("ClassId"));
			timebean.setLessonId(result.getString("LessonId"));

			ttarraybean.addTimeArray(timebean);
		}
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			close();
		}
		return ttarraybean;
	}

	public  TimeTable_ArrayBean subjectTable_InfoGet(String lessonId) {
		TimeTable_ArrayBean ttarraybean = new TimeTable_ArrayBean();
		ResultSet result = null;

		try{
		statement = connect.prepareStatement("SELECT * FROM time_table WHERE LessonId = ?");
        statement.setString(1,lessonId);
		result = statement.executeQuery();

		while(result.next()){
			TimeBean timebean = new TimeBean();
			timebean.setSubjectId(result.getString("SubjectId"));

			ttarraybean.addTimeArray(timebean);
		}
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			close();
		}
		return ttarraybean;
	}

	public  TimeTable_ArrayBean subjectName_InfoGet(String subjectId) {
		TimeTable_ArrayBean ttarraybean = new TimeTable_ArrayBean();
		ResultSet result = null;

		try{
		statement = connect.prepareStatement("SELECT * FROM subject_table WHERE SubjectId = ?");
        statement.setString(1,subjectId);
		result = statement.executeQuery();

		while(result.next()){
			TimeBean timebean = new TimeBean();
			timebean.setSubjectName(result.getString("SubjectName"));

			ttarraybean.addTimeArray(timebean);
		}
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			close();
		}
		return ttarraybean;
	}


	public TimeBean majorIdSearch(String senkou){
		TimeBean tbean = new TimeBean();

		try{
			statement = connect.prepareStatement("SELECT * FROM student_table WHERE MajorName = ?");
			statement.setString(1,senkou);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {

				tbean.setMajorId(resultSet.getInt("MajorId"));
			}

		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return tbean;

	}


	public boolean TimeTable_Registration(int majorId, int yearMonth, String dayTime, String ClassId, String room, String lessonId) {
		int result = 0;

		boolean exists = false;

		try{
			statement = connect.prepareStatement("INSERT INTO time_table (MajorId, YearMonth, DayTime, ClassId, Room, LessonId) VALUES (?, ?, ?, ?, ?, ?)");
			statement.setInt(1, majorId);
            statement.setInt(2, yearMonth);
            statement.setString(3, dayTime);
			statement.setString(4, ClassId);
            statement.setString(5, room);
            statement.setString(6, lessonId);
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


	public TimeBean lessonIdSearch(String subjectId){
		TimeBean tbean = new TimeBean();

		try{
			statement = connect.prepareStatement("SELECT * FROM lesson_table WHERE SubjectId = ?");
			statement.setString(1,subjectId);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {

				tbean.setLessonId(resultSet.getString("LessonId"));
			}

		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return tbean;

	}


}