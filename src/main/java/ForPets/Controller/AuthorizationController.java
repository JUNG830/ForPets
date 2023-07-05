package ForPets.Controller;

import ForPets.DTO.MemberDTO;
import ForPets.Service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Slf4j
public class AuthorizationController {
  private final MemberService memberService;




//  @PostMapping("/signup")
//  public ResponseEntity registerUser(@RequestBody MemberDTO form){
//    return ResponseEntity.ok(memberService.registerUser(form));
//  }
}
