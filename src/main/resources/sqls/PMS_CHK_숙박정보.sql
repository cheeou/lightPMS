CREATE TABLE PMS_CHK (
	ID           BIGINT       NOT NULL COMMENT 'ID', -- ID
	RSV_DT       VARCHAR(10)  NOT NULL COMMENT '예약 일자', -- 예약 일자
	SNO          INT          NOT NULL COMMENT '일련번호', -- 일련번호
	RSV_NUM      VARCHAR(20)  NOT NULL COMMENT '예약 번호', -- 예약 번호
	GUEST_ID     BIGINT       NULL     COMMENT '투숙객 ID', -- 투숙객 ID
	GUEST_NM     VARCHAR(100) NULL     COMMENT '투숙객 명', -- 투숙객 명
	GUEST_NM_ENG VARCHAR(200) NULL     COMMENT '투숙객 명 영어', -- 투숙객 명 영어
	GUEST_TEL    VARCHAR(18)  NULL     COMMENT '투숙객 전화', -- 투숙객 전화
	EMAIL        VARCHAR(100) NULL     COMMENT '이메일', -- 이메일
	LANG_CD      VARCHAR(20)  NULL     COMMENT '언어 CD', -- 언어 CD
	ARR_DT       VARCHAR(10)  NOT NULL COMMENT '도착 일자', -- 도착 일자
	ARR_TIME     VARCHAR(8)   NULL     COMMENT '도착 시간', -- 도착 시간
	DEP_DT       VARCHAR(10)  NOT NULL COMMENT '출발 일자', -- 출발 일자
	DEP_TIME     VARCHAR(8)   NULL     COMMENT '출발 시간', -- 출발 시간
	NIGHT_CNT    INT          NOT NULL COMMENT '숙박 수', -- 숙박 수
	ROOM_TYP_CD  VARCHAR(20)  NOT NULL COMMENT '객실 타입 CD', -- 객실 타입 CD
	ROOM_NUM     VARCHAR(10)  NULL     COMMENT '객실 번호', -- 객실 번호
	ADULT_CNT    INT          NOT NULL DEFAULT 0 COMMENT '성인 수', -- 성인 수
	CHLD_CNT     INT          NOT NULL DEFAULT 0 COMMENT '아동 수', -- 아동 수
	SALE_TYP_CD  VARCHAR(20)  NOT NULL COMMENT '판매 유형 CD', -- 판매 유형 CD
	STTUS_CD     VARCHAR(20)  NOT NULL COMMENT '상태 CD', -- 상태 CD
	SRC_CD       VARCHAR(20)  NOT NULL COMMENT '소스 CD', -- 소스 CD
	BRTH         VARCHAR(10)  NULL     COMMENT '생일', -- 생일
	GENDER       VARCHAR(20)  NULL     COMMENT '성별', -- 성별
	PAY_CD       VARCHAR(20)  NULL     COMMENT '결제 CD', -- 결제 CD
	ADVN_YN      VARCHAR(1)   NOT NULL DEFAULT 'N' COMMENT '선수금 여부', -- 선수금 여부
	SALE_PRC     DECIMAL(18)  NULL     COMMENT '판매 가격', -- 판매 가격
	SVC_PRC      DECIMAL(18)  NULL     COMMENT '서비스 가격', -- 서비스 가격
	CREATED_AT   TIMESTAMP    NULL     COMMENT '등록일', -- 등록일
	CREATED_BY   VARCHAR(100) NULL     COMMENT '등록자', -- 등록자
	UPDATED_AT   TIMESTAMP    NULL     COMMENT '변경일', -- 변경일
	UPDATED_BY   VARCHAR(100) NULL     COMMENT '변경자' -- 변경자
);
-- 숙박정보
ALTER TABLE PMS_CHK
	ADD CONSTRAINT PK_rsv_dtls -- 예약 상세 기본키
		PRIMARY KEY (
			ID -- ID
		);

-- 숙박정보 인덱스5
CREATE INDEX IX_PMS_CHK
	ON PMS_CHK( -- 숙박정보
		RSV_DT ASC -- 예약 일자
	);

-- 숙박정보 인덱스
CREATE INDEX IX_PMS_CHK2
	ON PMS_CHK( -- 숙박정보
		RSV_NUM ASC -- 예약 번호
	);

-- 숙박정보 인덱스2
CREATE INDEX IX_PMS_CHK3
	ON PMS_CHK( -- 숙박정보
		GUEST_NM ASC -- 투숙객 명
	);

-- 숙박기록 인덱스7
CREATE INDEX IX_TPE_CHK_BOOK7
	ON PMS_CHK( -- 숙박정보
		ARR_DT ASC, -- 도착 일자
		DEP_DT ASC  -- 출발 일자
	);

ALTER TABLE PMS_CHK
	MODIFY COLUMN ID BIGINT NOT NULL AUTO_INCREMENT COMMENT 'ID';
