create table T_PARTY_QUESTION (
    ID int not null auto_increment,
    QUESTION mediumtext,
    MEMBER_ID int not null,
    PARTY_ID int not null,
    constraint T_PARTY_QUESTION_PK primary key(ID)
);
