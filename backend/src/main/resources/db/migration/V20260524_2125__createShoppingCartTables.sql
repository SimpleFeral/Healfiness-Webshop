create table shopping_carts
(
    create_user character varying(15) not null,
    create_date timestamp with time zone not null default current_timestamp,
    last_modified_user character varying(15),
    last_modified_date timestamp with time zone,
    version bigint not null default 0,
    shopping_carts_id serial primary key,
    price numeric(10, 2) not null,
    user_id bigint not null,
    constraint fk_user
        foreign key (user_id) references users (users_id)
);

create table cart_items
(
    create_user character varying(15) not null,
    create_date timestamp with time zone not null default current_timestamp,
    last_modified_user character varying(15),
    last_modified_date timestamp with time zone,
    version bigint not null default 0,
    cart_items_id serial primary key,
    quantity integer not null,
    shopping_cart_id bigint not null,
    product_id bigint not null,
    constraint fk_shopping_cart
        foreign key (shopping_cart_id) references shopping_carts (shopping_carts_id),
    constraint fk_product
        foreign key (product_id) references products (products_id)
);