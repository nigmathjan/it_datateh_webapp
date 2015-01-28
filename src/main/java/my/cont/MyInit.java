package my.contr;


import org.springframework.stereotype.Controller;
import javax.annotation.PostConstruct;


@Controller
public class MyInit
{
	@PostConstruct
	public void myInit()
	{
		MyUtils.init();
	}
}
