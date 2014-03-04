1. 搭建开发环境
	1.1 导入开发包
		mysql驱动
		c3p0连接池
		dbutiles开发包
		beanutiles开发包
		jstl开发包
		log4j开发包
	1.2 创建程序包
		com.zhuwei.domain
		com.zhuwei.dao
		com.zhuwei.dao.impl
		com.zhuwei.service
		com.zhuwei.service.impl
		com.zhuwei.web.manager
		com.zhuwei.web.client
		com.zhuwei.utils
		
		webroot\manager : 保存后台相关的jsp
		webroot\client	： 保存前台相关的jsp
		webroot\images	: 保存图片
		
		前台分帧： index.jsp   (head.jsp, body.jsp)
		后台分帧： manager.jsp (head.jsp, body.jsp, left.jsp)
	
	1.3创建库
		create database bookstoreonline;
		
	
	1.4写网站一些常用的工具类
		CharacterEncoding
		HtmlFilter
		JdbcUtils
		DaoFactory
	
2.分类模块
	create table category
	(
		id varchar(40) primary key,
		name varchar(100) not null unique,
		description varchar(255)
	);	
	
3.Book Table
	 
	 create table book
	 (
	 	id varchar(40) primary key,
	 	name varchar(100) not null unique,
	 	author varchar(40) not null,
	 	price double not null,
	 	image varchar(100),
	 	description varchar(255),
	 	category_id varchar(40),
	 	constraint category_id_FK foreign key(category_id) references category(id)
	 );	

4.User Table
	
	create table user
	(
		id varchar(40) primary key,
		username varchar(40) not null unique,
		password varchar(40),
		phone varchar(40) not null,
		address varchar(100) not null,
		email varchar(100) not null
	);
	
5.Order Table
	
	create table orders
	(
		id varchar(40) primary key,
		ordertime datetime not null,
		price double ,
		state boolean,
		user_id varchar(40),
		constraint user_id_FK foreign key(user_id) references user(id)
		
	);
	
6. OrderItem Table

	create table orderitem
	(
		id varchar(40) primary key,
		quantity int,
		price double,
		orders_id varchar(40),
		book_id varchar(40),
		constraint order_id_FK foreign key(orders_id) references orders(id),
		constraint book_id_FK foreign key(book_id) references book(id)
	);

7.Privilege Table
	create table privilege
	(
		id varchar(40) primary key,
		name varchar(40)
	);
	
	create table user_privilege
	(
		user_id varchar(40),
		privilege_id varchar(40),
		primary key(user_id, privilege_id),
		constraint user_id_FK1 foreign key (user_id) references user(id),
		constraint privilege_id_FK foreign key (privilege_id) references privilege(id)	
	);		

8.数据库管理模块

create table mydatabase
(
	id varchar(40) primary key,
	name varchar(40) not null,
	path varchar(40) not null,
	backuptime datetime
);