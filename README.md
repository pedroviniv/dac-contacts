# dac-contacts
O **dac-contacts** trata-se de uma atividade avaliativa sobre Docker e Java Server Faces (JSF) da displina Desenvolvimento de Aplicações Corporativas (DAC).

O projeto divide-se em 3 módulos.

- **dac-contacts-shared**, desenvolvido utilizando JSE, este módulo contempla tanto as entidades quanto as interfaces da aplicação que serão compartilhadas entre os outros módulos.
- **dac-contacts-core**, desenvolvido utilizando JEE com Enterprise JavaBeans (EJB) e JDBC na camada de persistência, este módulo contempla as implementações dos serviços da aplicação e uma camada de infraestrutura para persistência das entidades no banco de dados PostgreSQL.
- **dac-contacts-webapp**, desenvolvido com JSF e CDI, este módulo contempla a camada de visualização WEB da aplicação, provendo uma interface de usuário para acesso as funcionalidades do sistema.

#### Requisitos Funcionais
___
- **RF01**: Pesquisar um contato pelo seu nome.
- **RF02**: Listar os contatos por ordem alfabética e agrupados pela primeira letra de seu nome
- **RF03**: Cadastrar um novo contato.
- **RF04**: Editar um contato existente.
- **RF05**: Excluir um contato existente.

#### Manual de implantação
___
**>>IMPORTANTE<<**
Como este projeto foi desenvolvido utilizando Docker para separar os módulos em containers distintos, para executar as etapas de implantação é necessário que se tenha o Docker instalado em sua máquina. Clique [neste link](https://docs.docker.com/engine/getstarted/step_one/#step-1-get-docker) e siga os passos indicados para baixar e instalar o Docker.

Uma vez instalado o Docker, copie este repositório para a sua máquina através
do comando:
- `git clone https://github.com/pedroviniv/dac-contacts.git`

Após copiar o repositório, abra o terminal na pasta do projeto copiado e para construir todos os módulos do projeto e instalar no repositório maven local da sua máquina, execute o comando:

- `mvn clean install`

Uma vez construído e instalado todos os módulos da aplicação, deve-se criar as imagens a partir do dockerfile e instanciar os containers a partir das imagens criadas, interligando-os através da flag `--link` para que os containers possam se comunicar entre si. 

Existem duas maneiras de realizar este procedimento, a maneira mais manual, executando cada comando e a maneira mais automática executando o docker compose que já contém todos os passos pré definidos.

Ambas as maneiras são definidas abaixo:

#####Manual

Ainda no terminal aberto no diretório do projeto, execute os comandos abaixo para construir as imagens:

1. `docker build -t dac-contacts-core-db ./dac-contacts-core/postgres`
2. `docker build -t dac-contacts-core ./dac-contacts-core`
3. `docker build -t dac-contacts-webapp ./dac-contacts-webapp`

Uma vez criada as imagens de cada módulo do projeto, agora basta instanciarmos um container para cada imagem criada. Para fazê-lo, execute os comandos abaixo (OBS: se alguma das portas abaixo já estiver sendo usada no seu SO, basta mudá-las para uma que não esteja):

1. `docker run -p 5433:5432 -d --name dac-contacts-core-db dac-contacts-core-db`

2. `docker run -p 8081:8080 -p 1098:1099 -p 1097:1098 -p 3874:3873 -p 3701:3700 -p 8182:8181 -p 8010:8009 -d --name dac-contacts-core --link dac-contacts-core-db:dac-contacts-core-db dac-contacts-core`

3. `docker run -p 8082:8080 -d --name dac-contacts-webapp --link dac-contacts-core:dac-contacts-core dac-contacts-webapp`


#####Automática

Ainda no terminal aberto no diretório do projeto, execute o comando abaixo:

1. `docker-compose up -d`


Seja da maneira manual ou da maneira automática, após executar os passos acima, os módulos estarão no ar e no caso de não haver a necessidade de mudar as portas, estarão disponíveis nas portas default fornecidas nas etapas acima.
- dac-contacts-core-db: `5433`
- dac-contacts-core: `8081`
- dac-contacts-webapp: `8082`







