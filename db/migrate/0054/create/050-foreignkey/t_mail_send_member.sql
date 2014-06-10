alter table T_MAIL_SEND_MEMBER add constraint T_MAIL_SEND_MEMBER_FK1 foreign key (MEMBER_ID) references T_MEMBER (ID);
