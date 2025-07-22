 🛒 Gerenciador de Pedidos - Java Spring Boot

Sistema completo de gerenciamento de pedidos, com autenticação via JWT, controle de estoque, cadastro de produtos, usuários e fluxo completo de pedidos. Desenvolvido em Java com Spring Boot.
---

## 🚀 Funcionalidades

- Autenticação de usuários com JWT
- Registro e login de usuários (comuns e administradores)
- Criação de pedidos
- Adição de itens a pedidos
- Finalização e pagamento de pedidos
- Controle automático de estoque
- Listagem e filtros de pedidos
- Métricas administrativas:
  - Top 5 usuários que mais compraram
  - Valor total faturado no mês
  - Ticket médio por usuário

---

## 🛠️ Tecnologias

- Java 21
- Spring Boot
- Spring Security (com JWT)
- JPA + Hibernate
- MySQL
- Lombok

---

## ⚙️ Configuração

### `application.properties`

```properties
spring.application.name=Gerenciador de Pedidos
jwt.secret=rootBuYMBPX6oagrEICwkpqAvXYOU9NAmwRtk

# ConfiguraÃ§Ã£o do MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/case-produtos
spring.datasource.username=root
spring.datasource.password=senha
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
spring.jpa.properties.hibernate.format_sql=true
```


## 💻 Como rodar o projeto localmente

### 🔧 Configurar o banco de dados

Crie o banco no MySQL:

CREATE DATABASE `case-produtos`;


### 🚀 Rodar a aplicação

Execute o seguinte comando no terminal:

./mvnw spring-boot:run


