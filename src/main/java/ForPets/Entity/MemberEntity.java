package ForPets.Entity;

import ForPets.Enum.UsingRole;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name="Member")
// insert 시 null 인 필드 제외
@DynamicInsert
// update 시 null 인 필드 제외
//@DynamicUpdate

public class MemberEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id_num;
    @Column(unique = true)
    private String id;
    private String password;
    @Enumerated(EnumType.STRING)
    @ColumnDefault("USING")
    private UsingRole usingRole;
//    @Transient
//    @DateTimeFormat(pattern = "yyyy-MM-dd/HH:mm:ss")
//    private LocalDateTime CreateDate;
//    @Transient
//    @DateTimeFormat(pattern = "yyyy-MM-dd/HH:mm:ss")
//    private LocalDateTime LastModifyDate;

}
