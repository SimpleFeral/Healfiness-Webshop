create type order_status as enum (
    'PENDING',
    'AWAITING_PAYMENT',
    'PROCESSING',
    'ON_HOLD',
    'BACK_ORDER',
    'DISPATCHED',
    'SHIPPED',
    'DELIVERED',
    'COMPLETED',
    'CANCELLED',
    'REFUNDED',
    'RETURNED',
    'FAILED'
);

create table orders (
    create_user character varying(15) not null,
    create_date timestamp with time zone not null default current_timestamp,
    last_modified_user character varying(15),
    last_modified_date timestamp with time zone,
    version bigint not null default 0,
    orders_id serial primary key,
    user_id integer not null references users(users_id),
    order_date timestamp with time zone not null default current_timestamp,
    status order_status not null,
    quantity integer not null,
    total_amount numeric(10, 2) not null
);

create table order_items (
    create_user character varying(15) not null,
    create_date timestamp with time zone not null default current_timestamp,
    last_modified_user character varying(15),
    last_modified_date timestamp with time zone,
    version bigint not null default 0,
    order_items_id serial primary key,
    orders_id integer not null references orders(orders_id) on delete cascade,
    product_id integer not null references products(products_id),
    quantity integer not null,
    order_date timestamp with time zone not null default current_timestamp,
    singlePriceAtOrderTime numeric(10, 2) not null
);