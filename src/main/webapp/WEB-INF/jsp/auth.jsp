<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
	<head>
		<title>Авторизация в системе</title>
	</head>
	
	<body>
		<form action="/webapp/auth.cgi" method="post">
			<h3>Вы еще не прошли авторизацию.</h3>
			<table>
				<tr>
					<td>Имя:</td><td><input name="login" type="text" size="20"></td>
				</tr>
				<tr>
					<td>Пароль:</td><td><input name="password" type="password" size="20"></td>
				</tr>
				<tr>
					<td></td><td><input type="submit" value="Вход"></td>
				</tr>
			</table>
		</form>
	</body>
</html>
