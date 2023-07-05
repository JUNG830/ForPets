package ForPets.Service;

import ForPets.DTO.MemberDTO;
import ForPets.DTO.TokenDTO;
import ForPets.Entity.MemberEntity;
import ForPets.Enum.UsingRole;
import ForPets.JWT.JwtUtil;
import ForPets.Repositories.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.tags.shaded.org.apache.xpath.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Member;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {
  private final MemberRepository memberRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtUtil jwtUtil;

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

  /**
   * 아이디 중복확인
   * @param id
   */
  private Boolean validateDuplicateMember(String id) {
    log.warn("벨리데이션" + id);
    MemberEntity result = memberRepository.findById(id);
    if(result != null) {
      return true;
    }
    return false;
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
   * 토큰 회원가입
   */
//  public MemberEntity registerUser(MemberDTO member) {
//    String id = member.getId();
//    String pwd = member.getPassword();
//    Optional<MemberEntity> found = memberRepository.findById(id);
//    if(found.isPresent()){
//      throw new IllegalArgumentException("중복중복");
//    }
//
//    MemberEntity memberEntity = MemberEntity.createUser(id, pwd, passwordEncoder, UsingRole.USING);
//    return memberRepository.save(memberEntity);
//  }

  /**
   * 로그인 성공 시 토큰 발행
   * @param member
   * @return
   */
  public MemberDTO loginUser(MemberDTO member) {
    MemberEntity user = memberRepository.findById(member.getId());
//                .orElseThrow(()->new IllegalArgumentException("유저가 존재하지 않음"));
    log.warn("토큰 발행할까?? 멤버서비스");
    //유저가 존재하면
    if(user != null){
      //패스워드 확인 후 맞으면
      if(passwordEncoder.matches(member.getPassword(), user.getPassword())) {
        log.warn("비밀번호는 맞았고,,");
        //토큰 발급
        MemberDTO memberDTO = jwtUtil.generateToken(member.getId());
        // Refresh Token DB에 저장
        updateMemberData(member.getId(), null, memberDTO.getAccess_token());
        return memberDTO;
      } else {
          throw new IllegalArgumentException("패스워드가 다름");
      }
    }
    throw new IllegalArgumentException("아이디가 다름");
  }


  /**
   * 회원정보 수정
   */
  public MemberEntity updateMemberData(String id, String password, String RefreshToken) {
    MemberEntity memberEntity = memberRepository.findById(id);
    memberEntity.setId_num(memberEntity.getId_num());
    memberEntity.setPassword(password);
    memberEntity.setRefresh_token(RefreshToken);
    MemberEntity result = memberRepository.save(memberEntity);
    log.warn(result.toString());
    return result;
  }
}
