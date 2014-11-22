create table T_SUBMIT_TAG_KIND (
    ID int not null auto_increment,
    DELETE_FLAG boolean default '0',
    SUBMIT_TAG_NAME varchar(255),
    constraint T_SUBMIT_TAG_KIND_PK primary key(ID)
);
