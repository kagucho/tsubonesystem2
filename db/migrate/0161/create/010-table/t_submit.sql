create table T_SUBMIT (
    ID int not null auto_increment,
    DELETE_FLAG boolean default '0',
    SUBMIT_PRODUCT_FILE_TYPE int,
    SOUND_CLOUD_URL varchar(255),
    SUBMIT_PRODUCT_FILE_PATH varchar(255),
    SUBMIT_PRODUCT_FILE_NAME varchar(255),
    SUBMIT_CAPTION_IMAGE_ID int,
    SUBMIT_DETAIL varchar(255),
    SUBMIT_NAME varchar(255),
    SUBMIT_TAG_KIND_ID int,
    TOP_ANNOUNCE_ID int,
    REGIST_ID int,
    constraint T_SUBMIT_PK primary key(ID)
);
