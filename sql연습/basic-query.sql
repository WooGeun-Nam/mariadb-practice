select version(), current_date, now() from dual;

-- 수학함수, 사직 연산도 된다.
select sin(pi()/4), 1+2+3+4 from dual;

-- 대소문자 구분 안한다.
sElect VERSION(), current_DATE, NOW() froM dual;

-- CLI에서 다중 실행 가능
select sin(pi() / 4); select 1+2*3-4/5 from dual;

-- 멀티라인 커맨드 \c 치면 취소
select 
version(),
now()
;

show databases; -- DB 확인
use test; -- DB 변경
show tables; -- Table 확인

-- table 생성 : DML
create table pet (
	name varchar(100),
	owner varchar(20),
	species varchar(20),
	gender char(1),
	birth date,
	death date
);

-- schema 확인 , describe
desc pet;

-- table 삭제
drop table pet;
show tables;

-- insert: DML(C)
insert into pet values('삼색이','쭈인','cat','f',null,null);

-- select: DML(R)
select * from pet;

-- update: DML(U)
update pet set name='리리' where name = '삼색이';

-- delete: DML(D)
delete from pet where name = '리리';

-- load data
load data local infile 'd:\pet.txt' into table pet;

-- select
select name, species from pet where name = 'bowser';