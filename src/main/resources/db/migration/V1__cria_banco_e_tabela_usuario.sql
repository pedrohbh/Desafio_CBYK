create schema if not exists desafio;

create table desafio.user (
    id bigserial primary key,
    email varchar(100) not null,
    senha varchar(100) not null,
    data_cadastro timestamp not null
);