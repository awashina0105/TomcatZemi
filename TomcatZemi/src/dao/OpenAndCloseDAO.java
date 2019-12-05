package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OpenAndCloseDAO {
	protected Connection connect = null;


	public void open() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost/zemi", "TomcatZemi", "tomcat" );

		}catch (SQLException e) {
			e.printStackTrace();

		}catch (ClassNotFoundException e) {
			e.printStackTrace();

		}finally {

		}
	}


	public void close() {
		if(connect != null){
			try {
				connect.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


}
