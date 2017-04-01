package ru.dins.web.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import java.util.UUID;

/**
 * @author Ilja Pavlov
 */
@Data
@NoArgsConstructor @AllArgsConstructor
@Table
public class Like {
    @PrimaryKey("post_id") @NonNull
    private UUID postId;

    private int counter;

    public Like increase(){
        counter++;
        return this;
    }

    public Like decrease(){
        counter--;
        return this;
    }

}
