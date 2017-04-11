package dins;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * @Author Olga Taranova
 */

@RunWith(SpringJUnit4ClassRunner.class)
public class DatabaseApplicationTest {
    private String KEYSPACE_NAME = "murumKeyspace";
    private Session session;
    @Before
    public void connect(){
        Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
        session = cluster.connect();
    }
    @Test
    public void testExistenceOfKeyspace(){

        ResultSet result =
                session.execute("SELECT * FROM system_schema.keyspaces;");
        List<String> matchedKeyspaces = result.all()
                .stream()
                .filter(r -> r.getString(0).equals(KEYSPACE_NAME.toLowerCase()))
                .map(r -> r.getString(0))
                .collect(Collectors.toList());
        assertEquals(matchedKeyspaces.size(), 1);
        assertTrue(matchedKeyspaces.get(0).equals(KEYSPACE_NAME.toLowerCase()));
    }
    @Test
    public void testExistenceOfTableUser(){
        ResultSet result = session.execute(
                "SELECT * FROM " + KEYSPACE_NAME + ".user;");

        List<String> columnNames =
                result.getColumnDefinitions().asList().stream()
                        .map(cl -> cl.getName())
                        .collect(Collectors.toList());

        assertEquals(columnNames.size(), 3);
        assertTrue(columnNames.contains("id"));
        assertTrue(columnNames.contains("email"));
        assertTrue(columnNames.contains("nickname"));
    }
    @Test
    public void testExistenceOfTablePost(){
        ResultSet result = session.execute(
                "SELECT * FROM " + KEYSPACE_NAME + ".post;");

        List<String> columnNames =
                result.getColumnDefinitions().asList().stream()
                        .map(cl -> cl.getName())
                        .collect(Collectors.toList());

        assertEquals(columnNames.size(), 6);
        assertTrue(columnNames.contains("id"));
        assertTrue(columnNames.contains("user_id"));
        assertTrue(columnNames.contains("creation_time"));
        assertTrue(columnNames.contains("modification_time"));
        assertTrue(columnNames.contains("content"));
        assertTrue(columnNames.contains("version"));
    }
    @Test
    public void testExistenceOfTableLike(){
        ResultSet result = session.execute(
                "SELECT * FROM " + KEYSPACE_NAME + ".like;");

        List<String> columnNames =
                result.getColumnDefinitions().asList().stream()
                        .map(cl -> cl.getName())
                        .collect(Collectors.toList());

        assertEquals(columnNames.size(), 2);
        assertTrue(columnNames.contains("post_id"));
        assertTrue(columnNames.contains("counter"));
    }
    @Test
    public void testExistenceOfTableAction(){
        ResultSet result = session.execute(
                "SELECT * FROM " + KEYSPACE_NAME + ".action;");

        List<String> columnNames =
                result.getColumnDefinitions().asList().stream()
                        .map(cl -> cl.getName())
                        .collect(Collectors.toList());

        assertEquals(columnNames.size(), 5);
        assertTrue(columnNames.contains("id"));
        assertTrue(columnNames.contains("action_time"));
        assertTrue(columnNames.contains("user_id"));
        assertTrue(columnNames.contains("post_id"));
        assertTrue(columnNames.contains("type"));

    }
    @Test
    public void DoesTablePostSortByTime(){
        String query = "Select * from murumkeyspace.post;";
        ResultSet result = session.execute(query);
        List<Row> resultList = result.all();
        if (resultList.size() > 1)
        for (int i = 1; i < resultList.size(); i++)
            assertTrue(resultList.get(i-1).get("creation_time", Date.class)
                    .after
                            (resultList.get(i).get("creation_time", Date.class)));
    }
    @Test
    public void DoesTableActionSortByTime(){
        String query = "Select * from murumkeyspace.action;";
        ResultSet result = session.execute(query);
        List<Row> resultList = result.all();
        if (resultList.size() > 1)
            for (int i = 1; i < resultList.size(); i++)
                assertTrue(resultList.get(i-1).get( "action_time", Date.class)
                        .after
                                (resultList.get(i).get("action_time", Date.class)));
    }
}