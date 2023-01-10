-- inner join

-- 예제1) 현재, 근무하고 있는 직원의 이름과 직책을 모두 출력하세요.
select e.first_name, t.title
from employees e, titles t
where e.emp_no = t.emp_no -- join 조건(n-1)
and to_date = '9999-01-01'; -- row 선택 조건
-- 표준 join
select e.first_name, t.title
from employees e join titles t on e.emp_no = t.emp_no
where to_date = '9999-01-01';

-- 예제2) 현재, 근무하고 있는 직원의 이름과 직책을 출력하되 여성 엔지니어(Engineer)만 출력하세요.
select e.first_name, e.gender, t.title
from employees e join titles t on e.emp_no = t.emp_no
where to_date = '9999-01-01'
and e.gender = 'F'
and t.title = 'Engineer';

--
-- ANSI/ISO SQL1999 JOIN 표준 문법
--

-- 1) Natural Join
--    조인 대상이 되는 두 테이블에 이름이 같은 공통 컬럼이 있으면 조인 조건
--    명시하지 않고 암묵적으로 조인이 된다.
select emp_no, e.first_name, t.title -- natural join은 join된 컬럼이 에러가 아님
from employees e natural join titles t
where to_date = '9999-01-01';

-- 2) join ~ using
-- natural join의 문제점
select count(*)
from salaries a natural join titles b
where a.to_date = '9999-01-01'
and b.to_date = '9999-01-01';

select count(*)
from salaries a join titles b using(emp_no)
where a.to_date = '9999-01-01'
and b.to_date = '9999-01-01';
-- 문제점 : FK와 PK의 컬럼명이 같아야 가능하다.

-- 3) join ~ on
-- 예제) 현재, 직책별 평균 연봉을 큰 순서대로 출력 하세요.
select t.title, avg(salary)
from titles t join salaries s
on t.emp_no = s.emp_no
where s.to_date = '9999-01-01'
and t.to_date = '9999-01-01'
group by t.title
order by avg(salary) desc;

-- 실습문제1
-- 현재, 직원별 근무 부서를 사번, 직원 이름, 부서명으로 출력해보세요.
select e.emp_no, e.first_name, d.dept_name
from departments d join dept_emp de
on d.dept_no = de.dept_no
join employees e
on de.emp_no = e.emp_no
where de.to_date = '9999-01-01';

-- 실습문제2
-- 현재, 지급되고 있는 급여를 출력 해보세요.
-- 사번, 이름, 급여 순으로 출력
select e.emp_no, e.first_name, s.salary
from employees e join salaries s
on e.emp_no = s.emp_no
where s.to_date = '9999-01-01';

-- 실습문제3
-- 현재, 직책별 평균연봉, 직원수를 구하되 직책별 직원수가 100명 이상인 직책만 출력하세요.
-- 직책, 평균연봉 직원수 순으로 출력
select t.title, avg(salary), count(title)
from titles t join salaries s
on t.emp_no = s.emp_no
where t.to_date = '9999-01-01'
and s.to_date = '9999-01-01'
group by t.title
having count(title) >= 100;

-- 실습문제4
-- 현재, 부서별로 직책이 Engineer인 직원들에 대해서만 평균 급여를 구하세요.
-- 부서이름, 평균급여 순으로 출력하세요.
select d.dept_name, avg(s.salary)
from departments d join dept_emp de 
on d.dept_no=de.dept_no
join salaries s
on de.emp_no = s.emp_no
join titles t
on de.emp_no = t.emp_no
where de.to_date = '9999-01-01'
and t.to_date = '9999-01-01'
and s.to_date = '9999-01-01'
and t.title = 'Engineer'
group by d.dept_name
order by avg(s.salary) desc;

-- outer join
