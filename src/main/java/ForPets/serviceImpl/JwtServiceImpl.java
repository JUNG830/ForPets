//package ForPets.serviceImpl;
//
//import ForPets.Repositories.MemberRepository;
//import ForPets.Service.JwtService;
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.AccessLevel;
//import lombok.RequiredArgsConstructor;
//import lombok.Setter;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Optional;
//
//@Transactional
//@Service
//@Setter(value = AccessLevel.PRIVATE)
//public class JwtServiceImpl extends JwtService {
//  //== 1 ==//
//  @Value("${jwt.secret}")
//  private String KEY;
//  @Value("${jwt.access.expiration}")
//  private long accessTokenValidityInSeconds;
//  @Value("${jwt.refresh.expiration}")
//  private long refreshTokenValidityInSeconds;
//  @Value("${jwt.access.header}")
//  private String accessHeader;
//  @Value("${jwt.refresh.header}")
//  private String refreshHeader;
//
//  //== 2 ==//
//  private static final String ACCESS_TOKEN_SUBJECT = "AccessToken";
//  private static final String REFRESH_TOKEN_SUBJECT = "RefreshToken";
//  private static final String USERID_CLAIM = "id";
//  private static final String BEARER = "Bearer ";
//
//  private final MemberRepository memberRepository;
//  private final ObjectMapper objectMapper;
//
//  public JwtServiceImpl(MemberRepository memberRepository, MemberRepository memberRepository1, ObjectMapper objectMapper) {
//    super(memberRepository);
//    this.memberRepository = memberRepository1;
//    this.objectMapper = objectMapper;
//  }
//
//  //== 3 ==//
//  @Override
//  public String createAccessToken(String id) {
//    return JWT.create()     //JWT 토큰을 생성하는 빌더를 반환합니다.
//
//            .withSubject(ACCESS_TOKEN_SUBJECT)
//            //빌더를 통해 JWT의 Subject를 정합니다. AccessToken이므로 저는 위에서 설정했던
//            //AccessToken의 subject를 가져와 사용하겠습니다
//
//            .withExpiresAt(new Date(System.currentTimeMillis() + accessTokenValidityInSeconds * 1000))
//            //만료시간을 설정하는 것입니다. 현재 시간 + 저희가 설정한 시간(밀리초) * 1000을 하면
//            //예를 들어 저의 경우는 accessTokenValidityInSeconds이 60*60*12이기 때문에
//            //현재시간에 60*60*12 * 1000 밀리초를 더한 '현재시간 + 12시간'가 설정이 되고
//            //따라서 12시간 이후에 이 토큰은 만료됩니다.
//
//            .withClaim(USERID_CLAIM, id)
//            //클레임으로는 id 하나만 사용합니다.
//            //추가적으로 식별자나, 이름 등의 정보를 더 추가하셔도 됩니다.
//            //추가하실 경우 .withClaim(클래임 이름, 클래임 값) 으로 설정해주시면 됩니다
//
//            .sign(Algorithm.HMAC512(KEY));
//            // HMAC512 알고리즘을 사용하여, 지정한 secret 키로 암호화 할 것입니다.
//  }
//
//  @Override
//  public String createRefreshToken() {
//    return JWT.create()
//            .withSubject(REFRESH_TOKEN_SUBJECT)
//            .withExpiresAt(new Date(System.currentTimeMillis() + refreshTokenValidityInSeconds * 1000))
//            .sign(Algorithm.HMAC512(KEY));
//  }
//
//  @Override
//  public void updateRefreshToken(String id, String refreshToken) {
//    memberRepository.findById(id)
//                    .ifPresentOrElse(
//                            member -> member.updateRefreshToken(refreshToken),
//                            () -> new Exception("회원이 없습니다")
//                    );
//  }
//
//  @Override
//  public void destroyRefreshToken(String id) {
//    memberRepository.findById(id)
//                    .ifPresentOrElse(
//                            member -> member.destroyRefreshToken(),
//                            () -> new Exception("회원이 없습니다")
//                    );
//  }
//
//  //== 5 ==//
//  @Override
//  public void sendToken(HttpServletResponse response, String accessToken, String refreshToken) {
//    response.setContentType("application/json;charset=UTF-8");
//    response.setStatus(HttpServletResponse.SC_OK);
//
//    setAccessTokenHeader(response, accessToken);
//    setRefreshTokenHeader(response, refreshToken);
//
//
//    Map<String, String> tokenMap = new HashMap<>();
//    tokenMap.put(ACCESS_TOKEN_SUBJECT, accessToken);
//    tokenMap.put(REFRESH_TOKEN_SUBJECT, refreshToken);
//
//    String token = objectMapper.writeValueAsString(tokenMap);
//
//    response.getWriter().write(token);
//  }
//
//
//  @Override
//  public Optional<String> extractAccessToken(HttpServletRequest request) {
//    return Optional.ofNullable(request.getHeader(accessHeader))
//            .map(accessToken -> accessToken.replace(BEARER, "")).orElse(null).describeConstable();
//  }
//
//  @Override
//  public Optional<String> extractRefreshToken(HttpServletRequest request) {
//    return Optional.ofNullable(request.getHeader(refreshHeader))
//            .map(refreshToken -> refreshToken.replace(BEARER, "")).orElse(null).describeConstable();
//  }
//
//  //== 4 ==//
//  @Override
//  public Optional<String> extractUserId(String accessToken) {
//    return JWT.require(Algorithm.HMAC512(KEY))
//            //토큰의 서명의 유효성을 검사하는데 사용할 알고리즘이 있는
//            //JWT verifier builder를 반환합니다
//
//            .build()    //반환된 빌더로 JWT verifier를 생성합니다
//            .verify(accessToken)      //accessToken을 검증하고 유효하지 않다면 예외를 발생시킵니다.
//            .getClaim(USERID_CLAIM)   //claim을 가져옵니다
//            .asString().describeConstable();
//
//  }
//
//  @Override
//  public void setAccessTokenHeader(HttpServletResponse response, String accessToken) {
//    response.setHeader(accessHeader, accessToken);
//  }
//
//  @Override
//  public void setRefreshTokenHeader(HttpServletResponse response, String refreshToken) {
//    response.setHeader(refreshHeader, refreshToken);
//  }
//
//}
