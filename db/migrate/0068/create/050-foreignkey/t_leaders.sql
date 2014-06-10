alter table T_LEADERS add constraint T_LEADERS_FK1 foreign key (OFFICER_KIND) references T_LEADERS_KIND (ID);
