-- 创建数据库
create database StudentinfoSystem;

-- 使用数据库
use StudentinfoSystem;

-- 用户表
create table user (
                      id int unsigned primary key auto_increment comment 'ID',
                      username varchar(20) not null unique comment '用户名',
                      password varchar(32)  comment '密码',
                      nickname varchar(10)  default '' comment '昵称',
                      email varchar(128) default '' comment '邮箱',
                      user_pic varchar(128) default '' comment '头像',
                      create_time datetime not null comment '创建时间',
                      update_time datetime not null comment '修改时间'
) comment '用户表';


-- 分类表
create table category(
                         id int unsigned primary key auto_increment comment 'ID',
                         category_name varchar(32) not null comment '分类名称',
                         category_alias varchar(32) not null comment '分类别名',
                         create_user int unsigned not null comment '创建人ID',
                         create_time datetime not null comment '创建时间',
                         update_time datetime not null comment '修改时间',
                         constraint fk_category_user foreign key (create_user) references user(id) -- 外键约束
);
-- 学生表
create table student(
                        id int unsigned primary key auto_increment comment 'ID',
                        name varchar(30) not null comment '姓名',
                        age int not null comment '年龄',
                        address varchar(30) not null comment '住址',
                        state varchar(3) default '草稿' comment '状态: 只能是[已发布] 或者 [草稿]',
                        category_id int unsigned comment '班级分类ID',
                        create_user int unsigned not null comment '创建人ID',
                        create_time datetime not null comment '创建时间',
                        update_time datetime not null comment '修改时间'
);