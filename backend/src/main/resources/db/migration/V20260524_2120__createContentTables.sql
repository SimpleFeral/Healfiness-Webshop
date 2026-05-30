create table categories
(
    create_user character varying(15) not null,
    create_date timestamp with time zone not null default current_timestamp,
    last_modified_user character varying(15),
    last_modified_date timestamp with time zone,
    version bigint not null default 0,
    categories_id serial primary key,
    name character varying(255) not null unique,
    description text
);

create table products
(
    create_user character varying(15) not null,
    create_date timestamp with time zone not null default current_timestamp,
    last_modified_user character varying(15),
    last_modified_date timestamp with time zone,
    version bigint not null default 0,
    products_id serial primary key,
    name character varying(255) not null unique,
    description text,
    price numeric(10, 2) not null,
    stocjk_quantity integer not null,
    category_id integer not null,
    constraint fk_category
        foreign key (category_id) references categories (categories_id)
);