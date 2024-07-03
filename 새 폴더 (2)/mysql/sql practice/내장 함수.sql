-- ----------------------------
-- 내장 함수
-- ----------------------------

-- Concat(), Concat_ws()
select concat('hello','-','world',5,6);
select concat_ws("-",'hello','world',5,6);

-- SubString() Index는 1번부터 시작
select "HELLO WORLD";
select substring("HELLO WORLD", 1); -- 6 Index 부터 마지막 문자까지 
select substring("HELLO WORLD",1,6); -- 1 Index 부터 6개 문자까지
select substring_index("HELLO MY NAME IS JUNG",' ',3); -- 문자열안에서 ' '공백이 3번째 나온 이후부터는 버리겠다.
select * from usertbl;
select userid,substring_index(mdate,'-',2) as '가입연월' from usertbl;

-- length() 문자열의 길이를 반환
select length("HELLO WORLD");
select * from buytbl;
select prodname,length(price) from buytbl;
select length(lastname) from classicmodels.employees;

-- lower() upper() 문자열을 모두 소문자와 대문자로 반환

-- trim() 문자열의 앞/뒤/양쪽에서 지정된 문자열을 제거
select trim("    HELLO WORLD    ");

-- replace(str, old_str, new_str) 문자열에서 old_str을 new_str로 대체

-- instr(str,substr) 문자열에서 substr이 처음 나타나는 위치 반환

-- lpad, rpad(str,len,pad_str) 문자열을 왼쪽, 오른쪽으로 패딩 
select lpad("aaa", 5, "0");

-- left(str,len), right(str,len) 문자열에서 왼쪽 또는 오린쪽에서 길이만큼 반환
select left("abcde",3);
select right("abcde",3);

-- mid(str, pos, len) 문자열에서 특정 시작점에서의 길이만큼 추출
select mid("abcdefghi", 5,2);

-- bin, oct, hex 각각 2진수, 8진수, 16진수 값을 반환합니다.
select bin(31);
select oct(31);
select hex(31);

-- reverse(str) 주어진 문자열을 거꾸로 반환
select reverse("123456789");

-- space(len) 길이만큼의 공백 반환
select concat("HELLO", space(10), "WORLD");

-- repeat(str, len) 문자열을 주어진 횟수만큼 반복
select repeat("abc",3);

-- locate(substr,str,[pos]) 첫번째로 발견한 문자열의 위치 반환

-- format(x, d) 세자리 수 마다 콤마를 넣고 주어진 길이만틈 소수점 반환

-- 날짜별 시간 관련 함수
-- curdate(), curtime(), now(), sysdate() 현재 날짜 또는 시간 반환

-- year(), month(), dayofmonth(), hour(), minute(), second()
select mdate from usertbl;
select year(mdate) from usertbl;
select month(mdate) from usertbl;
select day(mdate) from usertbl;

select now();
select date(now());
select curdate();
select time(now());
select curtime();

-- 현재 날짜시간에서 연,월,일,시,분,초를 각각 추출해내고
select year(now());
select month(now());
select day(now());
select hour(now());
select minute(now());
select second(now());
-- 다음과 같은 포맷팅으로 출력하세요
-- YYYY#MM#DD-hh|mm|ss
select concat(year(now()),'#',month(now()),'#',day(now()),'-',hour(now()),':',minute(now()),':',second(now()));
select replace(curdate(),'-','#');
select replace(curtime(),':','|');
select concat(replace(curdate(),'-','#'),' ',replace(curtime(),':','|'));
