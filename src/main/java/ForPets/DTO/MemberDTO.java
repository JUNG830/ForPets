package ForPets.DTO;

import ForPets.Entity.MemberEntity;
import ForPets.Enum.UserGrade;
import ForPets.Enum.UsingRole;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Optional;


@Data // getter, setter 만들어줌
@AllArgsConstructor //여기에 필드에 쓴 모든생성자만 만들어줌
@RequiredArgsConstructor // 생성자 자동 생성
//@NoArgsConstructor //기본 생성자를 만들어줌
@Slf4j
public class MemberDTO {
    private Long id_num;
    private String id;
    private String password;
    private UsingRole usingRole;
    private UserGrade userGrade;
    private LocalDateTime CreateDate;
    private LocalDateTime LastModifyDate;
    private String access_token;
    private String refresh_token;
    private Long accessTokenExpireInTime;

    public MemberDTO(String accessToken, String refreshToken, long accessTokenExpireInTime) {
        this.access_token = accessToken;
        this.refresh_token = refreshToken;
        this.accessTokenExpireInTime = accessTokenExpireInTime;
    }

  public MemberDTO(MemberEntity findMember) {
  }

}
