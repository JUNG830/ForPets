package ForPets.Controller;

import ForPets.DTO.MemberDTO;
import ForPets.Entity.MemberEntity;
import ForPets.Service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

  // http://localhost:8282/ 으로 접속 시
  @GetMapping("/")
  public ModelAndView Main() {
    ModelAndView mav = new ModelAndView();
    //jsp(html)로 갈때는 setViewName // class로 갈때는 setView
    mav.setViewName("Main");

    return mav;
  }

  @GetMapping("/SignUp")
  public ModelAndView SignUp() {
    log.warn("가라!");
    ModelAndView mav = new ModelAndView();
    //jsp(html)로 갈때는 setViewName // class로 갈때는 setView
    mav.setViewName("SignUp");

    return mav;
  }

  /* 회원가입 */
  @PostMapping("/SignUp")
  public ModelAndView memberSignUp(MemberEntity entity, HttpServletRequest req) throws Exception {
    ModelAndView mav = new ModelAndView();
    log.warn("★★★★★★★★★회원가입 Controller★★★★★★★★★");
    boolean isSave = false;

    String getId = req.getParameter("id");
    String getPwd = req.getParameter("password");
    log.warn(getId, getPwd);

    isSave = memberService.signUpMember(getId, getPwd);
    if (isSave) {
      log.warn("MEMBER 테이블 DB 저장 " + isSave);
      mav.setViewName("Login");
      return mav;
    } else {
      log.warn("MEMBER 테이블 DB 저장 " + isSave);
      mav.setViewName("Test");
      return mav;
    }
  }

  /*
  * 로그인
  */
  @GetMapping("/Login")
  public ModelAndView login() {
    ModelAndView mav = new ModelAndView();
    log.warn("로그인 GET");
    mav.setViewName("Login");
    return mav;
  }

  @PostMapping("/Login")
  public ModelAndView login(MemberEntity entity, HttpServletRequest req) throws Exception {
    log.warn("로그인 POST");
    ModelAndView mav = new ModelAndView();

    String getId = req.getParameter("id");
    String getPwd = req.getParameter("password");
    log.warn(getId, getPwd);

    boolean isSave = memberService.LoginMember(getId, getPwd);
    if(isSave) {
      mav.setViewName("Main");
    } else {
      mav.setViewName("Login");
    }
    return mav;
  }
}
