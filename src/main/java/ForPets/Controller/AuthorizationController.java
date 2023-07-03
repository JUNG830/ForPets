package ForPets.Controller;

import ForPets.DTO.MemberDTO;
import ForPets.Entity.MemberEntity;
import ForPets.Service.RegisterMemberService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthorizationController {
  private final RegisterMemberService registerMemberService;

  public AuthorizationController(RegisterMemberService registerMemberService) {
    this.registerMemberService = registerMemberService;
  }


  @PostMapping("/join")
  public ResponseEntity<String> join(@RequestBody MemberEntity member) {
    log.warn("회원가입 정보 확인" + member.toString());
    try {
      registerMemberService.join(member.getId(), member.getPassword());
      return ResponseEntity.ok("join success");
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}
