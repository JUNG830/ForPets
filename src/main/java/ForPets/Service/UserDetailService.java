package ForPets.Service;

import ForPets.Entity.MemberEntity;
import ForPets.JWT.UserDetailsImpl;
import ForPets.Repositories.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserDetailService implements UserDetailsService {
  private final MemberService memberService;
  private final MemberRepository memberRepository;


  /**
   * 시큐리티 로그인 체크
   *
   * @param id the username identifying the user whose data is required.
   * @return
   * @throws UsernameNotFoundException
   */
//  @Override
//  public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
//    log.warn("오냐");
//    Optional<MemberEntity> findOne = memberService.findById(id);
//    MemberEntity memberEntity = findOne.orElseThrow(() -> new UsernameNotFoundException("회원없음"));
//
//    return User.builder()
//            .username(memberEntity.getId())
//            .password(memberEntity.getPassword())
//            .roles(memberEntity.getUsingRole().toString())
//            .build();
//  }

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
    log.warn("로그인 정보 확인");
    MemberEntity member = memberRepository.findById(id);
    if (member != null) {
      return UserDetailsImpl.from(member);
    }
    throw new UsernameNotFoundException("로그인 실패");
  }

}
