select user(), database();

use mybatis_study;

select * from addresses;
select * from students;
select * from tutors;
select * from courses;
select * from course_enrollment;

select stud_id, name, email, dob, phone,
			substring(phone, 1, 3) as f,
			substring(phone, 5, 3) as m,
			substring(phone, 9, 4) as l 
		from students where stud_id = 1;

select stud_id as studId, name, email, phone, dob from students;

select stud_id, name, email, phone, dob, a.addr_id, street, city, state, zip, country
	from students s join addresses a on s.addr_id = a.addr_id 
	where stud_id = 1;

select t.tutor_id , t.name as tutor_name, email , c.course_id , c.name , description, start_date, end_date
	from tutors t left outer join courses c on t.tutor_id = c.tutor_id 
	where t.tutor_id = 1;

desc students;

insert into students (stud_id , name , email , phone , dob ) 
values(3, '홍길동', 'test@test.co.kr', '010-1234-1234', '1990-03-28');

delete from students where stud_id = 3;

update students set name = 'Timothy', email = 'test@test.co.kr', phone = '010-1234-1234', dob = '2000-08-01' where stud_id = 1;

