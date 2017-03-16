package web.persistence.repository;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.core.CassandraTemplate;

/**
 * Created by key on 16.03.2017.
 */
@Configuration @Data
public class repositoryConfig {
    public @Bean
    CassandraTemplate cassandraTemplate(){

    }
}
