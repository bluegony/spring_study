-- ��ġ �� �����ͺ��̽� �⵿
C:\mysql-5.0.22-win32\bin\mysqld.exe --console

-- �����ͺ��̽� ����
C:\mysql-5.0.22-win32\bin\mysqladmin.exe -u root shutdown

-- ���ο� ���� ��� (scott/tiger)
GRANT ALL PRIVILEGES ON *.* TO 'scott'@'%' IDENTIFIED BY 'tiger' WITH GRANT OPTION;

-- �����ͺ��̽� ��� (archy712)
CREATE DATABASE archy712 DEFAULT CHARACTER SET euckr DEFAULT COLLATE euckr_korean_ci;

-- ���̺� ��� (t1)
CREATE TABLE t1 (
	c1 VARCHAR(10) CHARACTER SET euckr COLLATE euckr_korean_ci
) DEFAULT CHARACTER SET euckr COLLATE euckr_korean_ci;

-- ������ ����
mysql -uscott -ptiger archy712 < item.sql

-- �ѱ� �׽�Ʈ
SET NAMES euckr;
INSERT INTO t1 (c1) VALUES('�ѱ�1');
SELECT * FROM t1;	-- �ѱ��� �� ����
SET NAMES utf8;	-- �䷸�� �ϸ� �ٽ� �ѱ� ����

-- ��¥ �׽�Ʈ
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

