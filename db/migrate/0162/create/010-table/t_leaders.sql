create table T_LEADERS (
    ID int not null auto_increment,
    ATTEND_UPDATE boolean default '0',
    MEMBER_UPDATE boolean default '0',
    SECRET_INFORMATION boolean default '0',
    MEMBER_ID int,
    OFFICER_KIND int,
    constraint T_LEADERS_PK primary key(ID)
);
