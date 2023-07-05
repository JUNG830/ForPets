package ForPets.Controller;

import ForPets.DTO.MemberDTO;
import ForPets.Entity.MemberEntity;
import ForPets.Repositories.MemberRepository;
import ForPets.Service.MemberService;
import ForPets.config.AdminAuthorize;
import ForPets.config.UserAuthorize;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@CrossOrigin(value = "http://localhost:3000")
//@RestController 이거는 boot에서 html을 사용해주는 것이다.
//하지만 부트에 jsp를 파싱할때는 @Controller로 바꿔줘야한다.@@
@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

  // Service(서비스) 로직 연결
  private final MemberService memberService;
  private final MemberRepository memberRepository;

  /**
   * 회원가입
   * @param member
   * @return
   */
  @PostMapping("/signup")
  public ResponseEntity<Boolean> signUp(@RequestBody MemberDTO member) {
    log.warn("회원가입 Controller" + member.toString());
    try {
      memberService.signUp(member.getId(), member.getPassword());
      return new ResponseEntity<>(true, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    }
  }

  /**
   * 토큰 활용 로그인
   */
  @PostMapping("/login")
  public ResponseEntity<MemberEntity> loginUser(@RequestBody MemberDTO member){
    log.warn("토큰 로그인 컨트롤");
    MemberDTO memberDTO = memberService.loginUser(member);
    log.warn(memberDTO.toString());
    MemberEntity memberEntity = memberRepository.findById(memberDTO.getId());
    return new ResponseEntity<>(memberEntity, HttpStatus.OK);
  }


  @GetMapping("/dashboard")
  // @AuthenticationPrincipal : UserDetailsService에서 Return한 객체를 파라미터로 직접 받아 사용할 수 있다.
  public String dashboardPage(@AuthenticationPrincipal User user, Model model) {
    model.addAttribute("loginId", user.getUsername());
    model.addAttribute("loginRoles", user.getAuthorities());
    return "dashboard";
  }


}
