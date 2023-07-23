create table if not exists "cin"
(
    number                varchar
        constraint cin_pk primary key,
    issue_date              date                  not null,
    delivrance_location               varchar                  not null
);
