alter table T_PARTY_SEND_MAIL add constraint T_PARTY_SEND_MAIL_FK1 foreign key (PARTY_ID) references T_PARTY (ID);
