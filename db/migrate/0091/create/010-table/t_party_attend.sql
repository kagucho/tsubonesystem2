create table T_PARTY_ATTEND (
    ID int not null auto_increment,
    PARTY_ID int,
    MEMBER_ID int,
    ATTEND boolean,
    constraint T_PARTY_ATTEND_PK primary key(ID)
);
