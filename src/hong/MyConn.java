package hong;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MyConn {

	
	Connection connection;
	Statement st;
	ResultSet rs;
	
	MyConn(){
		String url="jdbc:mysql://localhost:3306/hong";
		String ID="honghong";
		String PW="tlghk123";
		try {
			connection = DriverManager.getConnection(url,ID,PW);
			st = connection.createStatement();
			System.out.println("연결완료");	
					
		} catch (SQLException SQLex) {
			System.out.println("SQLException: " + SQLex.getMessage());
			System.out.println("SQLState: " + SQLex.getSQLState());
		}
	}
}

