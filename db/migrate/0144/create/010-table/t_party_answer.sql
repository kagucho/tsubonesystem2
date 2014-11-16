create table T_PARTY_ANSWER (
    ID int not null auto_increment,
    ANSWER_SEND_KIND int,
    ANSWER mediumtext,
    MEMBER_ID int,
    QUESTION_ID int,
    constraint T_PARTY_ANSWER_PK primary key(ID)
);
