package ForPets.Service;

import ForPets.Entity.MemberEntity;
import ForPets.JWT.UserDetailsImpl;
import ForPets.Repositories.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class UserDetailService implements UserDetailsService {
  private final MemberService memberService;
  private final MemberRepository memberRepository;

  @Autowired
  public UserDetailService(MemberService memberService, MemberRepository memberRepository) {
    this.memberService = memberService;
    this.memberRepository = memberRepository;
  }

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
    Optional<MemberEntity> user = Optional.ofNullable(memberRepository.findById(id)
            .orElseThrow(() -> new UsernameNotFoundException("user not found")));

    return UserDetailsImpl.from(user);
  }


}
