create table Currency_History (
	id int auto_increment primary key,
	base varchar(10),
	ticker varchar(10),
	time datetime(6),
	value double
);