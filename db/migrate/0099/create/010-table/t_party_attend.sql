create table T_PARTY_ATTEND (
    ID int not null auto_increment,
    ATTEND boolean,
    MEMBER_ID int,
    PARTY_ID int,
    constraint T_PARTY_ATTEND_PK primary key(ID)
);
