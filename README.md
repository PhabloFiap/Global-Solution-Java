# Projeto de Monitoramento de Poluição Marinha

Este projeto é uma aplicação Spring Boot para monitoramento de poluição marinha, incluindo a gestão de usuários e incidentes.

## Pré-requisitos

- Java 17
- Maven 3.6.3 ou superior
- Docker
- Docker Compose

## Configuração do Ambiente

### Passo 1: Clonar o Repositório

Clone o repositório para sua máquina local:

```sh
git clone https://github.com/seu-usuario/seu-repositorio.git
cd seu-repositorio

```

### Passo 2: Configurar o Banco de Dados

Este projeto utiliza um banco de dados Oracle XE. Certifique-se de que o Docker está instalado e rodando na sua máquina.

### Passo 3: Configurar as Variáveis de Ambiente

Crie um arquivo .env na raiz do projeto com as seguintes variáveis de ambiente:

```sh
DB_HOST=oracle
DB_PORT=1521
DB_SID=XE
DB_USERNAME=<seu Usuario>
DB_PASSWORD=<sua senha>

```
### Passo 4: Construir a Aplicação

Antes de construir a imagem Docker, construa o projeto com Maven para gerar o arquivo JAR:

```sh
mvn clean package -DskipTests

```
### Passo 5: Configurar o Docker Compose

Certifique-se de que seu arquivo docker-compose.yml está configurado corretamente:

```sh
version: '3.8'

services:
  oracle:
    image: oracleinanutshell/oracle-xe-11g
    ports:
      - "1521:1521"
    environment:
      - ORACLE_ALLOW_REMOTE=true
      - ORACLE_DISABLE_ASYNCH_IO=true
      - ORACLE_PASSWORD=<sua senha>
    volumes:
      - oracle-data:/u01/app/oracle
    healthcheck:
      test: ["CMD-SHELL", "echo 'SELECT 1 FROM dual;' | sqlplus system/oracle@localhost/XE"]
      interval: 30s
      timeout: 10s
      retries: 10

  app:
    build:
      context: .
      dockerfile: Dockerfile
    ports:
      - "8080:8080"
    environment:
      - DB_HOST=oracle
      - DB_PORT=1521
      - DB_SID=XE
      - DB_USERNAME=<Seu usuario>
      - DB_PASSWORD=<Sua senha>
    depends_on:
      - oracle

volumes:
  oracle-data:

```
### Passo 6: Rodar a Aplicação com Docker Compose
Execute o Docker Compose para iniciar os containers:

```sh
docker-compose up --build

```
### Passo 7: Acessar a Aplicação
A aplicação estará disponível em `http://localhost:8080`.

## Endpoints da API
Execute o Docker Compose para iniciar os containers:
```sh
docker-compose up --build
```

### Passo 7: Acessar a Aplicação
A aplicação estará disponível em `http://localhost:8080`.

## Documentação da API
A documentação da API está disponível em `http://localhost:8080/swagger-ui.html`.

### Contribuição
* Faça um fork do projeto
* Crie uma branch (git checkout -b feature/fooBar)
* Faça commit das suas mudanças (git commit -am 'Add some fooBar')
* Faça push para a branch (git push origin feature/fooBar)
* Crie um novo Pull Request
