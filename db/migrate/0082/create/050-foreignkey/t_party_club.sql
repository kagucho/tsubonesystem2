alter table T_PARTY_CLUB add constraint T_PARTY_CLUB_FK1 foreign key (PARTY_ID) references T_PARTY (ID);
alter table T_PARTY_CLUB add constraint T_PARTY_CLUB_FK2 foreign key (MEMBER_ID) references T_MEMBER (ID);
