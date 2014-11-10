create table T_IMAGE_UPLOAD (
    ID int not null auto_increment,
    FILE_PATH varchar(255),
    FILE_NAME varchar(255),
    constraint T_IMAGE_UPLOAD_PK primary key(ID)
);
