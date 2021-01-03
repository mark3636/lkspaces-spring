--liquibase formatted sql

--changeset mlukin stripComments:true
--comment: add image column

ALTER TABLE tbl_user
ADD COLUMN image BYTEA;
