package jp.dev.kintaisys.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/** セキュリティの対象外を設定 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		// セキュリティを適用しない
		web.ignoring().antMatchers("/webjars/**").antMatchers("/css/**").antMatchers("/js/**")
				.antMatchers("/h2-console/**");
	}

	/** セキュリティの各種設定 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// ログイン不要ページの設定
		http.authorizeRequests().antMatchers("/login").permitAll() // 直リンクOK
				.antMatchers("/user/signup").permitAll() // 直リンクOK
				.antMatchers("/user/signup/rest").permitAll() // 直リンクOK
				.antMatchers("/admin").hasAuthority("ROLE_ADMIN") // 権限制御
				.anyRequest().authenticated(); // それ以外は直リンクNG

		// ログイン処理
		http.formLogin().loginProcessingUrl("/login") // ログイン処理のパス
				.loginPage("/login") // ログインページの指定
				.failureUrl("/login?error") // ログイン失敗時の遷移先
				.usernameParameter("loginId") // ログインページのユーザーID
				.passwordParameter("password") // ログインページのパスワード
				.defaultSuccessUrl("/task/top", true); // 成功後の遷移先

		// ログアウト処理
		http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutUrl("/logout")
				.logoutSuccessUrl("/login?logout");

		// CSRF対策を無効に設定（一時的）
		// http.csrf().disable();
	}

	/** 認証の設定 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		PasswordEncoder encoder = passwordEncoder();
		// インメモリ認証
		/*
		 * auth .inMemoryAuthentication() .withUser("user") // userを追加
		 * .password(encoder.encode("user")) .roles("GENERAL") .and() .withUser("admin")
		 * // adminを追加 .password(encoder.encode("admin")) .roles("ADMIN");
		 */

		// ユーザーデータ認証
		auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
	}
}
