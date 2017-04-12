package ru.dins.web.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.cassandra.mapping.*;
import ru.dins.web.model.keys.ActionPrimaryKey;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author Ilja Pavlov
 */
@Table @Data @NoArgsConstructor @AllArgsConstructor
public class Action implements Serializable {
    @NonNull @PrimaryKey
    private ActionPrimaryKey key;


    @NonNull @Column("post_id") @Indexed
    private UUID postId;

    @NonNull
    private ActionType type;

    public enum ActionType {
        LIKE, CANCELLATION_LIKE , ADD_POST, EDIT_POST, DELETE_POST,
    }
}
