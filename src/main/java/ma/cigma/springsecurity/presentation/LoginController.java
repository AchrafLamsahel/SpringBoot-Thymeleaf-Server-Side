package ma.cigma.springsecurity.presentation;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
	public String login(Model m) {
		return "login";
	}
	
	@GetMapping(value = "/welcome")
	public String welcome(Model m) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		m.addAttribute("userLogIn", auth.getName());
		return "welcome";
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView methodForAdmin() {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		modelAndView.addObject("userName", "Welcome " + auth.getName());
		modelAndView.addObject("adminMessage", "Content Available Only for Admins with ADMIN Role");
		modelAndView.setViewName("/admin/admin");
		return modelAndView;
	}
	
	@RequestMapping(value = "/client", method = RequestMethod.GET)
	public ModelAndView methodForClient() {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		modelAndView.addObject("userName", "Welcome " + auth.getName());
		modelAndView.addObject("clientMessage", "Content Available Only for Clients with CLIENT Role");
		modelAndView.setViewName("client/client");
		return modelAndView;
	}

	@RequestMapping(value = "/access-denied", method = RequestMethod.GET)
	public ModelAndView accessdenied() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("access-denied");
		return modelAndView;
	}
}