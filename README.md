# ⛽ Sales Points System (Backend)

![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5.7-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-Enabled-2496ED?style=for-the-badge&logo=docker&logoColor=white)

## 📌 Sobre o Projeto
API RESTful robusta desenvolvida para o ecossistema de gerenciamento de vendas e bonificações de postos de combustível. O sistema centraliza a lógica de negócios para cálculo de pontuações, rankings de desempenho e administração de funcionários, garantindo integridade e performance.

##  Tecnologias & Ferramentas
- **Core**: Java 21 LTS
- **Framework**: Spring Boot 3.5.7 (Web, Data JPA, Validation)
- **Banco de Dados**: PostgreSQL 15
- **Containerização**: Docker & Docker Compose
- **Build**: Maven
- **Produtividade**: Lombok

## 🐳 Executando com Docker (Recomendado)
O projeto inclui um arquivo `docker-compose.yml` pré-configurado para instanciar o banco de dados PostgreSQL em segundos, eliminando a necessidade de instalações locais complexas.

### 1. Subir o Banco de Dados
Na raiz do projeto, execute:
```bash
docker-compose up -d
```
Isso iniciará um container `sales_system_db` na porta **5432**.

**Credenciais Padrão (Configuradas no Docker):**
- **URL**: `jdbc:postgresql://localhost:5432/sales_system`
- **User**: `postgres`
- **Password**: `admin`
- **Database**: `sales_system`

### 2. Parar o Banco de Dados
```bash
docker-compose down
```

## 🚀 Executando a Aplicação
Com o banco de dados rodando via Docker, você pode iniciar a API Spring Boot.

### Via Linha de Comando (Maven Wrapper)
```bash
# Linux/Mac
./mvnw spring-boot:run

# Windows
./mvnw.cmd spring-boot:run
```

A aplicação estará disponível em `http://localhost:8080`.

## 📸 Screenshots do Frontend (em desenvolvimento)
> A API alimenta as seguintes interfaces (Front-end):


![Dashboard Geral](https://i.imgur.com/M0wD25w.jpeg)
![Gestão de Vendas](https://i.imgur.com/OUP4kVe.jpeg)
![Relatório de Ranking](https://i.imgur.com/rR6cuiS.jpeg)
![Cadastro de Postos](https://i.imgur.com/B1H0kiB.jpeg)
![Lista de Funcionários](https://i.imgur.com/487YzTn.jpeg)
