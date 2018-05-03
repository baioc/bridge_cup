REST API COPAS DO MUNDO
=======================

Definição dos serviços REST para serem utilizados pelo aplicativo.

Exemplos de objetos retornados:
-------------------------------

##### Edição:
```json
{
    "ano": 2018,
    "sede": "Rússia"
}
```

##### Seleção:
```json
{
    "sigla": "BRA",
    "nome": "Brasil",
    "vezesCampeao": 5,
    "anoEstreia": 1930,
    "totalParticipacoes": 21,
    "melhorResultado": "Campeão (1958, 1962, 1970, 1994, 2002)",
    "iconeURL": "https://upload.wikimedia.org/wikipedia/commons/thumb/0/05/Flag_of_Brazil.svg/22px-Flag_of_Brazil.svg.png",
    "bandeiraURL": "https://upload.wikimedia.org/wikipedia/commons/thumb/0/05/Flag_of_Brazil.svg/720px-Flag_of_Brazil.svg.png"
}
```

##### Partida:
```json
{
    "idPartida": 9213812,
    "dataHora": 1523391603,
    "selecaoCasa": "BRA",
    "selecaoFora": "GER",
    "idEstadio": 5,
    "votosCasa": 10,
    "votosFora": 10,
    "votosEmpate": 10
}
```

##### Estádio:
```json
{
    "idEstadio": 4564,
    "nome": "Maracanã",
    "cidade": "Rio de Janeiro",
    "capacidade": 50000,
    "fotoURL": "http://images.china.cn/attachement/jpg/site1007/20140528/001ec94a1d8b14efbe730e.jpg",
    "latitude": "1.329193",
    "longitude": "7.23123"
}
```

##### Grupo:
```json
{
    "idGrupo": "A2018",
    "letra": "A",
    "ano": 2018,
    "selecoes": [
        "<Lista de seleções>"
    ],
    "partidas": [
        "<Lista de partidas>"
    ]
}
```

Endpoints
------------

##### Edições

1) Listas de edições da copa do mundo<br/>
**GET /api/v1/edicoes**

##### Grupos

2) Listas de grupos de uma copa do mundo<br/>
**GET /api/v1/edicoes/{ano}/grupos**

3) Obter um grupo<br/>
**GET /api/v1/grupos/{idGrupo}**

##### Seleções

4) Lista de seleções de um grupo<br/>
**GET /api/v1/grupos/{idGrupo}/selecoes**

5) Lista de seleções de uma copa<br/>
**GET /api/v1/edicoes/{ano}/selecoes**

6) Obter uma seleção<br/>
**GET /api/v1/selecoes/{sigla}**

##### Partidas

7) Lista de partidas de um grupo<br/>
**GET /api/v1/grupos/{idGrupo}/partidas**

8) Lista de partidas de uma seleção<br/>
**GET /api/v1/selecoes/{sigla}/partidas**

9) Lista de partidas de uma copa<br/>
**GET /api/v1/edicoes/{ano}/partidas**

10) Obter uma partida<br/>
**GET /api/v1/partidas/{idPartida}**

##### Votação

11) Votar no resultado de uma partida<br/>
**POST /api/v1/partidas/{idPartida}/votos**

###### Exemplo (POST body):
```
{
    "voto": "casa"
}
```
Obs: O valor da chave `voto` pode ser `casa`, `empate` ou `fora`.

##### Estádios

12) Listas de estádios de uma copa do mundo<br/>
**GET /api/v1/edicoes/{ano}/estadios**

13) Obter um estádio<br/>
**GET /api/v1/estadios/{idEstadio}**
