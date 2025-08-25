# API de E-commerce com Spring Boot

![Java](https://img.shields.io/badge/Java-17+-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x.x-brightgreen)
![JPA / Hibernate](https://img.shields.io/badge/JPA%20%2F%20Hibernate-blue)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-darkblue)
![Docker](https://img.shields.io/badge/Docker-blue)
![Maven](https://img.shields.io/badge/Maven-red)

## Descrição

Olá, este projeto é uma API RESTful para um sistema de e-commerce, desenvolvida como parte de um estudo aprofundado e prático sobre as tecnologias do ecossistema Spring. A API implementa funcionalidades essenciais como gerenciamento de usuários, produtos, categorias, pedidos (checkout) e um sistema de autenticação e autorização robusto baseado em JWT.

O objetivo principal foi construir uma base de código limpa, escalável e segura, seguindo as melhores práticas de arquitetura em camadas e design de APIs.

Sinta-se avontade para sugerir melhorias, funcionalidades, segurança e reportar bugs... crie uma issue e descreva sua idea.

Se você notou o diretório ".idx" na raiz do projeto e esta se perguntando "o que é isso?" pois bem... lhe trago boas noticias. Isso se trata de um projeto da google, o Project IDX, que consiste em um ambiente de desenvolvimento baseado em nuvem e com assistência  de agente IA, uma ideia da google que ja esta almejando o futuro da programação e desse mercado de trabalho para não ficar para trás. Neste projeto você não é obrigado a usar o workspace baseado em nuvem da google (Project IDX), mas recomendo bastante que você faça isso. Para mais detalhes sobre project IDX acesse: [https://idx.dev](https://idx.dev/).

## Funcionalidades

* **Autenticação e Autorização:**
    * Registro de novos usuários com criptografia de senha (BCrypt).
    * Login com geração de token JWT.
    * Filtro de segurança para validar tokens a cada requisição.
    * Controle de acesso baseado em papéis (`ROLE_USER`, `ROLE_ADMIN`).
* **Gerenciamento de Produtos e Categorias:**
    * CRUD completo para produtos.
    * CRUD completo para categorias.
    * Relacionamento entre produtos e categorias (`@ManyToOne`).
* **Sistema de Pedidos:**
    * Endpoint de checkout para criação de pedidos.
    * Lógica transacional (`@Transactional`) para garantir a integridade dos dados (ex: atualização de estoque).
    * Armazenamento de preço histórico nos itens do pedido.
    * Listagem de pedidos para administradores e para o usuário autenticado.
* **Upload de Arquivos:**
    * Endpoint para upload de imagens de produtos (`multipart/form-data`).
* **Recursos Adicionais:**
    * Paginação em todos os endpoints de listagem para melhor performance.
    * Tratamento de exceções centralizado (`@RestControllerAdvice`).
    * Documentação interativa da API com Swagger/OpenAPI.

## Tecnologias Utilizadas

* **Back-end:** Java 17, Spring Boot 3
* **Persistência:** Spring Data JPA, Hibernate
* **Banco de Dados:** PostgreSQL (gerenciado via Docker)
* **Segurança:** Spring Security 6, JSON Web Tokens (JWT)
* **Documentação:** Springdoc OpenAPI (Swagger UI)
* **Build:** Maven
* **Containerização:** Docker, Docker Compose
* **Outros:** Lombok

## Diagrama de Classes (Entidades Principais)

Este diagrama representa a estrutura e o relacionamento entre as principais entidades de negócio do projeto.

![/diagrama_de_classe_ecommerce_drawio.png](/diagrama_de_classe_ecommerce_drawio.png)

## Como Executar

O projeto está configurado para ser executado facilmente com Docker.

1.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/WANDEYAN/api-ecommerce.git](https://github.com/WANDEYAN/api-ecommerce.git)
    cd api-ecommerce
    ```

2.  **Configure o Banco de Dados:**
    * O arquivo `docker-compose.yml` irá iniciar um contêiner PostgreSQL. As credenciais padrão estão definidas nele. Certifique-se de que sua `application.properties` está configurada para se conectar a `jdbc:postgresql://localhost:5432/ecommerce_db`.

3.  **Inicie o Banco de Dados:**
    ```bash
    docker-compose up -d
    ```
    * O `-d` executa o contêiner em segundo plano.

4.  **Execute a Aplicação Spring Boot:**
    * Abra o projeto em sua IDE de preferência e execute a classe principal `ApiApplication.java`.

5.  **Acesse a Documentação:**
    * Com a aplicação rodando, acesse a documentação interativa da API em:
    * [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui.html)

## Jornada de Desenvolvimento (Log de Tarefas)

Este projeto foi construído de forma incremental. Abaixo está um resumo de cada tarefa implementada.

* **Tarefa 01: Configuração e Estrutura Inicial:** Criação do projeto com Spring Initializr, definição da estrutura de pacotes, configuração do banco de dados e primeiro commit.
* **Tarefa 02: Entidade e Repositório do Usuário:** Implementação da entidade `User` e da interface `UserRepository`.
* **Tarefa 03: Registro de Usuário:** Criação do endpoint de registro, com camada de serviço, DTOs e criptografia de senha.
* **Tarefa 04: Tratamento de Exceções:** Refatoração com exceções customizadas e um handler global (`@RestControllerAdvice`) para erros de negócio.
* **Tarefa 05: Login e Geração de JWT:** Implementação do endpoint de login e da lógica de geração de token JWT.
* **Tarefa 06: Filtro de Segurança JWT:** Criação do filtro para validar o token JWT a cada requisição e proteger endpoints.
* **Tarefa 07: CRUD de Produtos:** Implementação completa do CRUD para a entidade `Product`.
* **Tarefa 08: CRUD de Categorias e Relacionamentos:** Implementação do CRUD para `Category` e estabelecimento do relacionamento `@ManyToOne` com `Product`.
* **Tarefa 09: Refatoração RESTful:** Ajuste de todos os endpoints para seguirem as melhores práticas e convenções REST.
* **Tarefa 10: Paginação:** Implementação de paginação com `Page` e `Pageable` nos endpoints de listagem para maior escalabilidade.
* **Tarefa 11: Criação de Pedidos (Checkout):** Implementação da lógica de negócio mais complexa, com transações (`@Transactional`), múltiplos relacionamentos e uso do `@AuthenticationPrincipal`.
* **Tarefa 12: Controle de Acesso por Papel (RBAC):** Implementação de papéis (`ADMIN`, `USER`) e proteção de endpoints com `@PreAuthorize`.
* **Tarefa 13: Documentação com Swagger/OpenAPI:** Configuração e anotação da API para gerar uma documentação interativa e profissional.
* **Tarefa 14: Upload de Imagem de Produto:** Implementação do endpoint para upload de arquivos (`multipart/form-data`) e armazenamento local.
* **(Manutenção): Configuração de Ambiente Docker/IDX:** Adição de `docker-compose.yml` para padronizar o ambiente de banco de dados.

## Autor

* **[WANDÉ YAN]** - [https://github.com/WANDEYAN](https://github.com/WANDEYAN)