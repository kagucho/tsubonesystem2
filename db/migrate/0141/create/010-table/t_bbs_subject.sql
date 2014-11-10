create table T_BBS_SUBJECT (
    ID int not null auto_increment,
    DELETE_FLAG boolean default '0',
    UPDATE_TIME timestamp,
    MAIL_ID int,
    MEMBER_ID int,
    TITLE varchar(255),
    constraint T_BBS_SUBJECT_PK primary key(ID)
);
