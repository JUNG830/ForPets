package ForPets.Service;

import ForPets.DTO.MemberDTO;
import ForPets.DTO.TokenDTO;
import ForPets.Entity.MemberEntity;
import ForPets.Enum.UsingRole;
import ForPets.JWT.JwtUtil;
import ForPets.Repositories.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {
  private final MemberRepository memberRepository;
  private final PasswordEncoder passwordEncoder;
  @Autowired
  private JwtUtil jwtUtil;

  @Autowired
  public MemberService(PasswordEncoder passwordEncoder, MemberRepository repository, JwtUtil jwtUtil) {
    this.passwordEncoder = passwordEncoder;
    this.memberRepository = repository;
    this.jwtUtil = jwtUtil;
  }

  /**
   * 회원가입
   * @param id
   * @param pwd
   * @return
   */
  public String signUp(String id, String pwd) {
    MemberEntity memberEntity = MemberEntity.createUser(id, pwd, passwordEncoder, UsingRole.USING);
    validateDuplicateMember(id);
    log.warn(id + pwd);
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

  /**
   * 시큐리티 로그인
   * @param Id
   * @return
   */
//    public Optional<MemberEntity> findById(String Id) {
//
//        return memberRepository.findById(Id);
//    }

  /**
   * 토큰 활용 로그인 / 회원가입
   */
  public MemberEntity registerUser(MemberDTO member) {
    String id = member.getId();
    String pwd = member.getPassword();
    Optional<MemberEntity> found = memberRepository.findById(id);
    if(found.isPresent()){
      throw new IllegalArgumentException("중복중복");
    }

    MemberEntity memberEntity = MemberEntity.createUser(id, pwd, passwordEncoder, UsingRole.USING);
    return memberRepository.save(memberEntity);
  }


  public TokenDTO loginUser(MemberDTO member) {
    Optional<MemberEntity> user = memberRepository.findById(member.getId());
//                .orElseThrow(()->new IllegalArgumentException("유저가 존재하지 않음"));
    log.warn("토큰 발행할까?? 멤버서비스");
    //유저가 존재하면
    if(user != null){
      //패스워드 확인 후 맞으면
      if(passwordEncoder.matches(member.getPassword(), user.get().getPassword())) {
        log.warn("비밀번호는 맞았고,,");
        log.warn(user.get().getId().toString());
        log.warn(member.getId());
        //토큰 발급
        return jwtUtil.generateToken(user.get().getId());
      }
    }

    throw new IllegalArgumentException("패스워드가 다름");
  }
}
