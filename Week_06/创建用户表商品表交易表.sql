create table user(
id bigint unsigned auto_increment,
username varchar(255),
create_time bigint not null,
update_time bigint not null,
PRIMARY KEY(id)
)ENGINE = InnoDB DEFAULT CHARSET=utf8;

create table product(
id bigint unsigned auto_increment,
name varchar(255),
price int(20),
create_time bigint not null,
update_time bigint not null,
PRIMARY KEY(id)
)ENGINE = InnoDB DEFAULT CHARSET=utf8;

create table trade(
id bigint unsigned auto_increment,
user_id bigint not null,
product_id bigint not null,
extra varchar(255),
create_time bigint not null,
update_time bigint not null,
PRIMARY KEY(id)
)ENGINE = InnoDB DEFAULT CHARSET=utf8;
