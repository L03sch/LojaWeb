# LojaWeb

Projeto Java Web desenvolvido com **JSP + Servlets + JDBC + MySQL** para cadastro e consulta de **categorias** e **produtos**.

O sistema foi organizado como uma aplicação web tradicional, onde os servlets recebem as requisições, consultam os DAOs, e encaminham os dados para as telas JSP.

## Objetivo do projeto

O projeto serve para demonstrar um CRUD simples de dois módulos principais:

- **Categorias**
- **Produtos**

Com ele é possível:

- listar registros
- cadastrar novos itens
- editar itens existentes
- excluir registros
- testar a conexão com o banco de dados

## Tecnologias utilizadas

- **Java 17**
- **Maven**
- **Servlets 4.0**
- **JSP**
- **JSTL**
- **JDBC**
- **MySQL**

## Estrutura geral do projeto

```text
src/main/java/br/com/
├── dao/      -> acesso ao banco de dados
├── model/    -> classes de domínio
├── util/     -> utilitários de conexão
└── web/      -> servlets da aplicação

src/main/webapp/
├── index.jsp -> menu principal
├── css/      -> estilos das telas
├── js/       -> scripts de apoio
└── WEB-INF/views/
    ├── home.jsp
    ├── categoria-consulta.jsp
    ├── categoria-form.jsp
    ├── produto-consulta.jsp
    └── produto-form.jsp
```

## Entidades do sistema

### `Categoria`
Classe que representa uma categoria cadastrada no sistema.

Campos principais:

- `idCategoria`
- `nomeCategoria`
- `descricao`

### `Produto`
Classe que representa um produto.

Campos principais:

- `idProduto`
- `idCategoria`
- `nomeProduto`
- `estoqueProduto`
- `precoProduto`

> Observação: a classe `Produto` herda de `Categoria`, mas também mantém seu próprio campo `idCategoria`. Isso indica uma modelagem que pode ser revisada no futuro.

## Camada de acesso a dados

O projeto utiliza classes DAO para comunicação com o banco:

- `CategoriaDAO`
- `ProdutoDAO`

Essas classes são responsáveis por operações como:

- inserir
- atualizar
- listar
- buscar por ID
- excluir

## Servlets

### `CategoriaServlet`
Rota: **`/categorias`**

Responsável por:

- abrir a tela de listagem de categorias
- abrir a tela de cadastro
- abrir a tela de edição
- salvar categorias
- excluir categorias

### `ProdutoServlet`
Rota: **`/produtos`**

Responsável por:

- abrir a tela de listagem de produtos
- abrir a tela de cadastro
- abrir a tela de edição
- carregar a lista de categorias para o formulário de produto
- salvar produtos
- excluir produtos

### `TesteConexaoServlet`
Rota: **`/teste-conexao`**

Serve para verificar se a aplicação consegue conectar ao banco MySQL.

## Páginas JSP

### `index.jsp`
É a tela inicial do sistema. Funciona como menu principal, permitindo acessar:

- gerenciamento de categorias
- gerenciamento de produtos
- teste de conexão

### `WEB-INF/views/home.jsp`
Versão de menu principal dentro de `WEB-INF/views`, com visual mais elaborado.

### `categoria-consulta.jsp`
Tela de listagem das categorias.

Mostra:

- ID
- nome
- descrição
- ações de editar e excluir

### `categoria-form.jsp`
Tela usada para:

- incluir categoria
- alterar categoria

Campos:

- nome da categoria
- descrição

### `produto-consulta.jsp`
Tela de listagem dos produtos.

Mostra:

- ID do produto
- ID da categoria
- nome
- preço
- estoque
- ações de editar e excluir

### `produto-form.jsp`
Tela usada para:

- incluir produto
- alterar produto

Campos:

- categoria (`idCategoria` em um seletor com as categorias existentes)
- nome
- estoque
- preço

## Fluxo da aplicação

### Categorias
1. Acesse o menu principal.
2. Clique em **Gerenciar Categorias**.
3. O servlet carrega a listagem.
4. Para cadastrar, clique em **Incluir**.
5. Para editar, clique em **Alterar**.
6. Para excluir, use a ação de exclusão na listagem.

### Produtos
1. Acesse o menu principal.
2. Clique em **Gerenciar Produtos**.
3. O servlet carrega a listagem de produtos.
4. Para cadastrar um produto, clique em **Incluir**.
5. O formulário carrega as categorias cadastradas para seleção.
6. Para editar, clique em **Alterar**.
7. Para excluir, use a ação de exclusão na listagem.

## Conexão com o banco

A conexão é centralizada em `ConnectionFactory`.

Configuração atual esperada:

- **URL:** `jdbc:mysql://localhost:3306/loja_db?useSSL=false&serverTimezone=America/Sao_Paulo`
- **Usuário:** `loja`
- **Senha:** `loja`

É necessário ter o banco MySQL criado com esses dados, ou ajustar a classe `ConnectionFactory` conforme o seu ambiente.

## Execução do projeto

### Requisitos

- Java 17
- Maven
- Servidor compatível com Servlet 4.0, como Apache Tomcat 9
- MySQL rodando localmente

### Passos básicos

1. Configure o banco de dados `loja_db`.
2. Ajuste usuário e senha se necessário em `ConnectionFactory`.
3. Importe o projeto como projeto Maven.
4. Execute o build:

```bash
mvn clean package
```

5. Implante o `.war` gerado no seu servidor de aplicação.
6. Acesse o menu principal pelo navegador.

## Observações importantes

- O projeto usa páginas JSP dentro de `WEB-INF/views` para evitar acesso direto às telas principais.
- Algumas páginas na raiz do `webapp` podem existir como versões antigas ou alternativas do layout.
- O carregamento das categorias no formulário de produto depende da lista enviada pelo `ProdutoServlet`.

## Resumo

Este projeto é uma aplicação web didática de gerenciamento de categorias e produtos, com navegação simples, persistência em MySQL e telas JSP separadas por funcionalidade.

