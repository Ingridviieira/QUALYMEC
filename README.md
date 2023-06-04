# QUALYMEC  <!-- Listagem dos endpoints -->
# Integrantes
RM96269 - Bianca Dos Santos Pereira
RM95854 - Eduarda Nicoli Cavalheiro
RM95396 - Ingrid Vieira de Oliveira
RM95749 - João Vitor Santiago de Oliveira Braz
RM95384 - Leonardo Dantas Marques

## Objetivo do projeto:
Diminuir a falta de alimento

#Link do video pitch: https://youtu.be/sDJmt-fhRV8


## Endpoints 
# Doador- api/v1/Doador
 
- [Cadastrar Doador](#cadastrar-doacao)
- [Atualizar Doador](#Atualizar-doacao)
- [Apagar Doador](#Apagar-doacao)
- [Listar Doador]

# AlimentoDoado/api/v1/AlimentoDoado
- [Cadastrar AlimentoDoado]
- [Apagar AlimentoDoado]
- [Atualizar AlimentoDoado]
- [Listar AlimentoDoado]

# Instituto/api/v1/Instituto
- [Cadastrar Instituto]
- [Apagar Instituto]
- [Atualizar Instituto]
- [Listar Instituto]

# SolicitacaoDoacao/api/v1/SolicitacaoDoacao
- [Cadastrar SolicitacaoDoacao]
- [Apagar SolicitacaoDoacao]
- [Atualizar SolicitacaoDoacao]
- [Listar SolicitacaoDoacao]

<!-- Endereço do recurso -->

`GET`/api/v1/doador
**Exemplo de Entrada** 
```js
    {
 	"Id": '1L',
 	"NomeDoador": 'Marcos',
	"endereco":'R.das neves',
	"nomdeDocumento":'rg',
	"nrDocumento":'4634837'
	"alimentoDoado":{
		"Id": '1L',
		"nome": 'ONGAlimentoDoDia',
		"nrRegistro":'182237',
		"endereco":'R.das abelhas'
	}
   }
```
**Códigos da Resposta**

|código|Descrição
|-|-
200 | Dados Retornados com sucesso

--------------------

`POST`/api/v1/doador

**Campos da Requisição**
| Campo | Tipo | Obrigatório | Descrição |
|-------|------|:-----------:|-----------|
|NomeDoador|String|Sim       |O campo não pode estar vazio
|endero|String|Sim     |Descrever o tipo de alimento doado
|nomeDocumneto|String|Sim   |Texto com o nome do doador
|nrDocumento|int|Sim|Numero do documento do doador
||||


# Exemplo de corpo de requisição
```js
    {
 	"Id": '1L',
 	"NomeDoador": 'Marcos',
	"endereco":'R.das neves',
	"nomdeDocumento":'rg',
	"nrDocumento":'4634837'
	"alimentoDoado":{
		"Id": '1L',
		"nome": 'ONGAlimentoDoDia',
		"nrRegistro":'182237',
		"endereco":'R.das abelhas'
	}
   }
```
-------------------------

`PUT` /api/v1/ doador/{id}

**Campos da Requisição**
| Campo | Tipo | Obrigatório | Descrição |
|-------|------|:-----------:|-----------|
|NomeDoador|String|Sim       |O campo não pode estar vazio
|endero|String|Sim     |Descrever o tipo de alimento doado
|nomeDocumneto|String|Sim   |Texto com o nome do doador
|nrDocumento|int|Sim|Numero do documento do doador
||||



# Exemplo de corpo de requisição
```js
    {
 	"Id": '1L',
 	"NomeDoador": 'Marcos',
	"endereco":'R.das neves',
	"nomdeDocumento":'rg',
	"nrDocumento":'4634837'
	"alimentoDoado":{
		"Id": '1L',
		"nome": 'ONGAlimentoDoDia',
		"nrRegistro":'182237',
		"endereco":'R.das abelhas'
	}
   }
```

|código|Descrição
|-|-
200 | Dados Retornados com sucesso
400 | Dados atualizados
4004| Usuario não encontrado, com o id informado

--------------------------

### Cadastrar doador
`POST` api/v1/ doador

**Campos da Requisição**
| Campo | Tipo | Obrigatório | Descrição |
|-------|------|:-----------:|-----------|
|NomeDoador|String|Sim       |O campo não pode estar vazio
|endero|String|Sim     |Descrever o tipo de alimento doado
|nomeDocumneto|String|Sim   |Texto com o nome do doador
|nrDocumento|int|Sim|Numero do documento do doador
||||


```js
    {
 	"Id": '1L',
 	"NomeDoador": 'Marcos',
	"endereco":'R.das neves',
	"nomdeDocumento":'rg',
	"nrDocumento":'4634837'
	"alimentoDoado":{
		"Id": '1L',
		"nome": 'ONGAlimentoDoDia',
		"nrRegistro":'182237',
		"endereco":'R.das abelhas'
	}
   }
```

|código|Descrição
|-|-
201 | doacao cadastrada com sucesso
400 | Os campos enviados estão inválidos

-------------------------------------------

### Atualizar doador
`PUT` api/v1/doador/{id}

**Campos da Requisição**
| Campo | Tipo | Obrigatório | Descrição |
|-------|------|:-----------:|-----------|
|NomeDoador|String|Sim       |O campo não pode estar vazio
|endero|String|Sim     |Descrever o tipo de alimento doado
|nomeDocumneto|String|Sim   |Texto com o nome do doador
|nrDocumento|int|Sim|Numero do documento do doador
||||


# Exemplo de corpo de requisição
# neste exemplo os sentimentos  seria o campo de preecncher a motivaçao que sentiu após a doacao

```js
    {
 	"Id": '1L',
 	"NomeDoador": 'Marcos',
	"endereco":'R.das neves',
	"nomdeDocumento":'rg',
	"nrDocumento":'4634837'
	"alimentoDoado":{
		"Id": '1L',
		"nome": 'ONGAlimentoDoDia',
		"nrRegistro":'182237',
		"endereco":'R.das abelhas'
	}
   }
```
|código|Descrição
|-|-
200 | Refeições atualizadas com sucesso
400 | Os campos enviados são inválidos
404 | Não existe usuário com o id informado

----------------------------------

### Apagar doador
`DELETE` /api/v1/doador/{id}

|código|Descrição
|-|-
204 | Doacao apagada com sucesso
404 | Não existe doacao com o id informado

---------------------------
 