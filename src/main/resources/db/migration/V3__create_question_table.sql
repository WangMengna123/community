create table question
(
	id int auto_increment,
	title varchar(50),
	description text,
	gmt_create BIGINT,
	gmt_modified bigint,
	creator int,
	command_count int default 0,
	view_count int default 0,
	like_count int default 0,
	tags varchar(256),
	constraint question_pk
		primary key (id)
);
