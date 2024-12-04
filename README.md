# Sistema PetShop - Pet Friends

Este é o sistema **Pet Friends**, uma plataforma de gerenciamento para uma franquia de lojas de petshop que oferece tanto venda de produtos quanto serviços como agendamentos com veterinários, banho/tosa e passeios. O sistema foi desenvolvido para atender tanto as necessidades dos clientes quanto as das lojas e prestadores de serviço.

# Integração com Google Cloud Pub/Sub
Este projeto implementa a integração do sistema PetFriends com o Google Cloud Pub/Sub, utilizando uma arquitetura baseada em eventos com o **Axon Framework**. O design segue princípios de **Domain-Driven Design (DDD)**, **Clean Code** e padrões arquiteturais avançados como **CQRS** e **Event Sourcing**.

## Funcionalidades

- **Publicação de Mensagens**: Envia mensagens para tópicos no Google Cloud Pub/Sub.
- **Assinatura de Mensagens**: Recebe e processa mensagens de assinaturas configuradas.
- **Arquitetura Baseada em Eventos**: Comandos, eventos e consultas são separados para maior clareza e escalabilidade.
- **Gerenciamento de Domínio**: Utiliza agregados de domínio definidos com o Axon Framework.
- **Persistência de Eventos**: Estado armazenado como uma sequência de eventos (Event Sourcing).

## Tecnologias e Bibliotecas

- **Java 8+**: Linguagem principal do projeto.
- **Maven 3+**: Gerenciamento de dependências e build.
- **Google Cloud Pub/Sub**: Sistema de mensageria assíncrona.
- **Axon Framework**: Implementação de CQRS, Event Sourcing e orquestração de mensagens.
- **Spring Boot**: Framework para desenvolvimento de aplicações Java.
- **Lombok**: Redução de boilerplate no código.
- **JUnit 5**: Framework para testes unitários.
- **SLF4J**: Logging.

## Padrões e Práticas Utilizadas

### **1. Command Query Responsibility Segregation (CQRS)**

Separa a responsabilidade de escrita (Commands) da leitura (Queries), melhorando a performance e escalabilidade:
- **Commands**: Atualizam o estado do sistema.
- **Queries**: Recuperam dados otimizados para leitura.

### **2. Event Sourcing**

- Persistência de eventos em vez de estados finais.
- Cada mudança no sistema é armazenada como um evento.
- O estado do sistema é reconstruído a partir da sequência de eventos.

### **3. Domain-Driven Design (DDD)**

- **Agregados**: Entidades que encapsulam lógica e regras de negócios.
- **Value Objects**: Objetos imutáveis que representam conceitos no domínio.
- **Repositórios**: Abstração para persistir e recuperar agregados.

### **4. Princípios de Clean Code**

- **Responsabilidade Única (SRP)**: Cada classe ou método possui uma única responsabilidade.
- **Coesão**: Código modular e fácil de entender.
- **Injeção de Dependência (DI)**: Utiliza Spring Boot para desacoplar dependências.

## Arquitetura

A arquitetura segue os padrões promovidos pelo Axon Framework:

1. **Comandos (Commands)**:
   - Manipulam o estado do sistema.
   - Definidos como classes e processados por `@CommandHandler`.

2. **Eventos (Events)**:
   - Representam mudanças de estado e são armazenados no Event Store.
   - Publicados e assinados por `@EventHandler`.

3. **Consultas (Queries)**:
   - Recuperam dados otimizados para leitura, utilizando projeções ou repositórios.

4. **Agregados**:
   - Definidos com `@Aggregate`.
   - Centralizam a lógica de negócios e garantem consistência no estado.


## Configuração

1. **Clonar o repositório**:

   \`\`\`bash
   git clone https://github.com/ecrseer/gj-petfriends-google-cloud-pub-sub.git
   cd gj-petfriends-google-cloud-pub-sub
   \`\`\`

2. **Configurar as credenciais do GCP**:

   Defina a variável de ambiente GOOGLE_APPLICATION_CREDENTIALS apontando para o arquivo JSON com as credenciais do serviço:

   \`\`\`bash
   export GOOGLE_APPLICATION_CREDENTIALS=\"/caminho/para/seu/arquivo-de-credenciais.json\"
   \`\`\`

3. **Configurar o projeto GCP no Maven**:

   Edite o arquivo pom.xml para incluir as dependências necessárias para o Google Cloud Pub/Sub.

## Compilação e Execução

1. **Compilar o projeto**:

   \`\`\`bash
   mvn clean install
   \`\`\`

2. **Executar a aplicação**:

   \`\`\`bash
   java -jar target/nome-do-seu-artefato.jar
   \`\`\`
