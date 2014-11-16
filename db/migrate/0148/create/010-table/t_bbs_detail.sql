create table T_BBS_DETAIL (
    ID int not null auto_increment,
    DELETE_FLAG boolean default '0',
    UPDATE_TIME timestamp,
    MAIL_ID int,
    MEMBER_ID int,
    DETAIL mediumtext,
    SUBJECT_ID int,
    constraint T_BBS_DETAIL_PK primary key(ID)
);
