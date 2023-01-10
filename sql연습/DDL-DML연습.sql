-- DDL (데이터 정의어)
drop table member;
create table member (
	no int not null auto_increment,
    email varchar(100) not null,
    password varchar(64) not null,
    name varchar(100) not null,
    department varchar(100),
    primary key(no)
);
desc member;

alter table member add column juminbunho char(13) not null;

alter table member drop juminbunho;

alter table member add column juminbunho char(13) not null after email;

alter table member change column department dept varchar(200) not null;

alter table member add self_intro text;

alter table member drop juminbunho;

-- DML
desc member;
-- insert 순서를 맞춰야한다.
insert
into member
values (null, 'rolru7686@gmail.com', password('1234'), '남우근', '개발팀', null);

insert
into member(no, email, name, dept, password)
values (null, 'rolru76862@gmail.com', '남우근', '개발팀', password('1234'));

-- update
update member 
set email = 'rolru76863@gmail.com', password = password('5678')
where no = 2;

-- delete
delete 
from member
where no = 2;

select * from member;

-- transaction insert,update,delete 만 적용
select @@autocommit;
-- 1 : true 면 자동 commit
set @@autocommit=0;

insert
into member(no, email, name, dept, password)
values (null, 'rolru76865@gmail.com', '남우근5', '개발팀5', password('1234'));

-- rollback;

commit;