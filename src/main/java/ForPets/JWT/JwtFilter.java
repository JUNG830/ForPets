//package ForPets.JWT;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
///**
// * JWT로 인가를 하기 위한 클래스
// */
//@Component
//@Slf4j
//public class JwtFilter extends BasicAuthenticationFilter {
//
////          OncePerRequestFilter
//  private JwtProvider jwtProvider;
//  private UserDetailsService userDetailsService;
//  public static SecurityContext securityContext;
//  public static String loginSessioId;
//
//  public JwtFilter(AuthenticationManager authenticationManager, AuthenticationEntryPoint authenticationEntryPoint) {
//    super(authenticationManager, authenticationEntryPoint);
//  }
//  public JwtFilter(AuthenticationManager authenticationManager) {
//    super(authenticationManager);
//  }
//  public JwtFilter(AuthenticationManager authenticationManager, JwtProvider jwtProvider, UserDetailsService userDetailsService) {
//    super(authenticationManager);
//    this.jwtProvider = jwtProvider;
//    this.userDetailsService = userDetailsService;
//  }
//
//  public JwtFilter(AuthenticationManager authenticationManager, AuthenticationEntryPoint authenticationEntryPoint, JwtProvider jwtProvider, UserDetailsService userDetailsService) {
//    super(authenticationManager, authenticationEntryPoint);
//    this.jwtProvider = jwtProvider;
//    this.userDetailsService = userDetailsService;
//  }
//
//
//
//
//  @Override
//  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//    log.warn("토큰 유효성 확인");
//    log.warn("request" + request.toString());
//    log.warn("response : " + response.toString());
//    // request로 부터 Jwt 토큰을 추출함
//    String token = parseJwt(request);
//    log.warn("token : " + token);
//    if(token != null && jwtProvider.isTokenValid(token)){
//      // 토큰이 유효하면 토큰으로부터 username 얻어오기
//      String getId = jwtProvider.getUsername(token);
//      // UsernamePasswordAuthenticationToken을 생성하는 부분
//      UserDetails userDetails = userDetailsService.loadUserByUsername(getId);
//      if(userDetails != null) {
//        UsernamePasswordAuthenticationToken authenticationToken =
//                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//        // 해당 유저 정보로 UsernamePasswordAuthenticationToken 을 만들어 SecurityContextHolder 에 인증 정보 전달
//        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//        securityContext = SecurityContextHolder.getContext();
//        loginSessioId = getId;
//        log.warn("securityContext : " + securityContext.toString());
//      }
//    }
//    filterChain.doFilter(request, response);
//  }
//
//  private String parseJwt(HttpServletRequest request){
//    log.warn("parseJWT");
//    String headerAuth = request.getHeader("Authorization");             // 헤더 파싱
//    if(StringUtils.hasText(headerAuth)&&headerAuth.startsWith("Bearer")){     // Bearer 토큰 파싱
//      String token = headerAuth.substring(7);                     // jwt token 파싱
//      return token;
//    }
//    return null;
//  }
//}
