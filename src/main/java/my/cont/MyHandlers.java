package my.contr;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.*;
import javax.servlet.http.*;


@Controller
public class MyHandlers
{
	//Обработка запроса авторизации
	@RequestMapping("/auth.cgi")
	public String auth(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		String s_uname = request.getParameter("login");
		String s_upass = request.getParameter("password");
		
		//Если пользователь/пароль корректные
		if(MyUtils.isAuth(s_uname, s_upass))
		{
			//Сохраняем значения в cookie
			response.addCookie(new Cookie("user_name", s_uname));
			response.addCookie(new Cookie("user_pass", s_upass));
			
			//Отображаем домашнюю страницу
			model.addAttribute("user_name", s_uname);
			return "WEB-INF/jsp/home.jsp";
		}
		else
		{
			model.addAttribute("err", "Ошибка авторизации, неверный логин или пароль!");
			return "WEB-INF/jsp/err_page.jsp";
		}
	}
	
	
	//Обработка запроса на завершение сессии пользователя
	@RequestMapping("/logout.cgi")
	public String logout(HttpServletResponse response)
	{
		response.addCookie(new Cookie("user_name", "null"));
		response.addCookie(new Cookie("user_pass", "null"));
		
		return "WEB-INF/jsp/auth.jsp";
	}
	
	
	
	//Обработка запроса на взятие диска
	@RequestMapping("/take_disk.cgi")
	public String takeDisk
	(
		HttpServletRequest request,
		@CookieValue(value="user_name", defaultValue="anon") String user_name_cookie,
		@CookieValue(value="user_pass", defaultValue="anon") String user_pass_cookie
	)
	{
		if(MyUtils.isAuth(user_name_cookie, user_pass_cookie)) 
		{
			String s_disk_id = request.getParameter("disk_id");
			MyUtils.takeDisk(user_name_cookie, Integer.parseInt(s_disk_id));
			
			return "/free_disks.html";
		}
		else
		{
			return "WEB-INF/jsp/auth.jsp";
		}
	}
	
	
	//Обработка запроса на возвращение диска
	@RequestMapping("/give_back_disk.cgi")
	public String giveDiskBack
	(
		HttpServletRequest request,
		@CookieValue(value="user_name", defaultValue="anon") String user_name_cookie,
		@CookieValue(value="user_pass", defaultValue="anon") String user_pass_cookie
	)
	{
		if(MyUtils.isAuth(user_name_cookie, user_pass_cookie)) 
		{
			String s_disk_id = request.getParameter("disk_id");
			MyUtils.takeDiskBack(Integer.parseInt(s_disk_id));
			
			return "/taken_by_me_disks.html";
		}
		else
		{
			return "WEB-INF/jsp/auth.jsp";
		}
	}
	
	
	//Обработка запроса на создание нового диска
	@RequestMapping("/add_new_disk.cgi")
	public String addNewDisk
	(
		HttpServletRequest request,
		@CookieValue(value="user_name", defaultValue="anon") String user_name_cookie,
		@CookieValue(value="user_pass", defaultValue="anon") String user_pass_cookie
	)
	{
		if(MyUtils.isAuth(user_name_cookie, user_pass_cookie)) 
		{
			String s_disk_title = request.getParameter("disk_title");
			MyUtils.addNewDisk(user_name_cookie, s_disk_title);
			return "/index.html";
		}
		else
		{
			return "WEB-INF/jsp/auth.jsp";
		}
	}
}
