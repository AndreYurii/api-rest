# api-rest

# Documentação Backend

## Teste

Deverá ser passado em Postman dois parametros na Hearders!

KEY: Content-Type
VALUE: Aplication/Json

Na aba `Authorization -> Basic Auth` os seguintes parametros!

  Username: andreyuri 
### ###
  Password: 123

## URL´s das API´s

###  `/cliente`

url utilizada para buscar os dados de uma entidade.
retorna um HTTPSTATUS 200.

## `cliente/{id}`

url utilizada para deletar uma entidade utilizando como parametro o Identificador da Entidade.
retorna um HTTPSTATUS 200.

## `/cliente`

url utilizada para inserir os dados de uma entidade.
retorna um HTTPSTATUS 201.

## `/cliente`

url utilizada para atualizar os dados de uma entidade.
retorna um HTTPSTATUS 201.

## `cliente/{id}`

url utilizada para buscar os dados de uma entidade utilizando como parametro o Identificador da Entidade.
retorna um HTTPSTATUS 200.

### ###

# Documentação Front-end

## Teste

## Exemplo de dados válidos para cadastrar um cliente

{
  "cpf": "Utilizar um CPF válido",
  "dataDeNascimento": "dd/MM/yyyy",
  "email": "teste@email.com",
  "nacionalidade": "brasileira",
  "naturalidade": "salvador",
  "nome": "andre yuri",
  "sexo": "Masculino"
}

