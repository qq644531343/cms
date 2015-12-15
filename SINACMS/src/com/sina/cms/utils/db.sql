create database cms default charset utf8 COLLATE utf8_general_ci;

create table user (
 username varchar(24) primary key,
 password varchar(24),
 birthday varchar(36) default "",
 privilege	 varchar(24) default "normal",
 status   int  default   1,
 sex 	  varchar(2) default "男",
 lastLoginTime	 timestamp,
 lastLoginIp	 timestamp,		
 loginTimes	  int default 0
);

insert into user 
(username, password, birthday, privilege ,status, sex,loginTimes)
values 
('admin', 'admin', '2015-12-12 24:00', 'admin', 1, '男', 1);

create table article (
	tid BIGINT   primary   key   auto_increment,
	title   varchar(128),
	originUrl   varchar(512),
	clickNums   bigint,
	replyNums   int,
	createDate   timestamp,
	updateDate   timestamp,
	publishDate   timestamp null,
	status   int,
	ownerUsername   varchar(24),
	content   longtext
);