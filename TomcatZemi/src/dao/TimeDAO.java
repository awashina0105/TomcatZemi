package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import bean.TimeBean;

public class TimeDAO extends OpenAndCloseDAO {

	private PreparedStatement statement = null;

	public TimeDAO(){
		open();
	}


	public boolean time(int majorId, int yearMonth, String dayTime, String classId, String room, String lessonId) {
		int result = 0;

		boolean exists = false;

		TimeBean tbean = new TimeBean();


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






}