package altenpfleger.sample.dbservices;

import java.sql.*;


public class DBManager {
	
	
private static final String url ="jdbc:mysql://aws.connect.psdb.cloud/altenpflege?sslMode=VERIFY_IDENTITY";
public static boolean connectionStatus = false;

public static Connection con;


public static void connectDB(String username, String password)
{
	try
	{
		Class.forName("com.mysql.jdbc.Driver");

		con = DriverManager.getConnection(url, username, password);

		connectionStatus = true;
		System.out.print("Connceted");
		
	}	
	catch(Exception e)
	{
		connectionStatus = false;
		e.printStackTrace();
	}

}

public static String printSQLException(SQLException ex) {
	for (Throwable e : ex) {
		if (e instanceof SQLException) {
			System. out.println ("\n---SQLException---\n");
			SQLException sqlex = (SQLException) e;
			System.err.println("Message: " +sqlex.getMessage());
			
			System.err.println ("SQLState: "+sqlex.getSQLState ());
			System.err.println ("Error Code: " +sqlex.getErrorCode ());
			
			System. out.println ("");
			return sqlex.getMessage();
		}
		
		if (e instanceof SQLWarning) {
			System. out.println ("\n---SQLWarning---\n");
			SQLException sqlw = (SQLWarning) e;
			System.err.println("Message: " +sqlw.getMessage());
			
			System. out.println ("SQLState: " + sqlw.getSQLState ());
			System.out.print ("Vendor error code: " + sqlw.getErrorCode ());
			
			System. out.println("");
			//warning = warning.getNextWarning();
			return sqlw.getMessage();
		}
		
	
	}
	
	ex.printStackTrace(System.err);
	return ex.getMessage();
}

public static void sendQuery(String querey) throws SQLException
{
	Statement stmt = DBManager.con.createStatement();
	int rs = stmt.executeUpdate(querey);

	System.out.println("Number of records: " + rs);
}

	public static void sendQueryRemove(String querey) throws SQLException
	{
		Statement stmt = DBManager.con.createStatement();
		int rs = stmt.executeUpdate(querey);

		System.out.println("Number of records: " + rs);
	}

public static void closeDB() throws SQLException
{
	try
	{
		con.close();
		System.out.print("Disconnected");
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	
}

}
