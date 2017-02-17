create database cache;

use cache;

create table urlscache(url varchar(50) not null primary key,urltype varchar(50),retailerName varchar(50),visited boolean);