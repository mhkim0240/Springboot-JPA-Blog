package com.cos.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// 1. 어노테이션 제거
@Configuration
public class SecurityConfig{ // 2. extends 제거

	// 3. principalDetailService 제거
	
	@Autowired
    private AuthenticationManager authenticationManager;


	// 4. AuthenticationManager 메서드 생성
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean // IoC가 되요!!
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}
	
	// 5. 기본 패스워드 체크가 BCryptPasswordEncoder 여서 설정 필요 없음.


	// 6. 최신 버전(2.7)으로 시큐리티 필터 변경
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// 1. csrf 비활성화
		http.csrf().disable();

		// 2. 인증 주소 설정
		http.authorizeRequests(
				authorize -> authorize.antMatchers("/", "/auth/**", "/js/**", "/css/**", "/image/**", "/dummy/**").permitAll()
						.anyRequest().authenticated()
		);
		
		// 3. 로그인 처리 프로세스 설정
		http.formLogin(f -> f.loginPage("/auth/loginForm")
				.loginProcessingUrl("/auth/loginProc")
				.defaultSuccessUrl("/")
		);

		return http.build();
	}
	
	 public void updatePassword(String pwd) {
	        Authentication currentAuthentication = SecurityContextHolder.getContext().getAuthentication();

	        // 새로운 인증 토큰 생성
	        UsernamePasswordAuthenticationToken newAuthenticationToken =
	                new UsernamePasswordAuthenticationToken(currentAuthentication.getPrincipal(), pwd);

	        // AuthenticationManager를 사용하여 인증을 갱신
	        Authentication refreshedAuthentication = authenticationManager.authenticate(newAuthenticationToken);

	        // SecurityContextHolder를 사용하여 현재 SecurityContext에 갱신된 인증 정보를 설정
	        SecurityContextHolder.getContext().setAuthentication(refreshedAuthentication);
	    }
}

/*
@Configuration // IoC
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private PrincipalDetailService principalDetailService;
	
	@Bean //IoC가 되요. 
	BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}

	//시큐리티가 대신 로그인 해주는데 password를 가로채기를 하는데
	//해당 password가 뭘로 해쉬가 되어 회원가입이 되었는지 알아야 
	//같은 해쉬로 암호화해서 DB에 있는 해쉬랑 비교할 수 있음.
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
	}
	
	//@Bean
	//SecurityFilterChain configure(HttpSecurity http) throws Exception {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.csrf().disable() //csrf 토큰 비활성화 (테스트시 걸어두는게 좋음)
			.authorizeRequests()
				.antMatchers("/","/auth/**","/js/**","/css/**","/image/**")
				.permitAll()
				.anyRequest()
				.authenticated()
			
			.and()
				.formLogin()
				//.loginPage("/auth/signin")
				.loginPage("/auth/loginForm")
				.loginProcessingUrl("/auth/loginProc")
				.defaultSuccessUrl("/"); //스프링 시큐리티가 해당 주소로 요청오는 로그인을 가로채서 대신 로그인 해준다.

			//.fail
		
		
		//return http.build();
	}
}
*/

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

/* 49강 설명에 최신버전 코드 

@Configuration // IoC
public class SecurityConfig {

	@Bean
	BCryptPasswordEncoder encode() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
				.antMatchers("/auth/**")
				.permitAll()
				.anyRequest()
				.authenticated()
				.and()
				.formLogin()
				.loginPage("/auth/signin");
		return http.build();
	}

}

 * */



