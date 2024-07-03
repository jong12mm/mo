use lmsdb;
select * from tbl_current_lecture;
select * from tbl_classroom;
select * from tbl_teacher;
select * from tbl_lecture;

select no,lec_duration,lec_time,t_name,lec_name,tbl_classroom.class_no from tbl_current_lecture
inner join tbl_teacher
on tbl_teacher.t_id=tbl_current_lecture.t_id
inner join tbl_classroom
on tbl_classroom.class_no=tbl_current_lecture.class_no
inner join tbl_lecture
on tbl_current_lecture.lec_code=tbl_lecture.lec_code;

create or replace view view_current_lecture
as
select no,lec_duration,lec_time,t_name,lec_name,tbl_classroom.class_no from tbl_current_lecture
inner join tbl_teacher
on tbl_teacher.t_id=tbl_current_lecture.t_id
inner join tbl_classroom
on tbl_classroom.class_no=tbl_current_lecture.class_no
inner join tbl_lecture
on tbl_current_lecture.lec_code=tbl_lecture.lec_code;
select * from view_current_lecture;
select * from tbl_current_lecture;
select * from tbl_lecture;
select lec_name,
sum(if(lec_time='09:00 - 12:00',1,0)) as '09:00 - 12:00',
sum(if(lec_time='13:00 - 15:00',1,0)) as '13:00 - 15:00',
sum(if(lec_time='15:00 - 17:00',1,0)) as '15:00 - 17:00'
from view_current_lecture group by lec_name with rollup; 

drop procedure proc_insert_tbl_registration;
delimiter $$
create procedure proc_insert_tbl_registration(in sid varchar(45), in lcode int, lduration varchar(100))
begin 
	DECLARE error_code VARCHAR(5);
    DECLARE error_message VARCHAR(255);
    declare continue handler for SQLEXCEPTION 
    begin
		show errors;
		get DIAGNOSTICS CONDITION 1
			error_code = MYSQL_ERRNO,
            error_message = MESSAGE_TEXT;
        insert into tbl_errlog values(no,error_code,now(),error_message);
    end;
    insert into tbl_registration values (sid,lcode,lduration);
end $$
delimiter ;
select * from tbl_registration;
call proc_insert_tbl_registration('20190001',1001,'2023-05-22 - 2023-06-21');
call proc_insert_tbl_registration('20190001',1001,'2023-05-22 - 2023-06-21');
call proc_insert_tbl_registration('20190001',7001,'2023-05-22 - 2023-06-21');
call proc_insert_tbl_registration('70190001',1001,'2023-05-22 - 2023-06-21');
select * from tbl_errlog;

desc tbl_student;
desc tbl_student_bak;
delimiter $$
create trigger tbl_student_update_trg
after update
on tbl_student
for each row
begin
	insert into tbl_student_bak values (old.s_id,old.s_name,old.s_phone,now(),null);
end $$
delimiter ;
show triggers;
show create trigger tbl_student_update_trg;
insert into tbl_student values('20191234','나나나','010-1234-1234');
update tbl_student set s_name = '우우우' where s_id='20191234';
select * from tbl_student;
select * from tbl_student_bak;

desc tbl_teacher;
desc tbl_teacher_bak;
delimiter $$
create trigger tbl_teacher_update_trg
after update
on tbl_teacher
for each row
begin
	insert into tbl_teacher_bak values (old.t_id,old.t_name,old.t_phone,old.t_addr,now(),null);
end $$
delimiter ;
insert into tbl_teacher values(7,'아무개','010-222-333','대구광역시 달서구');
update tbl_teacher set t_name = '아무무' where t_id = 7;
update tbl_teacher set t_phone = '010-777-7777' where t_id = 7;
select * from tbl_teacher;
select * from tbl_teacher_bak;

delimiter $$
create trigger tbl_student_delete_trg
after delete
on tbl_student
for each row
begin
	insert into tbl_student_bak values (old.s_id,old.s_name,old.s_phone,null,now());
end $$
delimiter ;
delete from tbl_student where s_id = '20191234';
select * from tbl_student_bak;

delimiter $$
create trigger tbl_teacher_delete_trg
after delete
on tbl_teacher
for each row
begin
	insert into tbl_teacher_bak values (old.t_id,old.t_name,old.t_phone,old.t_addr,null,now());
end $$
delimiter ;
delete from tbl_teacher where t_id = 7;
select * from tbl_teacher;
select * from tbl_teacher_bak;


