alter table T_PARTY_ANSWER add constraint T_PARTY_ANSWER_FK1 foreign key (QUESTION_ID) references T_PARTY_QUESTION (ID);
alter table T_PARTY_ANSWER add constraint T_PARTY_ANSWER_FK2 foreign key (MEMBER_ID) references T_MEMBER (ID);
