package ru.dins.CassandraRepository;

import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import java.util.UUID;

/**
 * Created by key on 20.03.2017.
 */
@Table
public class Like {
    @PrimaryKey
    private UUID postId;
    private int counter;

    void Increment(){
        counter++;
    }
}
