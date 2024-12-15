create extension if not exists "uuid-ossp";

-- Таблица книг
create table if not exists book (
    uuid    uuid    not null default uuid_generate_v4(),
    title   varchar not null,
    author  varchar not null,
    genre   varchar,
    is_read boolean default false,
    review  text,
    constraint book_uuid_pk primary key (uuid)
);

-- Таблица ролей
create table if not exists role (
    id   serial  not null,
    name varchar not null,
    constraint role_id_pk primary key (id)
);

insert into role (id, name) values (1, 'admin');
insert into role (id, name) values (2, 'base');

-- Таблица аккаунтов
create table if not exists account (
    uuid     uuid    not null default uuid_generate_v4(),
    username varchar not null,
    name     varchar not null,
    lastname varchar not null,
    birthday date    not null,
    email    varchar not null,
    password varchar not null,
    avatar   varchar not null default 'https://mirtex.ru/wp-content/uploads/2023/04/unnamed.jpg',
    about    varchar not null default '-',
    role_id  integer not null default 2,
    constraint account_uuid_pk primary key (uuid),
    constraint account_username_uk unique (username),
    constraint account_email_uk unique (email),
    constraint account_role_fk foreign key (role_id) references role (id)
);

-- Таблица постов
create table if not exists post (
    uuid                uuid    not null default uuid_generate_v4(),
    author_uuid         uuid    not null,
    title               varchar not null,
    content             varchar not null,
    image               varchar not null,
    date_of_publication date    not null,
    constraint post_uuid_pk primary key (uuid),
    constraint post_author_fk foreign key (author_uuid) references account (uuid)
);

-- Таблица комментариев
create table if not exists comment (
    uuid                uuid    not null default uuid_generate_v4(),
    author_uuid         uuid    not null,
    post_uuid           uuid    not null,
    content             varchar not null,
    date_of_publication date    not null,
    constraint comment_uuid_pk primary key (uuid),
    constraint comment_author_fk foreign key (author_uuid) references account (uuid),
    constraint comment_post_fk foreign key (post_uuid) references post (uuid)
);
