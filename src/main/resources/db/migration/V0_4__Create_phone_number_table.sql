create table if not exists "phone_number"
(
    id                bigint
        constraint phone_number_pk primary key,
    country_code              varchar,
    number varchar(10),
    CONSTRAINT uk_country_code_number UNIQUE (country_code, number)
);
