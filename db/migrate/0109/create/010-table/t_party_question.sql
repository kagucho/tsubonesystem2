create table T_PARTY_QUESTION (
    ID int not null auto_increment,
    QUESTION mediumtext,
    PARTY_ID int,
    constraint T_PARTY_QUESTION_PK primary key(ID)
);
