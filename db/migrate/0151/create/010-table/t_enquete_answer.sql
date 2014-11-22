create table T_ENQUETE_ANSWER (
    ID int not null auto_increment,
    ENQUETE_SELECTED_ID int,
    MEMBER_ID int,
    constraint T_ENQUETE_ANSWER_PK primary key(ID)
);
