create table T_MEMBER_CLUB (
    ID int not null auto_increment,
    CLUB_ID int,
    MEMBER_ID int,
    constraint T_MEMBER_CLUB_PK primary key(ID)
);
