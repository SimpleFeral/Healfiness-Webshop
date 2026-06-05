create table iso_country_codes (
    create_user character varying(15) not null,
    create_date timestamp with time zone not null default current_timestamp,
    last_modified_user character varying(15),
    last_modified_date timestamp with time zone,
    version bigint not null default 0,
    iso_country_codes_id serial primary key,
    iso_country_code char(3) not null,
    country_name character varying(255) not null
);

create table cities (
    create_user character varying(15) not null,
    create_date timestamp with time zone not null default current_timestamp,
    last_modified_user character varying(15),
    last_modified_date timestamp with time zone,
    version bigint not null default 0,
    cities_id serial primary key,
    short_name char(3) not null,
    name character varying(255) not null,
    iso_country_code_id bigint references iso_country_codes(iso_country_codes_id) not null
);

create type address_types as enum (
    'HOME',
    'PICK_UP',
    'DELIVERY',
    'INVOICE'
);

create table addresses (
    create_user character varying(15) not null,
    create_date timestamp with time zone not null default current_timestamp,
    last_modified_user character varying(15),
    last_modified_date timestamp with time zone,
    version bigint not null default 0,
    addresses_id serial primary key,
    street character varying(255) not null,
    house_number character varying(255) not null,
    postal_code character varying(255) not null,
    suffix text,
    city_id bigint references cities(cities_id) not null,
    address_type address_types not null,
    user_id bigint references users(users_id) not null
);