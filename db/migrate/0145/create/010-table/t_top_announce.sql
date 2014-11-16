create table T_TOP_ANNOUNCE (
    ID int not null auto_increment,
    DELETE_FLAG boolean default '0',
    ANNOUNCE_TO_DAY date,
    ANNOUNCE_FROM_DAY date,
    ANNOUNCE_CONTENT mediumtext,
    ANNOUNCE_TITLE varchar(255),
    REGIST_MEMBER_ID int not null,
    constraint T_TOP_ANNOUNCE_PK primary key(ID)
);
