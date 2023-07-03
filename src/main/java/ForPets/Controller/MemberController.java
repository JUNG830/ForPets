package ForPets.Controller;

import ForPets.DTO.MemberDTO;
import ForPets.Entity.MemberEntity;
import ForPets.Service.MemberService;
import ForPets.config.AdminAuthorize;
import ForPets.config.UserAuthorize;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class MemberController {

  // Service(서비스) 로직 연결
  private MemberService memberService;
  MemberDTO memberDTO;

  public MemberController(MemberService memberService) {
    this.memberService = memberService;
  }

  @GetMapping("/dashboard")
  // @AuthenticationPrincipal : UserDetailsService에서 Return한 객체를 파라미터로 직접 받아 사용할 수 있다.
  public String dashboardPage(@AuthenticationPrincipal User user, Model model) {
    model.addAttribute("loginId", user.getUsername());
    model.addAttribute("loginRoles", user.getAuthorities());
    return "dashboard";
  }




//  @Autowired
//  JwtUtil jwtUtil;
//
//  @PostMapping("/auth/signup")
//  public ResponseEntity registerUser(@RequestBody MemberEntity form){
//    return ResponseEntity.ok(memberService.registerUser(form));
//  }
//
//  @PostMapping("/auth/signin")
//  public ResponseEntity loginUser(@RequestBody MemberEntity form){
//    return ResponseEntity.ok(memberService.loginUser(form));
//  }
}
