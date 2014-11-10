create table T_PARTY_SEND_MAIL (
    ID int not null auto_increment,
    MAIL_ID int,
    PARTY_ID int,
    constraint T_PARTY_SEND_MAIL_PK primary key(ID)
);
