--liquibase formatted sql

--changeset mlukin stripComments:true
--comment: add image column

ALTER TABLE tbl_map_marker
ADD COLUMN image BYTEA;
