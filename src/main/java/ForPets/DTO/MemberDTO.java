package ForPets.DTO;

import ForPets.Entity.BaseTimeEntity;
import ForPets.Enum.UsingRole;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;


@Data // getter, setter 만들어줌
//@AllArgsConstructor //여기에 필드에 쓴 모든생성자만 만들어줌
@RequiredArgsConstructor // 생성자 자동 생성
//@NoArgsConstructor //기본 생성자를 만들어줌
@Slf4j
public class MemberDTO {
    private Long id_num;
    private String id;
    private String password;
    private UsingRole usingRole;
    private LocalDateTime CreateDate;
    private LocalDateTime LastModifyDate;
}
