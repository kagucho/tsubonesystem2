alter table T_MEMBER_CLUB add constraint T_MEMBER_CLUB_FK1 foreign key (CLUB_ID) references T_CLUB (ID);
alter table T_MEMBER_CLUB add constraint T_MEMBER_CLUB_FK2 foreign key (MEMBER_ID) references T_MEMBER (ID);
