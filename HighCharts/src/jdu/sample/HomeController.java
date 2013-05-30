package jdu.sample;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/")
public class HomeController {

	@RequestMapping(value = "")
	public String home() {
		System.out.println("Home page: Passing through...");
		return "home";
	}


}