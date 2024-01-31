package com.cos.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // IoC
public class SecurityConfig {

	@Bean
	BCryptPasswordEncoder encode() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable() //csrf 토큰 비활성화
		.authorizeRequests().antMatchers("/","/auth/**","/js/**","/css/**","/image/**").permitAll().anyRequest().authenticated().and().formLogin()
				.loginPage("/auth/signin");
		return http.build();
	}
}

/*
 * //빈 등록 : 스프링 컨테이너에서 객체를 관리할 수 있게 하는 것.
 * 
 * @Configuration //빈등록 (IoC 관리)
 * 
 * @EnableWebSecurity //시큐리티 필터를 추가 => 스프링 시큐리티가 이미 활성화 되 있고, 어떤 설정을 해당 파일에서
 * 하겠다.
 * 
 * @EnableGlobalMethodSecurity(prePostEnabled=true) //특정 주소로 접근을 하면 권한 및 인증을 미리
 * 체크하겠다는 뜻. public class SecurityConfig extends WebSecurityConfigurerAdapter {
 * 
 * @Override protected void configure(HttpSecurity http) throws Exception{ http
 * .authorizeRequests() .antMatchers("/auth/**") .permitAll() .anyRequest()
 * .authenticated() .and() .formLogin() .loginPage("/auth/loginForm"); }
 * 
 * }
 */