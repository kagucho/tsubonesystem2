alter table T_SUBMIT add constraint T_SUBMIT_FK1 foreign key (TOP_ANNOUNCE_ID) references T_TOP_ANNOUNCE (ID);
alter table T_SUBMIT add constraint T_SUBMIT_FK2 foreign key (SUBMIT_TAG_KIND_ID) references T_SUBMIT_TAG_KIND (ID);
alter table T_SUBMIT add constraint T_SUBMIT_FK3 foreign key (REGIST_ID) references T_MEMBER (ID);
alter table T_SUBMIT add constraint T_SUBMIT_FK4 foreign key (SUBMIT_CAPTION_IMAGE_ID) references T_IMAGE_UPLOAD (ID);
