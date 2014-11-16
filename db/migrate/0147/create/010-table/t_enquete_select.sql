create table T_ENQUETE_SELECT (
    ID int not null auto_increment,
    SELECTED_CONTENTS varchar(255) not null,
    ENQUETE_ID int,
    constraint T_ENQUETE_SELECT_PK primary key(ID)
);
