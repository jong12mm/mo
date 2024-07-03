-- ----------------------------------------
-- VIEW (https://coding-factory.tistory.com/224)
-- ----------------------------------------
-- 사용자에게 접근이 허용된 자료만을 제한적으로 보여주기 위한
-- 가상 테이블

use shopdb;
select * from usertbl;
select * from buytbl;
create or replace view view_usertbl
as 
select userid,name,concat(mobile1,'-',mobile2) as phone
from usertbl;

select * from view_usertbl;

create or replace view view_usertbl_2
as 
select userid,name,concat(mobile1,'-',mobile2) as phone
from usertbl
where addr in ('서울','경기','경남');
select * from view_usertbl_2;
show tables;
select * from information_schema.views where table_schema='shopdb';
create or replace view view_user_buy
as
select U.userid,addr,concat(mobile1,'-',mobile2) as phone,prodname,amount
from usertbl U
inner join buytbl B
on U.userid = B.userid;

select * from view_user_buy;
select * from view_user_buy where amount>=5;
desc usertbl;

create table tbl_a(
	col1 int primary key,
    col2 int
);
create table tbl_b(
	col3 int primary key,
    col4 int
);
create or replace view view_a_b
as
select * from tbl_a
inner join tbl_b;
select * from view_a_b;
insert into view_a_b(col1,col2,col3,col4) values (1,1,1,1);

create view view_test
as
select userid,name,birthyear,addr,height
from usertbl;
select * from view_test;

insert into view_test values('aaa','에이',1999,'경기',188);
select * from view_test;

-- classicmodels에서 products,orderdetails,orders 에 대한 관계도를 확인해서
-- 열항목이 productname,productvendor,quantityordered,priceeach,orderdate가 포함된
-- view_product_order 만들어보세요
-- quantityOrdered가 5이상인 값만 포함되어야 한다.

use classicmodels;
select * from products;
select * from orders;
select * from orderdetails;

create or replace view view_order_orderdetails
as
select orders.ordernumber,productcode,quantityordered,priceeach,orderdate
from orders
inner join orderdetails
on orders.ordernumber=orderdetails.ordernumber;
select * from view_order_orderdetails;

create or replace view view_product_order
as
select productname,productvendor,quantityordered,priceeach,orderdate
from products
inner join view_order_orderdetails
on products.productcode=view_order_orderdetails.productcode
where quantityOrdered>=25;
select * from view_product_order;


create or replace view view_product_order_2
as
select productname,productvendor,quantityordered,priceeach,orderdate
from orderdetails OD
inner join products P
on OD.productcode=P.productcode
inner join orders O
on OD.ordernumber=O.ordernumber
where quantityordered>=25;
select * from view_product_order_2;