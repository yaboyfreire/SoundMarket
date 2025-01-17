![logo uni](Anexos/Logo.png)
# Relatório Final do Projeto
## Projeto de Desenvolvimento Móvel

Trabalho realizado por:
- **Martim Conceição** nº20231206
- **Rodrigo Freire** nº20230851

---

**Nome do projeto**: [SoundMarket](https://github.com/yaboyfreire/SoundMarket)  
**Link github**: [https://github.com/yaboyfreire/SoundMarket](https://github.com/yaboyfreire/SoundMarket)

---
## Divisão de tarefas
![Divisão de tarefas](Anexos/SOUNDMARKET_DIVISAO_TAREFAS.png)


A SoundMarket é uma aplicação que tem como objetivo servir de marketplace e um biblioteca móvel para o utilizador. Nesta aplicação é possível procurar por álbuns, ver quais estão à venda, adicionar álbuns à coleção e ainda adicionar álbuns a uma lista de desejos. Com esta aplicação estamos a resolver um problema já existente no mercado, procura e compra de álbuns de forma intuitiva sem complicações.


Um dos objetivos principais do projeto era criar uma aplicação que conseguisse guardar os álbuns que o utilizador tem em sua casa, sendo eles em cd ou vinil e ao mesmo tempo conseguir colocar esses álbuns à venda e ter a possibilidade de comprar outros álbuns que o utilizador já estivesse à procura há algum tempo. A motivação inicial deste projeto foi a vontade de ter uma aplicação no telefone que fosse possível guardar e ver toda a coleção de música em formato físico que tinha em casa, rapidamente foi proposta a ideia da aplicação conseguir também realizar a compra e venda de álbuns.

O público alvo desta aplicação é toda a gente que tem uma coleção em formato físico e quer sempre estar a par do que tem estando onde estiver, mas por ser uma aplicação de fácil utilização pode-se dizer que a aplicação acaba por ser direcionada para toda a gente que gosta de música uma vez que as pessoas que queiram começar a colecionar ou só a comprar os seus álbuns favoritos podem utilizar a nossa aplicação, é também por isto que a nossa aplicação não tem nenhuma faixa etária alvo, podendo ser usada desde crianças até idosos 

Existem algumas aplicações com alguns conceitos semelhantes ao SoundMarket mas nenhuma delas tem todos os conceitos no mesmo sítio como a nossa. Algumas destas aplicações são o Discogs que é um marketplace já existente há muitos anos no mercado, que também permite guardar a música numa coleção e o que se destaca no Discogs é a vasta biblioteca de álbuns que a aplicação tem sendo muito fácil de se encontrar o que se procura o que se pode apontar como um ponto fraco desta aplicação é a dificuldade na compra e venda de artigos, mais na compra que não é nada intuitiva e certamente para alguém que queira comprar algo pela primeira vez na aplicação vai ter dificuldades. Outras aplicações que também se podem comparar com o SoundMarket são a vinted, o ebay e a amazon estas aplicações são todas marketplaces e o que elas tem todas em comum é a facilidade em comprar, sendo já muito mais fácil para quem compra pela primeira vez por outro lado a parte não tão boa destas aplicações é que por venderem tanto tipo de artigos acaba por ser bastante complicado de se encontrar o que se procura se for algo mais especifico. Por fim ainda existem todas as aplicações de lojas de música que acabam por ter uma grande variedade de artigos mas que não permitem guardar a nossa coleção.

A aplicação desenvolvida permite aos utilizadores procurarem os álbuns favoritos, ouvi-los no spotify bem como adiciona-los a uma coleção personalizada. A app proporciona uma experiencia intuitiva e personalizada, permitindo a gestão eficiente dos conteúdos musicais.

## Enquadramento das diversas UCs:

### Programação de dispositivos móveis
Esta UC permitiu-nos fazer o desenvolvimento do design da aplicação e a implementação das APIs tanto a do Spotify como a nossa.

### Programação orientada a objetos
Nesta UC desenvolvemos tudo o que era relacionado com a nossa API e o diagrama de classes do projeto

### Bases de dados
Em bases de dados neste projetos fomos auxiliados na criação da nossa base de dados. Começando com o modelo entidade relação até à documentação da Base de dados 

### Competências comunicacionais
A UC de competências comunicacionais ajudou-nos e bastante em todos os documentos que eram relacionados com o projeto, de apresentações, vídeos, powerpoints, a posters 

### Matemática discreta

A matemática discreta é essencial para o desenvolvimento de aplicações, e a nossa não é exceção. 
Conceitos como aos que demos em aulas, lógica proposicional, conjuntos(entre outros) são utilizados para estruturar e garantir o funcionamento lógico de qualquer aplicação.

A lógica proposicional aparece em funcionalidades como a Search e o login. 
Na Search, P representa o utilizador inserir um termo válido, e Q o return dos resultados. A implicação P -> Q diz nos que, se o termo for válido, os resultados vão ser mostrados,  caso contrário (~P), uma mensagem de erro é exibida no lugar dos resultados. No login, P são credenciais corretas e Q o acesso ao resto da app, seguindo a mesma lógica P -> Q, com mensagens de erro em ~P.

Os conjuntos podem modelar coleções de álbuns e resultados de pesquisa. A coleção de um utilizador é um conjunto C, onde C != vazio indica que a coleção será exibida, caso contrário, uma mensagem informa que a sua coleção está vazia ou simplesmente não aparece. As operações de união (U) e interseção (∩) permitem comparar coleções entre usuários, como encontrar álbuns em comum (C1 ∩ C2) . Na busca, o conjunto S representa todos os álbuns do Spotify, e B ⊆ S os álbuns que atendem ao termo de pesquisa.

As relações são usadas para associar utilizadores a álbuns. Cada par (u, a) conecta um utilizador u a um álbum a, formando uma tabela relacional. 

Princípios de contagem ajudam em análises, como calcular quantos utilizadores ainda não possuem álbuns na coleção.


## Requisitos Técnicos:
 * Backend - No nosso backend foi utilizado Spring Boot v3.2.4 e Mysql
 * Frontend - no Frontend foi utilizado o Android Studio
 * APIs - No nosso projeto foram usadas a API do Spotify e a nossa própria API 

## Arquitetura da solução:
 * Arquitetura cliente-servidor- A app Android comunica com o backend desenvolvido em Spring Boot
 * Camadas:
   * Frontend- interface em kotlin
   * Backend -API REST em Spring Boot
   * Base de dados- MySQL para armazenamento de dados 

## Tecnologias usadas:
 * linguagens- Kotlin(Android Studio), Java(Api), MySQl(Base De Dados)
 * Frameworks- Spring Boot(Backend), Retrofit (APIs) 
 * API- Spotify Web API, SoundMarket API (A nossa API)
 * Ferramentas- Android Studio, Vscode, MySQl workbench, Postman (testes API), GitHub

## Caso de utilização do objeto “core” do projeto
### Personas

![Persona1](Anexos/Persona1JoaoSilva.png)

![Persona2](Anexos/Persona2MariaCosta.png)

O caso aqui apresentado vai ser o processo de adicionar um álbum à coleção.
### Adicionar um álbum à coleção

1. O utilizador João Silva vai registar-se na aplicação.
2. Depois de se registar, faz login e é redirecionado para a home page.
3. Na home page, vai ter uma barra de pesquisa (search bar) para encontrar o álbum.
4. Ao encontrar e carregar no álbum, irá aparecer no topo da página a opção “add to collection”.
5. Depois de escolher o formato e a condição, o utilizador carrega no botão que diz "add to collection" e o álbum será adicionado à coleção.

---

### Ver um álbum na coleção

1. O utilizador faz login na aplicação
2. Na home page no canto superior direito, o utilizador vai encontrar três símbolos e vai carregar no primeiro
3. Quando estiver na página do perfil, vai encontrar algumas informações sobre o seu perfil e em baixo um carrossel com alguns dos álbuns disponiveis na sua coleção
4. Em cima desse carrossel ele vai ver um botão e carregar nesse botão
5. Esse botão leva o utilizador para outra página onde estão a ser mostrados todos os álbuns na coleção do utilizador 

---

### Colocar um álbum à venda

1. Na home page, em baixo no centro do ecrã, vai aparecer a opção “sell” e o utilizador João Silva vai carregar nesse botão.
2. O utilizador será levado para outra página onde irá encontrar uma barra de pesquisa (search bar) para procurar o nome do álbum (aparecerão apenas os álbuns que estão presentes na coleção do utilizador), um botão para escolher o formato, outro para escolher a condição do disco, um espaço para colocar o preço de venda e ainda uma caixa de texto para adicionar uma descrição ao produto.
3. Depois de preencher tudo, o João carrega no botão que diz “list item” e o item é colocado à venda.

---

## Diagrama de classes
![Diagrama de classes](Anexos/ClassDiagramFinal.png)

---

## Guia de dados
![MER](Anexos/MER.png)

---

## Dicionário de dados
![Tabela_User](Anexos/BibliotecaDeDados/BD_USER.png)
![Tabela_admin](Anexos/BibliotecaDeDados/BD_ADMINISTRATOR.png)
![Tabela_Wishlist](Anexos/BibliotecaDeDados/BD_WISHLIST.png)
![Tabela_Album](Anexos/BibliotecaDeDados/BD_Album_Final.png)
![Tabela_Selling](Anexos/BibliotecaDeDados/BD_SELLING.png)
![Tabela_Buy](Anexos/BibliotecaDeDados/BD_BUY.png)
![Tabela_Chat](Anexos/BibliotecaDeDados/BD_CHAT.png)
![Tabela_Message](Anexos/BibliotecaDeDados/BD_MESSAGE.png)
![Tabela_Status](Anexos/BibliotecaDeDados/BD_STATUS.png)
![Tabela_Buy_Status](Anexos/BibliotecaDeDados/BD_BUY_STATUS.png)

---

### Documentação Rest
[Documentação Rest](https://github.com/yaboyfreire/SoundMarket/blob/Relat%C3%B3rioFinal/Docs/SoundMarket_SpotifyAPI.pdf)

### Manual Do Utilizador
[Manual do Utilizador](https://github.com/yaboyfreire/SoundMarket/blob/Relat%C3%B3rioFinal/Docs/ManualUtilizador.pdf)


## Planeamento e calendarização final (Gráfico de Gantt)
Aqui está o gráfico de gantt final, não dá para ver tudo muito bem por isso também está aqui o link do clickup que redireciona para o gráfico de gantt
[Gráfico de gantt no clickup](https://app.clickup.com/9012385181/v/g/8cjw5cx-492)

![gráfico de gantt](Anexos/Gantt1aEntrega.png)
![gráfico de gantt](Anexos/Gantt2aEntrega.png)
![gráfico de gantt](Anexos/Gantt3aEntrega.png)
---
 
## Auto-Avaliação

O projeto começou bem, e a parte do ui e navegação do projeto estavam a ser feitas rapidamente. isso mudou quando começamos a desenvolver a API devido a uma dificuldade que não nos era esperada,acabamos por isso a diminuir a velocidade com que desenvolviamos as coisas. tivemos então de voltar a aumentar o ritmo na fase final do projeto porque ainda tinhamos várias coisas em falta. O que fica por completar do projeto que foi defenido na 1ª entrega é colocar álbuns à venda, adicionar álbuns à wishlist e comunicar com outros vendedores, estas faltas acontecem porque como já disse encontramos várias dificuldades quando começamos a desenvolver a api, perdendo muito tempo no ínicio e não conseguimos recuperar esse tempo. Com isso tudo em conta achamos que a nossa nota deve ser 11

---

## Bibliografia

- **Discogs**: [Discogs](https://www.discogs.com/?gad_source=1&gclid=CjwKCAjw1NK4BhAwEiwAVUHPUFWETK8eLn6yyI2wQMschx5hc1324ZFLm2U9KAnclErglRte2uR9SBoCfj4QAvD_BwE)
- **Clickup**: [Clickup](https://clickup.com/lp)
- **Poster**: [Canva](https://www.canva.com/)
- **Vídeo**: [Online Video Cutter](https://online-video-cutter.com/), [Promo.com](https://promo.com/)
- **Voice-over**: [ChatGPT](https://openai.com/index/chatgpt)
- **Mockup**: [Figma Mockups](https://www.figma.com/files/team/1423672219666502754/recents-and-sharing/recently-viewed?fuid=1423672217439376078)
