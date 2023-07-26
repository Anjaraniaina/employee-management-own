create table if not exists "entreprise"
(
    id                bigint
        constraint entreprise_pk primary key,
    name              varchar                  not null,
    description              varchar                  not null,
    motto              varchar                  not null,
    address              varchar                  not null,
    email              varchar                  not null,
    fiscal_identity_id bigint references   "fiscal_identity"(id),
    logo               varchar                  not null
);
