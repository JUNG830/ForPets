package ForPets.Entity;

import ForPets.Enum.UserGrade;
import ForPets.Enum.UsingRole;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.Serializable;


@Data
@Entity
@Table(name="Member")
// insert 시 null 인 필드 제외
@DynamicInsert
// update 시 null 인 필드 제외
//@DynamicUpdate
//@AllArgsConstructor //여기에 필드에 쓴 모든생성자만 만들어줌
@RequiredArgsConstructor // 생성자 자동 생성
//@NoArgsConstructor //기본 생성자를 만들어줌
@Slf4j
public class MemberEntity extends BaseTimeEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id_num;
    @Column(unique = true)
    private String id;
    private String password;
    @Enumerated(EnumType.STRING)
    @ColumnDefault("USING") // 나중에 ACTIVE 로 변경 예정.
    private UsingRole using_role;
    @Enumerated(EnumType.STRING)
    @ColumnDefault("USER")
    private UserGrade user_grade;
    @Column(name = "refresh_token")
    private String refreshToken;

    public MemberEntity(String id, String password, UsingRole usingRole) {
        this.id = id;
        this.password = password;
        this.using_role = usingRole;

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

    //== 정보 수정 ==//
    public void updatePassword(PasswordEncoder passwordEncoder, String password){
        this.password = passwordEncoder.encode(password);
    }
//  public void updateNickName(String nickName){
//    this.nickName = nickName;
//  }

    public void updateRefreshToken(String refreshToken){
        this.refreshToken = refreshToken;
    }
    public void destroyRefreshToken(){
        this.refreshToken = null;
    }

    //== 패스워드 암호화 ==//
    public void encodePassword(PasswordEncoder passwordEncoder){
        this.password = passwordEncoder.encode(password);
    }

  //비밀번호 변경, 회원 탈퇴 시, 비밀번호를 확인하며, 이때 비밀번호의 일치여부를 판단하는 메서드입니다.
  public boolean matchPassword(PasswordEncoder passwordEncoder, String checkPassword){
    return passwordEncoder.matches(checkPassword, getPassword());
  }

}
