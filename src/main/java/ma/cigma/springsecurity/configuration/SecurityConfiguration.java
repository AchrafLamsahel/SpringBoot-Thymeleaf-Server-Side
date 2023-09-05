package ma.cigma.springsecurity.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import ma.cigma.springsecurity.service.IUserService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private PasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private IUserService userService;

	// par polymorphisime, la JVM (dans l'exécution) exécutera la méthode configure() de la classe
	// SecurityConfiguration et non pas celle de la classe
	// WebSecurityConfigurerAdapter
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/rest/**").hasAuthority("ADMIN");
		// CROSS SITE REQUEST FORGERY : La falsification des requêtes inter sites
//		http.csrf().disable();
		http.authorizeRequests().antMatchers("/").permitAll().
		antMatchers("/login").permitAll().
		antMatchers("/welcome").hasAnyAuthority("ADMIN", "CLIENT").
		antMatchers("/admin/**").hasAuthority("ADMIN")
		.antMatchers("/client/**").hasAuthority("CLIENT").
		anyRequest().authenticated()
		.and().
		formLogin().loginPage("/login").
		failureUrl("/login?error=true").
		defaultSuccessUrl("/welcome")
		.usernameParameter("username").
		passwordParameter("password").
		and().
		logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).
		logoutSuccessUrl("/").
		and().
		exceptionHandling().accessDeniedPage("/access-denied");
	}

	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
	}

}
