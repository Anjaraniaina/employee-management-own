create table if not exists "phone_number"
(
    id                varchar
        constraint phone_number_pk primary key,
    number               varchar                  not null
        constraint phone_number_ref_unique unique,
    employee_id references "employee"(id)
);
