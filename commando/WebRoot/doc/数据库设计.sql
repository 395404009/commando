--����ϵͳ
--������

--�û���
create table users(
   userid varchar2(20)  primary key,
   username varchar2(20),
   pwd varchar2(30),
   usex varchar2(10),
   photofile varchar2(50)
);

--�����
create table questions(
   qid varchar2(20) primary key,
   question varchar2(200),
   answer varchar2(600)
);

--�û�������Ĺ�����(userques)
create table userques(
    userid varchar2(20),
    qid varchar2(20),
    primary key(userid,qid)
);

--���۱�(evaluate)
create table evaluates(
   eid varchar2(20) primary key,
   context varchar2(200)
);

--���������۵Ĺ�����(queseval)
create table queseval(
  qid varchar2(20),
  eid varchar2(20),
  primary key(qid,eid)
);

--�û������۵Ĺ�����(usereval)
create table usereval(
   userid varchar2(20),
   eid varchar2(20),
   primary key(userid,eid)
);

--��������
insert into users values('1','����','123','��','a.png');
insert into questions values('1','����һ��ΪʲôҪŬ����','Ŭ������һ������Χ���粫���Ĺ��̣��е�������Ӯ�ˣ��е��˴����ˣ�������֮���Բ����ⳡ������ԭ��ȴ����ͬ�ģ�
�Ҳ�֪���������ʲôʱ������þ�ֻ�������һ�£�ʲôʱ�����鲻�þͽ���������򣬵���֪���Ҳ�ϲ�����ֱ����ڵ��ϴ�ȴ����������һ��װ����������');
insert into userques values('1','1');

insert into evaluates values('1','û��Ϊʲô����Ϊ���֣�������Ϊ���ÿ��֣�����Ŭ����');
insert into queseval values('1','1');
insert into usereval values('1','1');

select * from users;
select * from questions;
select * from userques;
select * from evaluates;
select * from queseval;
select * from usereval;

delete from users;
insert into users values('1','����','123','��','a.png');
commit;
select * from users where username='����' and pwd='123'