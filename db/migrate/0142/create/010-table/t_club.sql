create table T_CLUB (
    ID int not null auto_increment,
    DELETE_FLAG boolean default '0',
    CLUB_MEMO varchar(255) not null,
    LEADERS_ID int,
    CLUB_NAME varchar(255),
    constraint T_CLUB_PK primary key(ID)
);
