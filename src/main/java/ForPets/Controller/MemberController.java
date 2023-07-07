package ForPets.Controller;

import ForPets.DTO.MemberDTO;
import ForPets.Repositories.MemberRepository;
import ForPets.Service.MemberService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(value = "http://localhost:3000")
//@RestController 이거는 boot에서 html을 사용해주는 것이다.
//하지만 부트에 jsp를 파싱할때는 @Controller로 바꿔줘야한다.@@
@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {

  // Service(서비스) 로직 연결
  private final MemberService memberService;
  private final MemberRepository memberRepository;

  /**
   * 회원가입
   * @param
   * @return
   */
    @PostMapping("/signup")
    public ResponseEntity<Boolean> signUp(@RequestBody MemberDTO memberDTO) throws Exception {
        boolean isSave = memberService.signUp(memberDTO);
        if (isSave) {
          return new ResponseEntity<>(true, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
    }
  /**
   * 회원정보조회
   */
  @GetMapping("/member/{id}")
  public ResponseEntity getInfo(@PathVariable("id") String id) throws Exception {
    MemberDTO info = memberService.getInfo(id);
    return new ResponseEntity(info, HttpStatus.OK);
  }

  /**
   * 내정보조회
   */
  @GetMapping("/member")
  public ResponseEntity getMyInfo(HttpServletResponse response) throws Exception {

    MemberDTO info = memberService.getMyInfo();
    return new ResponseEntity(info, HttpStatus.OK);
  }

  /**
   * 토큰 활용 로그인
   */
//  @PostMapping("/login")
//  public ResponseEntity<?> loginMember(HttpServletResponse response, HttpSession session, @RequestBody Map<String, String> member){
//    log.warn("토큰 로그인 컨트롤");
//    String id = member.get("Id");
//    String pwd = member.get("password");
//    if((boolean)memberService.loginMember(id, pwd).get("login")){
//      log.info("로그인 성공 해서 토큰을 발급");
//      String token = (String)memberService.loginMember(id, pwd).get("token");
//      Cookie cookie = new Cookie("token", token); // 생성된 토큰을 cookie에 세팅
//      cookie.setMaxAge(60 * 60 * 12); // 유효기간 12시간으로 설정
//      cookie.setHttpOnly(true);
//      cookie.setPath("/");
//      response.addCookie(cookie); // 응답에 쿠키 추가
//
//      Optional<MemberEntity> getMember = memberRepository.findById(id);
//      session.setAttribute("memberInfo", getMember);  // 세션에 회원정보 저장
//
//      return ResponseEntity.ok(true);
//    }else
//      return ResponseEntity.ok(false);
//  }


  @GetMapping("/dashboard")
  // @AuthenticationPrincipal : UserDetailsService에서 Return한 객체를 파라미터로 직접 받아 사용할 수 있다.
  public String dashboardPage(@AuthenticationPrincipal User user, Model model) {
    model.addAttribute("loginId", user.getUsername());
    model.addAttribute("loginRoles", user.getAuthorities());
    return "dashboard";
  }


}
