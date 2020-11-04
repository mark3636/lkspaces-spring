--liquibase formatted sql

--changeset mlukin stripComments:true
--comment: create basic tables

INSERT INTO tbl_place_type(label, icon, description) VALUES
    ('Граффити', 'ic_palette', 'Места с таким типом содержат различные граффити.'),
    ('Заброшенные места', 'ic_domain', 'Места с таким типом показывают, где можно найти красивые заброшенные места. ' ||
     'Более подробную информацию о самом месте можно узнать в описании его маркера или в постах.'),
    ('Разное', 'ic_star', 'Нетипичные туристические места, которые удивят вас своей красотой и необычностью.');
