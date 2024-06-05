# Desafio Totvs

## Introdução
Este projeto é a solução resposta para o desafio Totvs Backend, disponível em PDF neste repositório

## Forma de Execução
### Requisitos
Necessário ter:
- Docker
- Maven


### Passos de execução
1 - Clone o projeto para o seu computador;
```
git clone https://github.com/pedrohbh/desafio_totvs.git
```

2 - Dentro da raíz do projeto, execute o "maven clean install" para instalar as dependências do projeto, rodar os testes e compilar;
```
mvn clean install
```

3 - Execute o comando para construir a imagem que será executada dentro de container Docker (não esquecer do "." ponto no final do comando);
```
docker build -t totvs/desafio_totvs .
```

4 - Execute o comando docker compose up para subir o sistema em container mais o banco de Dados PostgreSQL;
```
docker compose up
```

5 - Após finalizado o processo, o sistema estará diponível para uso

## Autenticação
Todas as chamadas REST a API necessita de autenticação. A autenticação é realizada de forma básica, com usuário e senha. Seguem os dados de usuário e senha:

### Usuário
```
user
```
### Senha
```
password
```
