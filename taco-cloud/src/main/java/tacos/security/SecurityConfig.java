package tacos.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	// 사용자의 HTTP 요청 경로에 대해 접근 제한과 같은 보안 관련 처리를 커스터마이징 하는 클래스
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
			.antMatchers("/design","/orders")
				.access("hasRole('ROLE_USER')")
			.antMatchers("/","/**").access("permitAll")
		.and()
			.httpBasic();
		http.csrf().disable();
		http.headers().frameOptions().disable();
		// h2-console 사용하기 위해 해당경로는 disalbe 처리
		/*
		 *  http .authorizeRequests()
		 * .antMatchers("/design","/orders") .access("hasRole('ROLE_USER')")
		 * .antMatchers("/","/**").access("permitAll")
		 * .antMatchers("/h2-console/**").permitAll() .and() .csrf()
		 * .ignoringAntMatchers("/h2-console/**").disable() .httpBasic();
		 */
	}
	
	@Autowired
	DataSource dataSource;
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.jdbcAuthentication()
			.dataSource(dataSource)
			.usersByUsernameQuery(
				"select username, password, enabled from users " +
				"where username=?"
					)
			.authoritiesByUsernameQuery(
				"select username, authority from authorities" + 
				"where username=?"
					)
			.passwordEncoder(new NoEncodingPasswordEncoder());
		/* 인메모리 방식으로 사용자 관리(하드코딩)
		 * auth.inMemoryAuthentication() .withUser("user1") .password("{noop}password1")
		 * .authorities("ROLE_USER") .and() .withUser("user2")
		 * .password("{noop}password2") .authorities("ROLE_USER");
		 */
	}

}
