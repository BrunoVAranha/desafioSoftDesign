# Desafio Técnico SoftDesign
## Requisitos:
* Java 17
* Docker (baixar container do banco de dados) 
### Recomendação:
Usar a o Postman (com a collection disponibilizada neste repositório) e uma IDE para executar/debugar a aplicação.

## Iniciando a aplicação:
Após clonar o repositório, vá até a pasta onde se encontra o arquivo 'docker-compose.yml' e use o comando `docker-compose up` no terminal para baixar o container do banco de dados.
Abra o docker desktop e inicie o container.

### Problema de portas com docker compose:
O banco de dados está configurado na porta 3306. Caso esta porta ja esteja sendo usada por outra aplicação, basta trocar a porta no arquivo 'docker-compose.yml' e rodar o comando `docker-compose up` novamente.

Feito isso, já é possível iniciar a aplicação.

## Acessando o banco de dados:
Para acessar as tabelas de pauta e voto, é necessário abrir o terminal do container no Docker Desktop
Use os comandos:
1. `mysql -uroot -proot;`
2. `use desafiodb;`

## Exemplo de procedimento usando postman:
Com a aplicação iniciada, enviar uma requisição no endpoint "/iniciarPauta". Isto começa a contagem de tempo (60 segundos por padrão), e retorna o JSON formatado para mobile, indicando que a pauta foi iniciada, e que temos dois botões nesta tela: um para votar sim, e outro para votar não, além de um input para inserir o cpf.

Agora que a pauta está aberta, pode-se enviar uma requisição em um dos dois endpoints ("/votarSim" ou "/votarNao"), com um id no corpo da requisição, que é obrigatório. Os votos são registrados no banco de dados com um id único, e uma chave estrangeira que os associam a uma pauta.
Assim que o tempo da pauta acabar, ela é encerrada, e cadastrada no banco de dados com o número de votos (sim e não), e também seu resultado.
