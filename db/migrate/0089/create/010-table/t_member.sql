create table T_MEMBER (
    ID int not null auto_increment,
    NAME varchar(255),
    HNAME varchar(255) not null,
    SEX varchar(255),
    MAIL varchar(255) not null,
    CURRICULUM varchar(255),
    ENTRANCE int,
    TEL1 varchar(255),
    TEL2 varchar(255),
    TEL3 varchar(255),
    USER_NAME varchar(255) not null,
    PASSWORD varchar(255) not null,
    OB_FLAG boolean not null,
    DELETE_FLAG boolean default '0',
    constraint T_MEMBER_PK primary key(ID)
);
