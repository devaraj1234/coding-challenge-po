create schema ERS_DB;

create table ers_db.ers_user_roles(
	ers_user_role_id serial primary key ,
	user_role varchar(10)
	);
	
create table ers_db.ers_reimbursement_type(
	reimb_type_id serial primary key,
	reimb_type varchar(10)
	);
	
create table ers_db.ers_reimbursement_status(
	reimb_status_id serial primary key,
	reimb_status varchar(10)
);


create table ers_db.ers_users(
	ers_user_id serial primary key,
	ers_user_name varchar(50) unique not null,
	ers_password varchar(50) not null,
	user_first_name varchar(100) not null,
	user_last_name varchar(100),
	user_email varchar(150) unique not null,
	user_role_id integer,
	CONSTRAINT user_info unique (ers_user_name, user_email),
	constraint user_role_fk foreign key (user_role_id) references ers_db.ers_user_roles(ers_user_role_id) on delete cascade
	);
	
create table ers_db.ers_reimbursement(
	reimb_id serial primary key,
	reimb_amount numeric,
	reimb_submitted timestamp,
	reimb_resolved timestamp,
	reimb_description varchar(250),
	reimb_receipt bytea,
	reimb_author integer,
	reimb_solver integer,
	reimb_status_id integer,
	reimb_type_id integer,
	constraint user_fk_auth foreign key (reimb_author) references ers_db.ers_users(ers_user_id) on delete cascade,
	constraint user_fk_reslvr foreign key (reimb_solver) references ers_db.ers_users(ers_user_id) on delete cascade,
	constraint ers_reimbursment_status_fk foreign key (reimb_status_id) references ers_db.ers_reimbursement_status(reimb_status_id) on delete cascade,
	constraint ers_reimbursment_type_fk foreign key (reimb_type_id) references ers_db.ers_reimbursement_type(reimb_type_id) on delete cascade
	);
	
	
insert into ers_db.ers_user_roles (default, 'manager');
insert into ers_db.ers_user_roles (default, 'employee');

-- for user login
insert into ers_db.ers_users values (default, 'testsuser', 'password', 'testuser', 'testuser', 'testuser@user', 2);

-- for employee/manager login
insert into ers_db.ers_users values (default, 'testemployee', 'password', 'testemployee', 'testemployee', 'testuser@employee', 1);
