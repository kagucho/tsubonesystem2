create table T_MAIL_SEND_MEMBER (
    ID int not null auto_increment,
    MAIL_ID int not null,
    MEMBER_ID int not null,
    constraint T_MAIL_SEND_MEMBER_PK primary key(ID)
);
