package ma.cigma.springsecurity;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import ma.cigma.springsecurity.dto.EmpVo;
import ma.cigma.springsecurity.dto.RoleVo;
import ma.cigma.springsecurity.dto.UserVo;
import ma.cigma.springsecurity.service.IEmpService;
import ma.cigma.springsecurity.service.IUserService;

/**
 * Les solutions pour corriger le problème de session closed au cas ou le mapping est Lazy :
 * 
 *  1- Value Object.
 *  2- OpenSessionInView : Spring utilise cette solution.
 *
 */

@SpringBootApplication
public class LoginCRUDApplication implements CommandLineRunner {

	@Autowired
	private IUserService userService;
	@Autowired
	private IEmpService empService;

	public static void main(String[] args) {
		SpringApplication.run(LoginCRUDApplication.class, args);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

	@Override
	public void run(String... args) throws Exception {
		userService.cleanDataBase();
		userService.save(new RoleVo("ADMIN"));
		userService.save(new RoleVo("CLIENT"));

		RoleVo roleAdmin = userService.getRoleByName("ADMIN");
		RoleVo roleClient = userService.getRoleByName("CLIENT");
		UserVo admin1 = new UserVo("admin1", "admin1", Arrays.asList(roleAdmin));
		UserVo client1 = new UserVo("client1", "client1", Arrays.asList(roleClient));
		userService.save(admin1);
		userService.save(client1);

		// *************
		empService.save(new EmpVo("emp1", 10000d, "Fonction1"));
		empService.save(new EmpVo("emp2", 20000d, "Fonction3"));
		empService.save(new EmpVo("emp3", 30000d, "Fonction4"));
		empService.save(new EmpVo("emp4", 40000d, "Fonction5"));
		empService.save(new EmpVo("emp5", 50000d, "Fonction6"));

	}

}
