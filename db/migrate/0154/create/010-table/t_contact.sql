create table T_CONTACT (
    ID int not null auto_increment,
    MESSAGE mediumtext,
    SUBJECT varchar(255),
    MAIL varchar(255),
    NAME varchar(255),
    constraint T_CONTACT_PK primary key(ID)
);
