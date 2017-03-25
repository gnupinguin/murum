package ru.dins.CassandraRepository;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.stereotype.Repository;
import ru.dins.services.User;

/**
 * Created by key on 20.03.2017.
 */
@Repository @NoArgsConstructor
public class CassandraRepository {
    @Autowired
    @Qualifier("MyCassandraTemplate")
    private CassandraTemplate cassandraTemplate;
//    @Autowired
//    @Qualifier("MySession")
//    private Session session;
    @Autowired
    @Qualifier("MyCassandraCluster")
    private Cluster cluster;

//    public void setSession(@NonNull String keyspaceName){
//
//        session.getCluster().connect(keyspaceName);
//    }
    public void insert(Post post){
        Session session = cluster.connect("murumKeyspace");
        cassandraTemplate.insert(post);
    }
    public void insert(User user){
        Session session = cluster.connect("usersSpace");
        cassandraTemplate.insert(user);
//        System.err.println(cassandraTemplate.count(Post.class));
    }

    public void selectAll (@NonNull String keyspaceName, @NonNull String tableName){
        Session session = cluster.connect(keyspaceName);
        String query = "SELECT * FROM " + tableName;
        session.getCluster().connect(keyspaceName);
        ResultSet resultSet = session.execute(query);
        System.out.println(resultSet.all());
    }


    public User findOneByName(@NonNull String username){
        String query = "SELECT * FROM users WHERE username= '" + username+"' ALLOW FILTERING";
        Session session = cluster.connect("usersSpace");
        User findedUser = cassandraTemplate.selectOne(query,User.class);
        return findedUser;
    }

}
