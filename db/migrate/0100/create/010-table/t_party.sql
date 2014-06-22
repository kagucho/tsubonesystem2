create table T_PARTY (
    ID int not null auto_increment,
    DELETE_FLAG boolean default '0',
    MEETING_RESULT mediumtext,
    OB_ATTEND_FLAG boolean default '0',
    MEETING_DEADLINE_TIME time,
    MEETING_DEADLINE_DAY date,
    MEETING_MEMO mediumtext not null,
    MEETING_ROOM varchar(255) not null,
    MEETING_TIME time,
    MEETING_DAY date,
    MEETING_NECESSARY_FLAG boolean not null,
    MEETING_NAME varchar(255) not null,
    CREATOR_ID int not null,
    constraint T_PARTY_PK primary key(ID)
);
