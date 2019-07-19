create table resource
(
    id         int auto_increment primary key,
    amount     int,
    amount_per_minute int,
    kingdom_id int,
    updated_at datetime,
    dtype      varchar(10),
    FOREIGN KEY (kingdom_id) REFERENCES kingdom (id)
);
