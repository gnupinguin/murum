package ru.dins.CassandraRepository;

import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import java.util.Date;
import java.util.UUID;

/**
 * Created by key on 20.03.2017.
 */
@Table("Posts")
public class Post {
    @PrimaryKey
    private UUID id;
    private String author;
    private Date creationTime;
    private Date modificationTime;
    private int version;
    private String text;
    public Post(UUID id, String author, Date creationTime, Date modificationTime, int version, String text){
        this.id = id;
        this.author = author;
        this.creationTime = creationTime;
        this.modificationTime = modificationTime;
        this.version = version;
        this.text = text;
    }
//        public Post(UUID id, String author, String text){
//        this.id = id;
//        this.author = author;
//        this.text = text;
//    }

}
