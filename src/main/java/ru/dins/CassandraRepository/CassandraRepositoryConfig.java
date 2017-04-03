package ru.dins.CassandraRepository;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.core.CassandraTemplate;

/**
 * Created by key on 20.03.2017.
 */
@Configuration @Data @NoArgsConstructor
public class CassandraRepositoryConfig{
    private static final String KEYSPACE_NAME = "murumKeyspace";
    private static final String USERS_KEYSPACE_NAME = "usersSpace";
    private static final String CONTACT_POINTS = "127.0.0.1";
    private static final int PORT = 9042;

    @Bean
    public Cluster MyCassandraCluster(){
        Cluster cluster = Cluster.builder().addContactPoint(CONTACT_POINTS).build();
        return cluster;
    }

    @Bean
    public CassandraTemplate MyCassandraTemplate() {
        //Query
        String queryForPosts = "CREATE TABLE IF NOT EXISTS posts(id UUID PRIMARY KEY, "
                + "author text, "
                + "text text, "
                + "version int, "
                + "modificationTime timestamp, "
                + "creationTime timestamp );";
        String queryForUsers = "CREATE TABLE IF NOT EXISTS users(id UUID PRIMARY KEY, "
                + "authorities list<text>,"
                + "username text, "
                + "password text, "
                + "accountNonExpired Boolean, "
                + "accountNonLocked Boolean, "
                + "credentialsNonExpired Boolean, "
                + "enabled Boolean ); ";
        Session session = MyCassandraCluster().connect(USERS_KEYSPACE_NAME);
        session.getCluster().connect(KEYSPACE_NAME);
        session.execute(queryForPosts);
        session.getCluster().connect(USERS_KEYSPACE_NAME);
        session.execute(queryForUsers);
        CassandraTemplate cassandraTemplate = new CassandraTemplate(session);
        return cassandraTemplate;
    }

}
