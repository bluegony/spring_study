--------------------------------------------------------------------------
-- 데이터베이스 등록
--------------------------------------------------------------------------
CREATE DATABASE kyuriboard DEFAULT CHARACTER SET euckr DEFAULT COLLATE euckr_korean_ci;

--------------------------------------------------------------------------
-- 유저 등록
--------------------------------------------------------------------------
GRANT ALL PRIVILEGES ON *.* TO 'scott'@'%' IDENTIFIED BY 'tiger' WITH GRANT OPTION;

--------------------------------------------------------------------------
-- 테이블 등록
--------------------------------------------------------------------------

USE kyuriboard;

--------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS user (
	userid VARCHAR(10) CHARACTER SET euckr COLLATE euckr_korean_ci NOT NULL,
	username VARCHAR(10) CHARACTER SET euckr COLLATE euckr_korean_ci NOT NULL,
	passwd VARCHAR(10) CHARACTER SET euckr COLLATE euckr_korean_ci NOT NULL,
	email VARCHAR(40) CHARACTER SET euckr COLLATE euckr_korean_ci NOT NULL,
	insertdate DATETIME NOT NULL, 	
	PRIMARY KEY (userid)
) DEFAULT CHARACTER SET euckr COLLATE euckr_korean_ci;

ALTER TABLE user
ADD INDEX idx_username(username);
--------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS board (
	boardid INT NOT NULL,
	title VARCHAR(80) CHARACTER SET euckr COLLATE euckr_korean_ci NOT NULL,
	visited INT NOT NULL,
	recom INT NOT NULL,
	userip VARCHAR(20) CHARACTER SET euckr COLLATE euckr_korean_ci NULL,
	htmlyn CHAR(1) NULL,
	insertdate DATETIME NOT NULL,
	content TEXT CHARACTER SET euckr COLLATE euckr_korean_ci NOT NULL,	
	userid VARCHAR(10) CHARACTER SET euckr COLLATE euckr_korean_ci NOT NULL,
	PRIMARY KEY (boardid) 
) DEFAULT CHARACTER SET euckr COLLATE euckr_korean_ci;
	
ALTER TABLE board
ADD INDEX idx_title(title);

--------------------------------------------------------------------------
CREATE TABLE IF NOT exists memo (
	memoid INT NOT NULL,
	boardid INT NOT NULL,
	content VARCHAR(80) CHARACTER SET euckr COLLATE euckr_korean_ci NOT NULL,
	insertdate DATETIME NOT NULL,
	userid VARCHAR(10) CHARACTER SET euckr COLLATE euckr_korean_ci NOT NULL,
	PRIMARY KEY(memoid)
) DEFAULT CHARACTER SET euckr COLLATE euckr_korean_ci;

ALTER TABLE MEMO
ADD FOREIGN KEY (boardid)
REFERENCES BOARD(boardid)
ON DELETE RESTRICT
ON UPDATE RESTRICT;

ALTER TABLE memo
ADD INDEX idx_boardid(boardid);

ALTER TABLE memo
ADD INDEX idx_userid(userid);


--------------------------------------------------------------------------
CREATE TABLE IF NOT exists sequence (
	name VARCHAR(30) NOT NULL,
	nextid INT NOT NULL,	
	primary key(name)
) DEFAULT CHARACTER SET euckr COLLATE euckr_korean_ci;
--------------------------------------------------------------------------
