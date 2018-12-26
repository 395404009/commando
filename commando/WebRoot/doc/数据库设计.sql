--提问系统
--表的设计

--用户表
create table users(
   userid varchar2(20)  primary key,
   username varchar2(20),
   pwd varchar2(30),
   usex varchar2(10),
   photofile varchar2(50)
);

--问题表
create table questions(
   qid varchar2(20) primary key,
   question varchar2(200),
   answer varchar2(600)
);

--用户与问题的关联表(userques)
create table userques(
    userid varchar2(20),
    qid varchar2(20),
    primary key(userid,qid)
);

--评价表(evaluate)
create table evaluates(
   eid varchar2(20) primary key,
   context varchar2(200)
);

--问题与评价的关联表(queseval)
create table queseval(
  qid varchar2(20),
  eid varchar2(20),
  primary key(qid,eid)
);

--用户与评价的关联表(usereval)
create table usereval(
   userid varchar2(20),
   eid varchar2(20),
   primary key(userid,eid)
);

--测试数据
insert into users values('1','张三','123','男','a.png');
insert into questions values('1','人这一生为什么要努力？','努力就是一个与周围世界搏斗的过程，有的人最后打赢了，有的人打输了，但他们之所以参与这场搏斗的原因却是相同的：
我不知道这个世界什么时候心情好就只轻轻打我一下，什么时候心情不好就将我往死里打，但我知道我不喜欢这种被按在地上打却毫不反抗，一心装死的怂样。');
insert into userques values('1','1');

insert into evaluates values('1','没有为什么，因为快乐，或者因为想获得快乐，所以努力。');
insert into queseval values('1','1');
insert into usereval values('1','1');

select * from users;
select * from questions;
select * from userques;
select * from evaluates;
select * from queseval;
select * from usereval;

delete from users;
insert into users values('1','张三','123','男','a.png');
commit;
select * from users where username='张三' and pwd='123'