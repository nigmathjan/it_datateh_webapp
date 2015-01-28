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
public class MyPageCreator
{
	//Вывод начальной страницы
	@RequestMapping("/index.html")
	public String gimmeIndexPage
	(
		@CookieValue(value="user_name", defaultValue="anon") String user_name_cookie, 
		@CookieValue(value="user_pass", defaultValue="anon") String user_pass_cookie,
		HttpServletRequest request, 
		HttpServletResponse response, 
		Model model
	)
	{
		if(MyUtils.isAuth(user_name_cookie, user_pass_cookie)) 
		{
			model.addAttribute("user_name", user_name_cookie);
			return "WEB-INF/jsp/home.jsp";
		}
		else
		{
			return "WEB-INF/jsp/auth.jsp"; 
		}
	}
	
	
	//Запрос таблицы свободных дисков
	@RequestMapping("/free_disks.html")
	public String gimmeFreeDisksPage
	(
		@CookieValue(value="user_name", defaultValue="anon") String user_name_cookie, 
		@CookieValue(value="user_pass", defaultValue="anon") String user_pass_cookie,
		Model model
	)
	{
		if(MyUtils.isAuth(user_name_cookie, user_pass_cookie)) 
		{
			model.addAttribute("free_disks_table", MyUtils.createFreeDisksTable(user_name_cookie));
			return "WEB-INF/jsp/free_disks.jsp";
		}
		else
		{
			return "WEB-INF/jsp/auth.jsp";
		}
	}
	
	
	//Вывод списка взятых пользователем дисков
	@RequestMapping("/taken_by_me_disks.html")
	public String gimmeTakenByMeDisksPage
	(
		@CookieValue(value="user_name", defaultValue="null") String user_name_cookie, 
		@CookieValue(value="user_pass", defaultValue="null") String user_pass_cookie,
		Model model
	)
	{
		if(MyUtils.isAuth(user_name_cookie, user_pass_cookie)) 
		{
			model.addAttribute("taken_by_me_disks_table", MyUtils.createTakenByMeDisksTable(user_name_cookie));
			return "WEB-INF/jsp/taken_by_me_disks.jsp";
		}
		else
		{
			return "WEB-INF/jsp/auth.jsp";
		}
	}
	
	
	//Вывод списка взятых у пользователя дисков
	@RequestMapping("/taken_from_me_disks.html")
	public String gimmeTakenFromMeDisksPage
	(
		@CookieValue(value="user_name", defaultValue="anon") String user_name_cookie, 
		@CookieValue(value="user_pass", defaultValue="anon") String user_pass_cookie,
		Model model
	)
	{
		if(MyUtils.isAuth(user_name_cookie, user_pass_cookie)) 
		{
			model.addAttribute("taken_from_me_disks_table", MyUtils.createTakenFromMeDisksTable(user_name_cookie));
			return "WEB-INF/jsp/taken_from_me_disks.jsp";
		}
		else
		{
			return "WEB-INF/jsp/auth.jsp";
		}
	}
	
	
	//Вывод формы создания диска
	@RequestMapping("/new_disk.html")
	public String gimmeNewDiskForm()
	{
		return "WEB-INF/jsp/new_disk.jsp";
	}
}
