create table book
(
    no int(11) not null AUTO_INCREMENT,
    name varchar(50) not null,
    primary key(no)
) engine=innodb auto_increment=1 default charset=utf8;


create table guestbook
(
    no int(11) not null AUTO_INCREMENT,
    name varchar(50) not null,
    password varchar(45) not null,
    message text not null,
    reg_date datetime not null,
    primary key(no)
) engine=innodb auto_increment=1 default charset=utf8;