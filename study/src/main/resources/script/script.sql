
drop table USER_TBL;
drop table board;
drop table image_attachment;

select * from USER_TBL;
select * from board order by free_id desc;

create sequence seq_board_id;

-- 회원 테이블
create table USER_TBL (
    user_id varchar2(50),
    name varchar2(10),
    password varchar2(300) not null,
    email varchar2(100),
    phone varchar2(20),
    enroll_date date default sysdate,
    constraints pk_member_id primary key(user_id)
);


-- 게시판 테이블
create table board(
    board_id number,
    board_user_id varchar2(50) not null,
    board_title varchar2(500) not null,
    board_content varchar2(4000) not null,
    board_created_at timestamp default systimestamp,
    constraints pk_board_id primary key(board_id),
    constraints fk_board_user_id foreign key(board_user_id) references user_tbl(user_id) on delete cascade
);


-- 이미지 파일 테이블
create table image (
    image_id number,
    board_id number,
    origin_name varchar2(500) not null,
    unique_name varchar2(500) not null,
    image_file_size number not null,
    constraint pk_image_attachment_id primary key(image_id),
    constraint fk_board_id foreign key(board_id) references board(board_id)
);

