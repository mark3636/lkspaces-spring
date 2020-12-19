--liquibase formatted sql

--changeset mlukin stripComments:true
--comment: create basic tables

INSERT INTO tbl_place_type(label, icon, description) VALUES
    (N'Граффити', 'ic_palette', N'Места с таким типом содержат различные граффити.'),
    (N'Заброшенные места', 'ic_domain', N'Места с таким типом показывают, где можно найти красивые заброшенные места. ' +
     N'Более подробную информацию о самом месте можно узнать в описании его маркера или в постах.'),
    (N'Разное', 'ic_star', N'Нетипичные туристические места, которые удивят вас своей красотой и необычностью.');
