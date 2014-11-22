create table T_Submit (
    ID int not null auto_increment,
    DELETE_FLAG boolean default '0',
    SUBMIT_PRODUCT_FILE_TYPE varchar(255),
    SUBMIT_PRODUCT_FILE_PATH varchar(255),
    SUBMIT_PRODUCT_FILE_NAME varchar(255),
    SUBMIT_CAPTION_IMAGE_ID varchar(255),
    SUBMIT_DETAIL varchar(255),
    SUBMIT_NAME varchar(255),
    constraint T_Submit_PK primary key(ID)
);
