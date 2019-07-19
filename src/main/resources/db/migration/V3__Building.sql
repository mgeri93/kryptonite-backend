CREATE TABLE building
(
    dtype         varchar(10),
    id            INT AUTO_INCREMENT PRIMARY KEY,
    building_type INT,
    hp            INT,
    level         INT,
    kingdom_id    INT,
    CONSTRAINT kingdom_id FOREIGN KEY (kingdom_id) REFERENCES kingdom (id)
);
