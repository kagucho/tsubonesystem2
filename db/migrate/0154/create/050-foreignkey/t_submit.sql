alter table T_SUBMIT add constraint T_SUBMIT_FK1 foreign key (T_IMAGE_UPLOAD_ID) references T_IMAGE_UPLOAD (ID);
