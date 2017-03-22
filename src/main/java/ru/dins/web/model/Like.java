package ru.dins.web.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import java.util.UUID;

/**
 * Created by gnupinguin on 17.03.17.
 */
@Data
@NoArgsConstructor @AllArgsConstructor
@Table
public class Like {
    @PrimaryKey @NonNull
    private UUID postId;

    private int counter;

    public int increase(){
        return ++counter;
    }

    public int decrease(){
        return --counter;
    }

}
