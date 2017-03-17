package ru.dins.web.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import java.util.Date;
import java.util.UUID;

/**
 * Created by gnupinguin on 17.03.17.
 */
@Table @Data @NoArgsConstructor @AllArgsConstructor
public class Post {

    @PrimaryKey
    private UUID id;

    @NonNull
    private String author;

    @NonNull
    private Date creationTime;

    @NonNull
    private Date modificationTime;

    private int version;

    @NonNull
    private String text;
}
