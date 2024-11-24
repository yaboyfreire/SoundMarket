use soundmarket;

create table utilizador (
						 user_id int auto_increment,
                         user_name varchar(255) not null,
                         user_gender varchar(255) not null,
                         user_bday date not null,
                         user_username varchar(50) not null,
                         user_email varchar(255) not null,
                         user_country varchar(255) not null,
                         user_passwordhash varchar(255) not null,
                         image blob,
                         primary key (user_id)
);  

create table administrator (
					admin_id int auto_increment,
                    admin_user_id int,
                    primary key (admin_id)
);
				

create table wishlist (
					   wl_id int auto_increment,
                       wl_user_id int,
                       wl_album_id int, 
                       wl_added_date datetime,
                       primary key (wl_id)
);

create table album  (
					 album_id int auto_increment,
                     album_user_id int,
                     album_condition varchar(255),
                     album_format varchar(255),
                     album_title varchar(255),
                     album_artist varchar(255),
                     album_genre varchar(255),
                     album_added_date datetime,
                     primary key (album_id)
);

create table selling (
					 selling_id int auto_increment,
                     selling_user_id int,
                     selling_album_id int,
                     selling_description varchar(255),
                     selling_price decimal,
                     selling_created_at datetime,
                     primary key (selling_id)
);

create table buy (
					buy_id int auto_increment,
                    buy_selling_id int,
                    buy_buyer_id int,
                    buy_price_paid decimal,
                    buy_status_id int,
                    buy_created_at datetime,
                    primary key (buy_id)
);

create table chat (
					chat_id int auto_increment,
                    chat_topic varchar(100),
                    chat_seller_id int,
                    chat_buyer_id int,
                    chat_started_at datetime,
                    primary key (chat_id)
);

create table message (
					msg_id int auto_increment,
                    msg_chat_id int,
                    msg_sender_id int,
                    msg_text text,
                    msg_sent_at datetime,
                    msg_receiver_id int,
                    primary key (msg_id)
);

create table state (
					state_id int auto_increment,
                    state_buy_id int,
                    state_type varchar(100),
                    state_created_at datetime,
                    primary key (state_id) 
);

create table buy_state (
						bs_id int auto_increment,
                        bs_buy_id int,
                        bs_state_id int,
                        bs_created_at datetime,
                        primary key (bs_id)
);

-- Foreign keys

alter table wishlist
add constraint fk_wishlist_user
foreign key (wl_user_id) references utilizador(user_id)
on delete no action on update no action,

add constraint fk_wishlist_album
foreign key (wl_album_id) references album(album_id);

alter table administrator
add constraint fk_admin_user
foreign key (admin_user_id) references utilizador(user_id)
on delete no action on update no action; 

alter table album 
add constraint fk_album_user
foreign key (album_user_id) references utilizador(user_id)
on delete no action on update no action;

alter table selling 
add constraint fk_selling_user
foreign key (selling_user_id) references utilizador(user_id)
on delete no action on update no action,

add constraint fk_selling_album
foreign key (selling_album_id) references album(album_id)
on delete no action on update no action;

alter table buy 
add constraint fk_buy_selling
foreign key (buy_selling_id) references selling(selling_id)
on delete no action on update no action,

add constraint fk_buy_buyer
foreign key (buy_buyer_id) references utilizador(user_id)
on delete no action on update no action;

alter table chat 
add constraint fk_chat_seller
foreign key (chat_seller_id) references utilizador(user_id)
on delete no action on update no action,

add constraint fk_chat_buyer
foreign key (chat_buyer_id) references utilizador(user_id)
on delete no action on update no action;

alter table message 
add constraint fk_message_chat
foreign key (msg_chat_id) references chat(chat_id)
on delete no action on update no action,

add constraint fk_message_sender
foreign key (msg_sender_id) references utilizador(user_id)
on delete no action on update no action,

add constraint fk_message_receiver
foreign key (msg_receiver_id) references utilizador(user_id)
on delete no action on update no action;

alter table state 
add constraint fk_state_buy
foreign key (state_buy_id) references buy(buy_id)
on delete no action on update no action;

alter table buy_state
add constraint fk_buy_state_buy
foreign key (bs_buy_id) references buy(buy_id)
on delete no action on update no action,

add constraint fk_buy_state_state
foreign key (bs_state_id) references state(state_id)
on delete no action on update no action;


