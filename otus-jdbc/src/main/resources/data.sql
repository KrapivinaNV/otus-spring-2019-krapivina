insert into BOOK (id, name) values (00000000-0000-0000-0000-000000000001, 'book1');
insert into BOOK (id, name) values (00000000-0000-0000-0000-000000000002, 'book2');
insert into BOOK (id, name) values (00000000-0000-0000-0000-000000000003, 'book3');

insert into AUTHOR (id, full_name) values (00000000-0000-0000-0000-000000000001, 'name1');
insert into AUTHOR (id, full_name) values (00000000-0000-0000-0000-000000000002, 'name2');
insert into AUTHOR (id, full_name) values (00000000-0000-0000-0000-000000000003, 'name3');

insert into GENRE (id, name) values (00000000-0000-0000-0000-000000000001, 'genre1');
insert into GENRE (id, name) values (00000000-0000-0000-0000-000000000002, 'genre2');
insert into GENRE (id, name) values (00000000-0000-0000-0000-000000000003, 'genre3');

insert into BOOK_AUTHOR (id_book, id_author) values (00000000-0000-0000-0000-000000000001, 00000000-0000-0000-0000-000000000001);
insert into BOOK_AUTHOR (id_book, id_author) values (00000000-0000-0000-0000-000000000002, 00000000-0000-0000-0000-000000000002);
insert into BOOK_AUTHOR (id_book, id_author) values (00000000-0000-0000-0000-000000000002, 00000000-0000-0000-0000-000000000003);
insert into BOOK_AUTHOR (id_book, id_author) values (00000000-0000-0000-0000-000000000003, 00000000-0000-0000-0000-000000000003);

insert into BOOK_GENRE (id_book, id_genre) values (00000000-0000-0000-0000-000000000001, 00000000-0000-0000-0000-000000000001);
insert into BOOK_GENRE (id_book, id_genre) values (00000000-0000-0000-0000-000000000002, 00000000-0000-0000-0000-000000000002);
insert into BOOK_GENRE (id_book, id_genre) values (00000000-0000-0000-0000-000000000003, 00000000-0000-0000-0000-000000000003);
