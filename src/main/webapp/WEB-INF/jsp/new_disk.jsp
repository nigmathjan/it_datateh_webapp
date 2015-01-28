<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
	<head>
		<title>Страница пользователя</title>
	</head>
	
	<body>
		<h3><b>Создать диск</b></h3>
		
		<form action="/webapp/add_new_disk.cgi" method="post">
			Название нового диска: <input name="disk_title" type="text">
			<input type="submit" value="Завести диск">
		</form>
		
		<br/>
		<a href="/webapp/index.html">Домашняя страница</a>
	</body>
</html>
