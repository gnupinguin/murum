CREATE KEYSPACE IF NOT EXISTS  murumKeyspace WITH replication = {'class':'SimpleStrategy','replication_factor':1};

DROP TABLE IF EXISTS murumkeyspace.action;
CREATE TABLE murumkeyspace.action (
    id uuid,
    action_time timestamp,
    user_id uuid,
    post_id uuid,
    type text,
    PRIMARY KEY (user_id, action_time)
) WITH CLUSTERING ORDER BY (action_time DESC);
CREATE INDEX post_action ON murumkeyspace.action(post_id);

DROP TABLE IF EXISTS murumkeyspace.post;
CREATE TABLE murumkeyspace.post (
    id uuid,
    user_id uuid,
    creation_time timestamp,
    modification_time timestamp,
    content text,
    version int,
    PRIMARY KEY(user_id, modification_time)
)WITH CLUSTERING ORDER BY (modification_time DESC);

DROP TABLE IF EXISTS murumkeyspace.like;
CREATE TABLE murumkeyspace.like (
    postid uuid PRIMARY KEY,
    counter int
);

DROP TABLE IF EXISTS murumkeyspace.user;
CREATE TABLE murumkeyspace.user(
	id uuid PRIMARY KEY,
	email text,
	nickname text	
);
CREATE INDEX user_email ON murumkeyspace.user(email);



INSERT INTO murumkeyspace.post JSON '{"id": "4e4e66a0-13ec-11e7-9f4f-4fab082d1ceb", "modification_time": "2017-03-28 19:25:30.506Z", "user_id": "01ba3850-1450-11e7-a164-d35384f14027", "creation_time": "2017-03-28 19:25:30.506Z", "content": "Hello, World!", "version": 0}';
INSERT INTO murumkeyspace.post JSON '{"id": "28b75590-13ed-11e7-9afb-d35384f14027", "modification_time": "2017-03-28 19:31:36.937Z", "user_id": "01ba3850-1450-11e7-a164-d35384f14027", "creation_time": "2017-03-28 19:31:36.937Z", "content": "This is the end, beautiful friend", "version": 0}';
INSERT INTO murumkeyspace.post JSON '{"id": "8de1c590-13ed-11e7-aa32-db15298acebc", "modification_time": "2017-03-28 19:34:26.665Z", "user_id": "01ba3850-1450-11e7-a164-d35384f14027", "creation_time": "2017-03-28 19:34:26.665Z", "content": "Человек - это звучит гордо!", "version": 0}';
INSERT INTO murumkeyspace.post JSON '{"id": "01ba3850-1450-11e7-a164-59d5df0beef9", "modification_time": "2017-03-29 07:19:11.701Z", "user_id": "01ba3850-1450-11e7-a164-d35384f14027", "creation_time": "2017-03-29 07:19:11.701Z", "content": "Why does not it work?", "version": 0}';
INSERT INTO murumkeyspace.post JSON '{"id": "1936cb10-1450-11e7-a4b4-6758eeaf42a1", "modification_time": "2017-03-29 07:19:51.105Z", "user_id": "01ba3850-1450-11e7-a164-d35384f14027", "creation_time": "2017-03-29 07:19:51.105Z", "content": "God, please, help me!", "version": 0}';



INSERT INTO murumkeyspace.action JSON '{"id": "4e4e66a0-13ec-11e7-9f4f-d35384f14027", "post_id": "28b75590-13ed-11e7-9afb-d35384f14027", "action_time": "2017-03-29 13:31:36.937Z", "user_id": "01ba3850-1450-11e7-a164-d35384f14027", "type": "LIKE"}';
INSERT INTO murumkeyspace.action JSON '{"id": "4e4e66a0-13ec-11e7-9f4f-d35384f14027", "post_id": "28b75590-13ed-11e7-9afb-d35384f14027", "action_time": "2017-03-29 13:31:36.937Z", "user_id": "01ba3850-1450-11e7-a164-d35384f14027", "type": "LIKE"}';


INSERT INTO murumkeyspace.like (postid, counter) VALUES ( 28b75590-13ed-11e7-9afb-d35384f14027, 1);

INSERT INTO murumkeyspace.user JSON '{"id": "01ba3850-1450-11e7-a164-d35384f14027", "email": "ilja.pavlov32@gmail.com", "nickname": "Ilja Pavlov"}';
