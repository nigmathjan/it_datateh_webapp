<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
	<head>
		<title>Страница пользователя</title>
	</head>
	
	<body>
		<h3><b>Добро пожаловать, ${user_name}</b>  [<a href="/webapp/logout.cgi">Выйти</a>]</h3>
		<a href="/webapp/free_disks.html">Свободные диски</a><br/>
		<a href="/webapp/taken_by_me_disks.html">Список взятых мною дисков</a><br/>
		<a href="/webapp/taken_from_me_disks.html">Список дисков, взятых у меня</a><br/>
		<a href="/webapp/new_disk.html">Завести новый диск</a><br/>
	</body>
</html>
