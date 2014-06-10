create table T_PARTY_CLUB (
    ID int not null auto_increment,
    PARTY_ID int,
    MEMBER_ID int,
    constraint T_PARTY_CLUB_PK primary key(ID)
);
