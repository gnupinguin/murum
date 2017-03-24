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
 * Created by gnupinguin on 24.03.17.
 */
@Table @Data @NoArgsConstructor @AllArgsConstructor
public class Action {
    @PrimaryKey @NonNull
    private UUID id;

    @NonNull
    private UUID postId;

    @NonNull
    private String type;

    @NonNull
    private Date actionTime;
}
