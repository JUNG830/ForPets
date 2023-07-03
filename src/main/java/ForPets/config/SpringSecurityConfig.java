package ForPets.config;

import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableMethodSecurity
public class SpringSecurityConfig {
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
//  @Bean
//  PasswordEncoder passwordEncoder() {
//    return new SimplePasswordEncoder();
//  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf().disable().cors().disable()
            .authorizeHttpRequests(request -> request
                    .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                    .requestMatchers("/status", "/images/**", "/Join", "/auth/join", "/Login", "/dashboard").permitAll()
                    .anyRequest().authenticated() // 어떠한 요청이라도 인증필요
            )
            .formLogin(login -> login                       // form 방식 로그인 사용
                    .loginPage("/Login")                    // [A] 커스텀 로그인 페이지 지정
                    .loginProcessingUrl("/Login-process")   // [B] submit 받을 url
                    .usernameParameter("id")                // [C] submit할 아이디
                    .passwordParameter("password")          // [D] submit할 비밀번호
                    .defaultSuccessUrl("/dashboard", true) // 성공 시 dashboard로
                    .permitAll()                            // 대시보드 이동이 막히면 안되므로 얘는 허용
            )
            .logout(withDefaults());                        // 로그아웃은 기본설정으로 (/logout으로 인증해제)

    return http.build();
  }
}
