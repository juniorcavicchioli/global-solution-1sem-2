# API core

Aqui se encontra a API REST do nosso projeto `Ifeed`.

Para todas as requisições exceto [log in](#log-in) e [sign up](#sign-up) é necessário informar um token obtido quando logado no header.
A princípio, o token levado em conta é o de usuário.

## Diagramas

**Diagrama de classes**
Tem uma classe faltando, a [`br.com.fiap.global.core.config.DataBaseSeeder`](./core/src/main/java/br/com/fiap/global/core/config/DataBaseSeeder.java). O motivo de não estar aí é que não tem utilidade nenhuma além de popular o banco para testes.
![Diagrama de classes](../diagramas/diagrama-de-classes.jpg)

**Modelo físico do banco de dados**
![Modelo Relacional do banco de dados](../diagramas/modelo-relacional.png)

## Documentação

Atualmente não há DTO para controle do retorno, então o retorno
atual de um objeto Usuario está assim:
```json
{
	"id": 1,
	"nome": "Fulano",
	"email": "exemplo@exemplo.com",
	"senha": "$2a$10$Js2UaHfVV67wV83hGQ6raOGWp3rPR12/t6PUSQMCNQe1GbiTp2QeS",
	"telefone": "123456789",
	"enabled": true,
	"password": "$2a$10$Js2UaHfVV67wV83hGQ6raOGWp3rPR12/t6PUSQMCNQe1GbiTp2QeS",
	"username": "exemplo@exemplo.com",
	"accountNonLocked": true,
	"authorities": [
		{
			"authority": "ROLE_USUARIO"
		}
	],
	"credentialsNonExpired": true,
	"accountNonExpired": true,
	"_links": {
		"self": {
			"href": "http://localhost:8080/api/usuario/1"
		},
		"delete": {
			"href": "http://localhost:8080/api/usuario/1"
		},
		"all": {
			"href": "http://localhost:8080/api/usuario"
		}
	}
}
```

Os retornos de lista possuem, além do objeto, uma paginação.
```json
{
	"_embedded": {
		"entityModelList": [
          { /*objeto*/ },
          { /*objeto*/ },
          { /*objeto*/ }
        ]
	},
	"_links": {
		"self": {
			"href": "http://localhost:8080/api/usuario?page=0&size=5"
		}
	},
	"page": {
		"size": 5,
		"totalElements": 1,
		"totalPages": 1,
		"number": 0
	}
}

```
## Endpoints
- Usuario
    - [sign up](#sign-up)
    - [show](#show-usuario)
    - [index](#index-usuario)
    - [update](#update-usuario)
    - [destroy](#destroy-usuario)
    - [log in](#log-in)
- Instituicao
    - [create](#create-instituicao)
    - [show](#show-instituicao)
    - [index](#index-instituicao)
    - [update](#update-instituicao)
    - [destroy](#destroy-instituicao)
- Arrecadacao
    - [create](#create-arrecadacao)
    - [show](#show-arrecadacao)
    - [index](#index-arrecadacao)
    - [update](#update-arrecadacao)
    - [destroy](#destroy-arrecadacao)


### Sign up

`POST`/api/usuario/registrar

*Campos de requisição*

| campo    | tipo  | obrigatório | descrição                                      |
|----------|-------|:-----------:|------------------------------------------------|
| email    | texto |     sim     | email da conta                                 |
| senha    | texto |     sim     | senha da conta                                 |
| nome     | texto |     sim     | nome do usuário                                |
| id       | longo |     ---     | Gerado automaticamente quando a conta é criada |
| telefone | texto |     sim     | telefone do usuario                            |

*Exemplo de requisição*
```json
{
  "email": "exemplo@exemplo.com",
  "senha": "12345678",
  "nome": "Fulano",
  "telefone": "123456789"
}
```

*Exemplos de respostas*

| código | descrição                      |
|--------|--------------------------------|
| 201    | Usuário cadastrado com sucesso |
| 400    | Dados inválidos                |

### Show usuario

`GET`/api/usuario/{usuarioId}

*Campos de requisição*

| campo     | tipo  | obrigatório | descrição     |
|-----------|-------|:-----------:|---------------|
| usuarioId | longo |     sim     | id do usuario |

*Exemplo de requisição*
```
    /api/usuario/1
```
*Exemplo de resposta*
```json
{
  "id": 1,
  "nome": "Fulano",
  "email": "exemplo@exemplo.com",
  "senha": "$2a$10$Js2UaHfVV67wV83hGQ6raOGWp3rPR12/t6PUSQMCNQe1GbiTp2QeS",
  "telefone": "123456789"
}
```

*Resposta*

| código | descrição                                |
|--------|------------------------------------------|
| 200    | Os dados foram retornados                |
| 404    | Nao foi encontrada uma conta com esse ID |

### Index usuario

`GET` /api/usuario

*Exemplo de resposta*
```json
[
  {
      "email": "exemplo@exemplo.com",
      "senha": "123456789",
      "nome": "Fulano",
      "id": 1,
      "telefone": "123456789"
  },
  {
      "email": "outlook@outlook.com",
      "senha": "987654321",
      "nome": "Ciclano",
      "id": 2,
      "telefone": "123456789"
  }
]
```

### Update usuario

`PUT`/api/usuario/{id}

*Campos de requisição*

| campo    | tipo  | obrigatório | descrição                                        |
|----------|-------|:-----------:|--------------------------------------------------|
| email    | texto |     sim     | email da conta                                   |
| senha    | texto |     sim     | senha da conta                                   |
| nome     | texto |     sim     | nome do usuário                                  |
| id       | longo |     sim     | ID do usuário. É declarado no path da requisição |
| telefone | texto |     sim     | telefone do usuario                              |

*Exemplo de requisição*

```json
{
  "email": "exemplo@exemplo.com",
  "senha": "123456789",
  "nome": "Fulano",
  "telefone": "12341234"
}
```
*Resposta*

| código | descrição                 |
|--------|---------------------------|
| 200    | Conta editada com sucesso |
| 404    | Conta não encontrada      |

### Destroy usuario

`DELETE` /api/usuario/{id}

*Campos de requisição*

| campo | tipo  | obrigatório | descrição                         |
|-------|-------|:-----------:|-----------------------------------|
| id    | longo |     sim     | ID da conta a ser apagada no path |

*Exemplo de requisição*
```
  /api/conta/1
```

*Resposta*

| código | descrição            |
|--------|----------------------|
| 204    | conta apagada        |
| 401    | conta não encontrada |

### Log in

`POST` /api/usuario/login

*Campos de requisição*

| campo | tipo  | obrigatório | descrição      |
|-------|-------|:-----------:|----------------|
| email | texto |     sim     | email da conta |
| senha | texto |     sim     | senha da conta |

*Exemplo de requisição*
```json
{
    "email": "exemplo@exemplo.com",
    "senha": 123456789
}
```

*Exemplo de resposta*

```json
{
	"token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJleGVtcGxvQGV4ZW1wbG8uY29tIiwiaXNzIjoiZ2xvYmFsIiwiZXhwIjoxNjg2MDAxMTAzfQ.i_VDZZ-qe4c-ozmgxTJqjRoh80PhffuWHZeTpwS35Co",
	"type": "JWT",
	"prefix": "Bearer"
}
```
*Resposta*

| código | descrição                              |
|--------|----------------------------------------|
| 200    | Dados de login encontrados e validados | 
| 400    | Dados inválidos                        |
| 401    | Conta não encontrada                   |

### Create instituicao

`POST`/api/instituicao

*Campos de requisição*

| campo     | tipo  | obrigatório | descrição                                            |
|-----------|-------|:-----------:|------------------------------------------------------|
| id        | longo |     ---     | Gerado automaticamente quando a instituição é criada |
| nome      | texto |     sim     | nome da instituição                                  |
| descricao | texto |     sim     | descrição da instituição                             |
| endereco  | texto |     sim     | endereço da instituição                              |
| contato   | texto |     sim     | contato da instituição                               |

*Exemplo de requisição*
```json
{
  "nome": "Exemplo de Instituição",
  "descricao": "Uma descrição breve da instituição.",
  "endereco": "Rua Exemplo, 123",
  "contato": "contato@instituicao.com"
}
```

*Exemplos de respostas*

| código | descrição                      |
|--------|--------------------------------|
| 201    | Instituição criada com sucesso |
| 400    | Dados inválidos                |

### Show instituicao

`GET`/api/instituicao/{id}

*Campos de requisição*

| campo | tipo  | obrigatório | descrição         |
|-------|-------|:-----------:|-------------------|
| id    | longo |     sim     | id da instituição |

*Exemplo de requisição*
```
    /api/instituicao/1
```
*Exemplo de resposta*
```json
{
  "id": 123456789,
  "nome": "Exemplo de Instituição",
  "descricao": "Uma descrição breve da instituição.",
  "endereco": "Rua Exemplo, 123",
  "contato": "contato@instituicao.com"
}
```

*Resposta*

| código | descrição                                      |
|--------|------------------------------------------------|
| 200    | Os dados foram retornados                      |
| 404    | Nao foi encontrada uma instituição com esse ID |

### Index instituicao

`GET` /api/instituicao

*Exemplo de resposta*
```json
[
  {
    "id": 1,
    "nome": "Exemplo de Instituição",
    "descricao": "Uma descrição breve da instituição.",
    "endereco": "Rua Exemplo, 123",
    "contato": "contato@instituicao.com"
  }
,
  {
    "id": 2,
    "nome": "Instituto de Educação ABC",
    "descricao": "Uma instituição dedicada ao acesso à educação de qualidade.",
    "endereco": "Avenida Principal, 456",
    "contato": "contato@educacaoabc.com"
  }
]
```

### Update instituicao

`PUT`/api/instituicao/{id}

*Campos de requisição*

| campo     | tipo  | obrigatório | descrição                     |
|-----------|-------|:-----------:|-------------------------------|
| id        | longo |     ---     | Passado no path da requisição |
| nome      | texto |     sim     | nome da instituição           |
| descricao | texto |     sim     | descrição da instituição      |
| endereco  | texto |     sim     | endereço da instituição       |
| contato   | texto |     sim     | contato da instituição        |

*Exemplo de requisição*
```json
{
  "nome": "Exemplo de Instituição",
  "descricao": "Uma descrição breve da instituição.",
  "endereco": "Rua Exemplo, 123",
  "contato": "contato@instituicao.com"
}
```
*Resposta*

| código | descrição                 |
|--------|---------------------------|
| 200    | Conta editada com sucesso |
| 404    | Conta não encontrada      |

### Destroy instituicao

`DELETE` /api/instituicao/{id}

*Campos de requisição*

| campo | tipo  | obrigatório | descrição                               |
|-------|-------|:-----------:|-----------------------------------------|
| id    | longo |     sim     | ID da instituicao a ser apagada no path |

*Exemplo de requisição*
```
  /api/instituicao/1
```

*Resposta*

| código | descrição                  |
|--------|----------------------------|
| 204    | instituicao apagada        |
| 401    | instituicao não encontrada |


### Create arrecadacao

`POST`/api/arrecadacao

*Campos de requisição*

| Campo         | Tipo            | Obrigatório | Descrição                                  |
|---------------|-----------------|:-----------:|--------------------------------------------|
| id            | longo           |     sim     | ID de arrecadação (gerado automaticamente) |
| valor         | ponto flutuante |     sim     | Valor da arrecadação                       |
| dtArrecadacao | data            |     sim     | Data da arrecadação                        |
| tipoPagamento | texto           |     sim     | Tipo de pagamento                          |
| usuario       | objeto          |     sim     | ID do Usuário associado à arrecadação      |
| instituicao   | objeto          |     sim     | ID da Instituição associada à arrecadação  |

*Exemplo de requisição*
```json
{
  "valor": 100.50,
  "dtArrecadacao": "2023-06-05",
  "tipoPagamento": "Cartão de Crédito",
  "usuario": {
    "id": 1
  },
  "instituicao": {
    "id": 1
  }
}
```

*Exemplos de respostas*

| código | descrição                      |
|--------|--------------------------------|
| 201    | Arrecadação criada com sucesso |
| 400    | Dados inválidos                |

### Show arrecadacao

`GET`/api/arrecadação/{id}

*Campos de requisição*

| campo | tipo  | obrigatório | descrição         |
|-------|-------|:-----------:|-------------------|
| id    | longo |     sim     | id da arrecadação |

*Exemplo de requisição*
```
    /api/arrecadacao/1
```
*Exemplo de resposta*
```json
{
  "id": 1,
  "valor": 150.75,
  "dtArrecadacao": "2023-06-05",
  "tipoPagamento": "boleto"
}
```

*Resposta*

| código | descrição                                      |
|--------|------------------------------------------------|
| 200    | Os dados foram retornados                      |
| 404    | Nao foi encontrada uma arrecadação com esse ID |

### Index arrecadacao

`GET` /api/arrecadacao

*Exemplo de resposta*
```json
[
  {
    "id": 1,
    "valor": 100.50,
    "dtArrecadacao": "2023-06-05",
    "tipoPagamento": "Cartão de Crédito",
    "usuario": {
      "id": 1,
      "email": "exemplo@exemplo.com",
      "senha": "12345678",
      "nome": "Fulano",
      "telefone": "123456789"
    },
    "instituicao": {
      "id": 1,
      "nome": "Exemplo de Instituição",
      "descricao": "Uma descrição breve da instituição.",
      "endereco": "Rua Exemplo, 123",
      "contato": "contato@instituicao.com"
    }
  }
,
  {
    "id": 2,
    "valor": 250.75,
    "dtArrecadacao": "2023-08-10",
    "tipoPagamento": "Boleto Bancário",
    "usuario": {
      "id": 3,
      "email": "usuario@exemplo.com",
      "senha": "senha123",
      "nome": "Usuário Exemplo",
      "telefone": "987654321"
    },
    "instituicao": {
      "id": 4,
      "nome": "Instituição ABC",
      "descricao": "Uma descrição detalhada da instituição ABC.",
      "endereco": "Avenida Principal, 789",
      "contato": "contato@instituicaoabc.com"
    }
  }


]
```

### Update arrecadacao

`PUT`/api/arrecadacao/{id}

*Campos de requisição*

| Campo         | Tipo            | Obrigatório | Descrição                                  |
|---------------|-----------------|:-----------:|--------------------------------------------|
| id            | longo           |     sim     | ID de arrecadação (gerado automaticamente) |
| valor         | ponto flutuante |     sim     | Valor da arrecadação                       |
| dtArrecadacao | data            |     sim     | Data da arrecadação                        |
| tipoPagamento | texto           |     sim     | Tipo de pagamento                          |
| usuario       | objeto          |     sim     | ID do Usuário associado à arrecadação      |
| instituicao   | objeto          |     sim     | ID da Instituição associada à arrecadação  |

*Exemplo de requisição*
```json
{
  "valor": 304.50,
  "dtArrecadacao": "2023-07-05",
  "tipoPagamento": "Boleto",
  "usuario": {
    "id": 1
  },
  "instituicao": {
    "id": 1
  }
}
```
*Resposta*

| código | descrição                       |
|--------|---------------------------------|
| 200    | Arrecadacao editada com sucesso |
| 404    | Arrecadacao não encontrada      |

### Destroy arrecadacao

`DELETE` /api/arrecadacao/{id}

*Campos de requisição*

| campo | tipo  | obrigatório | descrição                               |
|-------|-------|:-----------:|-----------------------------------------|
| id    | longo |     sim     | ID da arrecadacao a ser apagada no path |

*Exemplo de requisição*
```
  /api/arrecadacao/1
```

*Resposta*

| código | descrição                  |
|--------|----------------------------|
| 204    | arrecadacao apagada        |
| 401    | arrecadacao não encontrada |
