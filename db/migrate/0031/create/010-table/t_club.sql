create table T_CLUB (
    ID int not null auto_increment,
    CLUB_NAME varchar(255),
    OFFICER_ID int,
    CLUB_MEMO varchar(255) not null,
    constraint T_CLUB_PK primary key(ID)
);
