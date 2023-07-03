package ForPets.Entity;

import ForPets.Enum.UsingRole;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name="Board")
@DynamicInsert
public class BoardEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Board_Seq;
    private String title;
    private String writer;
    private String content;
    @Enumerated(EnumType.STRING)
    @ColumnDefault("USING")
    private UsingRole usingRole;
    private Long cnt;
//    @Transient
//    @DateTimeFormat(pattern = "yyyy-MM-dd/HH:mm:ss")
//    private LocalDateTime CreateDate;
//    @Transient
//    @DateTimeFormat(pattern = "yyyy-MM-dd/HH:mm:ss")
//    private LocalDateTime LastModifyDate;
}
