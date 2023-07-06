package ForPets.Service;

import ForPets.Repositories.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {
  private final MemberRepository memberRepository;
//  private final JwtProvider jwtProvider;

  /**
   * 회원가입
   * @param id
   * @param pwd
   * @return
   */
//  public String signUp(String id, String pwd) {
//    MemberEntity memberEntity = MemberEntity.createUser(id, pwd, passwordEncoder, UsingRole.USING);
//    validateDuplicateMember(id);
//    log.warn(id + pwd);
//    log.warn(memberEntity.toString());
//    memberRepository.save(memberEntity);
//
//    return memberEntity.getId();
//  }
//
//  /**
//   * 아이디 중복확인
//   * @param id
//   */
//  private Boolean validateDuplicateMember(String id) {
//    log.warn("벨리데이션" + id);
//    Optional<MemberEntity> result = memberRepository.findById(id);
//    if(result != null) {
//      return true;
//    }
//    return false;
//  }

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
   * 로그인
   * @param id
   * @param pwd
   * @return
   */
//  public Map<?,?> loginMember(String id, String pwd) {
//    Map<String, Object> map = new HashMap<>();
//    Optional<MemberEntity> member = memberRepository.findById(id);
//    String accessToken;
//    log.warn("토큰 발행할까?? 멤버서비스");
//    if(!member.isEmpty()){
//      if(member.get().getUsing_role() == UsingRole.USING && member.get().getUser_grade() == UserGrade.USER) {
//        if (passwordEncoder.matches(pwd, member.get().getPassword())) {
//          accessToken = jwtProvider.createToken(id);
//          log.warn("로그인 비밀번호 확인 : " + accessToken);
//          map.put("token", accessToken);
//        }
//      } else {
//        log.info("관리자입니다 관리자용 토큰 발급합니다");
//        accessToken = jwtProvider.createToken("admin");
//        map.put("token", accessToken);
//      }
//    }
//    map.put("login", false);
//    return map;
//  }


  /**
   * 회원정보 수정
   */
//  public MemberEntity updateMemberData(String id, String password, String RefreshToken) {
//    Optional<MemberEntity> memberEntity = memberRepository.findById(id);
//    memberEntity.setId_num(memberEntity.getId_num());
//    memberEntity.setPassword(password);
//    memberEntity.setRefresh_token(RefreshToken);
//    MemberEntity result = memberRepository.save(memberEntity);
//    log.warn(result.toString());
//    return result;
//  }

}
