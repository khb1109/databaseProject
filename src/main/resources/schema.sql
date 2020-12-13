create table employee
(
    ssn        int(10) unsigned not null auto_increment,
    name       varchar(10),
    adress     varchar(20),
    throughput int(10) default 0,
    primary key (ssn)
);
create table customer
(
    ssn    int(10) unsigned not null auto_increment,
    name   varchar(10),
    adress varchar(10),
    primary key (ssn)
);
create table item
(
    seriel int(20) unsigned not null auto_increment,
    stock  int(10),
    name   varchar(10),
    price  int(20),
    primary key (seriel)
);
create table infoOrder
(
    infoNumber     int(20) unsigned not null auto_increment,
    e_Name         varchar(10),
    c_Name         varchar(10),
    i_name         varchar(20),
    cnt            int(10),
    price          int(20),
    paymentDay     varchar(15),
    expectationDay varchar(15),
    delivery_date  varchar(15),
    primary key (infoNumber)
);



create table item
(
    no   int primary key,
    name varchar(10)
);


alter table item
    add price int(20) after name default '0';
