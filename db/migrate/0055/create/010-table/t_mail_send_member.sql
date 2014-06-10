create table T_MAIL_SEND_MEMBER (
    ID int not null auto_increment,
    MAIL_ID varchar(255) not null,
    MEMBER_ID varchar(255) not null,
    constraint T_MAIL_SEND_MEMBER_PK primary key(ID)
);
