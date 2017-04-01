package ru.dins.web.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;
import ru.dins.web.model.keys.PostPrimaryKey;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * @author Ilja Pavlov
 */
@Table @Data @NoArgsConstructor @AllArgsConstructor
public class Post implements Serializable{
    @NonNull @PrimaryKey
    PostPrimaryKey key;

    @NonNull @Column("creation_time")
    private Date creationTime;

    private int version;

    @NonNull
    private String content;
}
