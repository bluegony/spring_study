-- 설치 후 데이터베이스 기동
C:\mysql-5.0.22-win32\bin\mysqld.exe --console

-- 데이터베이스 종료
C:\mysql-5.0.22-win32\bin\mysqladmin.exe -u root shutdown

-- 새로운 유저 등록 (scott/tiger)
GRANT ALL PRIVILEGES ON *.* TO 'scott'@'%' IDENTIFIED BY 'tiger' WITH GRANT OPTION;

-- 데이터베이스 등록 (archy712)
CREATE DATABASE archy712 DEFAULT CHARACTER SET euckr DEFAULT COLLATE euckr_korean_ci;

-- 테이블 등록 (t1)
CREATE TABLE t1 (
	c1 VARCHAR(10) CHARACTER SET euckr COLLATE euckr_korean_ci
) DEFAULT CHARACTER SET euckr COLLATE euckr_korean_ci;

-- 데이터 포팅
mysql -uscott -ptiger archy712 < item.sql

-- 한글 테스트
SET NAMES euckr;
INSERT INTO t1 (c1) VALUES('한글1');
SELECT * FROM t1;	-- 한글이 잘 보임
SET NAMES utf8;	-- 요렇게 하면 다시 한글 깨짐

-- 날짜 테스트
CREATE TABLE t3 (
	c3 DATETIME
);

INSERT INTO t3
(c3)
VALUES (SYSDATE());

-----------------------
-- my.ini

[mysql]
default-character-set=euckr

[mysqld]
...
default-character-set=euckr

