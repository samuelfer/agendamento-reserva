create table reserva(

    id bigint not null,
    imovel_id int8 not null,
    data_hora_reserva timestamp not null,
    area_comum int8 not null,

    primary key(id)

);

CREATE SEQUENCE reserva_sequence START 1;
ALTER TABLE public.reserva ADD CONSTRAINT reserva_id_fk FOREIGN KEY (imovel_id) REFERENCES public.imovel(id);
