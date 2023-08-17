create table if not exists "employee"
(
    id                bigint
        constraint employee_pk primary key,
    first_name              varchar                  not null,
    last_name              varchar                  not null,
    birth_date              date,
    image                   text,
    address                 varchar,
    sex                     varchar,
    email_pro               varchar,
    email_perso             varchar,
    phone_number             varchar
        constraint phone_number_unique unique,
    cin_number    varchar,
    cin_issue_date    date,
    cin_delivrance_location    varchar,
    children                int,
    function                varchar,
    hiring_date date default now(),
    departure_date date,
    cnaps_number            varchar,
    socio_pro_category      varchar,
    matricule               varchar                  not null
        constraint employee_ref_unique unique
);
