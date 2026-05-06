# LojaWeb — aplicação exemplo em Java

Este repositório contém uma aplicação web simples escrita em Java que demonstra um CRUD básico para gerenciar categorias e produtos. O projeto utiliza JSP para as telas, Servlets para controlar as rotas, e JDBC/MySQL para persistência.

Visão geral
-----------
LojaWeb foi construída como um exemplo educativo: os Servlets recebem requisições HTTP, consultam os DAOs para operar sobre o banco e repassam os dados para as páginas JSP que exibem os formulários e listas.

Funcionalidades principais
--------------------------
- Gerenciar categorias (listar, criar, editar, excluir)
- Gerenciar produtos (listar, criar, editar, excluir)
- Teste de conexão com o banco de dados

Plataforma e dependências
-------------------------
- Java 17
- Maven (build/empacotamento)
- Servlets 4.0
- JSP + JSTL
- JDBC
- MySQL (conector disponível no pom)

Estrutura do projeto
--------------------
Código-fonte principal:

src/main/java/br/com/
  ├── dao/      — classes responsáveis pelo acesso ao banco
  ├── model/    — entidades do domínio
  ├── util/     — utilitários (ex.: ConnectionFactory)
  └── web/      — Servlets que tratam requisições

Conteúdo web:

src/main/webapp/
  ├── index.jsp    — página inicial / menu
  ├── css/         — estilos
  ├── js/          — scripts cliente
  └── WEB-INF/views/
      ├── home.jsp
      ├── categoria-consulta.jsp
      ├── categoria-form.jsp
      ├── produto-consulta.jsp
      └── produto-form.jsp

Modelos (entidades)
--------------------
- Categoria: representa uma categoria com campos como idCategoria, nomeCategoria e descricao.
- Produto: representa um produto (idProduto, idCategoria, nomeProduto, estoqueProduto, precoProduto). Note que, na implementação atual, `Produto` mantém referência/campo `idCategoria` — a modelagem pode ser ajustada mais tarde.

Persistência (DAOs)
-------------------
As classes `CategoriaDAO` e `ProdutoDAO` encapsulam operações CRUD principais: inserir, atualizar, listar, buscar por ID e excluir.

Servlets e rotas
----------------
- `CategoriaServlet` — rota `/categorias`: exibe listagem, formulário de inclusão/edição, salva e remove categorias.
- `ProdutoServlet` — rota `/produtos`: exibe listagem de produtos, formulário (carrega categorias), salva e remove produtos.
- `TesteConexaoServlet` — rota `/teste-conexao`: endpoint simples para verificar se a aplicação alcança o banco MySQL.

Páginas JSP
----------
- `index.jsp`: menu principal com links para categorias, produtos e teste de conexão.
- `WEB-INF/views/home.jsp`: versão do painel dentro de `WEB-INF`.
- `categoria-consulta.jsp`: tabela com ID, nome, descrição e ações (editar/excluir).
- `categoria-form.jsp`: formulário para criar/editar categorias (nome, descrição).
- `produto-consulta.jsp`: tabela com ID produto, ID categoria, nome, preço, estoque e ações.
- `produto-form.jsp`: formulário para criar/editar produtos (seletor de categoria, nome, estoque, preço).

Como navegar (fluxo rápido)
---------------------------
Categorias
1. Abra o menu principal.
2. Clique em Gerenciar Categorias.
3. Use Incluir para criar, Alterar para editar e a ação de excluir na listagem para remover.

Produtos
1. Vá ao menu principal.
2. Entre em Gerenciar Produtos.
3. Use Incluir para criar um produto (o formulário carrega as categorias disponíveis), Alterar para editar e a ação de excluir para remover.

Conexão com o banco
-------------------
As conexões são gerenciadas por `ConnectionFactory`. A configuração usada no projeto (padrão) é:

- URL: jdbc:mysql://localhost:3306/loja_db?useSSL=false&serverTimezone=America/Sao_Paulo
- Usuário: loja
- Senha: loja

Altere esses valores em `ConnectionFactory` se o seu ambiente usar credenciais ou endereço de banco diferentes.

Como executar
--------------
Requisitos mínimos:
- Java 17
- Maven
- Servidor compatível com Servlet 4.0 (ex.: Apache Tomcat 9+)
- Banco MySQL em funcionamento

Passos resumidos:

1. Certifique-se de criar o banco `loja_db` e configurar o usuário/senha ou ajustar `ConnectionFactory`.
2. No diretório do projeto, gere o artefato:

```powershell
mvn clean package
```

3. Implante o arquivo WAR gerado (target/lojaWeb.war) no seu servidor de aplicação.
4. Acesse a aplicação via navegador pelo contexto do seu servidor.

Notas e observações
-------------------
- As páginas dentro de `WEB-INF/views` são usadas para evitar acesso direto por URL.
- Há arquivos estáticos na raiz de `webapp` que podem ser versões alternativas do layout.
- O formulário de produto depende da lista de categorias provida pelo `ProdutoServlet`.

Resumo
------
LojaWeb é um exemplo didático que ilustra uma aplicação Java web clássica com JSP, Servlets e JDBC para operações de CRUD sobre categorias e produtos. Use-o como base para aprendizado ou para adaptar funcionalidades a projetos maiores.

