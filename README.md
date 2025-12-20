# ⛽ Sales Points System (Backend)

![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5.7-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-Enabled-2496ED?style=for-the-badge&logo=docker&logoColor=white)

## 📌 Sobre o Projeto
API RESTful robusta desenvolvida para o ecossistema de gerenciamento de vendas e bonificações de postos de combustível. O sistema centraliza a lógica de negócios para cálculo de pontuações, rankings de desempenho e administração de funcionários, garantindo integridade e performance.

##  Tecnologias & Ferramentas
- **Core**: Java 21 
- **Framework**: Spring Boot 3.5.7 
- **Banco de Dados**: PostgreSQL 15
- **Containerização**: Docker & Docker Compose
- **Build**: Maven

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

## � Documentação da API

### 🏢 Postos (`/postos`)
| Método | Rota | Descrição |
|--------|------|-----------|
| POST | `/postos` | Cria um novo posto |
| GET | `/postos` | Lista todos os postos |
| GET | `/postos/{id}` | Busca posto por ID |
| PUT | `/postos/{id}` | Atualiza posto existe |
| DELETE | `/postos/{id}` | Remove um posto |

**Corpo da Requisição (JSON):**
```json
{
  "nome": "Posto Central",
  "isAtivo": "ATIVO" // ou "INATIVO"
}
```

### 📦 Grupos de Produtos (`/grupos`)
| Método | Rota | Descrição |
|--------|------|-----------|
| POST | `/grupos` | Cria um novo grupo |
| GET | `/grupos` | Lista todos os grupos |
| PUT | `/grupos/{id}` | Atualiza um grupo |
| DELETE | `/grupos/{id}` | Remove um grupo |

**Corpo da Requisição (JSON):**
```json
{
  "nome": "Gasolina Aditivada",
  "pontos": 10
}
```

### 👷 Funcionários (`/funcionarios`)
| Método | Rota | Descrição |
|--------|------|-----------|
| POST | `/funcionarios` | Cadastra um funcionário |
| GET | `/funcionarios` | Lista todos funcionários |
| PUT | `/funcionarios/{id}` | Atualiza dados (cargo, status) |
| DELETE | `/funcionarios/{id}` | Remove funcionário |

**Corpo da Requisição (JSON):**
```json
{
  "matricula": 12345,
  "nome": "João Silva",
  "cargo": "FRENTISTA", // GERENTE, CAIXA, CHEFE_DE_PISTA...
  "status": "ATIVO",    // INATIVO, FERIAS, AFASTADO, DESLIGADO
  "postoId": "uuid-do-posto"
}
```

### ⛽ Produtos (`/produtos`)
| Método | Rota | Descrição |
|--------|------|-----------|
| POST | `/produtos` | Cria um produto |
| GET | `/produtos` | Lista todos produtos |
| GET | `/produtos/grupo/{id}` | Lista produtos de um grupo |
| PUT | `/produtos/{id}` | Atualiza produto |
| DELETE | `/produtos/{id}` | Remove produto |

**Corpo da Requisição (JSON):**
```json
{
  "nome": "Shell V-Power",
  "grupoId": "uuid-do-grupo"
}
```

### 💲 Vendas (`/vendas`)
| Método | Rota | Descrição |
|--------|------|-----------|
| POST | `/vendas` | Registra uma nova venda |
| GET | `/vendas` | Lista todas as vendas |
| PUT | `/vendas/{id}` | Corrige uma venda |
| DELETE | `/vendas/{id}` | Cancela uma venda |

**Corpo da Requisição (JSON):**
```json
{
  "funcionarioId": "uuid-do-funcionario",
  "produtoId": "uuid-do-produto",
  "quantidade": 50 // Litros ou unidades
}
```

## �📸 Screenshots do Frontend (em desenvolvimento)
> Veja de forma mais simples:

![Dashboard Geral](https://i.imgur.com/M0wD25w.jpeg)
![Gestão de Vendas](https://i.imgur.com/OUP4kVe.jpeg)
![Relatório de Ranking](https://i.imgur.com/rR6cuiS.jpeg)
![Cadastro de Postos](https://i.imgur.com/B1H0kiB.jpeg)
![Lista de Funcionários](https://i.imgur.com/487YzTn.jpeg)
