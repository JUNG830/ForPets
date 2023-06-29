package ForPets;

import ForPets.Entity.BaseTimeEntity;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDateTime;
import java.util.TimeZone;

// exclude를 이용해 Security 기능 끄기. (exclude = SecurityAutoConfiguration.class)
@SpringBootApplication
@EnableJpaAuditing
@Slf4j
public class ForPetsApplication {

	// TimeZone설정이 자동으로 이루어지도록해줌.
	// 빈이 생성되고, 빈의 의존관계 주입이 완료된 후 호출
	// 프로젝트가 처음 실행될 때, 한 번만 실행 시켜주는 어노테이션
//	@PostConstruct
//	void started() {
//		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
//	}
	public static void main(String[] args) {
		SpringApplication.run(ForPetsApplication.class, args);


	}

}
