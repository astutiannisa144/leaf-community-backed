--DROP TABLE t_post_file ;
--DROP TABLE t_article ;
--DROP TABLE t_comment ;
--DROP TABLE t_bookmark  ;
--DROP TABLE t_like ;
--DROP TABLE t_post;
--DROP TABLE t_user_polling;
--DROP TABLE t_polling_detail;
--DROP TABLE t_polling;
--DROP TABLE t_user_activity;
--DROP TABLE t_user_voucher;
--DROP TABLE t_voucher;
--DROP TABLE t_schedule;
--DROP TABLE t_activity;
--DROP TABLE t_activity_type;
--DROP TABLE t_category;
--DROP TABLE t_user_premium;
--DROP TABLE t_bank_account ;
--DROP TABLE t_user;
--DROP TABLE t_profile_social_media;
--DROP TABLE t_social_media;
--DROP TABLE t_premium;
--DROP TABLE t_profile;
--DROP TABLE t_job;
--DROP TABLE t_industry;
--DROP TABLE t_position;
--DROP TABLE t_role;
--DROP TABLE t_file; 
--DROP TABLE t_verification;
--BEGIN;
--ROLLBACK;

CREATE TABLE t_file(
	id varchar(36),
	file_content text NOT NULL,
	file_extension varchar(10) NOT NULL,
	created_by varchar(36) NOT NULL,
	created_at timestamp NOT NULL,
	updated_by varchar(36),
	updated_at timestamp,
	ver int NOT NULL,
	is_active boolean NOT NULL 
);
ALTER TABLE t_file
	ADD CONSTRAINT file_pk PRIMARY KEY (id);


CREATE TABLE t_role(
	id varchar(36),
	role_code varchar(10) NOT NULL,
	role_name varchar(30),
	created_by varchar(36) NOT NULL,
	created_at timestamp NOT NULL,
	updated_by varchar(36),
	updated_at timestamp,
	ver int NOT NULL,
	is_active bool NOT NULL
);

ALTER TABLE t_role
	ADD CONSTRAINT role_pk PRIMARY KEY(id);

ALTER TABLE t_role
	ADD CONSTRAINT role_bk UNIQUE(role_code);

CREATE TABLE t_position(
	id varchar(36),
	position_code varchar(10) NOT NULL,
	position_name varchar(30) NOT NULL,
	created_by varchar(36) NOT NULL,
	created_at timestamp NOT NULL,
	updated_by varchar(36),
	updated_at timestamp,
	ver int NOT NULL,
	is_active bool NOT NULL
);

ALTER TABLE t_position
	ADD CONSTRAINT position_pk PRIMARY KEY(id);

ALTER TABLE t_position
	ADD CONSTRAINT position_bk UNIQUE(position_code);

CREATE TABLE t_industry(
	id varchar(36),
	industry_code varchar(10) NOT NULL,
	industry_name varchar(30) NOT NULL,
	created_by varchar(36) NOT NULL,
	created_at timestamp NOT NULL,
	updated_by varchar(36),
	updated_at timestamp,
	ver int NOT NULL,
	is_active bool NOT NULL
);

ALTER TABLE t_industry
	ADD CONSTRAINT industry_pk PRIMARY KEY(id);

ALTER TABLE t_industry
	ADD CONSTRAINT industry_bk UNIQUE(industry_code);

CREATE TABLE t_job(
	id varchar(36),
	company_name varchar(30) NOT NULL,
	industry_id varchar(36) NOT NULL,
	position_id varchar(36) NOT NULL,
	created_by varchar(36) NOT NULL,
	created_at timestamp NOT NULL,
	updated_by varchar(36),
	updated_at timestamp,
	ver int NOT NULL,
	is_active bool NOT NULL
);

ALTER TABLE t_job
	ADD CONSTRAINT job_pk PRIMARY KEY(id);

ALTER TABLE t_job
	ADD CONSTRAINT industry_fk FOREIGN KEY(industry_id)
	REFERENCES t_industry(id);

ALTER TABLE t_job
	ADD CONSTRAINT position_fk FOREIGN KEY(position_id)
	REFERENCES t_position(id);

CREATE TABLE t_profile(
	id varchar(36),
	full_name varchar(30),
	phone_number varchar(30),
	facebook varchar(30),
	job_id varchar(36),
	file_id varchar(36),
	address text ,
	balance decimal(15,0),
	created_by varchar(36) NOT NULL,
	created_at timestamp NOT NULL,
	updated_by varchar(36),
	updated_at timestamp,
	ver int NOT NULL,
	is_active bool NOT NULL
);

ALTER TABLE t_profile
	ADD CONSTRAINT profile_pk PRIMARY KEY(id);

ALTER TABLE t_profile
	ADD CONSTRAINT job_fk FOREIGN KEY(job_id)
	REFERENCES t_job(id);

ALTER TABLE t_profile
	ADD CONSTRAINT file_fk FOREIGN KEY(file_id)
	REFERENCES t_file(id);


CREATE TABLE t_social_media(
	id varchar(36),
	social_media_code varchar(10) NOT NULL,
	social_media_name varchar(30) NOT NULL,
	social_media_link varchar(30),
	social_media_icon varchar(36),
	file_id varchar(36),
	created_by varchar(36) NOT NULL,
	created_at timestamp NOT NULL,
	updated_by varchar(36),
	updated_at timestamp,
	ver int NOT NULL,
	is_active bool NOT NULL
);

ALTER TABLE t_social_media
	ADD CONSTRAINT social_media_pk PRIMARY KEY(id);

ALTER TABLE t_social_media
	ADD CONSTRAINT social_media_bk UNIQUE (social_media_code);

ALTER TABLE t_social_media
	ADD CONSTRAINT file_fk FOREIGN KEY (file_id)
	REFERENCES t_file(id);

CREATE TABLE t_profile_social_media(
	id varchar(36),
	profile_id varchar(36) NOT NULL,
	social_media_id varchar(36) NOT NULL,
	username varchar(30),
	profile_link varchar(60),
	created_by varchar(36) NOT NULL,
	created_at timestamp NOT NULL,
	updated_by varchar(36),
	updated_at timestamp,
	ver int NOT NULL,
	is_active bool NOT NULL
);

ALTER TABLE t_profile_social_media
	ADD CONSTRAINT profile_social_media_pk PRIMARY KEY(id);

ALTER TABLE t_profile_social_media
	ADD CONSTRAINT profile_fk FOREIGN KEY(profile_id)
	REFERENCES t_profile(id);

ALTER TABLE t_profile_social_media
	ADD CONSTRAINT social_media_fk FOREIGN KEY(social_media_id)
	REFERENCES t_social_media(id);

ALTER TABLE t_profile_social_media
	ADD CONSTRAINT profile_social_media_bk UNIQUE(profile_id, social_media_id);

CREATE TABLE t_user(
	id varchar(36),
	email varchar(30) NOT NULL,
	pass text NOT NULL,
	verification_code varchar(6),
	role_id varchar(36) NOT NULL,
	profile_id varchar(36) NOT NULL,
	created_by varchar(36) NOT NULL,
	created_at timestamp NOT NULL,
	updated_by varchar(36),
	updated_at timestamp,
	ver int NOT NULL,
	is_active bool NOT NULL
);

ALTER TABLE t_user
	ADD CONSTRAINT user_pk PRIMARY KEY(id);

ALTER TABLE t_user
	ADD CONSTRAINT user_bk UNIQUE(email);

ALTER TABLE t_user
	ADD CONSTRAINT role_fk FOREIGN KEY(role_id)
	REFERENCES t_role(id);

ALTER TABLE t_user
	ADD CONSTRAINT profile_fk FOREIGN KEY(profile_id)
	REFERENCES t_profile(id);


CREATE TABLE t_verification(
	id varchar(36),
	verification_code varchar(10) NOT NULL,
	email varchar(30) NOT NULL,
	expired_time timestamp,
	created_by varchar(36) NOT NULL,
	created_at timestamp NOT NULL,
	updated_by varchar(36),
	updated_at timestamp,
	ver int NOT NULL,
	is_active boolean NOT NULL 
);

ALTER TABLE t_verification 
	ADD CONSTRAINT verification_pk PRIMARY KEY (id);

ALTER TABLE t_verification 
	ADD CONSTRAINT verfication_bk UNIQUE(verification_code);


CREATE TABLE t_premium(
	id varchar(36),
	premium_code varchar(10) NOT NULL,
	premium_name varchar(30) NOT NULL,
	duration int,
	price decimal(15,0),
	created_by varchar(36) NOT NULL,
	created_at timestamp NOT NULL,
	updated_by varchar(36),
	updated_at timestamp,
	ver int NOT NULL,
	is_active boolean NOT NULL 
);

ALTER TABLE t_premium 
	ADD CONSTRAINT premium_pk PRIMARY KEY (id);

ALTER TABLE t_premium 
	ADD CONSTRAINT premium_bk UNIQUE(premium_code);

CREATE TABLE t_user_premium(
	id varchar(36),
	member_id varchar(36) NOT NULL,
	premium_id varchar(36) NOT NULL,
	expire_date date,
	file_id varchar(36) NOT NULL,
	created_by varchar(36) NOT NULL,
	created_at timestamp NOT NULL,
	updated_by varchar(36),
	updated_at timestamp,
	ver int NOT NULL,
	is_active boolean NOT NULL 
);

ALTER TABLE t_user_premium 
	ADD CONSTRAINT user_premium_pk PRIMARY KEY (id);

ALTER TABLE t_user_premium 
	ADD CONSTRAINT member_fk FOREIGN KEY(member_id)
	REFERENCES t_user(id);

ALTER TABLE t_user_premium
	ADD CONSTRAINT premium_fk FOREIGN KEY(premium_id)
	REFERENCES t_premium(id);


CREATE TABLE t_activity_type(
	id varchar(36),
	activity_type_code varchar(10) NOT NULL,
	activity_type_name varchar(30) NOT NULL,
	created_by varchar(36) NOT NULL,
	created_at timestamp NOT NULL,
	updated_by varchar(36),
	updated_at timestamp,
	ver int NOT NULL,
	is_active boolean NOT NULL 
);

ALTER TABLE t_activity_type 
	ADD CONSTRAINT activity_type_pk PRIMARY KEY (id);
ALTER TABLE t_activity_type 
	ADD CONSTRAINT activity_type_bk UNIQUE(activity_type_code);

CREATE TABLE t_category(
	id varchar(36),
	category_code varchar(10) NOT NULL,
	category_name varchar(30) NOT NULL,
	created_by varchar(36) NOT NULL,
	created_at timestamp NOT NULL,
	updated_by varchar(36),
	updated_at timestamp,
	ver int NOT NULL,
	is_active boolean NOT NULL 
);

ALTER TABLE t_category 
	ADD CONSTRAINT category_pk PRIMARY KEY (id);
ALTER TABLE t_category 
	ADD CONSTRAINT category_bk UNIQUE(category_code);

CREATE TABLE t_activity(
	id varchar(36),
	activity_code varchar(10) NOT NULL,
	activity_type_id varchar(36) NOT NULL,
	member_id varchar(36) NOT NULL,
	category_id varchar(36) NOT NULL,
	file_id varchar(36),
	title varchar(50),
	description text,
	provider varchar(30),
	location_address varchar (30) ,
	time_start time ,
	time_end time,
	price DECIMAL(15,0),
	created_by varchar(36) NOT NULL,
	created_at timestamp NOT NULL,
	updated_by varchar(36),
	updated_at timestamp,
	ver int NOT NULL,
	is_active boolean NOT NULL 
);

ALTER TABLE t_activity 
	ADD CONSTRAINT activity_pk PRIMARY KEY (id);

ALTER TABLE t_activity 
	ADD CONSTRAINT activity_bk UNIQUE (activity_code);

ALTER TABLE t_activity 
	ADD CONSTRAINT activity_type_fk FOREIGN KEY(activity_type_id)
	REFERENCES t_activity_type(id);

ALTER TABLE t_activity 
	ADD CONSTRAINT category_fk FOREIGN KEY(category_id)
	REFERENCES t_category(id);

ALTER TABLE t_activity 
	ADD CONSTRAINT file_fk FOREIGN KEY(file_id)
	REFERENCES t_file(id);

ALTER TABLE t_activity 
	ADD CONSTRAINT member_fk FOREIGN KEY(member_id)
	REFERENCES t_user(id);

CREATE TABLE t_schedule(
	id varchar(36),
	activity_id varchar(36) NOT NULL,
	schedule_date date NOT NULL,
	created_by varchar(36) NOT NULL,
	created_at timestamp NOT NULL,
	updated_by varchar(36),
	updated_at timestamp,
	ver int NOT NULL,
	is_active boolean NOT NULL 
);

ALTER TABLE t_schedule 
	ADD CONSTRAINT schedule_pk PRIMARY KEY (id);
ALTER TABLE t_schedule 
	ADD CONSTRAINT activity_fk FOREIGN KEY(activity_id)
	REFERENCES t_activity(id);

CREATE TABLE t_voucher(
	id varchar(36),
	voucher_code varchar(10) NOT NULL,
	discount_price DECIMAL(15,0) NOT NULL,
	minimum_purchase DECIMAL(15,0),
	expired_date date,
	created_by varchar(36) NOT NULL,
	created_at timestamp NOT NULL,
	updated_by varchar(36),
	updated_at timestamp,
	ver int NOT NULL,
	is_active boolean NOT NULL 
);

ALTER TABLE t_voucher 
	ADD CONSTRAINT voucher_pk PRIMARY KEY (id);

ALTER TABLE t_voucher 
	ADD CONSTRAINT voucher_bk UNIQUE(voucher_code);

CREATE TABLE t_user_voucher(
	id varchar(36),
	member_id varchar(36) NOT NULL,
	voucher_id varchar(36) NOT NULL,
	created_by varchar(36) NOT NULL,
	created_at timestamp NOT NULL,
	updated_by varchar(36),
	updated_at timestamp,
	ver int NOT NULL,
	is_active boolean NOT NULL 
);

ALTER TABLE t_user_voucher 
	ADD CONSTRAINT user_voucher_pk PRIMARY KEY (id);

ALTER TABLE t_user_voucher 
	ADD CONSTRAINT voucher_fk FOREIGN KEY(voucher_id)
	REFERENCES t_voucher(id);

ALTER TABLE t_user_voucher 
	ADD CONSTRAINT member_fk FOREIGN KEY(member_id)
	REFERENCES t_user(id);

ALTER TABLE t_user_voucher 
	ADD CONSTRAINT user_voucher_ck UNIQUE(member_id,voucher_id);


CREATE TABLE t_user_activity(
	id varchar(36),
	member_id varchar(36),
	activity_id varchar(36) NOT NULL,
	file_id varchar(36),
	user_voucher_id varchar(36),
	is_approved boolean,
	total_price DECIMAL(15,0),
	invoice_code varchar(10),
	created_by varchar(36) NOT NULL,
	created_at timestamp NOT NULL,
	updated_by varchar(36),
	updated_at timestamp,
	ver int NOT NULL,
	is_active boolean NOT NULL 
);

ALTER TABLE t_user_activity 
	ADD CONSTRAINT user_activity_pk PRIMARY KEY (id);

ALTER TABLE t_user_activity 
	ADD CONSTRAINT member_fk FOREIGN KEY(member_id)
	REFERENCES t_user(id);

ALTER TABLE t_user_activity 
	ADD CONSTRAINT activity_fk FOREIGN KEY(activity_id)
	REFERENCES t_activity(id);

ALTER TABLE t_user_activity 
	ADD CONSTRAINT file_fk FOREIGN KEY(file_id)
	REFERENCES t_file(id);

ALTER TABLE t_user_activity 
	ADD CONSTRAINT user_voucher_fk FOREIGN KEY(user_voucher_id)
	REFERENCES t_user_voucher(id);


CREATE TABLE t_polling(
	id varchar(36) NOT NULL,
	content text ,
	expired timestamp,
	created_by varchar(36) NOT NULL,
	created_at timestamp NOT NULL,
	updated_by varchar(36),
	updated_at timestamp,
	ver int NOT NULL,
	is_active boolean NOT NULL 
);

ALTER TABLE t_polling 
	ADD CONSTRAINT polling_pk PRIMARY KEY (id);
	
CREATE TABLE t_polling_detail(
	id varchar(36) NOT NULL,
	content text,
	polling_id varchar(36),
	created_by varchar(36) NOT NULL,
	created_at timestamp NOT NULL,
	updated_by varchar(36),
	updated_at timestamp,
	ver int NOT NULL,
	is_active boolean NOT NULL 
);

ALTER TABLE t_polling_detail 
	ADD CONSTRAINT polling_detail_pk PRIMARY KEY (id);
ALTER TABLE t_polling_detail 
	ADD CONSTRAINT polling_fk FOREIGN KEY(polling_id)
	REFERENCES t_polling(id);
	
	
CREATE TABLE t_user_polling(
	id varchar(36) NOT NULL,
	member_id varchar(36) NOT NULL,
	polling_detail_id varchar(36) NOT NULL,
	polling_id varchar(36) NOT NULL,
	created_by varchar(36) NOT NULL,
	created_at timestamp NOT NULL,
	updated_by varchar(36),
	updated_at timestamp,
	ver int NOT NULL,
	is_active boolean NOT NULL 
);

ALTER TABLE t_user_polling 
	ADD CONSTRAINT user_polling_pk PRIMARY KEY (id);
ALTER TABLE t_user_polling 
	ADD CONSTRAINT polling_detail_id FOREIGN KEY(polling_detail_id)
	REFERENCES t_polling_detail(id);
ALTER TABLE t_user_polling 
	ADD CONSTRAINT member_fk FOREIGN KEY(member_id)
	REFERENCES t_user(id);
ALTER TABLE t_user_polling 
	ADD CONSTRAINT polling_fk FOREIGN KEY(polling_id)
	REFERENCES t_polling(id);
ALTER TABLE t_user_polling 
	ADD CONSTRAINT user_polling_ck UNIQUE(member_id,polling_detail_id);



CREATE TABLE t_post(
	id varchar(36),
	post_code varchar(10) NOT NULL,
	title varchar(30) NOT NULL,
	content text NOT NULL,
	is_premium boolean,
	category_id varchar(36) NOT NULL,
	member_id varchar(36),
	polling_id varchar(36),
	created_by varchar(36) NOT NULL,
	created_at timestamp NOT NULL,
	updated_by varchar(36),
	updated_at timestamp,
	ver int NOT NULL,
	is_active boolean NOT NULL 
);

ALTER TABLE t_post 
	ADD CONSTRAINT post_pk PRIMARY KEY (id);
ALTER TABLE t_post 
	ADD CONSTRAINT post_bk UNIQUE(post_code);
ALTER TABLE t_post 
	ADD CONSTRAINT category_fk FOREIGN KEY(category_id)
	REFERENCES t_category(id);
ALTER TABLE t_post 
	ADD CONSTRAINT polling_fk FOREIGN KEY(polling_id)
	REFERENCES t_polling(id);
ALTER TABLE t_post 
	ADD CONSTRAINT member_fk FOREIGN KEY(member_id)
	REFERENCES t_user(id);



CREATE TABLE t_like(
	id varchar(36),
	post_id varchar(36) NOT NULL,
	member_id varchar(36) NOT NULL,
	created_by varchar(36) NOT NULL,
	created_at timestamp NOT NULL,
	updated_by varchar(36),
	updated_at timestamp,
	ver int NOT NULL,
	is_active boolean NOT NULL 
);

ALTER TABLE t_like 
	ADD CONSTRAINT like_pk PRIMARY KEY (id);
ALTER TABLE t_like 
	ADD CONSTRAINT post_fk FOREIGN KEY(post_id)
	REFERENCES t_post(id);
ALTER TABLE t_like 
	ADD CONSTRAINT member_fk FOREIGN KEY(member_id)
	REFERENCES t_user(id);
ALTER TABLE t_like 
	ADD CONSTRAINT like_ck UNIQUE(member_id,post_id);

	
CREATE TABLE t_bookmark(
	id varchar(36),
	post_id varchar(36) NOT NULL,
	member_id varchar(36) NOT NULL,
	created_by varchar(36) NOT NULL,
	created_at timestamp NOT NULL,
	updated_by varchar(36),
	updated_at timestamp,
	ver int NOT NULL,
	is_active boolean NOT NULL 
);

ALTER TABLE t_bookmark 
	ADD CONSTRAINT bookmark_pk PRIMARY KEY (id);
ALTER TABLE t_bookmark 
	ADD CONSTRAINT post_fk FOREIGN KEY(post_id)
	REFERENCES t_post(id);
ALTER TABLE t_bookmark 
	ADD CONSTRAINT member_fk FOREIGN KEY(member_id)
	REFERENCES t_user(id);
ALTER TABLE t_bookmark 
	ADD CONSTRAINT bookmark_ck UNIQUE(member_id,post_id);	
	
	
	
CREATE TABLE t_comment(
	id varchar(36),
	post_id varchar(36) NOT NULL,
	member_id varchar(36) NOT NULL,
	content text NOT NULL,
	created_by varchar(36) NOT NULL,
	created_at timestamp NOT NULL,
	updated_by varchar(36),
	updated_at timestamp,
	ver int NOT NULL,
	is_active boolean NOT NULL 
);

ALTER TABLE t_comment 
	ADD CONSTRAINT comment_pk PRIMARY KEY (id);
ALTER TABLE t_comment 
	ADD CONSTRAINT post_fk FOREIGN KEY(post_id)
	REFERENCES t_post(id);
ALTER TABLE t_comment 
	ADD CONSTRAINT member_fk FOREIGN KEY(member_id)
	REFERENCES t_user(id);

	
CREATE TABLE t_post_file(
	id varchar(36),
	post_id varchar(36) NOT NULL,
	file_id varchar(36) NOT NULL,
	created_by varchar(36) NOT NULL,
	created_at timestamp NOT NULL,
	updated_by varchar(36),
	updated_at timestamp,
	ver int NOT NULL,
	is_active boolean NOT NULL 
);

ALTER TABLE t_post_file 
	ADD CONSTRAINT post_file_pk PRIMARY KEY (id);
ALTER TABLE t_post_file 
	ADD CONSTRAINT post_fk FOREIGN KEY(post_id)
	REFERENCES t_post(id);
ALTER TABLE t_post_file 
	ADD CONSTRAINT file_fk FOREIGN KEY(file_id)
	REFERENCES t_file(id);
	
	
	
CREATE TABLE t_article(
	id varchar(36),
	article_code varchar(10) NOT NULL,
	file_id varchar(36) NOT NULL,
	admin_id varchar(36) NOT NULL,
	title varchar(50) NOT NULL,
	content text NOT NULL,
	created_by varchar(36) NOT NULL,
	created_at timestamp NOT NULL,
	updated_by varchar(36),
	updated_at timestamp,
	ver int NOT NULL,
	is_active boolean NOT NULL 
);

ALTER TABLE t_article 
	ADD CONSTRAINT article_pk PRIMARY KEY (id);
ALTER TABLE t_article 
	ADD CONSTRAINT article_bk UNIQUE (article_code);
ALTER TABLE t_article 
	ADD CONSTRAINT file_fk FOREIGN KEY(file_id)
	REFERENCES t_file(id);
ALTER TABLE t_article 
	ADD CONSTRAINT admin_fk FOREIGN KEY(admin_id)
	REFERENCES t_user(id);

CREATE TABLE t_bank_account(
	id varchar(36),
	bank_name varchar(36) NOT NULL,
	file_id varchar(36) ,
	user_id varchar(36) NOT NULL,
	account_number varchar(20) NOT NULL,
	created_by varchar(36) NOT NULL,
	created_at timestamp NOT NULL,
	updated_by varchar(36),
	updated_at timestamp,
	ver int NOT NULL,
	is_active boolean NOT NULL 
);

ALTER TABLE t_bank_account 
	ADD CONSTRAINT bank_account_pk PRIMARY KEY (id);
ALTER TABLE t_bank_account 
	ADD CONSTRAINT bank_account_bk UNIQUE (account_number);
ALTER TABLE t_bank_account 
	ADD CONSTRAINT file_fk FOREIGN KEY(file_id)
	REFERENCES t_file(id);
ALTER TABLE t_bank_account 
	ADD CONSTRAINT user_fk FOREIGN KEY(user_id)
	REFERENCES t_user(id);
