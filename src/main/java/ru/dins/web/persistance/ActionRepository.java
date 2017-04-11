package ru.dins.web.persistance;

import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import com.datastax.driver.core.utils.UUIDs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.dins.web.model.Action;
import ru.dins.web.model.ActionType;
import ru.dins.web.model.Like;
import ru.dins.web.model.Post;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by gnupinguin on 24.03.17.
 */
@Repository
public class ActionRepository {
    @Autowired
    private CassandraTemplate template;

    private Calendar calendar = Calendar.getInstance();

    public void addLikeAction(UUID id, String author, Date actionTime){
        template.insert(new Action(UUIDs.timeBased(), id, author, ActionType.LIKE.toString(), actionTime));
    }

    public void addCancellationLikeAction(UUID id, String author, Date actionTime){
        template.insert(new Action(UUIDs.timeBased(), id, author, ActionType.CANCELLATION_LIKE.toString(), actionTime));
    }

    public void addPostAction(Post post){
        template.insert(new Action(UUIDs.timeBased(), post.getId(), post.getAuthor(), ActionType.ADD_POST.toString(), post.getCreationTime()));
    }

    public void addEditPostAction(Post post){
        template.insert(new Action(UUIDs.timeBased(), post.getId(), post.getAuthor(), ActionType.EDIT_POST.toString(), post.getModificationTime()));

    }

    public void addDeletePostAction(Post post){
        template.insert(new Action(UUIDs.timeBased(), post.getId(), post.getAuthor(), ActionType.DELETE_POST.toString(), post.getCreationTime()));

    }

    public List<Action> getUserHistory(String author){
        Select selectStatement = QueryBuilder.select().from("action");
        selectStatement.where(QueryBuilder.eq("author", author));
        selectStatement.allowFiltering();

        return template.select(selectStatement, Action.class);
    }

    public List<Action> getHistory(){
        return template.selectAll(Action.class);
    }


}
