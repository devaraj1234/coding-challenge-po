create schema shopapi;

drop table if exists users;
create table shopapi.users(
	user_id serial primary key,
	user_role varchar
	);

drop table if exists shopapi.login_details; 
create table shopapi.login_details(
	login_id serial primary key,
	login_user varchar(50) not null unique,
	login_password varchar(50),
	user_role_id integer,
	constraint user_role_fk foreign key (user_role_id) references shopapi.users(user_id) on delete cascade
	);
	
drop table if exists shopapi.employees;
create table shopapi.employees(
	employee_id serial primary key,
	employee_fname varchar(50) not null,
	employee_lname varchar(50),
	employee_email varchar(50) unique,
	employee_position varchar(50),
	employee_login_id integer,
	constraint employee_login_fk foreign key (employee_login_id) references shopapi.login_details(login_id) on delete cascade
	);

drop table if exists shopapi.customers;
create table shopapi.customers(
	customer_id serial primary key,
	customer_fname varchar(50) not null,
	customer_lname varchar(50),
	customer_email varchar (50) unique,
	customer_address varchar,
	customer_phone integer,
	customer_ssn integer,
	customer_login_id integer,
	constraint customer_login_fk foreign key (customer_login_id) references shopapi.login_details(login_id) on delete cascade
	);

drop table if exists shopapi.product;
create table shopapi.product(
	product_id serial primary key,
	product_name varchar(50),
	product_description varchar,
	availiable_quantity integer,
	expected_price_per_unit numeric(15, 2)
	);

drop table if exists shopapi.product_owner;
create table shopapi.product_owner(
	serial_no serial primary key,
	order_no integer unique,
	product_owned integer,
	product_owner integer,  
	owned_quantity integer,
	product_owned_date date,
	product_owned_status varchar,
	constraint product_owner_fk foreign key (product_owner) references shopapi.customers(customer_id) on delete cascade,
	constraint product_fk foreign key (product_owned) references shopapi.product(product_id) on delete cascade
	);

drop table if exists shopapi.account_collection;
create table shopapi.account_collection(
	collection_id serial primary key,
	product_order_no integer,	
	offered_price_per_unit NUMERIC(15,2), 
	total_price NUMERIC(15,2),
	payment_made NUMERIC(15,2),
	remaining_payment NUMERIC(15,2),
	payment_date date,
	constraint porduct_owned_collection_fk foreign key (product_order_no) references shopapi.product_owner(order_no) on delete cascade
	);

drop table if exists shopapi.offer_made;
create table shopapi.offer_made(
	offer_no serial primary key,
	offer_product integer,
	product_owner integer,
	offered_quantity integer,
	product_offer_date date,
	offered_price_per_unit NUMERIC(15,2),
	payment_made NUMERIC(15,2),
	product_offer_status varchar,
	constraint offer_made_fk foreign key(offer_product) references shopapi.product(product_id) on delete cascade
	);