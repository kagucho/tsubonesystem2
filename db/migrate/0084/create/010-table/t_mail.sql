create table T_MAIL (
    ID int not null auto_increment,
    REGIST_MEMBER_ID int,
    TITLE varchar(255),
    CONTENT mediumtext,
    constraint T_MAIL_PK primary key(ID)
);
