create table desafio.conta
(
    id              bigserial,
    data_vencimento date           not null,
    data_pagamento  date           not null,
    valor           numeric(19, 2) not null,
    descricao       varchar        not null,
    situacao_conta_id        smallint   not null,
    data_cadastro timestamp not null
);
alter table desafio.conta add constraint pk_conta primary key (id);
alter table desafio.conta add constraint fk_conta_situacao_conta FOREIGN KEY (situacao_conta_id) references desafio.situacao_conta(id);