INSERT INTO USERDTO (ID, USER_ID, PASSWORD, NAME, EMAIL, CREATE_DATE) VALUES (1, 'jinsu', 'jinsu', 'jinsu', 'jinsu@naver.com',CURRENT_TIMESTAMP());
INSERT INTO USERDTO (ID, USER_ID, PASSWORD, NAME, EMAIL,CREATE_DATE) VALUES (2, 'test', 'test', 'test', 'test@naver.com',CURRENT_TIMESTAMP());

INSERT INTO QUESTIONDTO (id, writer_id, title, contents, create_date, count_of_answer) VALUES (1, 1, '테스트 입력1', 'jinsu 유저 데이터 입력', CURRENT_TIMESTAMP(), 0);
INSERT INTO QUESTIONDTO (id, writer_id, title, contents, create_date, count_of_answer) VALUES (2, 2, '테스트 입력2', 'test 유저 데이터 입력', CURRENT_TIMESTAMP(), 0);