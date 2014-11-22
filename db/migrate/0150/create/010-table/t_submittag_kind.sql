create table T_SubmitTag_Kind (
    ID int not null auto_increment,
    DELETE_FLAG boolean default '0',
    SUBMIT_TAG_NAME varchar(255),
    constraint T_SubmitTag_Kind_PK primary key(ID)
);
