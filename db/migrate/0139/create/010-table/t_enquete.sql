create table T_ENQUETE (
    ID int not null auto_increment,
    MEMBER_ID int not null,
    CREATE_ID int not null,
    MEMO varchar(255) not null,
    TITLE mediumtext,
    constraint T_ENQUETE_PK primary key(ID)
);
