package web.persistence.repository;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by key on 16.03.2017.
 */
@Repository
@NoArgsConstructor
public class repository {

    private CassandraTemplate template;

}
