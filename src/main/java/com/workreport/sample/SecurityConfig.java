package com.workreport.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.workreport.sample.service.LoginService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	LoginService loginService;


	@Override
    public void configure(WebSecurity web) throws Exception {
        // セキュリティ設定を無視するリクエスト設定
        // 静的リソースに対するアクセスはセキュリティ設定を無視する
        web.ignoring().antMatchers("/css/**", "/img/**", "/js/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

//    	 ログインなしでアクセスできるURLと、保護するURLを定義
        http.authorizeRequests()
//        ログインなしでアクセス可能
        .antMatchers("/member/register", "/login", "/authenticate", "/error", "/member/register/result", "/WorkRegist","/css/**", "/js/**", "/images/**","/webjars/**").permitAll()
//		権限を持っているアカウントのみアクセス可能
      .antMatchers("/MemberList/**").hasRole("ADMIN")
      .antMatchers("/WorkRegist/**").hasAnyRole("ADMIN","REGISTER")
//      上記以外アクセスにログインが必要（権限関係なし）
        .anyRequest().authenticated()
        .and()
        .formLogin() //ログインページを指定、だれでもアクセス可。
            .loginPage("/login")
			.loginProcessingUrl("/authenticate")		//ログイン実行のURL
			.defaultSuccessUrl("/menu", true)	//ログイン成功時、URLの記載値で移動
			.usernameParameter("login_id")				//ログイン実行時のUserNameに使用するinput name属性
			.passwordParameter("password")				//ログイン実行時のPasswordに使用するinput name属性
			.failureUrl("/login?error=1")				//ログイン失敗時に、URLの記載値で移動
			.permitAll()
		.and()
		.logout()
			.logoutSuccessUrl("/logoutSuccess")//ログアウト成功後、URLの記載値で移動
			.invalidateHttpSession(true)
			.permitAll();

//        RequestMatcher csrfRequestMatcher = new RequestMatcher() {
//
//            private AntPathRequestMatcher disabledRequestMatcher =
//                    new AntPathRequestMatcher("/loginSuccess");
//
//            @Override
//            public boolean matches(HttpServletRequest request) {
//
//                // GETならCSRFのチェックはしない
//                if("GET".equals(request.getMethod()))
//                    return false;
//
//                // 特定のURLに該当する場合、CSRFチェックしない
//                if(disabledRequestMatcher.matches(request))
//                    return false;
//
//                return true;
//            }
//
//        };
//        http.csrf().requireCsrfProtectionMatcher(csrfRequestMatcher);
    }

    /**
     * パスワードの暗号化方式を定義
     * @return
     */
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

//    @Autowired
//    protected void config(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//            .inMemoryAuthentication()
//                .withUser(
//                        User.withDefaultPasswordEncoder()
//                        .username("foo").password("foopass").roles("USER").build())
//                        .and()
//            .inMemoryAuthentication()
//                .withUser(
//                        User.withDefaultPasswordEncoder()
//                        .username("bar").password("barpass").roles("ADMIN", "USER").build());
//    }

	@Autowired
	void configureAuthenticationManager(AuthenticationManagerBuilder auth) throws Exception {
		//
		auth.userDetailsService(loginService)
			.passwordEncoder(passwordEncoder());
	}
}

