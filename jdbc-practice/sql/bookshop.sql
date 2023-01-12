-- bookshop
show tables;
desc author;
desc book;

select no,name from author order by no asc;
insert into author values(null, ?, ?, ?);

insert into book values(null, title, author_no, null);

select no, title, author_no, rent from book order by no asc;

update book set rent = Y where no = no;

select name from author where no = 1;

select * from book;