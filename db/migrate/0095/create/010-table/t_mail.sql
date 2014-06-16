create table T_MAIL (
    ID int not null auto_increment,
    ERROR_FLAG false,
    CONTENT mediumtext,
    TITLE varchar(255),
    REGIST_MEMBER_ID int,
    constraint T_MAIL_PK primary key(ID)
);
