# Exercício Programado 2

## Projeto

O projeto é um aplicativo para android baseado numa Pokedex, com telas intuitivas e simples.
Ao executar o aplicativo o usuário se deparará com três opções: Pokedex, Treinadores e Cadastro.
Ao clicar em Alguma das opções o usuário será automaticamente redirecionado para outra tela. Como pode-se ver no 
[Menu](https://gitlab.com/GabrielTiveron/ep2/uploads/0d547fd97a31ef228e8d4bf0468ec14c/Menu.png)


Obs.: O diretorio q deve ser executado para abrir o aplicativo é o "Pokedex".

## Pokedex

[Lista todos os pokemons](https://gitlab.com/GabrielTiveron/ep2/uploads/e78226a677b0ac75d5c006591fc4c760/Pokedex.png)
e permite a busca por nomes e por tipos (após cerca de 2 minutos)
além de, ao selecionar um pokemon, ter acesso aos detalhes do mesmo.

### Detalhes do Pokemon

Esta tela lista [detalhes](https://gitlab.com/GabrielTiveron/ep2/uploads/69000aaa6698350fdb080c03cea311eb/Detalhes_Pokemon.png) de cada pokemon como: Nome, Tipo, Movimentos e Sprites;

## Cadastro Treinadores

Ao ter acesso à esta tela o usuário pode preencher os dados de [cadastro](https://gitlab.com/GabrielTiveron/ep2/uploads/39d9e6f1a6bee53d01d59cec4dfd15b6/Cadastro_Treinador.png) sendo que todos devem ser preenchidos, caso contrario o sistema alertará usuário.
Ao escrever o nome de um pokemon corretamente deve-se pressiona o botão "+" para adicioná-lo a lista do treinador, ao clicar no pokemon da lista se tem acesso aos detalhes dele
podendo, também, removê-lo da lista ao pressioná-lo.
Também 'é necessária a seleção de uma das opções disponíveis: Homem, Mulher.
Tendo preenchido os dados pode ser concluído o cadastro do treinador, que será armazenado na memória. 

## Lista de Treinadores

Nesta [atividade](https://gitlab.com/GabrielTiveron/ep2/uploads/4513b5b14a54273cd236fbe10d6e6a83/Lista_Treinadores.png) há uma lista simples contendo o nome e o ícone de cada treinador cadastrado, podendo-se remover ao pressioná-lo.
Pode-se ter acesso aos detalhes de cada treinador ao clicar em seu nome.

### Detalhes do treinador

Mostra [detalhes](https://gitlab.com/GabrielTiveron/ep2/uploads/00fcae993ed8c5c0c47ecdb7a5946b78/Detalhes_Treinador.png) como: Nome, ícone e lista de pokemons.
Pode-s clicar no pokemon para ir para a tela de Detalhes do Pokemon.

## Possíveis complicações

A pesquisa por tipo é possível apenas após o carregamento de todos os pokemons na pokedex.
A usabilidade do aplicativo, devido as requisições da API, é muito afetada pela conexão à internet. 


### Referências

* [Consumo da API](https://square.github.io/retrofit/)
* [Cores tema dos tipos](https://bulbapedia.bulbagarden.net/wiki/Main_Page)