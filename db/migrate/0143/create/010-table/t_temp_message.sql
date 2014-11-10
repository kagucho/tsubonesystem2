create table T_TEMP_MESSAGE (
    ID int not null auto_increment,
    REGIST_TIME timestamp,
    DELETE_FLAG boolean default '0',
    MESSAGE mediumtext,
    TARGET_MEMBER_ID int,
    constraint T_TEMP_MESSAGE_PK primary key(ID)
);
