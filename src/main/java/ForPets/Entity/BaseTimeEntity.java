package ForPets.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
@Getter
//JPA Entity 클래스들이 해당 어노테이션이 붙은 클래스를 상속한 경우 해당 클래스의 필드를 컬럼으로 인식하게 한다.
@MappedSuperclass
//JPA 내부에서 엔티티 객체가 생성/변경되는 것을 감지
//어노테이션 설정 후 AuditingEntityListener를 활성화시키기 위해서는 프로젝트에 @EnableJpaAuditing 설정을 추가해야한다.
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {
    @CreatedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd/HH:mm:ss")
    @Column(updatable = false)
    private LocalDateTime CreateDate;
    
    @LastModifiedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd/HH:mm:ss")
    private LocalDateTime LastModifyDate;
}
