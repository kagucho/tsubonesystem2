create table T_PARTY (
    ID int not null auto_increment,
    CREATOR_ID int not null,
    MEETING_NAME varchar(255) not null,
    MEETING_NECESSARY_FLAG boolean not null,
    MEETING_DAY TIMESTAMP NULL DEFAULT NULL not null,
    MEETING_ROOM varchar(255) not null,
    MEETING_MEMO mediumtext not null,
    MEETING_DEADLINE_DAY timestamp,
    OB_ATTEND_FLAG boolean default '0',
    DELETE_FLAG boolean default '0',
    constraint T_PARTY_PK primary key(ID)
);
