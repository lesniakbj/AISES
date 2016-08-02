BEGIN;
  CREATE TABLE IF NOT EXISTS Post(post_id SERIAL NOT NULL PRIMARY KEY,
                                text varchar(1024) NULL,
                                length int NULL,
                                date_created TIMESTAMP WITHOUT TIME ZONE);

  CREATE TABLE IF NOT EXISTS AISESUserPost(post_id integer REFERENCES Post(post_id),
                                           user_id integer REFERENCES AISESUser(user_id));
END;