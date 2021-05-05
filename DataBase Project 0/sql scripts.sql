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

drop table if exists shopapi.sales;
create table shopapi.sales(
	serial_no serial primary key,
	order_no integer unique,
	product integer,
	customer integer,  
	sales_quantity integer,
	price_per_unit numeric(15,2),
	sales_date date,
	sales_status varchar,
	constraint customer_fk foreign key (customer) references shopapi.customers(customer_id) on delete cascade,
	constraint product_fk foreign key (product) references shopapi.product(product_id) on delete cascade
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
	constraint porduct_owned_collection_fk foreign key (product_order_no) references shopapi.sales(order_no) on delete cascade
	);

drop table if exists shopapi.offers;
create table shopapi.offers(
	offer_no serial primary key,
	offer_product integer,
	customer integer,
	offered_quantity integer,
	product_offer_date date,
	offered_price_per_unit NUMERIC(15,2),
	payment_made NUMERIC(15,2),
	product_offer_status varchar,
	constraint offer_made_fk foreign key(offer_product) references shopapi.product(product_id) on delete cascade
	);
	
	
select * from shopapi.account_collection 
join shopapi.product_owner on shopapi.product_owner.order_no = shopapi.account_collection.product_order_no 
join shopapi.customers on shopapi.customers.customer_id = shopapi.product_owner.product_owner;

select * from shopapi.product_owner
join shopapi.product on shopapi.product.product_id = shopapi.product_owner.product_owned;

select * from shopapi.account_collection
join shopapi.product_owner on shopapi.product_owner.order_no = shopapi.account_collection.product_order_no 
join shopapi.product on shopapi.product.product_id = shopapi.product_owner.product_owned 
where shopapi.product_owner.product_owner = 1;


insert into shopapi.account_collection (product_order_no, offered_price_per_unit, total_price, payment_made, remaining_payment, payment_date)
select product_order_no, offered_price_per_unit, total_price, 10, (remaining_payment-10), payment_date from shopapi.account_collection 
where product_order_no = 2 order by remaining_payment asc limit 1;

select * from shopapi.account_collection where product_order_no = 2 order by remaining_payment asc limit 1;

insert into shopapi.account_collection values (default, 2, 25, 425, 100, 325, '2021-05-01');


SELECT date_trunc('week', payment_date) as week_of, date_part('week', payment_date) as week_number, sum(payment_made ) as weekly_collection
FROM   shopapi.account_collection 
group by date_trunc('week', payment_date), date_part('week', payment_date);

SELECT sum(payment_made),
     date_part('week', shopapi.account_collection.payment_date) 
FROM shopapi.account_collection;


select Company.company_code, Company.founder, count(DISTINCT(Lead_Manager.lead_manager_code)), count(DISTINCT(Senior_Manager.senior_manager_code)), count(DISTINCT(Manager.manager_code)), count(DISTINCT(Employee.employee_code))
from Employee join Manager on Employee.manager_code = Manager.manager_code
join Senior_Manager on Manager.senior_manager_code = Senior_Manager.senior_manager_code
join Lead_Manager on Senior_Manager.lead_manager_code = Lead_Manager.lead_manager_code
join Company on Company.company_code = Lead_Manager.company_code
group by company_code, founder
order by company_code asc;

begin transaction;
insert into shopapi.login_details values (default, 'durga1', 'password', 2);
insert  into shopapi.employees values (default, 'Durga', 'Paudel', 'durga1.paudel', 'Employee',
(select login_id from shopapi.login_details where login_user = 'durga1' and login_password = 'password'));
commit;
rollback transaction;

select login_id from shopapi.login_details where login_user = 'durga' and login_password = 'password';
insert into shopapi.login_details values(default, 'devrajac', 'password', 1);
delete from shopapi.login_details where login_user = 'durga1';

select * from shopapi.sales s
join shopapi.product p on p.product_id = s.product 
join customers c2 on c2.customer_id = s.customer;

UPDATE shopapi.product SET product_name = 'Wooden Coffe Table', availiable_quantity = 6, product_description = ' table made in 1955',
expected_price_per_unit = 499 WHERE product_id = 4;


insert into shopapi.login_details values (default, 'devachary1', 'password');