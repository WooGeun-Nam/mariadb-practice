-- subquery / 서브쿼리는 가급적 피하고 join을 이용

--
-- 1) select절, insert values(...)의 서브쿼리
-- 
-- select (select max(group_no from post) from dual;

--
-- 2) from절의 서브쿼리
--
select now() as n, sysdate() as s, 3 + 1 as r from dual;
select a.n, a.r
from (select now() as n, sysdate() as s, 3 + 1 as r from dual) a;

--
-- 3) where절의 서브쿼리
-- 

-- 예제) 현재, Fai Bale이 근무하는 부서에서 근무하는 다른 직원의 사번, 이름을 출력 하세요.
-- 쿼리2번 실행으로 작업을 진행할 시 자바가 데이터를 가지고 있을때 변경될 가능성을 생각
select de.dept_no
from employees e
join dept_emp de on e.emp_no = de.emp_no
where de.to_date = '9999-01-01'
and concat(e.first_name, ' ', e.last_name) = 'Fai Bale';

-- 'd004'
select e.emp_no, e.first_name
from employees e
join dept_emp de on e.emp_no = de.emp_no
where de.to_date = '9999-01-01'
and de.dept_no = (
	select de.dept_no
	from employees e
	join dept_emp de on e.emp_no = de.emp_no
	where de.to_date = '9999-01-01'
	and concat(e.first_name, ' ', e.last_name) = 'Fai Bale');
    
-- 3-1) 단일행 연산자: =, >, <, >=, <=, <>, != (비교연산자)
-- 실습문제1:
-- 현재, 전체 사원의 평균 연봉보다 적은 급여를 받는 사원의 이름과 급여를 출력 하세요.
select e.first_name, s.salary
from employees e
join salaries s on e.emp_no = s.emp_no
where s.to_date = '9999-01-01'
and s.salary < (
	select avg(salary)
	from salaries
	where to_date = '9999-01-01')
order by s.salary desc;

-- 실습문제2:
-- 현재, 가장 적은 평균 급여의 직책과 그 평균급여를 출력 하세요.
select min(a.avg_salary)
from (
	select t.title as title, avg(s.salary) as avg_salary
	from salaries s
	join titles t on s.emp_no = t.emp_no
	where s.to_date = '9999-01-01'
	and t.to_date = '9999-01-01'
	group by t.title) a;
    
-- sol1) subquery
select t.title as title, avg(s.salary) as avg_salary
from salaries s
join titles t on s.emp_no = t.emp_no
where s.to_date = '9999-01-01'
and t.to_date = '9999-01-01'
group by t.title
having avg_salary = (
	select min(a.avg_salary)
	from (
		select t.title as title, avg(s.salary) as avg_salary
		from salaries s
		join titles t on s.emp_no = t.emp_no
		where s.to_date = '9999-01-01'
		and t.to_date = '9999-01-01'
		group by t.title) a);

-- sol2) top-k
select t.title as title, avg(s.salary) as avg_salary
from salaries s
join titles t on s.emp_no = t.emp_no
where s.to_date = '9999-01-01'
and t.to_date = '9999-01-01'
group by t.title
order by avg_salary
limit 0,1;
    
-- 3-2) 복수행 연산자: 