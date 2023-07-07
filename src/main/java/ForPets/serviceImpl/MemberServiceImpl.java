package ForPets.serviceImpl;

import ForPets.DTO.MemberDTO;
import ForPets.Entity.MemberEntity;
import ForPets.Enum.UsingRole;
import ForPets.Exception.MemberException;
import ForPets.Exception.MemberExceptionType;
import ForPets.Repositories.MemberRepository;
import ForPets.Service.MemberService;
import ForPets.config.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class MemberServiceImpl implements MemberService {
  private final MemberRepository memberRepository;
  private final PasswordEncoder passwordEncoder;

  /**
   * 회원가입
   */
  @Override
  public Boolean signUp(MemberDTO memberDTO) {
    MemberEntity memberEntity = MemberEntity.createUser(memberDTO.getId(), memberDTO.getPassword(), passwordEncoder, UsingRole.USING);
    if(!validateDuplicateMember(memberDTO.getId())) {
      throw new IllegalArgumentException("중복중복");
    };
    log.warn(memberDTO.getId() + memberDTO.getPassword());
    log.warn(memberEntity.toString());
    memberRepository.save(memberEntity);
    return true;
  }

  /**
   * 아이디 중복확인
   * @param id
   */
  private Boolean validateDuplicateMember(String id) {
    log.warn("벨리데이션" + id);
    Optional<MemberEntity> result = memberRepository.findById(id);
    if(result.isEmpty()) {
      return true;   // 가입 가능
    }
    return false;    // 중복
  }

  @Override
  public void update(MemberDTO memberUpdateDto) throws Exception {

  }

  /**
   * 회원정보 수정
   */
//  @Override
//  public void update(MemberDTO memberDTO, String username) throws Exception {
//    MemberEntity member = memberRepository.findById(
//                    username) //SecurityContextHolder에 들어있는 Username 가져옴, TODO : 이거 변경함
//            .orElseThrow(() -> new MemberException(MemberExceptionType.NOT_FOUND_MEMBER));
//
//
////    memberDTO.age().ifPresent(member::updateAge);
////    memberDTO.name().ifPresent(member::updateName);
////    memberDTO.nickName().ifPresent(member::updateNickName);
//  }
//
//  /**
//   * 비밀번호 변경
//   */
//  @Override
//  public void updatePassword(String asIsPassword, String toBePassword, String username) throws Exception {
//
//
//    MemberEntity member = memberRepository.findById(
//                    username)
//            .orElseThrow(() -> new MemberException(MemberExceptionType.NOT_FOUND_MEMBER));
//
//    if(!member.matchPassword(passwordEncoder, asIsPassword) ) {
//      throw new MemberException(MemberExceptionType.WRONG_PASSWORD);
//    }
//    member.updatePassword(passwordEncoder, toBePassword);
//  }
//
//
//  /**
//   * 회원탈퇴
//   */
//  @Override
//  public void withdraw(String checkPassword, String username) throws Exception {
//    MemberEntity member = memberRepository.findById(
//                    username)
//            .orElseThrow(() -> new MemberException(MemberExceptionType.NOT_FOUND_MEMBER));
//
//
//    if(!member.matchPassword(passwordEncoder, checkPassword) ) {
//      throw new MemberException(MemberExceptionType.WRONG_PASSWORD);
//    }
//
//    memberRepository.delete(member);
//  }


  /**
   * 회원정보 가져오기
   */
  @Override
  public MemberDTO getInfo(String id) throws Exception {
    MemberEntity findMember = memberRepository.findById(id).orElseThrow(() -> new MemberException(MemberExceptionType.NOT_FOUND_MEMBER));
    return new MemberDTO(findMember);
  }

  /**
   * 내정보 가져오기
   */
  @Override
  public MemberDTO getMyInfo() throws Exception {
    MemberEntity findMember = memberRepository.findById(SecurityUtil.getLoginUserId()).orElseThrow(() -> new MemberException(MemberExceptionType.NOT_FOUND_MEMBER));
    return new MemberDTO(findMember);
  }
}
