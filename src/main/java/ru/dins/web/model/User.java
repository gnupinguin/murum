package ru.dins.web.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.cassandra.mapping.Indexed;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import java.util.UUID;

/**
 * @author Ilja Pavlov
 */
@Table @Data @AllArgsConstructor
public class User {
    @PrimaryKey @NonNull
    private UUID id;

    @Indexed @NonNull
    private String email;

    @NonNull
    private String nickname;
}
