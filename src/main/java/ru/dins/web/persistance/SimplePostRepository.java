package ru.dins.web.persistance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.repository.CrudRepository;
import ru.dins.web.model.Post;
import ru.dins.web.model.keys.PostPrimaryKey;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ilja Pavlov
 */
public class SimplePostRepository {
    @Autowired
    private CassandraTemplate template;

}
