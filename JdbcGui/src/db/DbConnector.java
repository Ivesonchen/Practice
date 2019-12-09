package db;

import java.sql.*;

import javax.swing.JTextArea;

public class DbConnector {
	public Connection conn;
	public Statement stmt;
	public ResultSet rs;
	public String message = "";
	
	public String connectDb() {
		String usr ="root";
		String pwd ="123";
		String url ="jdbc:mysql://localhost:3306/taotao";

		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Success loading Driver!");
			message += " Success loading Driver!\n";
			
		}
		catch(Exception e){
			System.out.println("Fail loading Driver!");
			message += " Fail loading Driver!\n";
			e.printStackTrace();
		}

		try{
			conn = DriverManager.getConnection(url, usr, pwd);
			System.out.println("Success connecting server!");
			message += " Success connecting server!\n";

//			show(rs);
		}catch(SQLException e){
			System.out.println("Connection URL or username or password errors!");
			message += " Connection URL or username or password errors!\n";
			e.printStackTrace();
		}
		
		return message;
	}
	
	public void show(ResultSet rs) throws SQLException 
	{
		while (rs.next())
		{
			System.out.println(rs.getString("cust") + " " + rs.getString("prod") + " " + rs.getString("day") + " " +
				   	rs.getString("month") + " " + rs.getString("year") + " " + rs.getString("quant"));
		}
	}
	
//	static public void main(String args[]) {
//		
//		DbConnector db = new DbConnector();
//		db.connectDb();		
//	}
	
	public ResultSet exec(String command, JTextArea resultArea) {
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(command);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			resultArea.append(e.getMessage() + "\n");
		}
		return rs;
	}
	
}
