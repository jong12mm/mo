use shopdb;
select * from buytbl;
select userid,
sum(if(prodname='모니터',amount,0)) as '모니터',
sum(if(prodname='운동화',amount,0)) as '운동화',
sum(if(prodname='메모리',amount,0)) as '메모리',
sum(if(prodname='청바지',amount,0)) as '청바지',
sum(if(prodname='책',amount,0)) as '책'
from buytbl
group by userid with rollup;

select * from buytbl;
select groupname,
sum(if(prodname='모니터' or prodname = '메모리' or prodname='노트북',amount,0)) as '판매량',
sum(if(prodname='운동화',amount,0)) as '운동화판매량',
sum(if(prodname='청바지',amount,0)) as '의류판매량',
sum(if(prodname='책',amount,0)) as '서적 판매량'
from buytbl group by groupname; 

create or replace view view_pivot_buytbl
as
select userid,
sum(if(groupname='전자',amount,0)) as '전자판매량',
sum(if(groupname='의류',amount,0)) as '의류판매량',
sum(if(groupname=null,amount,0)) as '운동화판매량',
sum(if(groupname='서적',amount,0)) as '서적판매량',
sum(amount) as '유저별구매량'
from buytbl group by userid with rollup;
-- ??? 왜 운동화 안찍히노

select * from view_pivot_buytbl;

select  -- select 이후에 나오는 표기할 목차는 sum으로 이루어진 것이므로 중복X -> 그룹핑 할 필요 X
sum(if(addr='서울',1,0)) as '서울',
sum(if(addr='경기',1,0)) as '경기',
sum(if(addr='경남',1,0)) as '경남',
sum(if(addr='경북',1,0)) as '경북'
from usertbl;

select
count(case when addr='서울' then 1 end) as '서울',
count(case when addr='경기' then 1 end) as '경기',
count(case when addr='경남' then 1 end) as '경남',
count(case when addr='경북' then 1 end) as '경북'
from usertbl;

