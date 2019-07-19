create table troop
(
    id      int auto_increment primary key,
    attack  int,
    defense int,
    hp      int,
    level   int
        kingdom_id INT,
    CONSTRAINT kingdom_id FOREIGN KEY (kingdom_id) REFERENCES kingdom (id)
)