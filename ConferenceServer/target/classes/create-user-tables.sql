BEGIN;
CREATE TABLE IF NOT EXISTS AISESUser(user_id SERIAL NOT NULL PRIMARY KEY,
                                     email_address varchar(256) NULL,
                                     first_name varchar(256) NULL,
                                     last_name varchar(256) NULL,
                                     date_created TIMESTAMP WITHOUT TIME ZONE,
                                     last_updated TIMESTAMP WITHOUT TIME ZONE);

CREATE TABLE IF NOT EXISTS SocialLoginType(social_login_type_id SERIAL NOT NULL PRIMARY KEY,
                                           provider_name varchar(256));

CREATE TABLE IF NOT EXISTS SocialLogin(social_login_type_id integer REFERENCES SocialLoginType(social_login_type_id),
                                       social_login_id varchar(256) NOT NULL PRIMARY KEY);

CREATE TABLE IF NOT EXISTS AISESUserSocialLogin(user_id integer REFERENCES AISESUser(user_id),
                                                social_id varchar(256) REFERENCES SocialLogin(social_login_id));
END;