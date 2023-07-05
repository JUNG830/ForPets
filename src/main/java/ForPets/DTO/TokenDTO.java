package ForPets.DTO;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
@Data
@RequiredArgsConstructor
@Slf4j
public class TokenDTO {
    private String accessToken;
    private String refreshToken;
    private Long accessTokenExpireInTime;


}
