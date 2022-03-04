DROP DATABASE IF EXISTS jsptry;

CREATE DATABASE jsptry;

# jsptry 사용
USE jsptry;

# 주소록 테이블 삭제
DELETE FROM addr;
DROP TABLE addr;

# 주소록 테이블 생성
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

INSERT INTO addr
SET `name` = '이몽룡',
address = '수원',
phone = '010-2222-2222';

INSERT INTO addr
SET `name` = '성춘향',
address = '화성',
phone = '010-3333-3333';

# 게시물 테이블 삭제
DELETE FROM article;
DROP TABLE article;

# 게시물 테이블 생성
CREATE TABLE article(
    idx INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    title VARCHAR(50) NOT NULL,
    `body` TEXT NOT NULL,
    nickname VARCHAR(30) NOT NULL
);

SELECT * FROM article;

INSERT INTO article
SET regDate = NOW(),
title = '제목1',
`body` = '내용1',
nickname = '홍길동';

INSERT INTO article
SET regDate = NOW(),
title = '제목2',
`body` = '내용2',
nickname = '성춘향';

INSERT INTO article
SET regDate = NOW(),
title = '제목3',
`body` = '내용3',
nickname = '이몽룡';

# 원하는 게시글 조회
SELECT * FROM article
WHERE idx = 1;

# 게시글 수정
UPDATE article
SET title='aaa',
`body`='bbb'
WHERE idx=1;

# 게시글 삭제
DELETE FROM article
WHERE idx=10;

# 회원 테이블 삭제
DELETE FROM `member`;
DROP TABLE `member`;

# 회원 테이블 생성
CREATE TABLE `member` (
    idx INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    loginId VARCHAR(50) NOT NULL,
    loginPw VARCHAR(50) NOT NULL,
    nickname VARCHAR(30) NOT NULL
);

SELECT * FROM `member`;

#회원 추가
INSERT INTO `member`
SET regDate=NOW(),
loginId="admin",
loginPw="admin",
nickname="관리자";

#로그인 정보로 회원번호 조회
SELECT idx
FROM `member`
WHERE loginId='admin'
AND loginPw='admin';

# 회원번호에 해당하는 회원로그인
SELECT *
FROM `member`
WHERE idx=1;