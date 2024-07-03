use testdb;
show variables like 'max_allowed_packet';
show variables like 'secure_file_priv';

create database filedb;
create table tbl_file(
title varchar(30),
save LONGBLOB)default character set = utf8mb4;
use filedb;
insert into tbl_file values('practice',load_file('C:\\SQL\\prac.zip'));
insert into tbl_file values('practice2',load_file('c:\\sql\\mola.pptx'));
select * from tbl_file;
select save from tbl_file where title = 'practice';
select save from tbl_file where title='practice'
INTO DUMPFILE 'c:/SQL/prac.zip';

delete from tbl_file;
select * from tbl_file;
insert into tbl_file values('practice',load_file('c:\\sql\\prac.zip'));
select * from tbl_file;
select save from tbl_file where title = 'practice'
into dumpfile 'c:/sql/prac.zip';
