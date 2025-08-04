--------------------------------------------------------
--  파일이 생성됨 - 월요일-8월-04-2025   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Sequence SEQ_BOARD_BNO
--------------------------------------------------------

   CREATE SEQUENCE  "SEQ_BOARD_BNO"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1021 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence SEQ_BOARD_COMMENT_CNO
--------------------------------------------------------

   CREATE SEQUENCE  "SEQ_BOARD_COMMENT_CNO"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 3061 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence SEQ_BOARD_FILE_FNO
--------------------------------------------------------

   CREATE SEQUENCE  "SEQ_BOARD_FILE_FNO"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence USERS_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "USERS_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 25 NOCACHE  NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Table BOARD
--------------------------------------------------------

  CREATE TABLE "BOARD" 
   (	"BNO" NUMBER, 
	"ID" NUMBER(10,0), 
	"TITLE" VARCHAR2(150 BYTE), 
	"CONTENT" CLOB, 
	"WRITE_DATE" DATE DEFAULT sysdate, 
	"BCOUNT" NUMBER DEFAULT 0, 
	"WRITE_UPDATE_DATE" DATE DEFAULT sysdate
   ) ;
--------------------------------------------------------
--  DDL for Table BOARD_COMMENT
--------------------------------------------------------

  CREATE TABLE "BOARD_COMMENT" 
   (	"CNO" NUMBER, 
	"ID" NUMBER(10,0), 
	"BNO" NUMBER, 
	"CONTENT" VARCHAR2(1000 BYTE), 
	"CDATE" DATE DEFAULT sysdate
   ) ;
--------------------------------------------------------
--  DDL for Table BOARD_COMMENT_HATE
--------------------------------------------------------

  CREATE TABLE "BOARD_COMMENT_HATE" 
   (	"ID" NUMBER(10,0), 
	"CNO" NUMBER
   ) ;
--------------------------------------------------------
--  DDL for Table BOARD_COMMENT_LIKE
--------------------------------------------------------

  CREATE TABLE "BOARD_COMMENT_LIKE" 
   (	"ID" NUMBER(10,0), 
	"CNO" NUMBER
   ) ;
--------------------------------------------------------
--  DDL for Table BOARD_CONTENT_HATE
--------------------------------------------------------

  CREATE TABLE "BOARD_CONTENT_HATE" 
   (	"ID" NUMBER(10,0), 
	"BNO" NUMBER
   ) ;
--------------------------------------------------------
--  DDL for Table BOARD_CONTENT_LIKE
--------------------------------------------------------

  CREATE TABLE "BOARD_CONTENT_LIKE" 
   (	"ID" NUMBER(10,0), 
	"BNO" NUMBER
   ) ;
--------------------------------------------------------
--  DDL for Table BOARD_FILE
--------------------------------------------------------

  CREATE TABLE "BOARD_FILE" 
   (	"FNO" NUMBER, 
	"BNO" NUMBER, 
	"FPATH" VARCHAR2(500 BYTE)
   ) ;
--------------------------------------------------------
--  DDL for Table BOARD_MEMBER
--------------------------------------------------------

  CREATE TABLE "BOARD_MEMBER" 
   (	"ID" NUMBER(10,0), 
	"USERID" VARCHAR2(50 BYTE), 
	"USERNAME" VARCHAR2(50 BYTE), 
	"PASSWORD" VARCHAR2(255 BYTE), 
	"ROLE" VARCHAR2(20 BYTE) DEFAULT 'ROLE_USER'
   ) ;
--------------------------------------------------------
--  DDL for View BOARD_COMMENT_VIEW
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE VIEW "BOARD_COMMENT_VIEW" ("CNO", "BNO", "ID", "USERID", "USERNAME", "CONTENT", "CDATE", "CLIKE", "CHATE") AS 
  WITH comment_like_counts AS (
    -- 댓글별 좋아요 개수를 미리 계산합니다.
    SELECT
        cno,
        COUNT(*) AS clike
    FROM
        board_comment_like
    GROUP BY
        cno
), comment_hate_counts AS (
    -- 댓글별 싫어요 개수를 미리 계산합니다.
    SELECT
        cno,
        COUNT(*) AS chate
    FROM
        board_comment_hate
    GROUP BY
        cno
)
-- 최종적으로 댓글 정보, 작성자 정보, 좋아요/싫어요 개수를 조합합니다.
SELECT
    c.cno,                               -- 댓글 번호
    c.bno,                               -- 게시글 번호
    c.id,                                -- 회원 번호 (FK)
    m.userid,                            -- 회원 아이디
    m.username,                          -- 회원 이름(닉네임)
    c.content,                           -- 댓글 내용
    c.cdate,                             -- 댓글 작성일
    NVL(lk.clike, 0) AS clike,           -- 좋아요 수 (없으면 0)
    NVL(ht.chate, 0) AS chate            -- 싫어요 수 (없으면 0)
FROM
    board_comment c
JOIN
    board_member m ON c.id = m.id
LEFT OUTER JOIN
    comment_like_counts lk ON c.cno = lk.cno
LEFT OUTER JOIN
    comment_hate_counts ht ON c.cno = ht.cno
;
--------------------------------------------------------
--  DDL for View BOARD_VIEW
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE VIEW "BOARD_VIEW" ("BNO", "TITLE", "ID", "USERNAME", "BCOUNT", "WRITE_DATE", "CONTENT", "BLIKE", "BHATE", "CCOUNT") AS 
  with bcl_count as (
    select bno, count(*) as blike_count 
    from board_content_like group by bno
), bch_count as (
    select bno, count(*) as bhate_count
    from board_content_hate group by bno
), bc_count as (
    select bc.bno, count(*) as ccount
    from board_comment bc group by bc.bno
)
select b.bno, b.title, b.id, bm.username, b.bcount, 
    b.write_date, b.content, 
    nvl(bcl.blike_count,0) as blike, nvl(bch.bhate_count,0) as bhate,
    nvl(bc.ccount,0) as ccount
from board b inner join board_member bm on b.id = bm.id
left outer join bcl_count bcl on b.bno = bcl.bno
left outer join bch_count bch on b.bno = bch.bno
left outer join bc_count bc on b.bno = bc.bno
;
--------------------------------------------------------
--  DDL for Index PK_BOARD
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_BOARD" ON "BOARD" ("BNO") 
  ;
--------------------------------------------------------
--  DDL for Index PK_BOARD_COMMENT
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_BOARD_COMMENT" ON "BOARD_COMMENT" ("CNO") 
  ;
--------------------------------------------------------
--  DDL for Index PK_BOARD_COMMENT_HATE
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_BOARD_COMMENT_HATE" ON "BOARD_COMMENT_HATE" ("ID", "CNO") 
  ;
--------------------------------------------------------
--  DDL for Index PK_BOARD_COMMENT_LIKE
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_BOARD_COMMENT_LIKE" ON "BOARD_COMMENT_LIKE" ("ID", "CNO") 
  ;
--------------------------------------------------------
--  DDL for Index PK_BOARD_CONTENT_HATE
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_BOARD_CONTENT_HATE" ON "BOARD_CONTENT_HATE" ("ID", "BNO") 
  ;
--------------------------------------------------------
--  DDL for Index PK_BOARD_CONTENT_LIKE
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_BOARD_CONTENT_LIKE" ON "BOARD_CONTENT_LIKE" ("ID", "BNO") 
  ;
--------------------------------------------------------
--  DDL for Index SYS_C0011811
--------------------------------------------------------

  CREATE UNIQUE INDEX "SYS_C0011811" ON "BOARD_MEMBER" ("ID") 
  ;
--------------------------------------------------------
--  DDL for Index SYS_C0011812
--------------------------------------------------------

  CREATE UNIQUE INDEX "SYS_C0011812" ON "BOARD_MEMBER" ("USERID") 
  ;
--------------------------------------------------------
--  DDL for Index SYS_C0011814
--------------------------------------------------------

  CREATE UNIQUE INDEX "SYS_C0011814" ON "BOARD_FILE" ("FNO") 
  ;
--------------------------------------------------------
--  DDL for Index PK_BOARD
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_BOARD" ON "BOARD" ("BNO") 
  ;
--------------------------------------------------------
--  DDL for Index PK_BOARD_COMMENT
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_BOARD_COMMENT" ON "BOARD_COMMENT" ("CNO") 
  ;
--------------------------------------------------------
--  DDL for Index PK_BOARD_COMMENT_HATE
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_BOARD_COMMENT_HATE" ON "BOARD_COMMENT_HATE" ("ID", "CNO") 
  ;
--------------------------------------------------------
--  DDL for Index PK_BOARD_COMMENT_LIKE
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_BOARD_COMMENT_LIKE" ON "BOARD_COMMENT_LIKE" ("ID", "CNO") 
  ;
--------------------------------------------------------
--  DDL for Index PK_BOARD_CONTENT_HATE
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_BOARD_CONTENT_HATE" ON "BOARD_CONTENT_HATE" ("ID", "BNO") 
  ;
--------------------------------------------------------
--  DDL for Index PK_BOARD_CONTENT_LIKE
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_BOARD_CONTENT_LIKE" ON "BOARD_CONTENT_LIKE" ("ID", "BNO") 
  ;
--------------------------------------------------------
--  DDL for Index SYS_C0011814
--------------------------------------------------------

  CREATE UNIQUE INDEX "SYS_C0011814" ON "BOARD_FILE" ("FNO") 
  ;
--------------------------------------------------------
--  DDL for Index SYS_C0011811
--------------------------------------------------------

  CREATE UNIQUE INDEX "SYS_C0011811" ON "BOARD_MEMBER" ("ID") 
  ;
--------------------------------------------------------
--  DDL for Index SYS_C0011812
--------------------------------------------------------

  CREATE UNIQUE INDEX "SYS_C0011812" ON "BOARD_MEMBER" ("USERID") 
  ;
--------------------------------------------------------
--  DDL for Trigger USERS_TRG
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE TRIGGER "USERS_TRG" 
BEFORE INSERT ON board_member
FOR EACH ROW
BEGIN
    SELECT users_seq.NEXTVAL INTO :NEW.id FROM DUAL;
END;
/
ALTER TRIGGER "USERS_TRG" ENABLE;
--------------------------------------------------------
--  DDL for Trigger USERS_TRG
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE TRIGGER "USERS_TRG" 
BEFORE INSERT ON board_member
FOR EACH ROW
BEGIN
    SELECT users_seq.NEXTVAL INTO :NEW.id FROM DUAL;
END;
/
ALTER TRIGGER "USERS_TRG" ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table BOARD_COMMENT
--------------------------------------------------------

  ALTER TABLE "BOARD_COMMENT" ADD CONSTRAINT "FK_BC_BNO" FOREIGN KEY ("BNO")
	  REFERENCES "BOARD" ("BNO") ON DELETE CASCADE ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table BOARD_COMMENT_HATE
--------------------------------------------------------

  ALTER TABLE "BOARD_COMMENT_HATE" ADD CONSTRAINT "FK_BCMH_ID" FOREIGN KEY ("ID")
	  REFERENCES "BOARD_MEMBER" ("ID") ON DELETE CASCADE ENABLE;
  ALTER TABLE "BOARD_COMMENT_HATE" ADD CONSTRAINT "FK_BCMH_CNO" FOREIGN KEY ("CNO")
	  REFERENCES "BOARD_COMMENT" ("CNO") ON DELETE CASCADE ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table BOARD_COMMENT_LIKE
--------------------------------------------------------

  ALTER TABLE "BOARD_COMMENT_LIKE" ADD CONSTRAINT "FK_BCML_ID" FOREIGN KEY ("ID")
	  REFERENCES "BOARD_MEMBER" ("ID") ON DELETE CASCADE ENABLE;
  ALTER TABLE "BOARD_COMMENT_LIKE" ADD CONSTRAINT "FK_BCML_CNO" FOREIGN KEY ("CNO")
	  REFERENCES "BOARD_COMMENT" ("CNO") ON DELETE CASCADE ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table BOARD_CONTENT_HATE
--------------------------------------------------------

  ALTER TABLE "BOARD_CONTENT_HATE" ADD CONSTRAINT "FK_BCH_ID" FOREIGN KEY ("ID")
	  REFERENCES "BOARD_MEMBER" ("ID") ON DELETE CASCADE ENABLE;
  ALTER TABLE "BOARD_CONTENT_HATE" ADD CONSTRAINT "FK_BCH_BNO" FOREIGN KEY ("BNO")
	  REFERENCES "BOARD" ("BNO") ON DELETE CASCADE ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table BOARD_CONTENT_LIKE
--------------------------------------------------------

  ALTER TABLE "BOARD_CONTENT_LIKE" ADD CONSTRAINT "FK_BCL_ID" FOREIGN KEY ("ID")
	  REFERENCES "BOARD_MEMBER" ("ID") ON DELETE CASCADE ENABLE;
  ALTER TABLE "BOARD_CONTENT_LIKE" ADD CONSTRAINT "FK_BCL_BNO" FOREIGN KEY ("BNO")
	  REFERENCES "BOARD" ("BNO") ON DELETE CASCADE ENABLE;
