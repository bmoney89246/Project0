package p0.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class CallableDriver{
	public void deleteTestCredentials(){
		Connection conn = ConnectionFactory.getConnection();
		try
		{
//			ResultSet resultSet = null;
			conn.setAutoCommit(false);
//			String sql = "{call deleteFakeUsernamesAndPasswords ()}"; // used to call functions in postgres
			PreparedStatement call = conn.prepareCall("call deleteFakeUsernamesAndPasswords ()"); // used for stored proc in Postgres
			// CallableStatement call = conn.prepareCall(sql); // used to call a function in
			// postgres
//			call.setDouble(1, 3.00);
//			call.setObject(2, resultSet, Types.OTHER);
			// call.registerOutParameter(2, Types.OTHER); // needed to set an out param for
			// a function
//			ResultSet ret = call.executeQuery(); 
			call.executeUpdate();
//			ret.next(); // load the first out param into ret
//			resultSet = (ResultSet) ret.getObject(1); grab the cursor (the first out param from our procedure)
	
			// resultSet = (ResultSet) call.getObject(2); // use to grab result set for a
			// function
	
//			while (resultSet.next()) {
				System.out.println("Test users deleted");
//			}
	
		}catch(
		SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
