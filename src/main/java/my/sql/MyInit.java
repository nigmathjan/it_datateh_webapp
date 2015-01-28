package my.sql;


public class MyInit
{
	//Инициализация бд
	public static void init()
	{
		try
		{
			MyHsqldbUtils.executeSql(s_sql_create_table_user);
			MyHsqldbUtils.executeSql(s_sql_create_table_disk);
			MyHsqldbUtils.executeSql(s_sql_create_table_takeitem);
			MyHsqldbUtils.executeSql(s_sql_set_table_user);
			MyHsqldbUtils.executeSql(s_sql_set_table_disk);
			MyHsqldbUtils.executeSql(s_sql_set_table_takeitem);
		} 
		catch( Exception e ) { e.printStackTrace(); }
	}
	
	
	//Поля
	//SQL запрос на создание таблицы пользователей
	private static String s_sql_create_table_user = 
		"DROP TABLE IF EXISTS user; " +
		"CREATE TABLE user" +
		"(" +
		"	id INT, " + 
		"	name CHAR(20), " + 
		"	pass CHAR(20), " +
		"	" +
		"	primary key(id) " +
		");";
	
	
	//SQL запрос на создание таблицы дисков
	private static String s_sql_create_table_disk = 
		"DROP TABLE IF EXISTS disk; " +
		"CREATE TABLE disk" +
		"(" +
		"	id INT, " + 
		"	title CHAR(50), " + 
		"	" +
		"	primary key(id) " +
		");";
	
	
	//SQL запрос на создание таблицы связь - пользователь - диск
	private static String s_sql_create_table_takeitem = 
		"DROP TABLE IF EXISTS takeitem; " +
		"CREATE TABLE takeitem" +
		"(" +
		"	disk_id INT, " + 
		"	owner_id INT, " + //Кто владелец
		"	captor_id INT" + //Кто взял
		");";
		
		
	//SQL запрос на заполнение таблицы user
	private static String s_sql_set_table_user = 
		"INSERT INTO user ( id, name, pass ) VALUES ( 0, 'John', '1234' );" +
		"INSERT INTO user ( id, name, pass ) VALUES ( 1, 'Paul', '4321' );" +
		"INSERT INTO user ( id, name, pass ) VALUES ( 2, 'George', '5678' );" +
		"INSERT INTO user ( id, name, pass ) VALUES ( 3, 'Ringo', '8765' );";
	
	
	//SQL запрос на заполнение таблицы disk
	private static String s_sql_set_table_disk = 
		"INSERT INTO disk ( id, title ) VALUES ( 0, 'Please Please Me' );" + 
		"INSERT INTO disk ( id, title ) VALUES ( 1, 'With The Beatles' );" + 
		"INSERT INTO disk ( id, title ) VALUES ( 2, 'A Hard Day s Night' );" + 
		"INSERT INTO disk ( id, title ) VALUES ( 3, 'Beatles For Sale' );" + 
		"INSERT INTO disk ( id, title ) VALUES ( 4, 'Help!' );" + 
		"INSERT INTO disk ( id, title ) VALUES ( 5, 'Rubber Soul' );" + 
		"INSERT INTO disk ( id, title ) VALUES ( 6, 'Revolver' );" + 
		"INSERT INTO disk ( id, title ) VALUES ( 7, 'Sgt. Pepper s Lonely Hearts Band' );" + 
		"INSERT INTO disk ( id, title ) VALUES ( 8, 'The Beatles' );" + 
		"INSERT INTO disk ( id, title ) VALUES ( 9, 'Yellow Submarine' );" + 
		"INSERT INTO disk ( id, title ) VALUES ( 10, 'Abbey Road' );" + 
		"INSERT INTO disk ( id, title ) VALUES ( 11, 'Let It Be' );";
	
	
	//SQL запрос на заполнение таблицы takeitem
	private static String s_sql_set_table_takeitem = 
		"INSERT INTO takeitem ( disk_id, owner_id, captor_id ) VALUES ( 0, 0, 0 );" + 
		"INSERT INTO takeitem ( disk_id, owner_id, captor_id ) VALUES ( 1, 0, 0 );" + 
		"INSERT INTO takeitem ( disk_id, owner_id, captor_id ) VALUES ( 2, 0, 2 );" + 
		"INSERT INTO takeitem ( disk_id, owner_id, captor_id ) VALUES ( 3, 1, 1 );" + 
		"INSERT INTO takeitem ( disk_id, owner_id, captor_id ) VALUES ( 4, 1, 1 );" + 
		"INSERT INTO takeitem ( disk_id, owner_id, captor_id ) VALUES ( 5, 1, 1 );" + 
		"INSERT INTO takeitem ( disk_id, owner_id, captor_id ) VALUES ( 6, 2, 2 );" + 
		"INSERT INTO takeitem ( disk_id, owner_id, captor_id ) VALUES ( 7, 2, 2 );" + 
		"INSERT INTO takeitem ( disk_id, owner_id, captor_id ) VALUES ( 8, 2, 2 );" + 
		"INSERT INTO takeitem ( disk_id, owner_id, captor_id ) VALUES ( 9, 3, 3 );" + 
		"INSERT INTO takeitem ( disk_id, owner_id, captor_id ) VALUES ( 10, 3, 3 );" + 
		"INSERT INTO takeitem ( disk_id, owner_id, captor_id ) VALUES ( 11, 3, 2 );";
}
