-- ---------------------------
-- Transaction
-- ---------------------------
-- 데이터베이스의 상태를 변환시키는 작업 수행 단위
-- 트랜잭션은 예외처리와 함께 쓰임
-- ---------------------------
-- 트랜잭션 성질 (검색엔진 참조 정리)
-- ---------------------------
-- 원자성
-- 일관성
-- 독립성,격리성
-- 영속성,지속성

-- 
create table tbl_tx(
	no int primary key,
    name varchar(20),
    age int,
    gender char(1)
);
select * from tbl_tx;

insert into tbl_tx values (1,'aa',55,'W'); -- auto commit
insert into tbl_tx values (2,'bb',44,'M'); -- auto commit
select * from tbl_tx;

start transaction;
	insert into tbl_tx values (3,'cc',55,'W');
	insert into tbl_tx values (4,'dd',55,'M');
	insert into tbl_tx values (5,'ee',55,'W');
	rollback; -- 전체 롤백

start transaction;
	savepoint s1;
	insert into tbl_tx values (3,'cc',55,'W');
	insert into tbl_tx values (4,'dd',55,'M');
    savepoint s2;
	insert into tbl_tx values (5,'cc',55,'W');
	insert into tbl_tx values (6,'dd',55,'M');
    savepoint s3;
	insert into tbl_tx values (7,'cc',55,'W');
	insert into tbl_tx values (8,'dd',55,'M');
    rollback to s2; -- savepoint s2로 롤백 -> 3,4만 commit한 상태로 롤백

select * from tbl_tx;
delete from tbl_tx;

drop procedure Tx_test;
delimiter $$
create procedure Tx_test()
begin
	declare continue handler for SQLEXCEPTION
    begin
		show errors;
		rollback;
    end;
    start transaction;
		insert into tbl_tx values(1,'dd',55,'M'); -- SQL
		insert into tbl_tx values(2,'dd',55,'M'); -- SQL
        insert into tbl_tx values(2,'dd',55,'M'); -- SQL
	commit;

end $$
delimiter ;
call Tx_test();
select * from tbl_tx;
