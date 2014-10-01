create table T_LEADERS (
    ID int not null auto_increment,
    CLUB_UPDATE boolean default '0',
    ATTEND_UPDATE boolean default '0',
    MEMBER_UPDATE boolean default '0',
    SECRET_INFORMATION boolean default '0',
    MEMBER_ID int,
    OFFICER_KIND int,
    constraint T_LEADERS_PK primary key(ID)
);
