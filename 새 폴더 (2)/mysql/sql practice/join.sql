use testdb;
-- ------------------------------
-- JOIN
-- ------------------------------
-- 두개이상의 테이블을 서로 묶어서 하나의 조회결과를 만드는데 사용

-- ------------------------------
-- JOIN 종류
-- ------------------------------
-- INNER JOIN	: ON이하의 조건절을 만족하는 행만 JOIN
-- OUTER JOIN	: ON이하의 조건절을 만족하지 않는 행도 JOIN
	-- LEFT OUTER JOIN  : 조건을 만족하지 않는 왼쪽 테이블의 행도 JOIN
    -- RIGHT OUTER JOIN : 조건을 만족하지 않는 오른쪽 테이블의 행도 JOIN
	-- FULL OUTER JOIN  : 조건을 만족하지 않는 왼/오른쪽 테이블의 행도 JOIN
-- CROSS JOIN	: 한쪽 테이블의 모든 행과 반대쪽 테이블의 모든 행을 JOIN, 조건절 x
-- SELF JOIN	: 한 테이블 내에서 다른 행과의 JOIN

-- INNER JOIN 기본
use shopdb;
select * from usertbl;
select * from buytbl;

select * 
from usertbl 
inner join buytbl
on usertbl.userid = buytbl.userid; 

-- INNER JOIN 원하는 열만 출력 (열이름 중복시 특정 테이블의 열이름으로 지정한다.)
select usertbl.userid,name,addr,mobile1,mobile2,prodname,price,amount
from usertbl
inner join buytbl
on usertbl.userid=buytbl.userid;

-- INNER JOIN (별칭지정)
select U.userid,name,addr,mobile1,mobile2,prodname,price,amount
from usertbl U
inner join buytbl B
on U.userid=B.userid;

-- WHERE 조건절 적용
select U.userid,name,addr,mobile1,mobile2,prodname,price,amount
from usertbl U
inner join buytbl B
on U.userid=B.userid
where amount<=5;

-- 문제
-- 1 바비킴의 userid,birthyear,prodname,groupname을 출력하세요.
select U.userid,name,birthyear,prodname,groupname
from usertbl U
inner join buytbl B
on U.userid=B.userid
where name = '바비킴';

-- 2 amount*price의 값이 100 이상인 행의 name,addr,prodname,mobile1-mobile2를(concat()함수사용)출력하세요
select name,addr,concat(mobile1,'-',mobile2),amount*price
from usertbl U
inner join buytbl B
on U.userid = B.userid
where amount*price >=100;

-- 3 groupname이 전자인 행의 userid,name,birthyear,prodname을 출력하세요
select U.userid,name,birthyear,prodname,groupname
from usertbl U
inner join buytbl B
on U.userid = B.userid
where groupname='전자';

-- OUTER JOIN

-- LEFT OUTER JOIN(on 조건을 만족하지 않는 left테이블의 행도 출력)
select *
from usertbl U
left outer join buytbl B
on U.userid=B.userid;

-- RIGHT OUTER JOIN(on 조건을 만족하지 않는 RIGHT 테이블의 행도 출력)
select *
from buytbl B
right outer join usertbl U
on U.userid=B.userid;

-- FULL OUTER JOIN(on 조건을 만족하지 않는 left, right 테이블의 행도 출력)
-- mysql에서는 full outer join을 지원하지 않음.
-- 대신에 union을 이용해서 left,right outer join을 연결한다.
select *
from usertbl U
left outer join buytbl B
on U.userid = B.userid
union
select *
from usertbl U
right outer join buytbl B
on U.userid=B.userid;

-- 여러 테이블을 JOIN
use classicmodels;

select P.productcode, productname,quantityordered,priceeach,orderdate,O.status
from orderdetails OD
inner join products P
on P.productcode=OD.productcode
inner join orders O
on O.ordernumber=OD.ordernumber;

-- RIGHT OUTER JOIN
select P.productcode, productname,quantityordered,priceeach,orderdate,O.status
from orderdetails OD
right outer join products P
on P.productcode = OD.productcode
right outer join orders O
on O.ordernumber = OD.ordernumber;