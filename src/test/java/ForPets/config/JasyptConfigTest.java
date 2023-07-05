package ForPets.config;

import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class JasyptConfigTest {

  @Test
  @DisplayName("JASYPT 암호화")
  public StringEncryptor stringEncryptor(){
    PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
    SimpleStringPBEConfig config = new SimpleStringPBEConfig();
    config.setPassword("forpetsMadeByWoo2023forpetsMadeByWoo2023");
    config.setPoolSize("1");
    config.setAlgorithm("PBEWithMD5AndDES");
    config.setStringOutputType("base64");
    config.setKeyObtentionIterations("1000");
    config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
    encryptor.setConfig(config);
    System.out.println(encryptor);
    return encryptor;
  }

}