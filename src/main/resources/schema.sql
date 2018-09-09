create table Login
(
	loginid integer not null auto_increment,
	username varchar(100) not null,
	password varchar(100) not null,
	primary key(loginid)
);
create table User
(
   userid integer not null auto_increment,
   name varchar(255) not null,
   address varchar(255) not null,
   phone varchar(10) not null,
   reference varchar(12) not null,
   salary integer not null,
   loginid integer not null,
   primary key(userid),
   foreign key(loginid) references Login(loginid)
);
