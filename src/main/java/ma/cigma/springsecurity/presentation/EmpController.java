package ma.cigma.springsecurity.presentation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ma.cigma.springsecurity.domaine.EmpVo;
import ma.cigma.springsecurity.service.IEmpService;

@Controller
@RequestMapping("/admin/emp")

//@CrossOrigin("http://localhost:4200")
public class EmpController {

	@Autowired
	private IEmpService service;

//	@RequestMapping("/form")
//	public String showform(Model m) {
//		m.addAttribute("empVo", new EmpVo());
//		return "/admin/emp/edit";// resources/templates/admin/emp/edit.html
//	}
	
	@RequestMapping("/form")
	public ModelAndView showform() {
		ModelAndView m=new ModelAndView();
		m.addObject("empVo", new EmpVo());
		m.setViewName("/admin/emp/edit");// resources/templates/admin/emp/edit.html
		return m;
	}

	@PostMapping(value = "/save")
	public String save(@ModelAttribute("empVo") EmpVo emp) {
		service.save(emp);
		return "redirect:/admin/emp/list";// will redirect to viewemp request mapping
	}

	@RequestMapping("/list")
	public String viewemp(Model m) {
		List<EmpVo> list = service.getEmployees();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		m.addAttribute("userName", "Welcome " + auth.getName());
		m.addAttribute("list", list);
		return "/admin/emp/list";
	}
	
//	@RequestMapping("/list")
//	public ModelAndView viewemp() {
//		ModelAndView m=new ModelAndView();
//		List<EmpVo> list = service.getEmployees();
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		m.addObject("userName", "Welcome " + auth.getName());
//		m.addObject("list", list);
//		m.setViewName("/admin/emp/list");
//		return m;
//	}

	@RequestMapping(value = "/edit/{id}")
	public String edit(@PathVariable Long id, Model m) {
		EmpVo emp = service.getEmpById(id);
		m.addAttribute("empVo", emp);
		return "/admin/emp/edit";
	}

	@RequestMapping(value = "/editsave", method = RequestMethod.POST)
	public String editsave(@ModelAttribute("empVo") EmpVo emp) {
		service.save(emp);
		return "redirect:/admin/emp/view";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable Long id) {
		service.delete(id);
		return "redirect:/admin/emp/list";
	}

	@RequestMapping("/emp/salary/{salary}")
	public String getBySalary(@PathVariable Double salary, Model m) {
		List<EmpVo> list = service.findBySalary(salary);
		m.addAttribute("list", list);
		return "/admin/emp/list";
	}

	/**
	 * Chercher la liste des employés ayant la même fonction
	 */
	@RequestMapping("/emp/fonction/{fonction}")
	public String getByFonction(@PathVariable String fonction, Model m) {
		List<EmpVo> list = service.findByFonction(fonction);
		m.addAttribute("list", list);
		return "/admin/emp/list";
	}

	/**
	 * Chercher la liste des employés ayant le même salaire et la même fonction
	 */
	@RequestMapping("/emp/salary_and_fonction/{salary}/{fonction}")
	public String getBySalaryAndFonction(@PathVariable Double salary, @PathVariable String fonction, Model m) {
		List<EmpVo> list = service.findBySalaryAndFonction(salary, fonction);
		m.addAttribute("list", list);
		return "/admin/emp/list";
	}

	/**
	 * Chercher l'employé qui le grand salaire
	 */
	@RequestMapping("/max_salary")
	public String getMaxSalary(Model m) {
		EmpVo empVo = service.getEmpHavaingMaxSalary();
		List<EmpVo> list = new ArrayList<>();
		list.add(empVo);
		m.addAttribute("list", list);
		return "/admin/emp/view";
	}

	/**
	 * Afficher la liste des employés en utilisant la pagination
	 */
	@RequestMapping("/pagination/{pageid}/{size}")
	public String pagination(@PathVariable int pageid, @PathVariable int size, Model m) {
		List<EmpVo> list = service.findAll(pageid, size);
		m.addAttribute("list", list);
		return "/admin/emp/view";
	}

	/**
	 * Trier les employés par le nom de champs qu'on passe dans l'URL
	 */
	@RequestMapping("/sort/{fieldName}")
	public String sortBy(@PathVariable String fieldName, Model m) {
		List<EmpVo> list = service.sortBy(fieldName);
		m.addAttribute("list", list);
		return "/admin/emp/view";
	}
}