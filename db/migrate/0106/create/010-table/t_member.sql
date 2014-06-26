create table T_MEMBER (
    ID int not null auto_increment,
    DELETE_FLAG boolean default '0',
    SEND_ERROR_FLAG boolean default '0',
    OB_FLAG boolean,
    PASSWORD varchar(255) not null,
    USER_NAME varchar(255) not null,
    TEL3 varchar(255),
    TEL2 varchar(255),
    TEL1 varchar(255),
    ENTRANCE int,
    CURRICULUM varchar(255),
    EMAIL_ADDRESS varchar(255) not null,
    SEX varchar(255),
    HNAME varchar(255) not null,
    NAME varchar(255),
    constraint T_MEMBER_PK primary key(ID)
);
