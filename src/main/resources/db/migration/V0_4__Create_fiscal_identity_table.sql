create table if not exists "fiscal_identity"
(
    id                bigint
        constraint fiscal_identity_pk primary key,
    nif              varchar                  not null,
    stat               varchar                  not null,
    rcs               varchar                  not null,
);
