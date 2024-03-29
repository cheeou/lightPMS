-- 투숙객
CREATE TABLE PMS_GUEST (
	ID           BIGINT       NOT NULL COMMENT 'ID', -- ID
	GUEST_NM     VARCHAR(100) NOT NULL COMMENT '투숙객 명', -- 투숙객 명
	GUEST_NM_ENG VARCHAR(100) NULL     COMMENT '투숙객 명 영어', -- 투숙객 명 영어
	GUEST_TEL    VARCHAR(18)  NULL     COMMENT '투숙객 전화', -- 투숙객 전화
	EMAIL        VARCHAR(100) NULL     COMMENT '이메일', -- 이메일
	BRTH         VARCHAR(10)  NULL     COMMENT '생일', -- 생일
	GENDER       VARCHAR(20)  NULL     COMMENT '성별', -- 성별
	LANG_CD      VARCHAR(20)  NULL     COMMENT '언어 CD', -- 언어 CD
	RMK          VARCHAR(500) NULL     COMMENT '비고', -- 비고
	CREATED_AT   TIMESTAMP    NOT NULL COMMENT '등록일', -- 등록일
	CREATED_BY   VARCHAR(100) NOT NULL COMMENT '등록자', -- 등록자
	UPDATED_AT   TIMESTAMP    NOT NULL COMMENT '변경일', -- 변경일
	UPDATED_BY   VARCHAR(100) NOT NULL COMMENT '변경자' -- 변경자
);

-- 투숙객
ALTER TABLE PMS_GUEST
	ADD CONSTRAINT PK_cust -- 고객 기본키
		PRIMARY KEY (
			ID -- ID
		);

-- 투숙객 인덱스
CREATE INDEX IX_PMS_GUEST
	ON PMS_GUEST( -- 투숙객
		GUEST_NM ASC -- 투숙객 명
	);

-- 투숙객 인덱스4
CREATE INDEX IX_PMS_GUEST4
	ON PMS_GUEST( -- 투숙객
		GUEST_TEL ASC -- 투숙객 전화
	);

-- 투숙객 인덱스6
CREATE INDEX IX_PMS_GUEST6
	ON PMS_GUEST( -- 투숙객
		EMAIL ASC -- 이메일
	);

ALTER TABLE PMS_GUEST
	MODIFY COLUMN ID BIGINT NOT NULL AUTO_INCREMENT COMMENT 'ID';
