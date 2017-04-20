INSERT INTO USERDTO (ID, USER_ID, PASSWORD, NAME, EMAIL) VALUES (1, 'jinsu', 'jinsu', 'jinsu', 'jinsu@naver.com');
INSERT INTO USERDTO (ID, USER_ID, PASSWORD, NAME, EMAIL) VALUES (2, 'test', 'test', 'test', 'test@naver.com');

INSERT INTO QUESTIONDTO (id, writer_id, title, contents, create_date) VALUES (1, 1, '테스트 입력1', 'jinsu 유저 데이터 입력', CURRENT_TIMESTAMP());
INSERT INTO QUESTIONDTO (id, writer_id, title, contents, create_date) VALUES (2, 2, '테스트 입력2', 'test 유저 데이터 입력', CURRENT_TIMESTAMP());