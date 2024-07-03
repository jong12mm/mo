-- 제어 흐름
set @var1 = 100;
set @var2 = 200;

select if(@var1>@var2,concat(@var1,'이 더 큽니다'),concat(@var2,'이 더 큽니다'));

select * from buytbl;
select *,if(amount>5,'우수고객','일반고객') as '등급' from buytbl;

-- 키가 170이상이면 170이상 170미만면 170미만으로 표시
select * from usertbl;
select name,if(height>=170,'170이상','170미만') from usertbl;

-- case - when - else - end
select case 10
when 1 then '일'
when 5 then '오'
when 10 then '십'
else '모름'
end as 'CASETEST';

select * from buytbl;
select*, case
when amount>=10 then 'VIP'
when amount>=5 then '우수'
else '일반'
end as '고객등급'
from buytbl;
