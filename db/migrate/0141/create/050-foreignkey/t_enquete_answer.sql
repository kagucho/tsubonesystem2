alter table T_ENQUETE_ANSWER add constraint T_ENQUETE_ANSWER_FK1 foreign key (ENQUETE_SELECTED_ID) references T_ENQUETE_SELECT (ID);
alter table T_ENQUETE_ANSWER add constraint T_ENQUETE_ANSWER_FK2 foreign key (MEMBER_ID) references T_MEMBER (ID);
