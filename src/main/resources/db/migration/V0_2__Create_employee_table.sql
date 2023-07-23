create table if not exists "employee"
(
    id                bigint
        constraint employee_pk primary key,
    firstName              varchar                  not null,
    lastName              varchar                  not null,
    birth_date              date,
    image                   text,
    address                 varchar,
    sex                     varchar,
    email_pro               varchar,
    email_perso             varchar,
    cin_number              references "cin"(number),
    children                int,
    function                varchar,
    hiring_date date default now(),
    departure_date date,
    cnaps_number            varchar,
    socio_pro_category      varchar,
    matricule               varchar                  not null
        constraint employee_ref_unique unique
);
