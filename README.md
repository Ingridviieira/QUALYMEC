# QUALYMEC  <!-- Listagem dos endpoints -->

## Objetivo do projeto:
diminuir a falta de alimento

## Endpoints 
# Doacao- api/v1/Doacao
 
- [Cadastrar Doacao](#cadastrar-doacao)
- [Atualizar doacao](#Atualizar-doacao)
- [Apagar doacao](#Apagar-doacao)
- [Listar doacao]

# Instituto/api/v1/SolicitarDoacao
- [Cadastrar solicitarDoacao]
- [Apagar solicitarDoacao]
- [Atualizar solicitarDoacao]
- [Listar solicitarDoacao]

<!-- Endereço do recurso -->

`GET`/api/v1/doacao
**Exemplo de Entrada** 
```js
    {
 	"Id": '1',
 	"Nome": 'Diana',
 	"tipoAlimento": 'Arroz',
 	"qtdAlimento": '1OKL',
   }
```
**Códigos da Resposta**

|código|Descrição
|-|-
200 | Dados Retornados com sucesso

--------------------

`POST`/api/v1/refeicao

**Campos da Requisição**
| Campo | Tipo | Obrigatório | Descrição |
|-------|------|:-----------:|-----------|
|Nome   |String|Sim          |O campo não pode estar vazio
|tipoAlimento|String|Sim     |Descrever o tipo de alimento doado
|qtdAlimento |String|Sim     |Texto com a quantidade de alimento a ser doado
||||


# Exemplo de corpo de requisição
```js
    {
 	"Id": '1',
 	"Nome": 'Diana',
 	"tipoAlimento": 'Arroz',
 	"qtdAlimento": '1OKL',
   }
```
-------------------------

`PUT` /api/v1/ doacao/{id}

**Campos da Requisição**
| Campo | Tipo | Obrigatório | Descrição |
|-------|------|:-----------:|-----------|
|Nome   |String|Sim          |O campo não pode estar vazio
|tipoAlimento|String|Sim     |Descrever o tipo de alimento doado
|qtdAlimento |String|Sim     |Texto com a quantidade de alimento a ser doado
||||


# Exemplo de corpo de requisição
```js
    {
 	"Id": '1',
 	"Nome": 'Diana',
 	"tipoAlimento": 'Arroz',
 	"qtdAlimento": '1OKL',
   }
```

|código|Descrição
|-|-
200 | Dados Retornados com sucesso
400 | Dados atualizados
4004| Usuario não encontrado, com o id informado

--------------------------

### Cadastrar doacao
`POST` api/v1/ doacao

**Campos da Requisição**
| Campo | Tipo | Obrigatório | Descrição |
|-------|------|:-----------:|-----------|
|Nome   |String|Sim          |O campo não pode estar vazio
|tipoAlimento|String|Sim     |Descrever o tipo de alimento doado
|qtdAlimento |String|Sim     |Texto com a quantidade de alimento a ser doado
||||

```js
     {
 	"Id": '1',
 	"Nome": 'Diana',
 	"tipoAlimento": 'Arroz',
 	"qtdAlimento": '1OKL',
   }
```

|código|Descrição
|-|-
201 | doacao cadastrada com sucesso
400 | Os campos enviados estão inválidos

-------------------------------------------

### Atualizar doacao
`PUT` api/v1/doacao/{id}

**Campos da Requisição**
| Campo | Tipo | Obrigatório | Descrição |
|-------|------|:-----------:|-----------|
|Nome   |String|Sim          |O campo não pode estar vazio
|tipoAlimento|String|Sim     |Descrever o tipo de alimento doado
|qtdAlimento |String|Sim     |Texto com a quantidade de alimento a ser doado
||||

# Exemplo de corpo de requisição
# neste exemplo os sentimentos  seria o campo de preecncher a motivaçao que sentiu após a doacao

```js
     {
 	"Id": '1',
 	"Nome": 'Diana',
 	"tipoAlimento": 'Arroz',
 	"qtdAlimento": '1OKL',
   }
```
|código|Descrição
|-|-
200 | Refeições atualizadas com sucesso
400 | Os campos enviados são inválidos
404 | Não existe usuário com o id informado

----------------------------------

### Apagar doacao
`DELETE` /api/v1/ doacao/{id}

|código|Descrição
|-|-
204 | Doacao apagada com sucesso
404 | Não existe doacao com o id informado

---------------------------
 