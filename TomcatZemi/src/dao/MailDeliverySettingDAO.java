package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.MailDeliverySettingBean;

public class MailDeliverySettingDAO extends OpenAndCloseDAO {

	private PreparedStatement statement = null;

	public MailDeliverySettingDAO(){
		open();
	}


	public boolean mailDeliverySetting(String studentId, int categoryId1, int categoryId2, int categoryId3, int categoryId4, int categoryId5) {

		int result = 0;
        boolean exists =false;




		try{
			statement = connect.prepareStatement("UPDATE mail_delivery_table SET CategoryId1=?, CategoryId2=?, CategoryId3=?, CategoryId4=?, CategoryId5=? WHERE StudentId=?");
			statement.setInt(1, categoryId1);
			statement.setInt(2, categoryId2);
			statement.setInt(3, categoryId3);
			statement.setInt(4, categoryId4);
			statement.setInt(5, categoryId5);
			statement.setString(6, studentId);
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


	public boolean mailDeliverySettingStart(String studentId, int categoryId1, int categoryId2, int categoryId3, int categoryId4, int categoryId5) {

		int result = 0;
        boolean exists =false;




		try{
			statement = connect.prepareStatement("INSERT INTO mail_delivery_table (studentId, categoryId1, categoryId2, categoryId3, categoryId4, categoryId5) VALUES (?, ?, ?, ?, ?, ?)");
			statement.setString(1, studentId);
			statement.setInt(2, categoryId1);
			statement.setInt(3, categoryId2);
			statement.setInt(4, categoryId3);
			statement.setInt(5, categoryId4);
			statement.setInt(6, categoryId5);

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


	public MailDeliverySettingBean mailDeliveryInfoGet(String studentId) {
		MailDeliverySettingBean mdsbean = new MailDeliverySettingBean();


		try {
        	statement = connect.prepareStatement("SELECT * FROM mail_delivery_table WHERE StudentId = ?");
        	statement.setString(1,studentId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

            	mdsbean.setCategoryId1(resultSet.getInt("CategoryId1"));
            	mdsbean.setCategoryId2(resultSet.getInt("CategoryId2"));
            	mdsbean.setCategoryId3(resultSet.getInt("CategoryId3"));
            	mdsbean.setCategoryId4(resultSet.getInt("CategoryId4"));
            	mdsbean.setCategoryId5(resultSet.getInt("CategoryId5"));
            }

        }catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

        return mdsbean;

	}

}