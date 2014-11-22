create table T_SUBMIT (
    ID int not null auto_increment,
    DELETE_FLAG boolean default '0',
    SUBMIT_PRODUCT_FILE_TYPE varchar(255),
    SUBMIT_PRODUCT_FILE_PATH varchar(255),
    SUBMIT_PRODUCT_FILE_NAME varchar(255),
    T_IMAGE_UPLOAD_ID varchar(255),
    SUBMIT_DETAIL varchar(255),
    SUBMIT_NAME varchar(255),
    constraint T_SUBMIT_PK primary key(ID)
);
