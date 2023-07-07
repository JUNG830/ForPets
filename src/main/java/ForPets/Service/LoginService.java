package ForPets.Service;

import ForPets.Entity.MemberEntity;
import ForPets.Repositories.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class LoginService implements UserDetailsService {
  private final MemberRepository memberRepository;

  @Override
  public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
    log.warn("오냐");
    MemberEntity user = memberRepository.findById(id)
            .orElseThrow(() -> new UsernameNotFoundException("해당 아이디가 존재하지 않습니다."));

    return org.springframework.security.core.userdetails.User.builder()
            .username(user.getId())
            .password(user.getPassword())
            .roles(user.getUser_grade().name())
            .roles(user.getUsing_role().name())
            .build();
  }


}
