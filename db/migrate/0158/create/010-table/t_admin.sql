create table T_ADMIN (
    ID int not null auto_increment,
    MEMBER_ID int,
    OFFICER_KIND int,
    constraint T_ADMIN_PK primary key(ID)
);
