CREATE TABLE IF NOT EXISTS  users (
    user_id bigint generated by default as identity,
    email varchar(255),
    password varchar(255),
    bio varchar(255),
    image varchar(255),
    username varchar(255),
    created_at datetime(6),
    updated_at datetime(6),
    primary key (user_id),
    constraint UK_email unique (email),
    constraint UK_username unique (username)
);

CREATE TABLE IF NOT EXISTS article (
    article_id bigint generated by default as identity,
    body varchar(255),
    description varchar(255),
    slug varchar(255),
    title varchar(255),
    user_id bigint,
    created_at datetime,
    updated_at datetime,
    primary key (article_id)
   -- foreign key (user_id) references users(user_id)
);

CREATE TABLE IF NOT EXISTS comments (
    comment_id bigint generated by default as identity,
    article_id bigint,
    body varchar(255),
    user_id bigint,
    created_at datetime,
    updated_at datetime,
    primary key (comment_id)
    --foreign key (article_id) references article(article_id)
);

CREATE TABLE IF NOT EXISTS  follow_relation (
    followee_id bigint not null,
    follower_id bigint not null,
    created_at datetime(6),
    updated_at datetime(6),
    primary key (followee_id, follower_id)
 );

CREATE TABLE IF NOT EXISTS tag (
    tag_id bigint generated by default as identity,
    tag varchar(255),
    article_id bigint,
    created_at datetime(6),
    updated_at datetime(6),
    primary key (tag_id),
    constraint UK_tag unique (tag)
--    foreign key (article_id) references article(article_id)
 );

CREATE TABLE IF NOT EXISTS article_tag (
    article_tag_id bigint generated by default as identity,
    article_id bigint,
    tag_id bigint,
    created_at datetime(6),
    updated_at datetime(6),
    primary key (article_tag_id)
--    constraint uix_article_article_id_tag_tag_id unique (article_id, tag_id),
--    constraint fk_article_article_id foreign key (article_id) references article,
--    constraint fk_tag_tag_id foreign key (tag_id) references tag
);

CREATE TABLE IF NOT EXISTS favorite (
    user_id bigint not null,
    favorited_id integer not null,
    favorite_type varchar(16) not null,
    created_at datetime(6),
    updated_at datetime(6),
    primary key (user_id, favorited_id, favorite_type)
);
