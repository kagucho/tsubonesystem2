create table T_PARTY_SETTINGS (
    ID int not null auto_increment,
    DELETE_FLAG boolean default '0',
    NOTICE_MEMBER_NUM int,
    NO_PUBLIC_FLAG boolean default '0',
    RESULT_EDIT_END_FLAG boolean default '0',
    OB_ATTEND_FLAG boolean default '0',
    MEETING_NECESSARY_FLAG boolean not null,
    PARTY_ID int,
    constraint T_PARTY_SETTINGS_PK primary key(ID)
);
