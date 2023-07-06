//package ForPets.JWT;
//
//import ForPets.DTO.MemberDTO;
//import io.jsonwebtoken.*;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//
//@Component
//@Slf4j
//@RequiredArgsConstructor
//public class JwtProvider {
//  private final long ACCESS_TOKEN_VALID_PERIOD = 1000L*60*60*12;     // 12시간
//  private final long REFRESH_TOKEN_VALID_PERIOD = 1000L*60*60*24*7; // 7일
//
//  @Value("${jasypt.encryptor.password}")
//  private String jwtSecret;
//
//
//  public String createToken(String id){
//    log.warn("토큰 만들기");
//    log.warn(System.getenv("JASYPT_PASSWORD"));
//    Date now = new Date();
//    Date accessTokenExpireIn = new Date(now.getTime() + ACCESS_TOKEN_VALID_PERIOD);
//
//    String accessToken = Jwts.builder()
//            // :username 으로 클레임을 만들건데, 그걸 Subject로 설정할거야
//            .setClaims(Jwts.claims().setSubject(id))
//            // :지금 발급되는 토큰이야
//            .setIssuedAt(now)
//            // :지금으로부터 ACCESS_TOKEN_VALID_PERIOD 까지 유효해
//            .setExpiration(accessTokenExpireIn)
//            // : HS512 로 인코딩 할거고, jwtSecret 이라는 키를 쓸거야
//            .signWith(SignatureAlgorithm.HS512, jwtSecret)
//            // 토큰 생성
//            .compact();
//
////    String refreshToken = Jwts.builder()
////            .setIssuedAt(now)
////            .setExpiration(new Date(now.getTime() + REFRESH_TOKEN_VALID_PERIOD))
////            .signWith(SignatureAlgorithm.HS512, jwtSecret)
////            .compact();
//    log.warn("accessToken : " + accessToken);
////    log.warn("refreshToken : " + refreshToken);
//    log.warn("accessTokenExpireIn.getTime() : " + accessTokenExpireIn.getTime());
//
//    return accessToken;
//  }
//
//  /**
//   * 토큰으로부터 username을 가져오는 부분(복호화)
//   * @param token
//   * @return
//   */
//  public String getUsername(String token){
//    // username 클레임이 Subject였으므로 .parseClaimsJws(token).getBody().getSubject() 으로 가져올 수 있음
//    return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
//  }
//
//  /**
//   * 토큰이 유효성 확인
//   * @param token
//   * @return
//   */
//  public boolean isTokenValid(String token){
//    try{
//      Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
//      return true;
//    }catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e){
//      log.warn("잘못된 JWT 서명입니다.");
//    }catch (ExpiredJwtException e){
//      log.warn("만료된 JWT 서명입니다.");
//    }catch (UnsupportedJwtException e){
//      log.warn("지원되지 않는 JWT 서명입니다.");
//    }catch (IllegalArgumentException e){
//      log.warn("JWT 토큰이 잘못되었습니다.");
//    }
//    return false;
//  }
//}
