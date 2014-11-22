alter table T_PARTY add constraint T_PARTY_FK1 foreign key (CREATOR_ID) references T_MEMBER (ID);
alter table T_PARTY add constraint T_PARTY_FK2 foreign key (RESULT_EDIT_MEMBER_ID) references T_MEMBER (ID);
