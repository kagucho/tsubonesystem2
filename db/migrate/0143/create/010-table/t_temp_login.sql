create table T_TEMP_LOGIN (
    ID int not null auto_increment,
    DELETE_FLAG boolean default '0',
    PASSWORD varchar(255) not null,
    USER_NAME varchar(255) not null,
    constraint T_TEMP_LOGIN_PK primary key(ID)
);
