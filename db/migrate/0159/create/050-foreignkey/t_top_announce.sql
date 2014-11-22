alter table T_TOP_ANNOUNCE add constraint T_TOP_ANNOUNCE_FK1 foreign key (REGIST_MEMBER_ID) references T_MEMBER (ID);
alter table T_TOP_ANNOUNCE add constraint T_TOP_ANNOUNCE_FK2 foreign key (IMAGE_ID) references T_IMAGE_UPLOAD (ID);
