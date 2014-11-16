create table T_TOP_ANNOUNCE (
    ID int not null auto_increment,
    ANNOUNCE_TO_DAY date,
    ANNOUNCE_FROM_DAY date,
    ANNOUNCE_CONTENT mediumtext,
    ANNOUNCE_TITLE varchar(255),
    REGIST_MEMBER_ID int,
    constraint T_TOP_ANNOUNCE_PK primary key(ID)
);
