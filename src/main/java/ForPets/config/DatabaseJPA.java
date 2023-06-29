package ForPets.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableJpaRepositories(
        basePackages="ForPets.Repositories",  //repository를 관리할 패키지 명시
        entityManagerFactoryRef = "entityManagerFactory", //EntityManagerFactory
        transactionManagerRef = "transactionManager") // transactionManager
public class DatabaseJPA {

    /*
    < 기본 변수이름을 그대로 이용 >
    spring.jpa.hibernate.naming.physical-strategy = org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl

    < camel case를 underscore 형태로 변경 >
    SpringPhysicalNamingStrategy
    */
     private static final String DEFAULT_NAMING_STRATEGY
            = "org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl";

    @Bean
    @Primary //해당 Bean을 우선적으로 선택하도록 하는 annotation
    public DataSource defaultDataSource() {
        HikariConfig dataSourceConfig = new HikariConfig();
        dataSourceConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/forpets?characterEncoding=UTF-8&serverTimezone=Asia/Seoul&allowMultiQueries=true");
        dataSourceConfig.setUsername("forpets");
        dataSourceConfig.setPassword("FORPETS2023");
        dataSourceConfig.setMaximumPoolSize(10);
        dataSourceConfig.setMinimumIdle(5);
        dataSourceConfig.setMaxLifetime(1200000);
        dataSourceConfig.setConnectionTimeout(20000);
        dataSourceConfig.setIdleTimeout(300000);

        return new HikariDataSource(dataSourceConfig);
    }

    @Bean(name = "entityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder) {

        Map<String, String> propertiesHashMap = new HashMap<>();
        propertiesHashMap.put("hibernate.physical_naming_strategy",DEFAULT_NAMING_STRATEGY);

        LocalContainerEntityManagerFactoryBean rep =
                builder.dataSource(defaultDataSource())
                        //엔티티가 저장된 경로
                        .packages("ForPets.Entity")
                        .properties(propertiesHashMap)
                        .build();

        return rep;
    }

    @Primary
    @Bean(name = "transactionManager")
    PlatformTransactionManager transactionManager(
            EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactory(builder).getObject());
    }


}
