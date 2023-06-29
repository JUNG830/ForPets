package ForPets.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
  private final JwtFilter jwtFilter;
  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
      .csrf().disable()
      .formLogin().disable();

    return httpSecurity
          .authorizeHttpRequests(
            authorize -> authorize
                    // 인증없이 접근 가능한 권한 부여
                    .requestMatchers("/Main").permitAll()
                    .requestMatchers("/images/**", "/Login", "/SignUp").permitAll()
                    .anyRequest().authenticated() // 어떠한 요청이라도 인증필요
                    .and()
                    //여기
                    .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
          )
          .httpBasic(Customizer.withDefaults())
          .build();









//      .authorizeHttpRequests(request -> request
//              .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
//              // 인증없이 접근 가능한 권한 부여
//              .requestMatchers("/status", "/images/**", "/Login", "/SignUp").permitAll()
//              .anyRequest().authenticated()	// 어떠한 요청이라도 인증필요
//      )
//      .formLogin(login -> login	// form 방식 로그인 사용
//              .loginPage("/Login")	// [A] 커스텀 로그인 페이지 지정
//              .loginProcessingUrl("/Login")	// [B] submit 받을 url
//              .usernameParameter("id")	// [C] submit할 아이디
//              .passwordParameter("password")	// [D] submit할 비밀번호
//              .defaultSuccessUrl("/Main", true)	// 성공 시 dashboard로
//              .permitAll()	// 대시보드 이동이 막히면 안되므로 얘는 허용
//      )
//      .logout(withDefaults());	// 로그아웃은 기본설정으로 (/logout으로 인증해제)

    return http.build();
  }

//  @Bean
//  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//    // 인가(접근권한) 설정 (index : 모든 링크(사용자)에 대해 허용을 해 준 상태, 권한관리필터)
//    HttpSecurity.authorizeHttpRequests().antMatchers("/").permitAll();
//    // admin 하위의 모든 자원 -> "ADMIN"에게 부여
//    http.authorizeHttpRequests().antMatchers("/admin/**").hasRole("ADMIN");
//    // member 하위의 모든 자원 -> "ADMIN", "MEMBER" 에게 부여
//    http.authorizeHttpRequests().antMatchers("/member/**").hasAnyRole("ADMIN", "MEMBER");
//    // GUEST는 무권한 -> 생략
//    // loginSuccess 접근 -> "ADMIN"만 접근 허용
//    http.authorizeHttpRequests().antMatchers("/user2/loginSuccess").hasAnyRole("3", "4", "5");
//
//    // 사이트 위변조 요청 방지
//    http.csrf().disable();
//
//    // 로그인 설정
//    http.formLogin()
//            .loginPage("/user2/login")
//            .defaultSuccessUrl("/user2/loginSuccess")
//            .failureUrl("/user2/login?success=100)")
//            .usernameParameter("uid")
//            .passwordParameter("pass");
//
//    // 로그아웃 설정
//    http.logout()
//            .invalidateHttpSession(true)
//            .logoutRequestMatcher(new AntPathRequestMatcher("/user2/logout"))
//            .logoutSuccessUrl("/user2/login?success=200");
//
//    // 사용자 인증 처리 컴포넌트 서비스 등록
//    http.userDetailsService(service);
//    return http.build();
//  }

  @Bean
  public PasswordEncoder PasswordEncoder () {
    //return new MessageDigestPasswordEncoder("SHA-256");
    return new BCryptPasswordEncoder();
  }
}
