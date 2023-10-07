
drop table user;
drop table free_board;
drop table food_board;
drop table travel_board;
drop table image_attachment;
drop table image_attachment_mapping;

select * from "USER";

-- 회원 테이블
create table "USER" (
    user_id varchar2(50),
    name varchar2(10),
    password varchar2(300) not null,
    email varchar2(100),
    phone varchar2(20),
    enroll_date date default sysdate,
    constraints pk_member_id primary key(user_id)
);


-- 자유게시판 테이블
create table free_board(
    free_id number,
    free_user_id varchar2(50) not null,
    free_title varchar2(500) not null,
    free_content varchar2(4000) not null,
    free_created_at timestamp default systimestamp,
    constraints pk_free_id primary key(free_id),
    constraints fk_free_member_id foreign key(free_member_id) references member(member_id) on delete cascade
);

select * from food_board;
-- 맛집 게시판 테이블
create table food_board(
    food_id number,
    food_user_id varchar2(50) not null,
    food_title varchar2(500) not null,
    food_content varchar2(4000) not null,
    food_created_at date default sysdate,
    food_image_id number,
    constraints pk_food_id primary key(food_id),
    constraints fk_food_user_id foreign key(food_user_id) references user_tbl(user_id) on delete cascade,
    constraints fk_food_image_id foreign key(food_image_id) references image_attachment(image_id) on delete cascade
);


-- 여행 게시판 테이블
create table travel_board(
    travel_id number,
    travel_user_id varchar2(50) not null,
    travel_title varchar2(500) not null,
    travel_content varchar2(4000) not null,
    travel_created_at timestamp default systimestamp,
    constraints pk_travel_id primary key(travel_id),
    constraints fk_travel_member_id foreign key(travel_member_id) references member(member_id) on delete cascade
);


-- 이미지 파일 테이블
create table image_attachment (
    image_id number,
    image_original_filename varchar2(500) not null,
    image_renamed_filename varchar2(500) not null,
    image_file_size number not null,
    image_created_at timestamp default systimestamp,
    constraint pk_image_attachment_id primary key(image_id)
);


-- 이미지 파일 매핑 테이블
--create table image_attachment_mapping (
--    mapping_id number,
--    ref_table varchar2(50) not null,
--    ref_id number not null,
--    image_id number not null,
--    constraint pk_question_image_mapping_id primary key(mapping_id),
--    constraint fk_image_id foreign key(image_id) references image_attachment(image_id) on delete cascade
--);




create table food_board(
    food_id number,
    food_member_id varchar2(50) not null,
    food_title varchar2(500) not null,
    food_content varchar2(4000) not null,
    food_created_at timestamp default systimestamp,
    constraints pk_food_id primary key(food_id),
    constraints fk_food_member_id foreign key(food_member_id) references member(member_id) on delete cascade
);

select * from food_board;


insert into food_board values (
SEQ_FOOD_BOARD_ID.nextval, '테스트하려고 넣었습니다.', default, 'honggd', '안녕하세요~'
);

