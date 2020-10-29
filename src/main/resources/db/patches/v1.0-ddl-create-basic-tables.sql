--liquibase formatted sql

--changeset mlukin stripComments:true
--comment: create basic tables

CREATE TABLE tbl_user
(
    id SERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL
);

CREATE TABLE tbl_place_type
(
    id SERIAL PRIMARY KEY,
    label VARCHAR(255) NOT NULL UNIQUE ,
    icon VARCHAR(255),
    description TEXT
);

CREATE TABLE tbl_map_marker
(
    id SERIAL PRIMARY KEY,
    lng NUMERIC NOT NULL,
    lat NUMERIC NOT NULL,
    label VARCHAR(255) NOT NULL,
    description TEXT,
    id_place_type INT NOT NULL,
    FOREIGN KEY (id_place_type) REFERENCES tbl_place_type(id)
);

CREATE TABLE tbl_post
(
    id SERIAL PRIMARY KEY,
    label VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    date TIMESTAMP NOT NULL,
    status VARCHAR(255) NOT NULL,
    id_place_type INT NOT NULL,
    id_author INT NOT NULL,
    id_map_marker INT,
    FOREIGN KEY (id_place_type) REFERENCES tbl_place_type(id),
    FOREIGN KEY (id_author) REFERENCES tbl_user(id),
    FOREIGN KEY (id_map_marker) REFERENCES tbl_map_marker(id)
);

CREATE TABLE tbl_event
(
    id SERIAL PRIMARY KEY,
    label VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    start_date TIMESTAMP NOT NULL,
    end_date TIMESTAMP NOT NULL,
    status VARCHAR(255) NOT NULL,
    id_author INT NOT NULL,
    id_map_marker INT,
    FOREIGN KEY (id_author) REFERENCES tbl_user(id),
    FOREIGN KEY (id_map_marker) REFERENCES tbl_map_marker(id)
);

CREATE TABLE tbl_attendance
(
    id SERIAL PRIMARY KEY,
    id_user INT NOT NULL,
    id_event INT NOT NULL,
    FOREIGN KEY (id_user) REFERENCES tbl_user(id),
    FOREIGN KEY (id_event) REFERENCES tbl_event(id)
);
