package my.contr;


import java.util.ArrayList;
import my.sql.*;


public class MyUtils
{
	//Инициализация
	public static void init()
	{
		MySqlConnections.init();
	} 
	
	
	//Проверка cookie, авторизирован ли данный пользователь
	public static boolean isAuth(String user_name, String user_pass)
	{
		return MySqlConnections.auth(user_name, user_pass);
	}
	
	
	//Взять свободный диск
	public static void takeDisk(String s_user_name, int n_disk_id)
	{
		MySqlConnections.takeDisk(s_user_name, n_disk_id);
	}
	
	
	//Вернуть диск
	public static void takeDiskBack(int n_disk_id)
	{
		MySqlConnections.takeDiskBack(n_disk_id);
	}
	
	
	//Создать диск
	public static void addNewDisk(String s_user_name, String s_disk_title)
	{
		MySqlConnections.addNewDisk(s_user_name, s_disk_title);
	}
	
	
	//Создать таблицу свободных дисков
	public static String createFreeDisksTable(String s_user_name)
	{
		String s_html = 
			"<table border=\"1px\">" + 
			"<tr>" + 
			"<td>Номер</td><td>Тайтл</td><td>Владелец</td>" +
			"</tr>";
		
		ArrayList<String[]> result_list = MySqlConnections.freeDiskList(s_user_name);
		
		for(String[] s : result_list)
		{
			s_html += "<tr><form action=\"/webapp/take_disk.cgi\">" +
				"<td>" + s[0] + "</td>" + 
				"<td>" + s[1] + "</td>" + 
				"<td>" + s[2] + "</td>" + 
				"<td><input name=\"disk_id\" type=\"hidden\" value=\"" + s[0] + "\"><input type=\"submit\" value=\"Взять\"></td>" +
				"</form></tr>";
		}
		
		return s_html + "</table>";
	}
	
	
	//Создать таблицу взятых пользователем дисков
	public static String createTakenByMeDisksTable(String s_user_name)
	{
		String s_html = 
			"<table border=\"1px\">" + 
			"<tr>" + 
			"<td>Номер</td><td>Тайтл</td><td>Владелец</td>" +
			"</tr>";
		
		ArrayList<String[]> result_list = MySqlConnections.showTakenMeDisks(s_user_name);
		
		for(String[] s : result_list)
		{
			s_html += "<tr><form action=\"/webapp/give_back_disk.cgi\">" +
				"<td>" + s[0] + "</td>" + 
				"<td>" + s[1] + "</td>" + 
				"<td>" + s[3] + "</td>" + 
				"<td><input name=\"disk_id\" type=\"hidden\" value=\"" + s[0] + "\"><input type=\"submit\" value=\"Вернуть\"></td>" +
				"</form></tr>";
		}
		
		return s_html + "</table>";
	}
	
	
	//Создать таблицу взятых у пользователя дисков
	public static String createTakenFromMeDisksTable(String s_user_name)
	{
		String s_html = 
			"<table border=\"1px\">" + 
			"<tr>" + 
			"<td>Номер</td><td>Тайтл</td><td>Кто взял</td>" +
			"</tr>";
		
		ArrayList<String[]> result_list = MySqlConnections.showMyTakenDisks(s_user_name);
		
		for(String[] s : result_list)
		{
			s_html += "<tr>" +
				"<td>" + s[0] + "</td>" + 
				"<td>" + s[1] + "</td>" + 
				"<td>" + s[3] + "</td>" + 
				"</tr>";
		}
		
		return s_html + "</table>";
	}
}
