create table area_comum(

    id bigint not null,
    cod_area_comum varchar(100) not null,
    descricao_area_comum varchar(255),
    horario_em_minutos_minimo_antecedencia int8 not null,
    quantidade_reserva_permitida_por_semana int8 not null,
    quantidade_reserva_permitida_por_mes int8 not null,

    primary key(id)

);
ALTER TABLE area_comum ADD CONSTRAINT area_comum_cod_area_comum_unique UNIQUE (cod_area_comum);
CREATE SEQUENCE area_comum_sequence START 1;
