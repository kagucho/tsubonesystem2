create table T_MEMBER (
    ID int not null auto_increment,
    NAME varchar(255),
    HNAME varchar(255),
    SEX varchar(255) not null,
    MAIL varchar(255),
    CURRICULUM varchar(255) not null,
    ENTRANCE int not null,
    TEL1 varchar(255) not null,
    TEL2 varchar(255) not null,
    TEL3 varchar(255) not null,
    USER_NAME varchar(255),
    OB_FLAG boolean not null,
    PASSWORD varchar(255),
    constraint T_MEMBER_PK primary key(ID)
);
