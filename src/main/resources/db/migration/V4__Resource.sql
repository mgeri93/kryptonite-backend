create table resource
(
    dtype      varchar(10),
    id         int auto_increment primary key,
    amount     int,
    updated_at date,
    kingdom_id INT,
    CONSTRAINT kingdom_id FOREIGN KEY (kingdom_id) REFERENCES kingdom (id)
)