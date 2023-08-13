create table imovel(

    id bigint not null,
    numero text not null,
    inadimplente bool not null default false,

    primary key(id)

);
ALTER TABLE imovel ADD CONSTRAINT imovel_numero_unique UNIQUE (numero);
CREATE SEQUENCE imovel_sequence START 1;