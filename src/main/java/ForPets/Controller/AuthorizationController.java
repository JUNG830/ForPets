package ForPets.Controller;

import ForPets.DTO.MemberDTO;
import ForPets.Service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthorizationController {

  private final MemberService memberService;
  public AuthorizationController(MemberService memberService) {
    this.memberService = memberService;
  }

  /**
   * 회원가입
   * @param member
   * @return
   */
  @PostMapping("/signUp")
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
  @PostMapping("/signin")
  public ResponseEntity loginUser(@RequestBody MemberDTO member){
    log.warn("토큰 로그인 컨트롤");
    log.warn(member.getId() + member.getPassword());
    log.warn(memberService.loginUser(member).toString());

    return ResponseEntity.ok(memberService.loginUser(member));
  }

  @PostMapping("/signup")
  public ResponseEntity registerUser(@RequestBody MemberDTO form){
    return ResponseEntity.ok(memberService.registerUser(form));
  }
}
