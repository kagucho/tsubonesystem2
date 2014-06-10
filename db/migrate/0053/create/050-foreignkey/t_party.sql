alter table T_PARTY add constraint T_PARTY_FK1 foreign key (CREATOR_ID) references T_MEMBER (ID);
