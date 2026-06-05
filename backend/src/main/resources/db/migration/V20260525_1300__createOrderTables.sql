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
    user_id bigint not null references users(users_id),
    order_date timestamp with time zone not null default current_timestamp,
    order_status order_status not null,
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
    order_id bigint not null references orders(orders_id) on delete cascade,
    product_id bigint not null references products(products_id),
    quantity bigint not null,
    order_date timestamp with time zone not null default current_timestamp,
    single_price_at_order_time numeric(10, 2) not null
);