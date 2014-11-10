alter table T_BBS_DETAIL add constraint T_BBS_DETAIL_FK1 foreign key (MEMBER_ID) references T_MEMBER (ID);
alter table T_BBS_DETAIL add constraint T_BBS_DETAIL_FK2 foreign key (SUBJECT_ID) references T_BBS_SUBJECT (ID);
