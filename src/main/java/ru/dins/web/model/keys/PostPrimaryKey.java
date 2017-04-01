package ru.dins.web.model.keys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import org.springframework.cassandra.core.Ordering;
import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.repository.MapId;
import org.springframework.data.cassandra.repository.support.BasicMapId;

import java.io.Serializable;
import java.util.*;

/**
 * @author Ilja Pavlov
 */
@PrimaryKeyClass @Data @AllArgsConstructor
public class PostPrimaryKey implements Serializable {
    @NonNull @PrimaryKeyColumn(name="user_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private UUID userId;

    @NonNull @PrimaryKeyColumn( ordinal = 2, type = PrimaryKeyType.CLUSTERED)
    private UUID id;

    @NonNull @PrimaryKeyColumn(name = "modification_time", ordinal = 1, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
    private Date modificationTime;
}
