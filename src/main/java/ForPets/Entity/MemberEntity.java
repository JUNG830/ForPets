package ForPets.Entity;

import ForPets.Enum.UsingRole;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name="Member")
// insert 시 null 인 필드 제외
@DynamicInsert
// update 시 null 인 필드 제외
@DynamicUpdate
@RequiredArgsConstructor
@Slf4j
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
    private String refresh_token;

    public MemberEntity(String id, String password, UsingRole usingRole) {
        this.id = id;
        this.password = password;
        this.usingRole = usingRole;

    }

//    @Transient
//    @DateTimeFormat(pattern = "yyyy-MM-dd/HH:mm:ss")
//    private LocalDateTime CreateDate;
//    @Transient
//    @DateTimeFormat(pattern = "yyyy-MM-dd/HH:mm:ss")
//    private LocalDateTime LastModifyDate;

    public static MemberEntity createUser(String userId, String pw, PasswordEncoder passwordEncoder, UsingRole userRole) {
        log.warn("createUser : " + userId + passwordEncoder.encode(pw) + userRole);
        return new MemberEntity(userId, passwordEncoder.encode(pw), userRole);
    }

}
