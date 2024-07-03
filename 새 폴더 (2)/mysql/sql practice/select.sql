-- 01 변수
-- 변하는 수
-- 수(Data, 자료)는 기본 선저장 - 후처리를 원칙으로 한다.
-- 저장된 수가 특정상황에 있어 바뀔 가능성이 있는 경우 이 수를 변수라고 한다.

use shopdb;
set @var1 = 5;
set @var2 = 4.56;
set @var3 = "가수이름=>";

select @var1;
select @var2;
select @var3;
select @var1 + @var2; -- 정수 + 실수같이 자료형이 다를 경우 강제 형변환.

select @var3 as 'Title' ,name, addr from usertbl;  -- mysql 변수 @@~ -> 시스템 변수(환경 변수)

-- LIMIT 에서 변수 사용
set @rowcnt = 3;
prepare SqlQuery01
from 'select * from usertbl order by height limit ?';
execute sqlQuery01 using @rowcnt;

-- 형변환
-- 연산작업시(ex 대입연산, 비교연산...) 자료형(Data Type)이 불일치시 자료형을 일치시키는 작업
-- 자동형변환(암시적형변환)	: 시스템에 의한 형변환(데이터 손실을 최소화를 지향)
-- 강제형변환(명시적형변환)	: 프로그래머에 의한 형변환(프로그램 제작 목적에 따른 -> 데이터 손실 우려가 비교적 큼) ex) cast(), convert()
select mdate from usertbl;
select cast('2024$01$01' as date);
select cast('2024!01!01' as date);

select num,
concat(cast(price as char(10)),'X', cast(amount as char(10))) as '가격X수량' ,
price*amount as '결과값'
from buytbl;

select 100+200;
select '100'+200;
select '100' + '200';
select 'a100' + '200' + '300'; -- 문자열 a100을 숫자형으로 해석하려하지만 문자가 포함되어 있기때문에 x
							   -- 연산처리를 하는 과정에서 형변환이 일어난다.
-- 숫자 비교연산의 결과(참 : 1, 거짓 : 0)
select 1>2;
select 2>'1asd';
select 0 = 'mega';

-- 문제
-- 1 userTbl의 평균키를 구하라(CAST를 이용, 평균키 정수형으로 형변환할것)
select * from usertbl;
select avg(height) from usertbl; 
select cast(avg(height) as signed integer) from usertbl;
-- 2 '2020년 5월 7일'문자열을 DATE 자료형으로 바꿔서 출력하세요(예:2020-05-07)
select cast('2020년 5월 7일' as date);
select cast('2020+05+07' as date);