create table desafio.situacao_conta
(
    id            smallserial primary key,
    nome_situacao varchar(100) not null
);


INSERT INTO desafio.situacao_conta(id, nome_situacao) VALUES(1, 'Pago');
INSERT INTO desafio.situacao_conta(id, nome_situacao) VALUES(2, 'Aberto');
INSERT INTO desafio.situacao_conta(id, nome_situacao) VALUES(3, 'Em atraso');
