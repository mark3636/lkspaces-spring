--liquibase formatted sql

--changeset mlukin stripComments:true
--comment: create basic tables

CREATE TABLE tbl_user
(
    id INT IDENTITY PRIMARY KEY,
    email NVARCHAR(255) NOT NULL UNIQUE,
    first_name NVARCHAR(255) NOT NULL,
    last_name NVARCHAR(255) NOT NULL,
    password NVARCHAR(255) NOT NULL,
    role NVARCHAR(255) NOT NULL
);

CREATE TABLE tbl_place_type
(
    id INT IDENTITY PRIMARY KEY,
    label NVARCHAR(255) NOT NULL UNIQUE ,
    icon NVARCHAR(255),
    description NVARCHAR(1000)
);

CREATE TABLE tbl_map_marker
(
    id INT IDENTITY PRIMARY KEY,
    lng NUMERIC NOT NULL,
    lat NUMERIC NOT NULL,
    label NVARCHAR(255) NOT NULL,
    description NVARCHAR(2000),
    status NVARCHAR(255) NOT NULL,
    id_place_type INT NOT NULL,
    FOREIGN KEY (id_place_type) REFERENCES tbl_place_type(id)
);

CREATE TABLE tbl_post
(
    id INT IDENTITY PRIMARY KEY,
    label NVARCHAR(255) NOT NULL,
    description NVARCHAR(2000) NOT NULL,
    date DATETIME NOT NULL,
    status NVARCHAR(255) NOT NULL,
    id_place_type INT NOT NULL,
    id_author INT NOT NULL,
    id_map_marker INT,
    FOREIGN KEY (id_place_type) REFERENCES tbl_place_type(id),
    FOREIGN KEY (id_author) REFERENCES tbl_user(id),
    FOREIGN KEY (id_map_marker) REFERENCES tbl_map_marker(id)
);

CREATE TABLE tbl_event
(
    id INT IDENTITY PRIMARY KEY,
    label NVARCHAR(255) NOT NULL,
    description NVARCHAR(2000) NOT NULL,
    start_date DATETIME NOT NULL,
    end_date DATETIME NOT NULL,
    status NVARCHAR(255) NOT NULL,
    id_author INT NOT NULL,
    id_map_marker INT,
    FOREIGN KEY (id_author) REFERENCES tbl_user(id),
    FOREIGN KEY (id_map_marker) REFERENCES tbl_map_marker(id)
);

CREATE TABLE tbl_attendance
(
    id INT IDENTITY PRIMARY KEY,
    id_user INT NOT NULL,
    id_event INT NOT NULL,
    FOREIGN KEY (id_user) REFERENCES tbl_user(id),
    FOREIGN KEY (id_event) REFERENCES tbl_event(id)
);
