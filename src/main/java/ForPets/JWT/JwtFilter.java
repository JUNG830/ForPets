package ForPets.JWT;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    private final UserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.warn("토큰 유효성 확인");
        // request로 부터 Jwt 토큰을 추출함
        String token = parseJwt(request);
        log.warn(token);
        if(token != null && jwtUtil.isTokenValid(token)){
            // 토큰이 유효하면 토큰으로부터 Subject인 username을 가져와~
            String username = jwtUtil.getUsername(token);
            // UsernamePasswordAuthenticationToken을 생성하는 부분
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if(userDetails != null) {
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                // 해당 유저 정보로 UsernamePasswordAuthenticationToken 을 만들어 SecurityContextHolder 에 인증 정보 전달
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }

    private String parseJwt(HttpServletRequest request){
        String headerAuth = request.getHeader("Authorization");
        if(StringUtils.hasText(headerAuth)&&headerAuth.startsWith("Bearer")){
            return headerAuth.substring(7);
        }
        return null;
    }
}
