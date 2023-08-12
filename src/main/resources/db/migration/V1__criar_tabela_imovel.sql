create table imovel(

    id bigint not null,
    numero text not null,
    inadimplente bool not null default false,

    primary key(id)

);

CREATE SEQUENCE imovel_sequence START 1;