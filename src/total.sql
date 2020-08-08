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

insert into product values('모델명1','냉장고이름1','냉장고',1,500,100000,5000000,'이미지경로1',1);
insert into product values('모델명2','냉장고이름2','냉장고',2,400,200000,4000000,'이미지경로2',0);
insert into product values('모델명3','냉장고이름3','냉장고',3,300,300000,3000000,'이미지경로3',0);
insert into product values('모델명4','냉장고이름4','냉장고',4,200,400000,2000000,'이미지경로4',0);
insert into product values('모델명5','냉장고이름5','냉장고',5,100,500000,1000000,'이미지경로5',0);



create table favorites(
id varchar2(50),
model varchar2(50),
name varchar2(50),
category varchar2(50)
);

insert into favorites values('userId1','모델명1','냉장고이름1','냉장고');
insert into favorites values('userId1','모델명2','냉장고이름2','냉장고');