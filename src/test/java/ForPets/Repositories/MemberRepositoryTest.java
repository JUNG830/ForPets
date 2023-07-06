package ForPets.Repositories;

import ForPets.Entity.BaseTimeEntity;
import ForPets.Entity.MemberEntity;
import ForPets.Enum.UsingRole;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;
    BaseTimeEntity baseTimeEntity;

    @Test
    @DisplayName("회원가입 테스트")
    public void signUpTest() {

        //log.warn("★★★★★★★★★회원가입 서비스★★★★★★★★★");
        //memberInfo.setId_num(1L);
        for(int i=2; i<=20; i++) {
            MemberEntity memberEntity = new MemberEntity();
            memberEntity.setId("test" + i);
            memberEntity.setPassword("test1234");
            memberEntity.setUsing_role(UsingRole.USING);
            //memberInfo.setCreateDate(LocalDateTime.now().withNano(0));
            //memberInfo.setLastDate(LocalDateTime.now().withNano(0));
            memberRepository.save(memberEntity);
            System.out.println("시간 : " + String.valueOf(memberEntity.getCreateDate()));
        }


    }
}