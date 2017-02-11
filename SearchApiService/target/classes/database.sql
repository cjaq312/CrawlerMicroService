create database persist;

use persist;

create table productspersist(id varchar(50) not null primary key,name varchar(50),longdescription varchar(500),smalldescription varchar(500),price float,skuid varchar(50),url varchar(50),color varchar(50),size varchar(50),retailerName varchar(50),retailerId varchar(50),manufacturer varchar(50),primarycategory varchar(50),secondarycategory varchar(50),tertiarycategory varchar(50));