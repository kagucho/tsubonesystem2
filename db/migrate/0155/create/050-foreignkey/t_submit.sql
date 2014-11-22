alter table T_SUBMIT add constraint T_SUBMIT_FK1 foreign key (SUBMIT_CAPTION_IMAGE_ID) references T_IMAGE_UPLOAD (ID);
