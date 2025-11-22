## 🚀 Vagou - Sistema de Gerenciamento de Vagas
Sistema completo para gerenciamento de vagas de emprego desenvolvido em Spring Boot com segurança JWT.

👨‍💻 Integrantes
Pedro Henrique Silva de Morais - RM98804
Lucas Rodrigues Delfino - RM550196

📋 Sobre o Projeto
O Vagou é uma API RESTful para plataforma de vagas de emprego que permite:

📊 Gerenciamento de vagas

👥 Controle de usuários com diferentes perfis

📝 Candidaturas a vagas

🔐 Autenticação e autorização com JWT

📱 API documentada com Swagger

🛠 Tecnologias Utilizadas
Java 21

Spring Boot 3.5.7

Spring Security - Autenticação e autorização

JWT - Tokens de acesso

Spring Data JPA - Persistência de dados

H2 Database - Banco em memória

SpringDoc OpenAPI - Documentação da API

Maven - Gerenciamento de dependências

Jakarta Validation - Validação de dados

🏗 Estrutura do Projeto
text
src/main/java/br/com/fiap/
├── config/          # Configurações (Swagger, Security)
├── controller/      # Controladores REST
├── entity/         # Entidades JPA
├── dto/            # Data Transfer Objects
├── service/        # Lógica de negócio
├── repository/     # Interfaces de persistência
├── security/       # Configurações de segurança
├── exception/      # Tratamento de exceções
├── enums/          # Enumeradores
└── vo/             # Value Objects

👥 Perfis de Usuário

CANDIDATO - Pode se candidatar a vagas

RECRUTADOR - Pode criar e gerenciar vagas

ADMIN - Acesso completo ao sistema

🚀 Como Executar
Pré-requisitos
Java 21
Maven 3.6+

📚 Endpoints da API
Autenticação (Público)
POST /auth/register - Registrar candidato

POST /auth/register/recrutador - Registrar recrutador

POST /auth/login - Login e obter token JWT

Vagas
GET /api/vagas - Listar vagas ativas (Público)

GET /api/vagas/{id} - Buscar vaga por ID (Público)

POST /api/vagas - Criar vaga (Recrutador/Admin)

PUT /api/vagas/{id} - Atualizar vaga (Recrutador/Admin)

DELETE /api/vagas/{id} - Excluir vaga (Recrutador/Admin)

Candidaturas
POST /api/candidaturas - Criar candidatura (Candidato)

GET /api/candidaturas/minhas-candidaturas - Minhas candidaturas (Candidato)

PUT /api/candidaturas/{id}/status - Atualizar status (Recrutador/Admin)

Usuários
GET /api/users - Listar usuários (Admin)

GET /api/users/{id} - Buscar usuário por ID

PUT /api/users/{id} - Atualizar usuário

DELETE /api/users/{id} - Excluir usuário (Admin)

🔐 Segurança
Autenticação via JWT (JSON Web Token)

Autorização baseada em roles

Sessão STATELESS

Senhas criptografadas com BCrypt

CORS configurado

🗄 Banco de Dados
H2 Console
URL: http://localhost:8080/h2-console

JDBC URL: jdbc:h2:mem:vagasdb

Usuário: sa

Senha: (vazio)

Entidades Principais
User - Usuários do sistema

Vaga - Vagas de emprego

Candidatura - Candidaturas às vagas

📖 Documentação da API
Swagger UI
Acesse a documentação interativa em:

text
http://localhost:8080/swagger-ui.html
OpenAPI JSON
text
http://localhost:8080/api-docs

🧪 Testes Manuais Recomendados
1. Configuração Inicial
bash
# Health Check
GET http://localhost:8080/health

# Acessar Swagger
GET http://localhost:8080/swagger-ui.html

# Acessar H2 Console  
GET http://localhost:8080/h2-console
2. Fluxo Completo de Teste
Registrar recrutador

Fazer login e obter token

Criar vagas

Registrar candidato

Fazer login do candidato

Candidatar-se a vagas

Gerenciar candidaturas

⚙ Configurações
application.properties
properties
# Servidor
server.port=8080

# Banco H2
spring.datasource.url=jdbc:h2:mem:vagasdb
spring.h2.console.enabled=true

# JWT
jwt.secret=mySecretKeymySecretKeymySecretKeymySecretKey
jwt.expiration=86400000

# Swagger
springdoc.api-docs.path=/api-docs
springdoc.swagger-ui.path=/swagger-ui.html

🎯 Funcionalidades Principais

✅ Implementadas
CRUD completo de vagas

Sistema de candidaturas

Autenticação JWT

Controle de acesso por roles

Validação de dados

Tratamento global de exceções

Documentação com Swagger

Banco H2 em memória

CORS configurado

🔄 Em Desenvolvimento

Paginação e filtros avançados

Upload de currículos

Notificações por email

Dashboard administrativo

🤝 Contribuição
Fork o projeto

Crie uma branch para sua feature (git checkout -b feature/AmazingFeature)

Commit suas mudanças (git commit -m 'Add some AmazingFeature')

Push para a branch (git push origin feature/AmazingFeature)

Abra um Pull Request

📞 Suporte
Em caso de dúvidas ou problemas, abra uma issue no repositório ou entre em contato com a equipe de desenvolvimento.
