-- ------------------------------
-- INDEX
-- ------------------------------
-- 데이터 베이스 테이블의 검색 성늘을  향상시키기 위해 사용되는 데이터 구조
-- where 이하 조건절열에 index로 지정된 열을 사용한다.
-- index로 지정된 열은 기본적으로 정렬 처리가 된다(모든 DBMS는 아님)

-- ------------------------------
-- MYSQL INDEX 종류
-- ------------------------------
-- B-Tree : 기본값, 대부분의 데이터 index에 잘 적용되어 사용 (Binary-tree) 정렬을 원칙으로 함
-- Hash : 해시 함수를 이용한 index, 정확한 일치 검색에 사용(포함검색에는 다소 성능이 저하될 수 있다.)
-- Full-text : 전체 텍스트 검색에 사용되는 index, 텍스트 검색기능 향상시 유리
-- Spatial : 공간데이터(위도/경도등을 담는 지도데이터)를 처리하기 위한 Index, 지리 정보 검색에 유리

-- ------------------------------
-- MYSQL INDEX TYPE
-- ------------------------------
-- 클러스터형 인덱스			: PK열에 기본적으로 적용되는 index, 사전편찬 순서에 맞게 정렬이 된다. [순차탐색방법을 사용] [기본 : B-Tree]
				     -- : 한 테이블에 한개만 생성
                     -- : 실제 데이터의 정렬이 인덱스의 순서로 정렬
                     -- : 보조인덱스보다 빠른 속도
                     
-- 보조(Secondary) 인덱스	: PK이외 다른 제약조건이나 수동으로 설정시 적용되는 index 검색알고리즘에 맞게 설정된다.
					 -- : 한 테이블에 여러 Index를 생성











-- 01 제약조건 PK 설정시 unique index 확인
use testdb;
create table tbl_a(
	col1 int primary key,
    col2 int
);
desc tbl_a;
show index from tbl_a;
select * from tbl_a;

-- 02 제약조건 unique 설정시 unique index 확인
create table tbl_b(
	col1 int primary key,
    col2 int unique,
    col3 int
);
desc tbl_b;
show index from tbl_b;

-- 03 index 삭제
alter table tbl_b drop primary key;
show index from tbl_b;
desc tbl_b;

alter table tbl_b drop constraint col2;
-- alter table tbl_b drop index [인덱스명];
show index from tbl_b;
desc tbl_b;

-- 보조 인덱스 추가
create table tbl_c(
	col1 int primary key,
    col2 int,
    col3 int,
    unique index col2_index(col2)
);
desc tbl_c;
show index from tbl_c;
create table tbl_d(
	col1 int primary key,
    col2 int,
    col3 int,
    unique index col2_3_index(col2,col3)
);
show index from tbl_d;

create table tbl_e(
	col1 int primary key,
    col2 int,
    col3 int
);
show index from tbl_e;
create index col2_index on tbl_e(col2);
show index from tbl_e;
create index col3_index on tbl_e(col3);
show index from tbl_e;

create table tbl_f(
	col1 int primary key,
    tbl_e_col1 int,
    col3 int,
    constraint fk_tbl_f_tbl_e foreign key(tbl_e_col1) references tbl_e(col1)
    on update cascade
    on delete cascade
);
show index from tbl_f;


