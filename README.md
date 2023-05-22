# Desafio Técnico SoftDesign
## Requisitos:
* Java 17
* Docker (baixar container do banco de dados) 
## Iniciando a aplicação:
Após clonar o repositório, vá até a pasta onde se encontra o arquivo 'docker-compose.yml' e use o comando `docker-compose up` no terminal para baixar o container do banco de dados.
Abra o docker desktop e inicie o container:
![image](https://github.com/BrunoVAranha/desafioSoftDesign/assets/49883183/2cf6f647-c3e9-435f-bbe2-5f94a3a1bd3b)

### Problema de portas com docker compose:
O banco de dados está configurado na porta 3306. Caso esta porta ja esteja sendo usada por outra aplicação, basta trocar a porta no arquivo 'docker-compose.yml' e rodar o comando `docker-compose up` novamente.

Feito isso, já é possível abrir o projeto em uma IDE e iniciar a aplicação.

## Acessando o banco de dados:
Para acessar as tabelas de pauta e voto, é necessário abrir o terminal do container no Docker Desktop
![image](https://github.com/BrunoVAranha/desafioSoftDesign/assets/49883183/4212a344-ffcd-43f0-bbd6-2511156323d1)

Use os comandos:
1. `mysql -uroot -proot;`
2. `use desafiodb;`
