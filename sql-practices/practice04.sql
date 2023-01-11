-- 서브쿼리(SUBQUERY) SQL 문제입니다.

-- 문제1.
-- 현재 평균 연봉보다 많은 월급을 받는 직원은 몇 명이나 있습니까?
select count(*)
from salaries
where to_date = '9999-01-01'
and salary > (
	select avg(salary)
	from salaries
	where to_date = '9999-01-01');

-- 문제2. (x)
-- 현재, 각 부서별로 최고의 급여를 받는 사원의 사번, 이름, 급여를 조회하세요. 
-- 단 조회결과는 연봉의 내림차순으로 정렬되어 나타나야 합니다. 

-- 문제3.
-- 현재, 자신의 부서 평균 급여보다 급여가 많은 사원의 사번, 이름과 급여를 조회하세요 
select e.emp_no, e.first_name, s.salary
from employees e
join dept_emp de on e.emp_no = de.emp_no
join salaries s on e.emp_no = s.emp_no
join (
	select de.dept_no as dept_no, avg(salary) as avg_salary
	from dept_emp de
	join salaries s on de.emp_no = s.emp_no
	where s.to_date = '9999-01-01'
	and de.to_date = '9999-01-01'
	group by de.dept_no) as a on de.dept_no = a.dept_no
where de.to_date = '9999-01-01'
and s.to_date = '9999-01-01'
and s.salary > a.avg_salary;

-- 문제4.
-- 현재, 사원들의 사번, 이름, 매니저 이름, 부서 이름으로 출력해 보세요.
select e.emp_no, e.first_name, a.manager_name, a.dept_name
from employees e
join dept_emp de on e.emp_no = de.emp_no
join (
	select d.dept_name as dept_name, d.dept_no as dept_no, e.first_name as manager_name
	from departments d
	join dept_manager dm on d.dept_no = dm.dept_no
	join employees e on e.emp_no = dm.emp_no
	where dm.to_date = '9999-01-01') as a on de.dept_no = a.dept_no
where de.to_date = '9999-01-01';

-- 문제5.
-- 현재, 평균급여가 가장 높은 부서의 사원들의 사번, 이름, 직책, 급여를 조회하고 급여 순으로 출력하세요.
select e.emp_no, e.first_name, t.title, s.salary
from dept_emp de
join employees e on de.emp_no = e.emp_no
join titles t on e.emp_no = t.emp_no
join salaries s on e.emp_no = s.emp_no
where de.to_date = '9999-01-01'
and t.to_date = '9999-01-01'
and s.to_date = '9999-01-01'
and de.dept_no = (
	select a.dept_no
	from (
		select de.dept_no as dept_no , avg(s.salary) as avg_salary
		from dept_emp de
		join salaries s on de.emp_no = s.emp_no
		where de.to_date = '9999-01-01'
		and s.to_date = '9999-01-01'
		group by de.dept_no
        order by avg_salary desc
        limit 1) as a);

-- 문제6.
-- 평균 급여가 가장 높은 부서는? 
-- 부서이름, 평균 급여
select d.dept_name, a.avg_salary
from departments d
join (
	select de.dept_no as dept_no , avg(s.salary) as avg_salary
	from dept_emp de
	join salaries s on de.emp_no = s.emp_no
	where de.to_date = '9999-01-01'
	and s.to_date = '9999-01-01'
	group by de.dept_no
	order by avg_salary desc
	limit 1) as a on d.dept_no = a.dept_no;

-- 문제7.
-- 평균 급여가 가장 높은 직책?
-- 직책, 평균급여
select t.title as title, avg(s.salary) as avg_salary
from titles t
join salaries s on t.emp_no = s.emp_no
where t.to_date = '9999-01-01'
and s.to_date = '9999-01-01'
group by t.title
having avg_salary = (
	select max(a.avg_salary)
	from(
		select t.title as title, avg(s.salary) as avg_salary
		from titles t
		join salaries s on t.emp_no = s.emp_no
		where t.to_date = '9999-01-01'
		and s.to_date = '9999-01-01'
		group by t.title) as a);
        
select t.title as title, avg(s.salary) as avg_salary
from titles t
join salaries s on t.emp_no = s.emp_no
where t.to_date = '9999-01-01'
and s.to_date = '9999-01-01'
group by t.title
order by avg_salary desc
limit 1;

-- 문제8.
-- 현재 자신의 매니저보다 높은 연봉을 받고 있는 직원은?
-- 부서이름, 사원이름, 연봉, 매니저 이름, 매니저 연봉 순으로 출력합니다.
select b.dept_name, b.name, s.salary, b.manager_name, b.manager_salary
from(
	select e.emp_no as emp_no, e.first_name as name, a.manager_name as manager_name, a.dept_name as dept_name, a.manager_salary as manager_salary
	from employees e
	join dept_emp de on e.emp_no = de.emp_no
	join (
		select d.dept_name as dept_name, d.dept_no as dept_no, e.first_name as manager_name, s.salary as manager_salary
		from departments d
		join dept_manager dm on d.dept_no = dm.dept_no
		join employees e on e.emp_no = dm.emp_no
		join salaries s on e.emp_no = s.emp_no
		where dm.to_date = '9999-01-01'
		and s.to_date = '9999-01-01') as a on de.dept_no = a.dept_no
	where de.to_date = '9999-01-01') as b
join salaries s on b.emp_no = s.emp_no
where s.to_date = '9999-01-01'
and s.salary > b.manager_salary;