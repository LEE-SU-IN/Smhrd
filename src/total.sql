create table product(
model varchar2(50),
name varchar2(50),
category varchar2(50),
pClass number(20),
maxEv number(20),
eCost number(20),
price number(20),
img varchar2(50),
refund number(20)
);

insert into product values('�𵨸�1','������̸�1','�����',1,500,100000,5000000,'�̹������1',1);
insert into product values('�𵨸�2','������̸�2','�����',2,400,200000,4000000,'�̹������2',0);
insert into product values('�𵨸�3','������̸�3','�����',3,300,300000,3000000,'�̹������3',0);
insert into product values('�𵨸�4','������̸�4','�����',4,200,400000,2000000,'�̹������4',0);
insert into product values('�𵨸�5','������̸�5','�����',5,100,500000,1000000,'�̹������5',0);



create table favorites(
id varchar2(50),
model varchar2(50),
name varchar2(50),
category varchar2(50)
);

insert into favorites values('userId1','�𵨸�1','������̸�1','�����');
insert into favorites values('userId1','�𵨸�2','������̸�2','�����');