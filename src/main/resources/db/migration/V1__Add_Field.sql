create table liked_pokemon
(
    id         binary(16)   not null
        primary key,
    liked_date date         not null,
    name       varchar(255) not null
)
    go
