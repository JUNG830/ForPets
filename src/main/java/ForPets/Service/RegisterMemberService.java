package ForPets.Service;

import ForPets.Entity.MemberEntity;
import ForPets.Enum.UsingRole;
import ForPets.Repositories.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RegisterMemberService {
  private final PasswordEncoder passwordEncoder;
  private final MemberRepository memberRepository;

  @Autowired
  public RegisterMemberService(PasswordEncoder passwordEncoder, MemberRepository memberRepository) {
    this.passwordEncoder = passwordEncoder;
    this.memberRepository = memberRepository;
  }

  public String join(String id, String pw) {
    MemberEntity memberEntity = MemberEntity.createUser(id, pw, passwordEncoder, UsingRole.USING);
    validateDuplicateMember(id);
    log.warn(id + pw);
    log.warn(memberEntity.toString());
    memberRepository.save(memberEntity);

    return memberEntity.getId();
  }

  private void validateDuplicateMember(String id) {
    log.warn("벨리데이션" + id);
    memberRepository.findById(id)
            .ifPresent(m -> {
              throw new IllegalStateException("이미 존재하는 회원입니다.");
            });
  }
}
