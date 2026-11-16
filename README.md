# DAO JDBC — vendedores e departamentos

Projeto de estudo em **Java, JDBC e MySQL**, executado pelo console, para praticar acesso a dados com o padrão Data Access Object (DAO).

## O que este projeto demonstra

- **Desenvolvimento Fullstack:** interfaces DAO, implementações JDBC, entidades e separação entre aplicação e persistência.
- **SQL e dados relacionais:** consultas com filtros e `INNER JOIN`, inserção, atualização e exclusão com `PreparedStatement`.
- **Suporte de Sistemas Júnior:** investigação de conexão, driver, estrutura de tabelas, registros inexistentes e restrições de integridade.
- **Documentação de diagnóstico:** identificação dos pré-requisitos e dos dados necessários para reproduzir cada operação.

É um exercício de persistência; não inclui API REST ou interface gráfica.

## Estrutura e evidências

- [`src/application/Main.java`](src/application/Main.java): demonstração das operações.
- [`src/db/DB.java`](src/db/DB.java): carregamento de configuração e abertura/fechamento de recursos JDBC.
- [`src/model/dao`](src/model/dao): contratos e factory.
- [`SellerDaoJDBC`](src/model/dao/impl/SellerDaoJDBC.java): CRUD e consultas com vendedor/departamento.
- [`DepartmentDaoJDBC`](src/model/dao/impl/DepartmentDaoJDBC.java): operações de departamento.
- [`src/model/entities`](src/model/entities): `Seller` e `Department`.

## Preparação e execução

1. Usar JDK 21, configurado no projeto Eclipse, e um servidor MySQL de desenvolvimento.
2. Importar o projeto Java na IDE e adicionar o MySQL Connector/J ao classpath. O `.classpath` referencia uma biblioteca local chamada `MySQL_connector`, que precisa ser configurada na máquina.
3. Preparar as tabelas `department` e `seller` com os campos esperados pelos DAOs. Este repositório não inclui script de criação ou massa de dados.
4. Ajustar localmente as propriedades `user`, `password` e `dburl` de `db.properties` para um banco de teste; não publicar credenciais reais.
5. Conferir os IDs usados em `Main.java` antes de executar `application.Main`.

**A demonstração altera e exclui registros. Executar somente em uma base descartável de estudo.** Alguns IDs são fixos; um registro ausente pode interromper o fluxo antes das etapas seguintes.

## Modelo esperado pelos DAOs

| Tabela | Campos referenciados no código |
|---|---|
| `department` | `Id`, `Name` |
| `seller` | `Id`, `Name`, `Email`, `BirthDate`, `BaseSalary`, `DepartmentId` |

O vínculo é `seller.DepartmentId` → `department.Id`. A criação definitiva do schema deve conferir tipos, chaves e geração de IDs conforme o MySQL utilizado.

## Diagnóstico no ambiente local

| Sintoma | Conferência sugerida |
|---|---|
| `db.properties` não encontrado | Executar a aplicação com a raiz do projeto como diretório de trabalho. |
| Driver não encontrado | Conferir o Connector/J e a biblioteca `MySQL_connector` na IDE. |
| Conexão recusada ou acesso negado | Conferir serviço, host, porta, banco e permissões do usuário de teste. |
| Tabela ou coluna não encontrada | Comparar o schema com os nomes usados nas consultas JDBC. |
| `findById` retorna `null` | Conferir a existência do ID antes de acessar ou alterar a entidade. |
| Exclusão bloqueada por integridade | Consultar os vínculos entre vendedor e departamento antes da operação. |

Para reproduzir um problema, registrar operação, ID fictício, estado inicial dos dados e mensagem da exceção. Preservar uma cópia da base de estudo antes de repetir o fluxo de escrita.

## Limitações e próximos passos

- Configuração de driver e schema ainda é manual.
- Não há suíte de testes automatizados versionada.
- Evoluções possíveis: script de schema/seed, validação de IDs ausentes, testes de integração e revisão do ciclo de vida das conexões.
