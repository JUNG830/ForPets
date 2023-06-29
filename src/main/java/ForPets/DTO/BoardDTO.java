package ForPets.DTO;

import ForPets.Entity.BaseTimeEntity;
import ForPets.Enum.UsingRole;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Data // getter, setter 만들어줌
@AllArgsConstructor //여기에 필드에 쓴 모든생성자만 만들어줌
//@NoArgsConstructor //기본 생성자를 만들어줌
@Slf4j
public class BoardDTO {
  private Long Board_Seq;
  private String title;
  private String writer;
  private String content;
  private UsingRole usingRole;
  private Long cnt;
  private LocalDateTime CreateDate;
  private LocalDateTime LastModifyDate;
}
