create table TB_USER (
	user_id varchar(30) primary key comment 'User 고유번호 (사용자가 만드는 건 아님)', 
	password varchar(10) NOT NULL comment '비밀번호',
	email varchar(50) NOT NULL comment 'email 주소(실제 사용자 ID)',
	name varchar(30) NOT NULL comment '표시되는 이름(중복 허용)',
	introduce varchar(600) comment '소갯말',
	address varchar(200) comment '주소',
	location varchar(40) comment '국가',
	upload_cnt int comment 'Upload 한 전체 숫자',
	post_cnt int comment 'Post 한 전체 숫자',
	earn_total_money int comment '수익의 전체 금액',
	earn_reword_money int comment '리워드로 받은 전체 금액',
	resent_send_addr varchar(200) comment '최근 보낸 주소',
	resent_send_name varchar(30) comment '최근 보낸 이름',
	point int comment '엽서를 구매할 수 있는 포인트',
	user_status char(1) default 'N' comment '계정 상태(정상-N/정지-S/휴먼-Q)',
    created_date datetime default now() comment '생성일시'
) comment = '회원';

create table TB_FOLLOW (
	user_id varchar(30) not null comment 'Follow를 하는 사람 ID (follower)',
	following varchar(30) not null comment 'Follow되는 대상 ID',
    created_date datetime default now() comment '생성일시',
	primary key (user_id, following)
) COMMENT = '팔로워';

create  table TB_PURCHASE (
	seq int primary key auto_increment comment '구매 고유 번호',
	user_id varchar(30) comment '구매한 사람의 id', 
	painting_id varchar(64) comment '구매된 그림의 id', 
	purchase_date datetime comment '구매된 날짜',
	sentence varchar(200) comment '구매하면서 작성된 한마디',
	private_at char(1) comment 'sentence의 공개/비공개 여부',
	receiver_basic_addr varchar(200) comment '엽서 수신자 기본 주소',
	receiver_detail_addr varchar(200) comment '엽서 수신자 상세 주소',
	receiver_zipcode varchar(200) comment '엽서 수신자 우편번호',
	receiver_city varchar(200) comment '엽서 수신자 도시명',
	sender_addr varchar(30) comment '엽서 발신자 주소',
	receiver_name varchar(200) comment '엽서 수신자 이름',
	sender_name varchar(30) comment '엽서 발신자 이름',
	location varchar(40) comment '구매자의 국가',
	purchase_status char(1) comment '구매 진행 상태 (요청-R/발송-S/환불요청-R/삭제-D)',
    created_date datetime default now() comment '생성일시'
) COMMENT = '구매';

create table TB_REWARD ( 
	seq int primary key auto_increment comment '리워드 고유 번호',
    user_id varchar(30) comment '리워드 요청자 id',
    account_no varchar(20) comment '입금 요청된 계좌번호',
    account_name varchar(30) comment '입금 요청된 계좌주 이름',
    earm_requested_money int comment '입금 요청된 금액',
    reward_status char(1) comment '입금 진행 상태 (요청-R/비정상-A/완료-C)',
    created_date datetime default now() comment '생성일시'
) COMMENT = '리워드';

create table TB_PAINTING (
    painting_id varchar(64) primary key comment '그림의 고유 ID',
    upload_date datetime comment '그림이 업로드 된 날짜',
    artist_id varchar(30) comment '그림을 올린 사람 ID',
    artist_name varchar(30) comment '그림을 올린 사람 이름',
    sentence varchar(600) comment '그림 소갯말', 
    location varchar(40) comment '업로드한 사람의 국가',
    posted_num int default 0 comment '구매된 숫자',
    posted_people_cnt int default 0 comment '구매된 숫자(사람수 기준)',
    original_size varchar(10) comment '원본 이미지의 size',
    view_cnt int default 0 comment '조회수',
    share_cnt int default 0 comment 'share 버튼을 통해 공유된 횟수',
    file_group_seq bigint comment '첨부파일 그룹 아이디',
    painting_status char(1) comment '현재 그림의 상태(정상-N/블라인드-B/삭제-D)',
    created_date datetime default now() comment '생성일시'
) COMMENT = '페인팅';


create table TB_NEW_PAINTING (
	seq int primary key auto_increment comment '신규 페인팅 고유 번호',
    painting_id varchar(64) comment '그림의 고유 ID'
) COMMENT = '신규 페인팅';

create table TB_POPULAR_PAINTING (
	seq int primary key auto_increment comment '인기 페인팅 고유 번호',
    painting_id varchar(64) comment '그림의 고유 ID',
    purchase_count int comment '그림 구매 숫자'
) COMMENT = '인기 페인팅';

create table TB_LOGIN (
	seq int primary key auto_increment comment '로그인 구분 고유 번호',
    user_id varchar(30) comment '로그인 사용자 id',
	hash    varchar(64) comment '사용자 구분 해쉬값',
	expire_date  date comment '로그인 유효 날짜',
	access_gubun  char(1) comment '접속 구분'
) COMMENT = '로그인';

create table TB_FILE_GROUP (
    seq bigint not null auto_increment comment '파일그룹아이디',
    group_name varchar(255) comment '그룹명',
    created_date datetime default now() comment '생성일자',
    primary key (seq)
) COMMENT = '파일 그룹';

create table TB_FILE_INFO (
    id varchar(255) not null comment '파일 아이디',
    file_group_seq bigint not null comment '파일그룹아이디',
    content_type varchar(255) comment '파일 mime type',
    extension varchar(255) comment '파일 확장자',
    name varchar(255) comment '서버저장 파일명(확장자 제외)',
    ori_name varchar(255) comment '사용자가 업로드한 파일명(확장자 포함)',
    display_name varchar(255) comment '화면표시용 파일명',
    path varchar(255) comment '파일명과 절대경로를 제외한 파일 경로',
    size bigint comment '파일 용량',
    created_date datetime default now() comment '생성일자',
    last_modified_date datetime default now() comment '수정일자',
    primary key (id)
) COMMENT = '파일 정보';