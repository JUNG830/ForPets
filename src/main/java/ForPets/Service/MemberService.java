package ForPets.Service;

import ForPets.Entity.MemberEntity;
import ForPets.Repositories.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    
    // 시큐리티 테스트
    public Optional<MemberEntity> findById(String Id) {

        return memberRepository.findById(Id);
    }

//    public MemberEntity findByIdForAuthToken(String id) {
//    };

//    @Autowired
//    private JwtUtil jwtUtil;

//    public MemberEntity registerUser(MemberEntity form) {
//        MemberEntity found = memberRepository.findById(form.getId());
//        if(found!=null){
//            throw new IllegalArgumentException("중복중복");
//        }
//
//        UsingRole role = UsingRole.USING;
//
//        MemberEntity user = new MemberEntity.From(form, passwordEncoder.encode(form.getPassword()), role);
//        return memberRepository.save(user);
//    }

}
