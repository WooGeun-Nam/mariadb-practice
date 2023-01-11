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
    
-- 3-2) 복수행 연산자: in, not in, 비교연산자any, 비교연산자all

-- any 사용법
-- 1. =any : in
-- 2. >any, >=any : 최솟값
-- 3. <any, <=any : 최댓값
-- 4. <>any, !=any : not in

-- all 사용법
-- 1. =all : (x)
-- 2. >all, >=all : 최댓값
-- 3. <all, <=all : 최솟값
-- 4. <>all, !=all

-- 실습문제3
-- 현재 급여가 50000 이상인 직원의 이름과 급여를 출력 하세요.
-- 돌리 6000
-- 또치 8000

-- sol1) join
select e.first_name, s.salary
from employees e
join salaries s on e.emp_no = s.emp_no
where s.to_date = '9999-01-01'
and s.salary >= 50000
order by s.salary;

-- sol2) subquery multi coulmn
select e.first_name, s.salary
from employees e
join salaries s
on e.emp_no = s.emp_no
and s.to_date = '9999-01-01'
and (e.emp_no, s.salary) =any (
	select emp_no, salary
	from salaries
	where to_date = '9999-01-01'
	and salary >= 50000 )
order by s.salary;

-- 실습문제4: 현재, 각 부서별로 최고 급여를 받고 있는 직원의 이름과 월급을 출력 하세요.
-- 총무 둘리 4000
-- 영업 또치 5000

-- sol1: where절 subquery(in)
select d.dept_name, e.first_name, s.salary
from departments d
join dept_emp de on d.dept_no = de.dept_no
join employees e on de.emp_no = e.emp_no
join salaries s on e.emp_no = s.emp_no
where de.to_date = '9999-01-01'
and s.to_date = '9999-01-01'
and (d.dept_no, s.salary) in (
	select de.dept_no, max(s.salary)
	from dept_emp de
	join salaries s on de.emp_no = s.emp_no
	where de.to_date = '9999-01-01'
	and s.to_date = '9999-01-01'
	group by de.dept_no);

-- sol2: from절 join
select d.dept_name, e.first_name, s.salary
from departments d
join dept_emp de on d.dept_no = de.dept_no
join employees e on de.emp_no = e.emp_no
join salaries s on e.emp_no = s.emp_no
join (select de.dept_no, max(s.salary) as max_salary
	from dept_emp de
	join salaries s on de.emp_no = s.emp_no
	where de.to_date = '9999-01-01'
	and s.to_date = '9999-01-01'
	group by de.dept_no) a on de.dept_no = a.dept_no and s.salary = a.max_salary
where de.to_date = '9999-01-01'
and s.to_date = '9999-01-01';