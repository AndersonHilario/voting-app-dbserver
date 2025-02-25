# Projeto Jakarta EE com Spring e Lombok

## Descrição do Projeto

Este projeto é uma aplicação desenvolvida com **Jakarta EE**, utilizando **Spring Data JPA** para acesso a dados, **Spring MVC** para o controle das requisições HTTP e o uso do **Lombok** para simplificar a implementação de classes Java. Trata-se de um sistema que visa demonstrar as melhores práticas em desenvolvimento de software com foco em modularidade, organização e desempenho.

## Funcionalidades

- Gerenciamento de entidades;
- Integração com banco de dados relacional via Spring Data JPA;
- API RESTful utilizando Spring MVC;
- Modelagem com Jakarta EE e utilização de dependências padronizadas;
- Anotações do Lombok para reduzir código boilerplate (getters, setters, etc.);
- Estruturado para implementação seguindo os princípios SOLID.

---

## Tecnologias Utilizadas

O projeto foi desenvolvido utilizando as seguintes ferramentas e frameworks:

- **Java SDK 23**
- **Jakarta EE**
- **Spring Boot**
- **Spring MVC**
- **Spring Data JPA**
- **Lombok**
- **Banco de Dados Relacional**
- **Maven**

---

## Como Executar o Projeto

1. **Pré-Requisitos**
  - Ter o Java 17 ou superior instalado (preferencialmente Java 23).
  - Ter instalado o Maven para gerenciar as dependências.
  - Configurar um banco de dados relacional de sua preferência (ex.: MySQL ou PostgreSQL).

2. **Configuração**
  - Clone este repositório para sua máquina local.
  - Configure o arquivo `src/main/resources/application.properties` conforme o exemplo abaixo:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/suabase
    spring.datasource.username=seuusuario
    spring.datasource.password=suasenha
    spring.jpa.hibernate.ddl-auto=update
    ```

3. **Executar**
  - Compile o projeto e baixe as dependências com:
    ```bash
    mvn clean install
    ```
  - Execute o projeto utilizando:
    ```bash
    mvn spring-boot:run
    ```

4. **Testes**
  - Execute os testes usando:
    ```bash
    mvn test
    ```

---

## Autor

Desenvolvido por Anderson Hilario Junior