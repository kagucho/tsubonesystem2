alter table T_BBS_SUBJECT add constraint T_BBS_SUBJECT_FK1 foreign key (MEMBER_ID) references T_MEMBER (ID);
