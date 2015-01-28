package my.sql;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class MyHsqldbUtils
{
	//Название класса-драйвера
	private static String s_class_name = "org.hsqldb.jdbcDriver";
	//Имя БД
	private static String s_db_name = "mydb";
	//БД-url
	private static String s_db_url = "jdbc:hsqldb:file:dbpath/";
	//Имя пользователя
	private static String s_db_user = "SA";
	//Пароль пользователя
	private static String s_db_pass = "";
	
	
	//Выполнение SQL запроса (с возвращаемыми результатами)
	public static ResultSet returnResultSet(String s_sql)
	{
		ResultSet result_rs = null;
		Connection connection = null;
		Statement statement = null;
		
		try
		{
			Class.forName(s_class_name);
			connection = DriverManager.getConnection(s_db_url + s_db_name, s_db_user, s_db_pass);
			statement = connection.createStatement();
			result_rs = statement.executeQuery(s_sql);
		}
		catch( Exception e ) { e.printStackTrace(); }
		finally 
		{ 
			try { connection.close(); statement.close(); }
			catch( Exception e ) { e.printStackTrace(); }
		}
		
		return result_rs;
	}
	
	
	//Выполнение SQL запроса (результаты не нужны)
	public static void executeSql(String s_sql)
	{
		Connection connection = null;
		Statement statement = null;
		
		try
		{
			Class.forName(s_class_name);
			connection = DriverManager.getConnection(s_db_url + s_db_name, s_db_user, s_db_pass);
			statement = connection.createStatement();
			statement.executeUpdate(s_sql);
		}
		catch( Exception e ) { e.printStackTrace(); }
		finally 
		{ 
			try { connection.close(); statement.close(); }
			catch( Exception e ) { e.printStackTrace(); }
		}
	}
}
