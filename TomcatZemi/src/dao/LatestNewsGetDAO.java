package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.LatestNewsGetArrayBean;
import bean.LatestNewsGetBean;

public class LatestNewsGetDAO extends OpenAndCloseDAO {

	private PreparedStatement statement = null;

	public LatestNewsGetDAO(){
		open();
	}





	public LatestNewsGetArrayBean latestNewsGet(Date today) {

		LatestNewsGetArrayBean latestNewsGetArray = new LatestNewsGetArrayBean();
		ResultSet result = null;

		try{
			statement = connect.prepareStatement("SELECT * FROM notice_table WHERE Date => today");
	        statement.setDate(1,today);
			result = statement.executeQuery();

			while(result.next()){
				LatestNewsGetBean lngbean = new LatestNewsGetBean();
				lngbean.setNoticeId(result.getInt("NoticeId"));
				lngbean.setNoticeTitle(result.getString("NoticeTitle"));
				lngbean.setCategoryId(result.getInt("CategoryId"));
				lngbean.setTeacherId(result.getString("TeacherId"));
				lngbean.setPageUrl(result.getString("PageUrl"));
				lngbean.setDate(result.getDate("Date"));

				latestNewsGetArray.addLatestNewsGetArray(lngbean);
			}
			}catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally {
				close();
			}
			return latestNewsGetArray;
		}


	public LatestNewsGetArrayBean categoryNameGet(int categoryId) {

		LatestNewsGetArrayBean categoryNameArray = new LatestNewsGetArrayBean();
		ResultSet result = null;

		try{
			statement = connect.prepareStatement("SELECT * FROM category_table WHERE CategoryId = ?");
	        statement.setInt(1,categoryId);
			result = statement.executeQuery();

			while(result.next()){
				LatestNewsGetBean lngbean = new LatestNewsGetBean();
				lngbean.setCategoryName(result.getString("CategoryName"));
				categoryNameArray.addLatestNewsGetArray(lngbean);
			}
			}catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally {
				close();
			}
			return categoryNameArray;
		}

	public LatestNewsGetArrayBean CategoryIdNewsGet(Date today, int CategoryId) {

		LatestNewsGetArrayBean latestNewsGetArray = new LatestNewsGetArrayBean();
		ResultSet result = null;

		try{
			statement = connect.prepareStatement("SELECT * FROM notice_table WHERE Date => today AND CategoryId = ?");
	        statement.setDate(1,today);
	        statement.setInt(2,CategoryId);
			result = statement.executeQuery();

			while(result.next()){
				LatestNewsGetBean lngbean = new LatestNewsGetBean();
				lngbean.setNoticeId(result.getInt("NoticeId"));
				lngbean.setNoticeTitle(result.getString("NoticeTitle"));
				lngbean.setCategoryId(result.getInt("CategoryId"));
				lngbean.setTeacherId(result.getString("TeacherId"));
				lngbean.setPageUrl(result.getString("PageUrl"));
				lngbean.setDate(result.getDate("Date"));

				latestNewsGetArray.addLatestNewsGetArray(lngbean);
			}
			}catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally {
				close();
			}
			return latestNewsGetArray;
		}


}