package my.sql;


import java.util.ArrayList;
import java.sql.ResultSet;


public class MySqlConnections
{
	//Вернуть взятый диск
	public static void takeDiskBack(int n_disk_id)
	{
		String s_sql = 
			"UPDATE takeitem " +
			"SET captor_id = owner_id " +
			"WHERE disk_id = " + n_disk_id + "; ";
			
		try
		{
			MyHsqldbUtils.executeSql(s_sql);
		}
		catch( Exception e ) { e.printStackTrace(); }
	}
	
	
	//Создать новый диск
	public static void addNewDisk(String s_user_name, String s_disk_title)
	{
		int n_user_id = userIdByName(s_user_name);
		int n_disk_id = maxDiskIndex()+1;
		
		String s_sql = 
			"INSERT INTO disk (id, title) VALUES( " + n_disk_id + ", '" + s_disk_title + "' ); " +
			"INSERT INTO takeitem (disk_id, owner_id, captor_id) VALUES( " + n_disk_id + ", " + n_user_id + ", " + n_user_id + " );";
			
		try
		{
			MyHsqldbUtils.executeSql(s_sql);
		}
		catch( Exception e ) { e.printStackTrace(); }
	}
	
	
	//Взять свободный диск
	public static void takeDisk(String s_user_name, int n_id)
	{
		String s_sql = 
			"UPDATE takeitem " +
			"SET captor_id = (SELECT id FROM user WHERE name='" + s_user_name + "') " + 
			"WHERE disk_id = " + n_id + ";";
			
		try
		{
			MyHsqldbUtils.executeSql(s_sql);
		}
		catch( Exception e ) { e.printStackTrace(); }
	}
	
	
	//Список взятых у пользователя дисков
	public static ArrayList<String[]> showMyTakenDisks(String s_user_name)
	{
		ArrayList<String[]> result_list = new ArrayList<String[]>();
		String s_sql = 
			"SELECT disk.id, disk.title, user.id, user.name " +
			"FROM disk, takeitem, user " +
			"WHERE takeitem.owner_id <> takeitem.captor_id " +
			"AND takeitem.owner_id = " + userIdByName(s_user_name) + " " +
			"AND disk.id = takeitem.disk_id " +
			"AND user.id = takeitem.captor_id; ";
		
		try
		{
			ResultSet rs_temp = MyHsqldbUtils.returnResultSet(s_sql);
			while(rs_temp.next())
			{
				String s_buf[] = new String[4];
				
				s_buf[0] = Integer.toString(rs_temp.getInt("disk.id"));
				s_buf[1] = rs_temp.getString("disk.title");
				s_buf[2] = Integer.toString(rs_temp.getInt("user.id"));
				s_buf[3] = rs_temp.getString("user.name");
				
				result_list.add(s_buf);
			}
		}
		catch( Exception e ) { e.printStackTrace(); }
		
		return result_list;
	}
	
	
	//Список взятых пользователем дисков
	public static ArrayList<String[]> showTakenMeDisks(String s_user_name)
	{
		ArrayList<String[]> result_list = new ArrayList<String[]>();
		String s_sql = 
			"SELECT disk.id, disk.title, user.id, user.name " +
			"FROM disk, takeitem, user " +
			"WHERE takeitem.owner_id <> takeitem.captor_id " +
			"AND takeitem.captor_id = " + userIdByName(s_user_name) + " " +
			"AND disk.id = takeitem.disk_id " +
			"AND user.id = takeitem.owner_id; ";
			
		try
		{
			ResultSet rs_temp = MyHsqldbUtils.returnResultSet(s_sql);
			while(rs_temp.next())
			{
				String s_buf[] = new String[4];
				
				s_buf[0] = Integer.toString(rs_temp.getInt("disk.id"));
				s_buf[1] = rs_temp.getString("disk.title");
				s_buf[2] = Integer.toString(rs_temp.getInt("user.id"));
				s_buf[3] = rs_temp.getString("user.name");
				
				result_list.add(s_buf);
			}
		}
		catch( Exception e ) { e.printStackTrace(); }
		
		return result_list;
	}
	
	
	//Список всех невзятых дисков у пользователей, исключая свои
	public static ArrayList<String[]> freeDiskList(String s_user_name)
	{
		ArrayList<String[]> result_list = new ArrayList<String[]>();
		
		String s_sql = 
			"SELECT disk.id, disk.title, user.name " +
			"FROM user, takeitem, disk " +
			"WHERE takeitem.owner_id=takeitem.captor_id " +
			"AND disk.id=takeitem.disk_id " +
			"AND user.id=takeitem.owner_id " +
			"AND user.name <> '" + s_user_name + "' " + 
			"GROUP BY disk.id, disk.title, user.name;";
		
		try
		{
			ResultSet rs_temp = MyHsqldbUtils.returnResultSet(s_sql);
			while(rs_temp.next())
			{
				String[] s_buf = new String[3];
				
				s_buf[0] = Integer.toString(rs_temp.getInt("disk.id"));
				s_buf[1] = rs_temp.getString("disk.title");
				s_buf[2] = rs_temp.getString("user.name");
				
				result_list.add(s_buf);
			}
		}
		catch( Exception e ) { e.printStackTrace(); }
		
		return result_list;
	}
	
	
	//Авторизация (проверка, есть ли пользователь с таким именем и паролем в базе)
	public static boolean auth(String s_name, String s_pass)
	{
		String s_sql = "SELECT id FROM user WHERE name='" + s_name + "' AND pass='" + s_pass + "';";
		
		try
		{
			ResultSet rs_temp = MyHsqldbUtils.returnResultSet(s_sql);
			while(rs_temp.next()) { return true; }
		}
		catch( Exception e ) { e.printStackTrace(); }
		
		return false;
	}
	
	
	//Узнать id пользователя по имени
	private static int userIdByName(String s_username)
	{
		String s_sql = 
			"SELECT id FROM user WHERE name='" + s_username + "'";
		int n_result = -1;
		
		try
		{
			ResultSet rs_temp = MyHsqldbUtils.returnResultSet(s_sql);
			while(rs_temp.next())
			{
				n_result = rs_temp.getInt("id");
			}
		}
		catch( Exception e ) { e.printStackTrace(); }
		
		return n_result;
	}
	
	
	//Узнать максимальный индекс диска из аблицы disk
	private static int maxDiskIndex()
	{
		String s_sql = 
			"SELECT MAX(id) AS max_disk_id FROM disk;";
			
		int n_result = -1;
		
		try
		{
			ResultSet rs_temp = MyHsqldbUtils.returnResultSet(s_sql);
			while(rs_temp.next())
			{
				n_result = rs_temp.getInt("max_disk_id");
			}
		}
		catch( Exception e ) { e.printStackTrace(); }
		
		return n_result;
	}
	
	
	//Инициализация бд
	public static void init()
	{
		MyInit.init();
	}
}
