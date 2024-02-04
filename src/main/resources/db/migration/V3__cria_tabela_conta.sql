create table desafio.conta
(
    id              bigserial primary key,
    data_vencimento date           not null,
    data_pagamento  date           not null,
    valor           numeric(19, 2) not null,
    descricao       varchar        not null,
    situacao        varchar(100)   not null,
    data_cadastro timestamp not null
);

