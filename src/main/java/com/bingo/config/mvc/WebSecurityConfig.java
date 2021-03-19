package com.bingo.config.mvc;

import java.io.PrintWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @Configuration</br>
 *                      在 Spring boot 应用中使用 Spring
 *                     Security，用到了 @EnableWebSecurity注解，官方说明为，该注解和 @Configuration
 *                     注解一起使用, 注解 WebSecurityConfigurer
 *                     类型的类，或者利用@EnableWebSecurity 注解继承
 *                     WebSecurityConfigurerAdapter的类，这样就构成了 Spring Security
 *                     的配置。
 *
 */
@Configuration
// @EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserService userService;

	@Override
	 protected void configure(HttpSecurity http) throws Exception {
			http
					.authorizeRequests()
					.antMatchers("/user/**").hasAnyRole("ADMIN", "USER")// 添加/product/**
																// 下的所有请求只能由user角色才能访问
					.antMatchers("/admin/**").hasRole("ADMIN") // 添加/admin/**
																// 下的所有请求只能由admin角色才能访问
					.anyRequest().authenticated() // 没有定义的请求，所有的角色都可以访问（tmp也可以）。
					.and()
					.formLogin()
					.successHandler((request, response, auth) -> {
						Object object = auth.getPrincipal();
						response.setContentType("application/json;charset=utf-8");
						PrintWriter printWriter = response.getWriter();
						printWriter.write(new ObjectMapper().writeValueAsString(object));
						printWriter.flush();
						printWriter.close();
					})
					.and()
					.rememberMe()
					.key("boot")
					.and()
					.logout()
					.and()
					// .csrf().disable()// 关闭自动防御 CSRF 攻击
					// .and()
					// .httpBasic()
					// 只允许一个登录 user必须重写hashcode和equals
					.sessionManagement()
					.maximumSessions(1)
					// 禁止其他人登录
					.maxSessionsPreventsLogin(true)
			;
	 /*http.authorizeRequests()
				// 匹配指定路径
				.antMatchers("/", "/home", "/user/login")
				// 指定任何人都可以访问
				.permitAll()
				// 指定所有请求
				.anyRequest()
				// 所有人通过认证的都可以访问
				.authenticated()
				.and()
				// 指定支持基于表单的身份验证。如果未指定FormLoginConfigurer#loginPage(String)，则将生成默认登录页面
				.formLogin()
				// 指定登录页
				.loginPage("/login")
				// 指定任何人都可以访问
				.permitAll()
				.and()
				// 添加退出登录支持。当使用WebSecurityConfigurerAdapter时，这将自动应用。默认情况是，访问URL”/
				// logout”，使HTTP
				// Session无效来清除用户，清除已配置的任何#rememberMe()身份验证，清除SecurityContextHolder，然后重定向到”/login?success”
				.logout()
				// 指定任何人都可以访问
				.permitAll()
				// 配置 Http Basic 验证
				// .and()
				// .httpBasic()
				;
				*/
	 }

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		/*auth.inMemoryAuthentication()
				.withUser("admin") // 添加用户admin
				.password("{noop}admin") // 不设置密码加密
				.roles("ADMIN", "USER")// 添加角色为admin，user
				.and()
				.withUser("user") // 添加用户user
				.password("{noop}user")
				.roles("USER")
				.and()
				.withUser("tmp") // 添加用户tmp
				.password("{noop}tmp")
				.roles(); // 没有角色
				*/
		auth.userDetailsService(userService)
				.passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		// return NoOpPasswordEncoder.getInstance();// 使用不使用加密算法保持密码
		return new BCryptPasswordEncoder();
	}

	@Bean
	public HttpSessionEventPublisher httpSessionEventPublisher() {
		return new HttpSessionEventPublisher();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		// 添加不做权限的URL
		web.ignoring()
				.antMatchers("/swagger-resources/**")
				.antMatchers("/swagger-ui.html")
				.antMatchers("/webjars/**")
				.antMatchers("/v2/**")
				.antMatchers("/js/**")
				.antMatchers("/css/**")
				.antMatchers("/images/**")
				.antMatchers("/h2-console/**");
	}

}
