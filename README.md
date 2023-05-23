# Desafio Técnico SoftDesign
## Requisitos:
* Java 17
* Docker
### Recomendação:
Usar a o Postman com a collection disponibilizada nas RELEASES deste repositório.

## Iniciando a aplicação:
Após clonar o repositório, vá até a pasta onde se encontra o arquivo 'docker-compose.yml' e use o comando `docker-compose up mysqldb` no terminal para iniciar o container do banco de dados.

Espere o banco de dados estar inicializado, e em outro terminal, na mesma pasta, use o comando `docker-compose up desafioApp`para iniciar o container da aplicação.
Feito isso, a aplicação estará pronta para receber as requisições nos seus endpoints.
### Obs:
É possível utilizar o comando `docker-compose up` para inicializar os dois containers de uma vez, mas não é garantido que o banco de dados vai inicializar completamente antes da aplicação, impedindo que a aplicação inicie corretamente. Tentei diversas configurações, mas nenhuma funcionou 100% das vezes.


### Recomendação:
Usar a o Docker Desktop para observar os containers iniciando, e também utilizar os terminais.

#### Problema de portas com docker compose:
O banco de dados está configurado na porta 3306. Caso esta porta ja esteja sendo usada por outra aplicação, basta trocar a porta no arquivo 'docker-compose.yml' e rodar o comando `docker-compose up` novamente.

## Exemplo de procedimento usando postman:
Com a aplicação iniciada, enviar uma requisição (POST sem nenhum body) no endpoint "/iniciarPauta". Isto começa a contagem de tempo (60 segundos por padrão), e retorna o JSON formatado para mobile, indicando que a pauta foi iniciada, e que temos dois botões nesta tela: um para votar sim, e outro para votar não, além de um input para inserir o cpf.

exemplo json do corpo da request para votar: 
`{
    "idVoto" : "126567"
}`

Agora que a pauta está aberta, pode-se enviar uma requisição em um dos dois endpoints ("/votarSim" ou "/votarNao"), com um id no corpo da requisição, que é obrigatório. Os votos são registrados no banco de dados com um id único, e uma chave estrangeira que os associam a uma pauta.
Assim que o tempo da pauta acabar, ela é encerrada, e cadastrada no banco de dados com o número de votos (sim e não), e também seu resultado.

## Acessando o banco de dados:
Para acessar as tabelas de pauta e voto, é necessário abrir o terminal do container no Docker Desktop
Use os comandos:
1. `mysql -uroot -proot;`
2. `use desafiodb;`
3. `select * from pauta;` para ver as pautas cadastradas
3. `select * from voto;` para ver os votos cadastrados
