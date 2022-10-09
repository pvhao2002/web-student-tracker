
declare @i int;
select @i = max(id) from student
GO
insert into student(id,first_name, last_name, email) values(@i,'hao','kira','hao@gmail.com');