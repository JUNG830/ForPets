package ForPets.Service;

import ForPets.Entity.MemberEntity;
import ForPets.Repositories.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    /* 회원가입 서비스 */
    public boolean signUpMember(String id, String pwd) {

        log.warn("★★★★★★★★★회원가입 서비스★★★★★★★★★");
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setId(id);
        memberEntity.setPassword(pwd);
        //memberEntity.setUsingRole(UsingRole.USING);
        //memberEntity.setCreateDate(LocalDateTime.now().withNano(0));
        //memberEntity.setLastDate(LocalDateTime.now().withNano(0));
        MemberEntity result = memberRepository.save(memberEntity);
        log.warn(result.toString());

        return true;
    }

    /**
     * 로그인
     */
    public boolean LoginMember(String id, String pwd) {
        log.warn("★★★★★★★★★로그인 서비스★★★★★★★★★");
        log.warn("입력한 아이디(id) : " + id);
        log.warn("입력한 비밀번호(pwd) : " + pwd);

        List<MemberEntity> memberEntityList = memberRepository.findByIdAndPassword(id, pwd);
        for(MemberEntity e : memberEntityList) {
            return true;
        }
        return false;
    }

    public User findByUserUuidForAuthToken(String userUuid) {
    }
}
