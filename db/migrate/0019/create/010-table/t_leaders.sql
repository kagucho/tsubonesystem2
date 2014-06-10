create table T_LEADERS (
    ID int not null auto_increment,
    OFFICER_KIND int,
    MEMBER_ID int,
    CLUB_ID int,
    constraint T_LEADERS_PK primary key(ID)
);
