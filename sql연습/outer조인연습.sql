-- outer join

-- AI는 null 넣으면 자동
-- insert into dept values(null, '총무');
-- insert into dept values(null, '개발');
-- insert into dept values(null, '영업');
-- insert into dept values(null, '기획');

select * from dept;

-- insert into emp values(null, '둘리', 1);
-- insert into emp values(null, '마이콜', 2);
-- insert into emp values(null, '또치', 3);
-- insert into emp values(null, '길동이', null);

select * from emp;

select e.name, d.name
from emp e join dept d
on e.dept_no = d.no;

-- left join
select e.name as '이름', ifnull(d.name, '없음') as '부서'
from emp e left join dept d
on e.dept_no = d.no;

-- right join
select ifnull(e.name, '없음') as '이름', d.name as '부서'
from emp e right join dept d
on e.dept_no = d.no;