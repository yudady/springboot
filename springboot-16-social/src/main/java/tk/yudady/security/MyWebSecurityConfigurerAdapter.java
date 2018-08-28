package tk.yudady.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class MyWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

	String[] passUrls = {"/registration/**", "/authentication/**", "/logout/**"};
	String[] csrfUrls = {"/api/**", "/rest/**"};

	@Autowired
	private MyLogoutSuccessHandler myLogoutSuccessHandler;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
			// UsernamePasswordAuthenticationFilter
			.formLogin()//
			.usernameParameter("username").passwordParameter("password") //
			.loginPage("/authentication/request")//
			.loginProcessingUrl("/authentication/form") //
			.failureForwardUrl("/authentication/login") //
			.successForwardUrl("/redirectHome") //
			//
			.and()//
			.logout()//
			.logoutUrl("/logout")//
			.logoutSuccessHandler(myLogoutSuccessHandler)//
			.deleteCookies("JSESSIONID")//
			.clearAuthentication(true)//
			.invalidateHttpSession(true)//
			//
			.and()//
			.authorizeRequests().antMatchers(passUrls).permitAll()//
			.anyRequest().authenticated()//
			//
			.and().csrf().disable()//
		// .ignoringAntMatchers(csrfUrls)
		//
		// .and().and().sessionManagement().maximumSessions(1)
		//
		;
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService());

		// .passwordEncoder(passwordEncoder());
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return new MyUserDetailsService();
	};

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	};
}