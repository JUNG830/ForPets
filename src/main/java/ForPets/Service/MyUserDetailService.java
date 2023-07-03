package ForPets.Service;

import ForPets.Entity.MemberEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MyUserDetailService implements UserDetailsService {
  private final MemberService memberService;

  @Autowired
  public MyUserDetailService(MemberService memberService) {
    this.memberService = memberService;
  }

  @Override
  public UserDetails loadUserByUsername(String insertedUserId) throws UsernameNotFoundException {
    Optional<MemberEntity> findOne = memberService.findById(insertedUserId);
    MemberEntity memberEntity = findOne.orElseThrow(() -> new UsernameNotFoundException("없는 회원입니다 ㅠ"));

    return User.builder()
            .username(memberEntity.getId())
            .password(memberEntity.getPassword())
            .roles(memberEntity.getUsingRole().toString())
            .build();
  }
}
