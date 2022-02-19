DROP DATABASE IF EXISTS jsptry;

CREATE DATABASE jsptry;

# jsptry 사용
USE jsptry;

# 주소록 테이블 삭제
DELETE FROM addr;
DROP TABLE addr;

# 게시물 테이블 생성
CREATE TABLE addr(
    idx INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(20) NOT NULL,
    address VARCHAR(50) NOT NULL,
    phone VARCHAR(14) NOT NULL
);

SELECT * FROM addr;

# 데이터 추가
INSERT INTO addr
SET `name` = '홍길동',
address = '서울',
phone = '010-1111-1111';
