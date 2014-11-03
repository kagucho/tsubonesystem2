create table T_MAIL (
    ID int not null auto_increment,
    BROWSING_RIGHTS int default '3',
    ERROR_FLAG boolean default '0',
    CONTENT mediumtext,
    TITLE varchar(255),
    REGIST_MEMBER_ID int,
    constraint T_MAIL_PK primary key(ID)
);
