📚🎬 Catálogo Simples de Livros, Filmes e Séries

Aplicação web desenvolvida em Java como Projeto Integrador Transdisciplinar (PIT) em Ciência da Computação. O sistema permite catalogar, consultar, editar, excluir e pesquisar itens de mídia (livros, filmes e séries) com persistência em banco de dados relacional.

O projeto foi estruturado com foco na refatoração e separação de responsabilidades (Situação-Problema 3), aplicando os princípios da Programação Orientada a Objetos (POO) e padrões arquiteturais para evitar código monolítico e duplicação de conexões SQL.

🏗️ Arquitetura e Organização do Projeto

A aplicação segue o padrão arquitetural MVC (Model-View-Controller) combinado com as camadas Service e DAO (Data Access Object):

catalogo-midia-web/
├── sql/
│   └── schema.sql                      # Script de criação do banco, tabela e carga inicial
├── src/main/
│   ├── java/br/com/catalogo/
│   │   ├── controller/
│   │   │   └── CatalogoServlet.java    # Controlador HTTP (recebe requisições e aciona views)
│   │   ├── dao/
│   │   │   ├── IItemMidiaDAO.java      # Interface de contrato para persistência
│   │   │   └── ItemMidiaDAO.java       # Implementação JDBC com PreparedStatement
│   │   ├── model/
│   │   │   └── ItemMidia.java          # Entidade de domínio encapsulada
│   │   ├── service/
│   │   │   └── ItemMidiaService.java   # Regras de negócio e validação de dados
│   │   └── util/
│   │       └── FabricaDeConexoes.java  # Centralizador de conexões JDBC
│   └── webapp/
│       ├── index.jsp                   # Listagem principal e barra de busca
│       ├── formItem.jsp                # Formulário de cadastro e edição
│       └── detalhes.jsp                # Visualização completa de um item
└── pom.xml                             # Gerenciamento de dependências Maven


Papel de cada camada

Model (ItemMidia): Representa a entidade principal com atributos privados e métodos getters/setters (encapsulamento).

Util (FabricaDeConexoes): Centraliza a abertura de conexões JDBC, eliminando repetição de credenciais e strings de conexão.

DAO (IItemMidiaDAO / ItemMidiaDAO): Isola todas as instruções SQL (CRUD e busca), utilizando PreparedStatement para mitigar ataques de SQL Injection e blocos try-with-resources para fechamento automático de recursos.

Service (ItemMidiaService): Concentra as regras de negócio e validações de integridade (campos obrigatórios, limites de caracteres e intervalo válido para o ano de lançamento).

Controller (CatalogoServlet): Gerencia exclusivamente o roteamento das requisições GET e POST e o encaminhamento para as páginas JSP.

View (*.jsp): Camada de apresentação construída com JSP e JSTL (<c:out>), garantindo o escape seguro de saídas contra Cross-Site Scripting (XSS).

🛠️ Tecnologias Utilizadas

Linguagem: Java 17

Web: Jakarta/Javax Servlet API 4.0 & JSP / JSTL 1.2

Persistência: JDBC (Java Database Connectivity)

Banco de Dados: MySQL 8+

Servidor de Aplicação: Apache Tomcat 9

Gerenciador de Dependências: Apache Maven

🚀 Como Executar o Projeto

1. Pré-requisitos

JDK 17 instalado

Apache Maven configurado

Servidor Apache Tomcat 9

MySQL Server ativo

2. Configuração do Banco de Dados

Abra o seu cliente MySQL (MySQL Workbench ou terminal).

Execute o script disponível em sql/schema.sql para criar o banco catalogo_db, a tabela item_midia e inserir os registros iniciais de teste.

Caso o usuário ou senha do seu banco local sejam diferentes de root / root, atualize as constantes na classe FabricaDeConexoes.java:

private static final String URL = "jdbc:mysql://localhost:3306/catalogo_db?useSSL=false&serverTimezone=UTC&characterEncoding=UTF-8";
private static final String USUARIO = "seu_usuario";
private static final String SENHA = "sua_senha";


3. Deploy e Execução

Clone este repositório ou extraia os arquivos do projeto:

git clone https://github.com/SEU_USUARIO/catalogo-midia-web.git


Importe o projeto como um Projeto Maven Existente na sua IDE (IntelliJ IDEA, Eclipse ou NetBeans).

Associe o projeto a um servidor Apache Tomcat 9 e inicie o servidor.

Acesse no navegador:

http://localhost:8080/catalogo-midia-web/catalogo


✨ Funcionalidades Implementadas

[x] Cadastro de novos livros, filmes e séries

[x] Listagem ordenada de todos os itens catalogados

[x] Busca dinâmica por título ou autor/diretor

[x] Visualização detalhada de cada obra (incluindo sinopse)

[x] Edição de registros existentes

[x] Exclusão de itens do catálogo

[x] Validação de entradas e tratamento de exceções com mensagens amigáveis

👨‍💻 Autor

Desenvolvido por Eduardo Cosmos Solarenco Junior — Curso de Ciência da Computação (Cruzeiro do Sul Virtual).
